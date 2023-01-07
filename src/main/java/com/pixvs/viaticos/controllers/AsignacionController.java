package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.APISAACGException;
import com.pixvs.viaticos.exceptions.AsignacionException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.AlertaDefinicion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.ResumenSolicitudViaticoProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.PolizasService;
import com.pixvs.viaticos.util.Email;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryFragment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/asignacion")
public class AsignacionController extends GenericController {

    @Autowired
    private SolicitudViaticoService solicitudViaticoService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private AsignacionViaticoService asignacionViaticoService;

    @Autowired
    private ConceptoViaticoService conceptoViaticoService;

    @Autowired
    private GrupoJerarquicoPuestoService grupoJerarquicoPuestoService;

    @Autowired
    private MatrizViaticoService matrizViaticoService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private AsignacionPasajeService asignacionPasajeService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private PolizasService polizasService;

    @Autowired
    Email emailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ArchivoController archivoController;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SistemaService sistemaService;

    private String pathLogo="";
    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer solicitudId = json.get("solicitudId") != null ? new Integer((String) json.get("solicitudId")) : null;
        SolicitudViatico solicitudViatico = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudId);

        HashMap<String, Object> datosFicha = new HashMap<>();

        if (solicitudViatico == null) {
            throw new AsignacionException(AsignacionException.STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);
        }

        if (solicitudViatico.getEstatusId() != ListadoCMM.EstatusSolicitudViatico.AUTORIZADA
                && solicitudViatico.getEstatusId() != ListadoCMM.EstatusSolicitudViatico.RECURSOS_ASIGNADOS
                && solicitudViatico.getEstatusId() != ListadoCMM.EstatusSolicitudViatico.ENVIADA_FINANZAS
                && solicitudViatico.getEstatusId() != ListadoCMM.EstatusSolicitudViatico.EN_REVISION_COMPROBACION) {
            throw new AsignacionException(AsignacionException.STATUS_ERROR_SOLICITUD_NO_AUTORIZADA_RECURSOS_ASIGNADOS_ENVIADA_FINANZAS);
        }

        datosFicha.put("solicitudViatico", solicitudViatico);
        datosFicha.put("conceptoViatico", conceptoViaticoService.getListadoConceptoViaticoProjection());
        datosFicha.put("tipoPasaje", listadoCMMService.buscarPorCodigo("tipoPasaje"));
        Asignacion asignacion = asignacionService.buscaPorSolicitudViaticoId(solicitudId);

        if (asignacion != null) {
            datosFicha.put("asignacion", asignacion);
            datosFicha.put("asignacionViatico", asignacionViaticoService.searchAsignacionId(asignacion.getId()));
            datosFicha.put("asignacionPasaje", asignacionPasajeService.searchAsignacionId(asignacion.getId()));
        }

        GrupoJerarquicoPuesto grupoJerarquicoPuesto = grupoJerarquicoPuestoService.searchListadoPuestoId(solicitudViatico.getPuestoId());
        if (grupoJerarquicoPuesto != null) {
            datosFicha.put("matrizViatico", matrizViaticoService.searchGrupoJerarquicoIdAndListadoZonaId(grupoJerarquicoPuesto.getGrupoJerarquicoId(), solicitudViatico.getCiudadDestino().getZonaEconomicaId()));
        }

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(asignacionService.getListadoSolicitudViaticoAsignacionProjection());
    }

    @Override
    public JsonResponse buscaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse eliminaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    private boolean existsLogo() throws Exception {
        try {
            pathLogo=archivoService.getRutaRaizCMM()+"\\"+archivoService.getLogoRutaActivo().getRutaFisica();
            File file = new File(pathLogo);
            return file.exists();
        }catch (Exception ex){
            throw  ex;
        }
    }
    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        //Validar que exista el logo
        if(existsLogo()==false){
            System.out.println("No existe: "+pathLogo);
            throw new AsignacionException("El logo no existe: "+pathLogo,AsignacionException.STATUS_ERROR_GUARDAR_OFICIO_COMISION,false);

        }
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        //Datos cancelacion
        Map<String,Object> cancelarPoliza=(Map<String, Object>) json.get("datosCancelacionPoliza");

        //Mapper Convert Object to Model
        ObjectMapper mapper = new ObjectMapper();

        // Is Asignacion
        Boolean isAsignacion = mapper.convertValue(json.get("isAsignacion"), Boolean.class);

        // Solicitud Viatico
        SolicitudViatico solicitudViatico = mapper.convertValue(json.get("solicitudViatico"), SolicitudViatico.class);

        //Asignacion
        Asignacion asignacion = mapper.convertValue(json.get("asignacion"), Asignacion.class);

        //Verificar que no se haya guardado ya una comprobacion para no duplicar registros
        if(asignacion.getId() == null){
            Asignacion _asignacion = (Asignacion) asignacionService.buscaPorSolicitudViaticoId(solicitudViatico.getId());
            if(_asignacion != null)
                throw new Exception("Ya han sido asignado los recursos de la solicitud que intenta guardar. Favor de regresar al listado y buscar la solicitud.");
        }

        // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
        else {
            Asignacion _asignacion = (Asignacion) asignacionService.buscarPorId(asignacion.getId());
            if (!asignacion.getTimestamp().equals(_asignacion.getTimestamp())) {
                throw new Exception("La asignación con el código [" + asignacion.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
            }
        }

        if (!isAsignacion) {

            if (asignacion.getId() == null) {
                asignacion.setSolicitudViaticoId(solicitudViatico.getId());
                asignacion.setCreadoPorId(usuarioId);
                asignacion.setFechaCreacion(fecha);
            } else {
                asignacion.setModificadoPorId(usuarioId);
                asignacion.setFechaUltimaModificacion(fecha);
            }

            asignacion = (Asignacion) asignacionService.guardar(asignacion);

            if (asignacion == null) {
                throw new AsignacionException(AsignacionException.STATUS_ERROR_GUARDAR_ASIGNACION);
            }

            //Asignacion Viatico
            AsignacionViatico[] asignacionViaticoList = mapper.convertValue(json.get("asignacionViatico"), AsignacionViatico[].class);
            for (AsignacionViatico asignacionViatico : asignacionViaticoList) {
                if (asignacionViatico.getId() == null) {
                    asignacionViatico.setAsignacionId(asignacion.getId());
                    asignacionViatico.setCreadoPorId(usuarioId);
                    asignacionViatico.setFechaCreacion(fecha);
                } else {
                    // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
                    AsignacionViatico _asignacionViatico = (AsignacionViatico) asignacionViaticoService.buscarPorId(asignacionViatico.getId());
                    if(!asignacionViatico.getTimestamp().equals(_asignacionViatico.getTimestamp())){
                        throw new Exception("La asignación viatico con el código [" + asignacionViatico.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
                    }

                    // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                    if (asignacionViatico.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                        if(!sistemaService.permiteEliminarRegistro(asignacionViatico.getId(), "tblSolicitudViaticoAsignacionViatico"))
                            throw new AsignacionException(AsignacionException.STATUS_ERROR_ASIGNACION_VIATICO_RELACION);
                    }

                    asignacionViatico.setModificadoPorId(usuarioId);
                    asignacionViatico.setFechaUltimaModificacion(fecha);
                }

                asignacionViatico = (AsignacionViatico) asignacionViaticoService.guardar(asignacionViatico);

                if (asignacionViatico == null) {
                    throw new AsignacionException(AsignacionException.STATUS_ERROR_GUARDAR_ASIGNACION_VIATICO);
                }
            }

            //Asignacion Pasaje
            AsignacionPasaje[] asignacionPasajesList = mapper.convertValue(json.get("asignacionPasaje"), AsignacionPasaje[].class);
            for (AsignacionPasaje asignacionPasaje : asignacionPasajesList) {
                if (asignacionPasaje.getId() >= 1000000000) {
                    asignacionPasaje.setAsignacionId(asignacion.getId());
                    asignacionPasaje.setCreadoPorId(usuarioId);
                    asignacionPasaje.setFechaCreacion(new Timestamp(new Date().getTime()));
                } else {
                    // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
                    AsignacionPasaje _asignacionPasaje = (AsignacionPasaje) asignacionPasajeService.buscarPorId(asignacion.getId());
                    if(!asignacionPasaje.getTimestamp().equals(_asignacionPasaje.getTimestamp())){
                        throw new Exception("La asignación pasaje cco con el código [" + asignacionPasaje.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
                    }

                    // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                    if (asignacionPasaje.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                        if(!sistemaService.permiteEliminarRegistro(asignacionPasaje.getId(), "tblSolicitudViaticoAsignacionPasaje"))
                            throw new AsignacionException(AsignacionException.STATUS_ERROR_ASIGNACION_VIATICO_RELACION);
                    }

                    asignacionPasaje.setModificadoPorId(usuarioId);
                    asignacionPasaje.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                }

                asignacionPasaje = (AsignacionPasaje) asignacionPasajeService.guardar(asignacionPasaje);

                if (asignacionPasaje == null) {
                    throw new AsignacionException(AsignacionException.STATUS_ERROR_GUARDAR_ASIGNACION_PASAJE);
                }
            }
        //Generar la Poliza cancelatoria
        }
        else if(cancelarPoliza!=null && cancelarPoliza.get("estatusPoliza").toString()!="" && cancelarPoliza.get("estatusPoliza").toString().toUpperCase().charAt(0)=='C'){

            //No cancelar Póliza Gasto Comprometido si ya existe una Póliza Gasto Por Comprobar
            Asignacion findAsignacion= asignacionService.buscaPorSolicitudViaticoId(asignacion.getSolicitudViaticoId());
            if(findAsignacion.getPolizaGastoPorComprobarId()!=null && cancelarPoliza.containsKey("estatusPoliza") &&
                    cancelarPoliza.get("estatusPoliza").toString().toUpperCase().charAt(0)=='C'){
                throw new AsignacionException(AsignacionException.STATUS_ERROR_EXISTE_POLIZA_GPC);
            }
            //End Validacion
            // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
            if(!asignacion.getTimestamp().equals(findAsignacion.getTimestamp())){
                throw new Exception("La asignación con el código [" + asignacion.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
            }

            HashMap<String, Object> poliza = crearJsonPolizaComprometido(asignacion,cancelarPoliza);

            asignacion.setFechaUltimaModificacion(fecha);
            asignacion.setModificadoPorId(usuarioId);
            asignacion.setFechaComprometido(null);
            asignacion.setPolizaComprometidoId(null);
            asignacion.setNumeroPolizaComprometido(null);

            asignacion = (Asignacion) asignacionService.guardar(asignacion);

            solicitudViatico.setEstatusId(ListadoCMM.EstatusSolicitudViatico.AUTORIZADA);
            solicitudViatico.setFechaUltimaModificacion(fecha);
            solicitudViatico.setModificadoPorId(usuarioId);

            solicitudViaticoService.guardar(solicitudViatico);

        }//Si es asignacion, creamos la Póliza e iniciamos la Alerta
        else {

            HashMap<String, Object> poliza = crearJsonPolizaComprometido(asignacion,cancelarPoliza);

            asignacion.setPolizaComprometidoId(Integer.valueOf(poliza.get("PolizaId").toString()));
            asignacion.setNumeroPolizaComprometido(poliza.get("Poliza").toString());
            asignacion.setFechaUltimaModificacion(fecha);
            asignacion.setModificadoPorId(usuarioId);

            asignacionService.guardar(asignacion);

            HashMap<String, String> datosOficio = guardaOficioComision(solicitudViatico.getId(), usuarioId);

            solicitudViatico.setOficioComisionArchivoId(datosOficio.get("archivoId"));

            solicitudViatico.setEstatusId(ListadoCMM.EstatusSolicitudViatico.ENVIADA_FINANZAS);
            solicitudViatico.setFechaUltimaModificacion(fecha);
            solicitudViatico.setModificadoPorId(usuarioId);
            solicitudViaticoService.guardar(solicitudViatico);

            String alertaId = alertaService.iniciarAlerta(AlertaDefinicion.ASIGNACION_VIATICOS_RECURSOS_ASIGNADOS, solicitudViatico.getId(), solicitudViatico.getNumeroSolicitud(), "Solicitud: " + solicitudViatico.getNumeroSolicitud() + ", Estatus: Recursos Asignados", usuarioId);

            emailService.enviaCorreo(alertaId, datosOficio.get("rutaArchivo"));
        }

        return new JsonResponse("");
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/enviarrevision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse enviarRevision(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer solicitudId = new Integer(json.get("solicitudId").toString());
        String motivo = json.get("motivo").toString();
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        ResumenSolicitudViaticoProjection resumenSolicitud = solicitudViaticoService.buscarSolicitudViaticoResumenPorId(solicitudId);

        //Buscamos la solicitud a enviar a revision
        String alertaId = alertaService.iniciarAlerta(AlertaDefinicion.ASIGNACIÓN_VIATICOS_EN_REVISION, resumenSolicitud.getId(), resumenSolicitud.getNumeroSolicitud(), "Solicitud: " + resumenSolicitud.getNumeroSolicitud() + ", Estatus: En Revisión, Motivo: " + (motivo == null ? "" : motivo), usuarioId);

        emailService.enviaCorreo(alertaId);

        return new JsonResponse(true, "Solicitud Enviada a Revisión");
    }

    private HashMap<String, Object> crearJsonPolizaComprometido(Asignacion asignacion, Map<String, Object> cancelarPoliza) throws Exception {
        boolean enviarFinanzas = false;
        boolean cancelacion = false;
        Integer COMPRUEBA_SOLICITANTE = 1;
        Integer COMPRUEBA_RECURSOS_MATERIALES = 2;

        try {

            List<JsonCtasPolizaComprometido> cuentas = polizasService.getCtasPolizaComprometido(asignacion.getId());
            JsonPolizaComprometido jsonPoliza = new JsonPolizaComprometido(asignacion, cuentas);

            if(cancelarPoliza.containsKey("fechaCancelacion") && !cancelarPoliza.get("fechaCancelacion").toString().isEmpty()){
                jsonPoliza.setFechaCancelacionPoliza(cancelarPoliza.get("fechaCancelacion").toString());
                jsonPoliza.setEstatus(cancelarPoliza.get("estatusPoliza").toString().toUpperCase().charAt(0));
                cancelacion=true;
            }

            for (JsonCtasPolizaComprometido cuenta : cuentas) {
                enviarFinanzas = cuenta.getQuienComprueba().equals(COMPRUEBA_SOLICITANTE) || enviarFinanzas;
            }

            HashMap<String, Object> polizaComprometido = polizasService.getPolizaComprometido(new Gson().toJson(jsonPoliza), asignacion.getSolicitudViatico().getEjercicio());
            System.out.println(polizaComprometido);
            //Cuenta no existe o no tiene presupuesto
            if(polizaComprometido.containsKey("ObjetoGastoId")){
                throw  new APISAACGException("Cuenta "+polizaComprometido.get("ObjetoGastoId").toString()+" no existe o no tiene presupuesto: | "+polizaComprometido.get("PartidaPresupuestal").toString(),false);
            }

            //Periodo cerrado o cualquier otro error
            if(polizaComprometido.containsKey("error")){
                throw  new APISAACGException(polizaComprometido.get("error").toString(),false);
            }

            polizaComprometido.put("enviarFinanzas",cancelacion? false : enviarFinanzas);

            return polizaComprometido;
        }
        catch(APISAACGException ex){
            throw ex;
        }
    }

    private HashMap<String, String> guardaOficioComision(int solicitudId, int usuarioId) throws Exception {
        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);

        JsonArray ids = new JsonArray();
        ids.add(solicitudId);
        ids.add(usuario.getEmpleadoId());

        HashMap<String, Object> params = new HashMap<>();
        params.put("SolicitudId", solicitudId);
        params.put("Separador", File.separator);

        String nombreArchivo = archivoController.getJasperReport("OficioDeComision", "Oficio de Comisión", params, dataSource.getConnection(), ids);

        if (nombreArchivo==null) {
            throw new AsignacionException("Oficio de Comisión. "+archivoController.getExMessage(),AsignacionException.STATUS_ERROR_GUARDAR_OFICIO_COMISION,false);
        }

        HashMap<String, String> jsonReport = new HashMap<>();

        if (ids != null) {
            jsonReport = archivoService.getJsonReport(ids);
        }

        String rutaFisicaArchivo = jsonReport.get("rutaGuardar") + File.separator + nombreArchivo;

        Archivo archivo = new Archivo();
        archivo.setNombreOriginal(nombreArchivo);
        archivo.setNombreFisico("Oficio_de_Comision");
        archivo.setReferenciaId(solicitudId);
        archivo.setOrigenArchivoId(Integer.valueOf(jsonReport.get("origenId")));
        archivo.setRutaFisica(rutaFisicaArchivo);
        archivo.setTipoArchivoId(ListadoCMM.TipoArchivo.PDF);
        archivo.setVigente(true);
        archivo.setExportado(false);
        archivo.setFechaExportado(null);
        archivo.setFechaCreacion(new Timestamp(new Date().getTime()));
        archivo.setCreadoPorId(usuarioId);
        archivo = (Archivo) archivoService.guardar(archivo);

        if (archivo == null) {
            throw new AsignacionException(AsignacionException.STATUS_ERROR_GUARDAR_OFICIO_COMISION);
        }

        HashMap<String, String> datos = new HashMap<>();
        datos.put("archivoId", archivo.getId());
        datos.put("rutaArchivo", archivoService.getRutaRaizCMM() + File.separator + archivo.getRutaFisica());

        return datos;
    }
}