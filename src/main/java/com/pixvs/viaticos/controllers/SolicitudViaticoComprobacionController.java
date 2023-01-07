package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.AlertaDefinicion;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.CuentaGastoService;
import com.pixvs.viaticos.service.saacg.PolizasService;
import com.pixvs.viaticos.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

import static com.pixvs.viaticos.model.viaticos.mapeos.MenuPrincipal.ASIGNAR_VIATICOS;

@RestController
@RequestMapping("/api/solicitudviaticocomprobacion")
public class SolicitudViaticoComprobacionController extends GenericController {

    @Autowired
    private SolicitudViaticoComprobacionService solicitudViaticoComprobacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleValidacionService solicitudViaticoComprobacionDetalleValidacionService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private RolMenuService rolMenuService;

    @Autowired
    private PolizasService polizasService;

    @Autowired
    private CuentaGastoService cuentaGastoService;

    @Autowired
    Email emailService;

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

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);

        ObjectMapper mapper = new ObjectMapper();

        SolicitudViaticoComprobacion comprobacion = mapper.convertValue(json.get("solicitudViaticoComprobacion"), SolicitudViaticoComprobacion.class);
        SolicitudViaticoComprobacionDetalleValidacion[] detallesValidacion = mapper.convertValue(json.get("solicitudViaticoComprobacionDetalleValidacion"), SolicitudViaticoComprobacionDetalleValidacion[].class);
        SolicitudViatico solicitud = comprobacion.getSolicitudViatico();



        for (SolicitudViaticoComprobacionDetalleValidacion detalleValidacion : detallesValidacion) {
            if (detalleValidacion.getId() == null) {
                detalleValidacion.setCreadoPorId(usuarioId);
                detalleValidacion.setFechaCreacion(fecha);
            } else {
                detalleValidacion.setModificadoPorId(usuarioId);
                detalleValidacion.setFechaUltimaModificacion(fecha);
            }

            solicitudViaticoComprobacionDetalleValidacionService.guardar(detalleValidacion);
        }

        boolean usuarioSolicitante = solicitud.getCreadoPorId().equals(usuarioId)
                || solicitud.getEmpleadoId().equals(usuario.getEmpleadoId());

        if (usuarioSolicitante) {
            comprobacion.setSolicitanteFinalizoComprobacion(true);
            comprobacion.setFechaSolicitanteFinalizoComprobacion(fecha);
        }

        if (!comprobacion.getRmFinalizoComprobacion()
                && rolMenuService.buscarPorRolIdAndMenuId(usuario.getRolId(), ASIGNAR_VIATICOS) != null) {
            comprobacion.setRmFinalizoComprobacion(true);
            comprobacion.setFechaRMFinalizoComprobacion(fecha);

            if (!usuarioSolicitante) {
                String alertaId = alertaService.iniciarAlerta(AlertaDefinicion.INFORME_COMPROBACION_FINALIZAR, solicitud.getId(), solicitud.getNumeroSolicitud(), "Solicitud: " + solicitud.getNumeroSolicitud() + ", Comprobación Finalizada por RM", usuarioId);

                emailService.enviaCorreo(alertaId);
            }
        }

        comprobacion.setModificadoPorId(usuarioId);
        comprobacion.setFechaUltimaModificacion(fecha);

        solicitudViaticoComprobacionService.guardar(comprobacion);

        if (comprobacion.getSolicitanteFinalizoComprobacion()
                && comprobacion.getRmFinalizoComprobacion()) {
            String alertaId = alertaService.iniciarAlerta(AlertaDefinicion.INFORME_COMPROBACION_ENVIAR, solicitud.getId(), solicitud.getNumeroSolicitud(), "Solicitud: " + solicitud.getNumeroSolicitud() + ", Estatus: Comprobación Finalizada", usuarioId);

            emailService.enviaCorreo(alertaId);
        }

        return new JsonResponse("");
    }
}
