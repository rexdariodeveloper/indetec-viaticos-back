package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.EstadoException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.Estado;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Estado.ListadoEstadosProjection;
import com.pixvs.viaticos.service.CiudadesService;
import com.pixvs.viaticos.service.EstadosService;
import com.pixvs.viaticos.service.PaisesService;
import com.pixvs.viaticos.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/estados")
public class EstadosController extends GenericController {

    @Autowired
    private PaisesService paisesService;

    @Autowired
    private EstadosService estadosService;

    @Autowired
    private CiudadesService ciudadesService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String, Object> datosFicha = new HashMap<>();

        datosFicha.put("comboPaises", paisesService.getComboPaises());
        datosFicha.put("listadoEstados", estadosService.getListadoEstados());

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(estadosService.getListadoEstados());
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody ArrayList<Estado> estados, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        for (Estado estado : estados) {
            if (estado.getId() < 0) {
                ListadoEstadosProjection estadoTemp = (ListadoEstadosProjection) estadosService.buscaPorPaisIdAndCodigo(estado.getPaisId(), estado.getCodigo());

                if (estadoTemp != null) {
                    throw new EstadoException(EstadoException.STATUS_CODIGO_REPETIDO);
                }

                estado.setFechaCreacion(fecha);
                estado.setCreadoPorId(usuarioId);
            } else {
                ListadoEstadosProjection estadoTemp = (ListadoEstadosProjection) estadosService.buscarPorId(estado.getId());

                if (estadoTemp != null && !estado.getTimestamp().equals(estadoTemp.getTimestamp())) {
                    throw new EstadoException(EstadoException.STATUS_CAMBIO_TIMESTAMP);
                }

                if (estado.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if (ciudadesService.buscaPorEstadoId(estado.getId()) != null) {
                        throw new EstadoException(EstadoException.STATUS_ERROR_ESTADO_CIUDADES);
                    }
                }

                if (estado.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if(!sistemaService.permiteEliminarRegistro(estado.getId(), "tblEstado"))
                        throw new EstadoException(EstadoException.STATUS_ERROR_ESTADO_RELACIONADA);
                }
                estado.setFechaUltimaModificacion(fecha);
                estado.setModificadoPorId(usuarioId);
            }
        }

        estadosService.guardar(estados);

        return new JsonResponse(true, "Cambios guardados.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }
}
