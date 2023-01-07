package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.GenericException;
import com.pixvs.viaticos.model.viaticos.Alerta;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.service.AlertaService;
import com.pixvs.viaticos.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController extends GenericController{

    @Autowired
    private AlertaService alertaService;

    @Autowired
    Email emailService;

    @RequestMapping(value = "/listadoalertas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getListadoAlertas( @RequestHeader HttpHeaders headers)throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        return new JsonResponse(alertaService.getListadoAlertas(usuarioId),"Listado Alertas");
    }

    @RequestMapping(value = "/autorizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse autorizarAlerta(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers)throws Exception {
        Long alertaId = Long.parseLong(json.get("alertaId").toString());
        Integer usuarioId = JwtFilter.getUsuarioId(headers);

        String alerta = alertaService.autorizarAlerta(alertaId, usuarioId);

        emailService.enviaCorreo(alerta);

        return new JsonResponse(alerta,"Tramite Autorizado.");
    }

    @RequestMapping(value = "/revision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse revisionAlerta(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers)throws Exception {
        Long alertaId = Long.parseLong(json.get("alertaId").toString());
        String motivo = json.get("motivo") != null ? (String) json.get("motivo") : null;
        Integer usuarioId = JwtFilter.getUsuarioId(headers);

        String alerta = alertaService.revisionAlerta(alertaId, motivo, usuarioId);

        emailService.enviaCorreo(alerta);

        return new JsonResponse(alerta,"Tramite en Revisión.");
    }

    @RequestMapping(value = "/rechazar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse rechazarAlerta(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers)throws Exception {
        Long alertaId = Long.parseLong(json.get("alertaId").toString());
        String motivo = json.get("motivo") != null ? (String) json.get("motivo") : null;
        Integer usuarioId = JwtFilter.getUsuarioId(headers);

        String alerta = alertaService.rechazarAlerta(alertaId, motivo, usuarioId);

        emailService.enviaCorreo(alerta);

        return new JsonResponse(alerta,"Tramite Rechazado.");
    }

    @RequestMapping(value = "/ocultar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse ocultarAlertas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers)throws Exception {
        String alertasId = (String)json.get("alertasId");
        alertasId = alertasId.replace("[","").replace("]","");
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        return new JsonResponse(alertaService.ocultarAlertas(alertasId, usuarioId),"Notificaciones Ocultas.");
    }

    @Override
    public JsonResponse getDatosFichas(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse buscaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse eliminaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }
}
