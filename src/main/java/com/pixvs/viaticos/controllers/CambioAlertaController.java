package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.CambioAlertaException;
import com.pixvs.viaticos.model.viaticos.CambioAlerta;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.projection.CambioAlerta.EditarCambioAlertaProjection;
import com.pixvs.viaticos.service.CambioAlertaService;
import com.pixvs.viaticos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/cambio_alerta")
public class CambioAlertaController extends GenericController {

    @Autowired
    CambioAlertaService cambioAlertaService;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {

        HashMap datosFicha  = new HashMap();

        //Si se envio un Id, buscar el registro
        if(json != null && json.containsKey("cambioAlertaId")) {
            Integer cambioAlertaId =  new Integer((String)json.get("cambioAlertaId"));
            datosFicha.put("cambioAlerta", cambioAlertaService.buscarParaEditarPorId(cambioAlertaId));
        }

        datosFicha.put("empleados", empleadoService.getComboProjectionActivos());
        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(cambioAlertaService.getListadoCambioAlertas(), "Cambio Alertas");
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody CambioAlerta cambioAlerta, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        if(cambioAlerta.getId() == null) {
            cambioAlerta.setFolio(cambioAlertaService.getSiguienteAutonumerico());
            cambioAlerta.setFechaCreacion(fecha);
            cambioAlerta.setCreadoPorId(usuarioId);
        } else {
            EditarCambioAlertaProjection cambioAlertaTemp = cambioAlertaService.buscarParaEditarPorId(cambioAlerta.getId());

            if (!cambioAlerta.getTimestamp().equals(cambioAlertaTemp.getTimestamp())) {
                throw new CambioAlertaException(CambioAlertaException.STATUS_CAMBIO_TIMESTAMP);
            }

            cambioAlerta.setFechaUltimaModificacion(fecha);
            cambioAlerta.setModificadoPorId(usuarioId);
        }

        if(cambioAlertaService.existeRegistro(cambioAlerta.getId(), cambioAlerta.getEmpleadoOrigenId(), cambioAlerta.getFechaInicio(), cambioAlerta.getFechaFin())) {
            throw new CambioAlertaException(CambioAlertaException.STATUS_ERROR_RANGO_FECHAS);
        }

        cambioAlertaService.guardar(cambioAlerta);

        return new JsonResponse(true,"Registro guardado.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());
        Integer cambioAlertaId = (Integer) json.get("id");

        CambioAlerta cambioAlerta = (CambioAlerta) cambioAlertaService.buscarPorId(cambioAlertaId);

        cambioAlerta.setBorrado(true);
        cambioAlerta.setFechaUltimaModificacion(fecha);
        cambioAlerta.setModificadoPorId(usuarioId);

        cambioAlertaService.guardar(cambioAlerta);

        return new JsonResponse(true, "Registro borrado.");
    }
}
