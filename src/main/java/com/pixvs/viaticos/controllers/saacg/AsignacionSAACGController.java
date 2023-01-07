package com.pixvs.viaticos.controllers.saacg;

import com.pixvs.viaticos.controllers.GenericController;
import com.pixvs.viaticos.exceptions.AsignacionException;
import com.pixvs.viaticos.model.viaticos.Asignacion;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.AlertaDefinicion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.RecursosTransferidosFinanzasSolicitudViaticoProjection;
import com.pixvs.viaticos.service.AlertaService;
import com.pixvs.viaticos.service.AsignacionService;
import com.pixvs.viaticos.service.SolicitudViaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import static com.pixvs.viaticos.exceptions.AsignacionException.*;

@RestController
@RequestMapping("/apisaacg/asignacion")
public class AsignacionSAACGController extends GenericController {

    @Autowired
    private SolicitudViaticoService solicitudViaticoService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private AlertaService alertaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
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
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/recursos_transferidos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse transferirRecursos(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Timestamp fecha = new Timestamp(new Date().getTime());

        //Verificamos que vengan los parametros requeridos en el JSON
        if (!json.containsKey("solicitudId") || !json.containsKey("polizaGastoPorComprobarId") || !json.containsKey("numeroPolizaGastoPorComprobarId") || !json.containsKey("tipo"))
            throw new AsignacionException(STATUS_ERROR_PARAMETROS_INVALIDOS_RECURSOS_ASIGNADOS_SAACG);

        Integer solicitudId = new Integer(json.get("solicitudId").toString());
        int polizaGastoPorComprobarId = new Integer(json.get("polizaGastoPorComprobarId").toString());
        String numeroPolizaGastoPorComprobarId = json.get("numeroPolizaGastoPorComprobarId").toString();
        String tipo = json.get("tipo").toString();

        //Buscamos la solicitud a la que se cambiara el estatus a "Recursos Asignados"
        SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudId);

        //Si no se encontro, lanzamos error
        if (solicitud == null)
            throw new AsignacionException(STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);

        //Si el estatus de la solicitud no es "Enviada a Finanzas" lanzamos error
        if (solicitud.getEstatusId() != ListadoCMM.EstatusSolicitudViatico.ENVIADA_FINANZAS && tipo.equals("A"))
            throw new AsignacionException(STATUS_ERROR_SOLICITUD_ESTATUS_DIFERENTE_A_ENVIADA_A_FINANZAS);

        //Buscamos la asignacion para llenar los campos de Poliza de Gasto por Comprobar
        Asignacion asignacion = asignacionService.buscaPorSolicitudViaticoId(solicitudId);

        if (asignacion == null)
            throw new AsignacionException(STATUS_ERROR_ASIGNACION_NO_ENCONTRADA);

        //Si la encontramos, guardamos la informacion
        try {
            asignacion.setPolizaGastoPorComprobarId(tipo.equals("A")?polizaGastoPorComprobarId:null);
            asignacion.setNumeroPolizaGastoPorComprobarId(tipo.equals("A")?numeroPolizaGastoPorComprobarId:null);
            asignacion.setFechaUltimaModificacion(fecha);
            asignacionService.guardar(asignacion);

            solicitud.setEstatusId(tipo.equals("A")?ListadoCMM.EstatusSolicitudViatico.RECURSOS_ASIGNADOS:ListadoCMM.EstatusSolicitudViatico.ENVIADA_FINANZAS);
            solicitud.setFechaUltimaModificacion(fecha);
            solicitudViaticoService.guardar(solicitud);
        } catch (Exception ex) {
            throw new AsignacionException(STATUS_ERROR_ACTUALIZAR_ASIGNACION_RECURSOS_ASIGNADOS);
        }

        //Retornamos true para indicar que salio correctamente
        return new JsonResponse(true, "Recursos Asignados");
    }
}