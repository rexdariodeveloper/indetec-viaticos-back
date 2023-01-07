package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.GrupoJerarquicoException;
import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import com.pixvs.viaticos.model.viaticos.GrupoJerarquicoPuesto;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico.EditarGrupoJerarquicoProjection;
import com.pixvs.viaticos.service.GrupoJerarquicoPuestoService;
import com.pixvs.viaticos.service.GrupoJerarquicoService;
import com.pixvs.viaticos.service.ListadoPuestoService;
import com.pixvs.viaticos.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/grupos_jerarquicos")
public class GrupoJerarquicoController extends GenericController {

    @Autowired
    GrupoJerarquicoService grupoJerarquicoService;

    @Autowired
    GrupoJerarquicoPuestoService grupoJerarquicoPuestoService;

    @Autowired
    ListadoPuestoService puestoService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap datosFicha  = new HashMap();

        Integer id = null;

        //Si se envio un Id, buscar el registro
        if(json != null && json.containsKey("registroId")) {
            id = new Integer((String) json.get("registroId"));
            datosFicha.put("grupoJerarquico", grupoJerarquicoService.buscarParaEditarPorId(id));
            datosFicha.put("grupoJerarquicoPuestos", grupoJerarquicoPuestoService.buscarPorGrupoGerarquicoId(id));
        }

        datosFicha.put("puestos", puestoService.getPuestosGrupoJerarquico(id));

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(grupoJerarquicoService.getListado());
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody GrupoJerarquico grupoJerarquico, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        List<Integer> puestosId = grupoJerarquico.getPuestosId();
        List<GrupoJerarquicoPuesto> puestos;

        if(grupoJerarquico.getId() == null) {
            EditarGrupoJerarquicoProjection grupoJerarquicoTemp = grupoJerarquicoService.buscarPorNombre(grupoJerarquico.getNombre());

            if (grupoJerarquicoTemp != null) {
                throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_GRUPO_REPETIDO);
            }

            grupoJerarquico.setFechaCreacion(fecha);
            grupoJerarquico.setCreadoPorId(usuarioId);
        } else {
            if (grupoJerarquico.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                if(!sistemaService.permiteEliminarRegistro(grupoJerarquico.getId(), "tblGrupoJerarquico"))
                    throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_ERROR_GRUPO_JERARQUICO_RELACIONADA);
            }

            GrupoJerarquico grupoJerarquicoTemp = (GrupoJerarquico) grupoJerarquicoService.buscarPorId(grupoJerarquico.getId());

            if (!grupoJerarquico.getTimestamp().equals(grupoJerarquicoTemp.getTimestamp())) {
                throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_CAMBIO_TIMESTAMP);
            }

            grupoJerarquico.setFechaUltimaModificacion(fecha);
            grupoJerarquico.setModificadoPorId(usuarioId);

            puestos = grupoJerarquicoPuestoService.buscarPorGrupoGerarquicoId(grupoJerarquico.getId());
            List<GrupoJerarquicoPuesto> puestosTemp = new ArrayList<>();

            for (GrupoJerarquicoPuesto puesto : puestos) {
                if (!puestosId.contains(puesto.getListadoPuestoId())) {
                    GrupoJerarquicoPuesto puestoTemp = puesto;

                    puestoTemp.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
                    puestoTemp.setFechaUltimaModificacion(fecha);
                    puestoTemp.setModificadoPorId(usuarioId);

                    if (puesto.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                        if(!sistemaService.permiteEliminarRegistro(puesto.getId(), "tblGrupoJerarquicoPuesto"))
                            throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_ERROR_GRUPO_JERARQUICO_PUESTO_RELACIONADA);
                    }

                    puestosTemp.add(puesto);
                }
            }

            if (!puestosTemp.isEmpty()) {
                grupoJerarquicoPuestoService.guardar(puestosTemp);
            }
        }

        puestos = new ArrayList<>();

        grupoJerarquico = (GrupoJerarquico) grupoJerarquicoService.guardar(grupoJerarquico);

        for (int puestoId : puestosId) {
            GrupoJerarquicoPuesto puesto = (GrupoJerarquicoPuesto) grupoJerarquicoPuestoService.buscarPorGrupoJerarquicoIdAndPuestoId(grupoJerarquico.getId(), puestoId);

            if (puesto == null) {
                puesto = new GrupoJerarquicoPuesto();
                puesto.setGrupoJerarquicoId(grupoJerarquico.getId());
                puesto.setListadoPuestoId(puestoId);
                puesto.setEstatusId(ListadoCMM.EstatusRegistro.ACTIVO);
                puesto.setFechaCreacion(fecha);
                puesto.setCreadoPorId(usuarioId);
                puestos.add(puesto);
            }
        }

        if (!puestos.isEmpty()) {
            grupoJerarquicoPuestoService.guardar(puestos);
        }

        return new JsonResponse(true,"Registro guardado.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        GrupoJerarquico grupoJerarquico = (GrupoJerarquico) grupoJerarquicoService.buscarPorId((Integer) json.get("id"));

        grupoJerarquico.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
        grupoJerarquico.setFechaUltimaModificacion(fecha);
        grupoJerarquico.setModificadoPorId(usuarioId);

        if (grupoJerarquico.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
            if(!sistemaService.permiteEliminarRegistro(grupoJerarquico.getId(), "tblGrupoJerarquico"))
                throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_ERROR_GRUPO_JERARQUICO_RELACIONADA);
        }

        grupoJerarquicoService.guardar(grupoJerarquico);

        List<GrupoJerarquicoPuesto> puestos = grupoJerarquicoPuestoService.buscarPorGrupoGerarquicoId(grupoJerarquico.getId());

        for (GrupoJerarquicoPuesto puesto : puestos) {
            puesto.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
            puesto.setFechaUltimaModificacion(fecha);
            puesto.setModificadoPorId(usuarioId);

            if (puesto.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                if(!sistemaService.permiteEliminarRegistro(puesto.getId(), "tblGrupoJerarquicoPuesto"))
                    throw new GrupoJerarquicoException(GrupoJerarquicoException.STATUS_ERROR_GRUPO_JERARQUICO_PUESTO_RELACIONADA);
            }
        }

        if (!puestos.isEmpty()) {
            grupoJerarquicoPuestoService.guardar(puestos);
        }

        return new JsonResponse(true, "Registro borrado.");
    }
}
