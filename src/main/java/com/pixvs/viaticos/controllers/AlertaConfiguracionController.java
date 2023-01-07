package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.dao.viaticos.ListadoCMMDao;
import com.pixvs.viaticos.exceptions.AlertaException;
import com.pixvs.viaticos.model.viaticos.AlertaConfiguracion;
import com.pixvs.viaticos.model.viaticos.AlertaEtapaAccion;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.AlertaConfiguracion.EditarAlertaConfiguracionProjection;
import com.pixvs.viaticos.model.viaticos.projection.AlertaEtapaAccion.ConfigurarAlertaEtapaAccionProjection;
import com.pixvs.viaticos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alertasconfiguraciones")
public class AlertaConfiguracionController extends GenericController {

    @Autowired
    private ListadoCMMService listadoCMMService;
    @Autowired
    private AlertaEtapaAccionService alertaEtapaAccionService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private AlertaConfiguracionService alertaConfiguracionService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap datosFicha  = new HashMap();
        datosFicha.put("etapas", listadoCMMService.getListadoCMMComboByNombreAndActivo("AlertaEtapa"));
        datosFicha.put("empleados", empleadoService.getComboProjectionActivos());
        datosFicha.put("figuras", listadoCMMService.getListadoCMMComboByNombreAndActivo("AlertaConfiguracionFigura"));
        datosFicha.put("tiposNotificacion", listadoCMMService.getListadoCMMComboByNombreAndActivo("TipoNotificacion"));
        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody AlertaConfiguracion configuracionBody, @RequestHeader HttpHeaders headers) throws Exception {

        AlertaConfiguracion configuracionEditar;
        Integer usuarioId = JwtFilter.getUsuarioId(headers);

        if(configuracionBody.getId() != null){
            configuracionEditar = alertaConfiguracionService.buscarPorId(configuracionBody.getId());
            if(configuracionEditar == null){
                throw new AlertaException(AlertaException.STATUS_REGISTRO_NO_EXISTENTE);
            }
            if(!configuracionEditar.getTimestamp().equals(configuracionBody.getTimestamp())){
                throw new AlertaException(AlertaException.STATUS_CAMBIO_TIMESTAMP);
            }
        }else{
            configuracionEditar = new AlertaConfiguracion();
            configuracionEditar.setEstatusId(ListadoCMM.EstatusRegistro.ACTIVO);
        }

        configuracionEditar.setEtapaAccionId(configuracionBody.getEtapaAccionId());
        configuracionEditar.setEmpleadoId(configuracionBody.getEmpleadoId());
        configuracionEditar.setFiguraId(configuracionBody.getFiguraId());
        configuracionEditar.setTipoNotificacionId(configuracionBody.getTipoNotificacionId());
        configuracionEditar.setEnPlataforma(configuracionBody.getEnPlataforma());
        configuracionEditar.setEnCorreoElectronico(configuracionBody.getEnCorreoElectronico());
        configuracionEditar.setFechaCreacion(configuracionBody.getFechaCreacion());
        configuracionEditar.setCreadoPorId(usuarioId);
        configuracionEditar.setFechaUltimaModificacion(configuracionBody.getFechaUltimaModificacion());
        configuracionEditar.setModificadoPorId(usuarioId);

        if(configuracionBody.getEstatusId() != null){
            configuracionEditar.setEstatusId(configuracionBody.getEstatusId());
        }

        alertaConfiguracionService.guardar(configuracionEditar);

        return new JsonResponse(true,"Registro guardado.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @RequestMapping(value = "/etapas/acciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse buscarAccionesPorEtapa(@RequestBody Map<String, Object> json) throws Exception {
        Integer etapaId = (Integer)json.get("etapaId");
        List<ConfigurarAlertaEtapaAccionProjection> etapaAcciones = alertaEtapaAccionService.buscarListadoConfigurarProjection(etapaId);
        return new JsonResponse(etapaAcciones);
    }

    @RequestMapping(value = "/etapas/configuraciones", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse buscarConfiguracionesPorEtapa(@RequestBody Map<String, Object> json) throws Exception {
        Integer etapaId = (Integer)json.get("etapaId");
        List<Integer> etapaAccionesIds = alertaEtapaAccionService.buscarListadoIds(etapaId);
        List<EditarAlertaConfiguracionProjection> configuraciones = alertaConfiguracionService.buscarListadoEditarProjection(etapaAccionesIds);
        return new JsonResponse(configuraciones);
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda/multiple", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody List<AlertaConfiguracion> configuracionesBody, @RequestHeader HttpHeaders headers) throws Exception {

        for (AlertaConfiguracion configuracion : configuracionesBody){
            guarda(configuracion,headers);
        }

        return new JsonResponse(true,"Registros guardado.");
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/elimina/multiple", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse elimina(@RequestBody List<Integer> configuracionesIdsBody, @RequestHeader HttpHeaders headers) throws Exception {

        Integer usuarioId = JwtFilter.getUsuarioId(headers);

        for (Integer configuracionId : configuracionesIdsBody){
            alertaConfiguracionService.eliminar(configuracionId,usuarioId);
        }

        return new JsonResponse(true,"Registros borrados.");
    }

}
