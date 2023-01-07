package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.PaisException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.Pais;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Pais.ListadoPaisesProjection;
import com.pixvs.viaticos.service.EstadosService;
import com.pixvs.viaticos.service.GenericService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/paises")
public class PaisesController extends GenericController {

    @Autowired
    private PaisesService paisesService;

    @Autowired
    private EstadosService estadosService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(paisesService.getListadoPaises());
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody ArrayList<Pais> paises, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        for (Pais pais : paises) {
            if (pais.getId() < 0) {
                ListadoPaisesProjection paisTemp = (ListadoPaisesProjection) paisesService.buscarPorCodigo(pais.getCodigo());

                if (paisTemp != null) {
                    throw new PaisException(PaisException.STATUS_CODIGO_REPETIDO);
                }

                pais.setFechaCreacion(fecha);
                pais.setCreadoPorId(usuarioId);
            } else {
                ListadoPaisesProjection paisTemp = (ListadoPaisesProjection) paisesService.buscarPorId(pais.getId());

                if (paisTemp != null && !pais.getTimestamp().equals(paisTemp.getTimestamp())) {
                    throw new PaisException(PaisException.STATUS_CAMBIO_TIMESTAMP);
                }

                if (pais.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if(!sistemaService.permiteEliminarRegistro(pais.getId(), "tblPais"))
                        throw new PaisException(PaisException.STATUS_ERROR_PAIS_ESTADOS);
                }
                pais.setFechaUltimaModificacion(fecha);
                pais.setModificadoPorId(usuarioId);
            }
        }

        paisesService.guardar(paises);

        return new JsonResponse(true, "Cambios guardados.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }
}
