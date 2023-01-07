package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.RevisionException;
import com.pixvs.viaticos.exceptions.SolicitudViaticoException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.AlertaDefinicion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.*;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.PolizasService;
import com.pixvs.viaticos.util.Archivos;
import com.pixvs.viaticos.util.Email;
import com.pixvs.viaticos.util.ServidorFTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.codehaus.jettison.json.JSONArray;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.pixvs.viaticos.model.viaticos.mapeos.MenuPrincipal.*;

@RestController
@RequestMapping("/api/revision")
public class RevisionController extends GenericController {

    @Autowired
    private SolicitudViaticoService solicitudViaticoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private SolicitudViaticoComprobacionService solicitudViaticoComprobacionService;

    @Autowired
    private AsignacionViaticoService asignacionViaticoService;

    @Autowired
    private AsignacionPasajeService asignacionPasajeService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleService solicitudViaticoComprobacionDetalleService;

    @Autowired
    private SolicitudViaticoComprobacionPasajeService solicitudViaticoComprobacionPasajeService;

    @Autowired
    private SolicitudViaticoComprobacionCargoService solicitudViaticoComprobacionCargoService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleValidacionService solicitudViaticoComprobacionDetalleValidacionService;

    @Autowired
    private SolicitudViaticoRevisionService solicitudViaticoRevisionService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private RolMenuService rolMenuService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleImpuestoService solicitudViaticoComprobacionDetalleImpuestoService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    private PolizasService polizasService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ArchivoController archivoController;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConfiguracionEnteService configuracionEnteService;

    @Autowired
    private ServidorFTP servidorFTPService;

    @Autowired
    private ConceptoViaticoService conceptoViaticoService;

    @Autowired
    Email emailService;
    private String pathLogo="";

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        HashMap<String, Object> datosFicha = new HashMap<>();

        Integer solicitudViaticoId = json.get("solicitudViaticoId") != null ? new Integer ((String)json.get("solicitudViaticoId")) : null;

        SolicitudViatico solicitudViatico = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudViaticoId);

        if (solicitudViatico == null) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);
        }

        List<Integer> estatus = Arrays.asList(
                EstatusSolicitudViatico.EN_PROCESO_AUTORIZACION_REVISION,
                EstatusSolicitudViatico.AUTORIZACION_REVISION_APROBADA,
                EstatusSolicitudViatico.COMPROBACION_FINALIZADA,
                EstatusSolicitudViatico.REVISADA
        );

        if (!estatus.contains(solicitudViatico.getEstatusId())) {
            throw new RevisionException(RevisionException.STATUS_ERROR_ESTATUS_INCORRECTO);
        }

        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);
        boolean permisoRevisiones = rolMenuService.buscarPorRolIdAndMenuId(usuario.getRolId(), REVISION) != null;

        List<List> tblPermiso = alertaService.getPermisoAutorizacionAlerta(REVISION, solicitudViatico.getId(), usuarioId);

        Integer alertaId = tblPermiso.get(0).get(0) != null ? Integer.valueOf(tblPermiso.get(0).get(0).toString()) : null;
        Boolean mostrarAcciones = tblPermiso.get(0).get(1) != null ? Boolean.valueOf(tblPermiso.get(0).get(1).toString()) : null;

        if (!permisoRevisiones && mostrarAcciones == null) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_FORBIDEN);
        }

        mostrarAcciones = solicitudViatico.getEstatusId() != EstatusSolicitudViatico.REVISADA
                && ((mostrarAcciones != null && mostrarAcciones) || (permisoRevisiones && solicitudViatico.getEstatusId() != EstatusSolicitudViatico.EN_PROCESO_AUTORIZACION_REVISION));


        datosFicha.put("alertaId", alertaId);

        datosFicha.put("solicitudViatico", solicitudViatico);

        Asignacion asignacion = asignacionService.buscaPorSolicitudViaticoId(solicitudViaticoId);
        datosFicha.put("asignacionViatico", asignacionViaticoService.searchAsignacionId(asignacion == null ? null : asignacion.getId()));
        datosFicha.put("asignacionPasaje", asignacionPasajeService.searchAsignacionId(asignacion == null ? null : asignacion.getId()));

        SolicitudViaticoComprobacion solicitudViaticoComprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitudViaticoId);
        datosFicha.put("solicitudViaticoComprobacion", solicitudViaticoComprobacion);

        if(solicitudViaticoComprobacion.getPolizaComprobacionId()!=null){
            mostrarAcciones=true;
        }

        datosFicha.put("mostrarAcciones", mostrarAcciones);

        // Solicitud Viatico Comprobacion Detalle
        List<SolicitudViaticoComprobacionDetalle> solicitudViaticoComprobacionDetalleList = solicitudViaticoComprobacionDetalleService.searchSolicitudViaticoComprobacionId(solicitudViaticoComprobacion == null ? null : solicitudViaticoComprobacion.getId());
        datosFicha.put("solicitudViaticoComprobacionDetalle", solicitudViaticoComprobacionDetalleList);

        // Solicitud Viatico Comprobacion Detalle Impuesto
        List<SolicitudViaticoComprobacionDetalleImpuesto> solicitudViaticoComprobacionDetalleImpuestoList = new ArrayList<>();
        for (SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList) {
            List<SolicitudViaticoComprobacionDetalleImpuesto> _solicitudViaticoComprobacionDetalleImpuestoList = this.solicitudViaticoComprobacionDetalleImpuestoService.buscaSolicitudViaticoComprobacionDetalleId(solicitudViaticoComprobacionDetalle.getId());
            for (SolicitudViaticoComprobacionDetalleImpuesto solicitudViaticoComprobacionDetalleImpuesto : _solicitudViaticoComprobacionDetalleImpuestoList){
                solicitudViaticoComprobacionDetalleImpuestoList.add(solicitudViaticoComprobacionDetalleImpuesto);
            }
        }
        datosFicha.put("solicitudViaticoComprobacionDetalleImpuesto", solicitudViaticoComprobacionDetalleImpuestoList);

        // Archivo Tipo Comprobante
        List<Archivo> archivoList = new ArrayList<>();
        for (SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList) {
            List<Archivo> _archivoList = this.archivoService.searchReferenciaIdAndOrigenArchivoId(solicitudViaticoComprobacionDetalle.getId(), ListadoCMOA.CarpetaArchivoSolicitudes);
            for (Archivo archivo: _archivoList) {
                archivoList.add(archivo);
            }
        }
        datosFicha.put("archivo", archivoList);

        //Solicitud Viatico Comprobacion Pasaje
        List<SolicitudViaticoComprobacionPasaje> solicitudViaticoComprobacionPasajeList = new ArrayList<>();
        for(SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList) {
            SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje = solicitudViaticoComprobacionPasajeService.searchSolicitudViaticoComprobacionDetalleId(solicitudViaticoComprobacionDetalle == null ? null : solicitudViaticoComprobacionDetalle.getId());
            if (solicitudViaticoComprobacionPasaje != null) {
                solicitudViaticoComprobacionPasajeList.add(solicitudViaticoComprobacionPasaje);
            }

        }
        datosFicha.put("solicitudViaticoComprobacionPasaje", solicitudViaticoComprobacionPasajeList);

        //Solicitud Viatico Comprobacion Cargo
        List<SolicitudViaticoComprobacionCargo> solicitudViaticoComprobacionCargoList = new ArrayList<>();
        for(SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje : solicitudViaticoComprobacionPasajeList) {
            SolicitudViaticoComprobacionCargo solicitudViaticoComprobacionCargo = solicitudViaticoComprobacionCargoService.searchSolicitudViaticoComprobacionPasajeId(solicitudViaticoComprobacionPasaje == null ? null : solicitudViaticoComprobacionPasaje.getId());
            if (solicitudViaticoComprobacionCargo != null) {
                solicitudViaticoComprobacionCargoList.add(solicitudViaticoComprobacionCargo);
            }
        }
        datosFicha.put("solicitudViaticoComprobacionCargo", solicitudViaticoComprobacionCargoList);

        datosFicha.put("solicitudViaticoComprobacionDetalleValidacion", solicitudViaticoComprobacionDetalleValidacionService.searchSolicitudViaticoComprobacionId(solicitudViaticoComprobacion != null ? solicitudViaticoComprobacion.getId() : null));

        // Moneda
        datosFicha.put("moneda", monedaService.buscarTodos());

        // Forma Pago
        datosFicha.put("formaPago", listadoCMMService.buscarPorCodigo("FormaPago"));

        // Concepto Viatico
        datosFicha.put("conceptoViatico", conceptoViaticoService.getListadoConceptoViaticoProjection());

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);
        boolean permisoRevisiones = rolMenuService.buscarPorRolIdAndMenuId(usuario.getRolId(), REVISION) != null;

        if (!permisoRevisiones) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_FORBIDEN);
        }

        return new JsonResponse(solicitudViaticoService.getListadoRevisionSolicitudViatico());
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
            throw new RevisionException("El logo no existe: "+pathLogo,RevisionException.STATUS_ERROR_REPORT,false);

        }
        int usuarioId = JwtFilter.getUsuarioId(headers);

        // Usuario
        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);

        // Time
        Timestamp fecha = new Timestamp(new Date().getTime());

        ObjectMapper mapper = new ObjectMapper();

        // Solicitud Viatico
        SolicitudViatico solicitud = mapper.convertValue(json.get("solicitud"), SolicitudViatico.class);

        String motivo = json.get("motivo") != null && !json.get("motivo").toString().isEmpty() ? (String) json.get("motivo") : null;
        if(motivo!=null && motivo!=""){
            solicitud.setEstatusId(EstatusSolicitudViatico.EN_REVISION_COMPROBACION);
        }
        if (solicitud == null) {
            throw new RevisionException(RevisionException.STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);
        }

        int alertaDefinicionId;
        String estatus;

        Map<String,String> ObjectComprobacion =null;
        if(json.containsKey("polizaComprobacion")){
            ObjectMapper obMapper=new ObjectMapper();
            ObjectComprobacion = obMapper.readValue(json.get("polizaComprobacion").toString(), Map.class);

        }

        // Solicitud Viatico Comprobacion
        SolicitudViaticoComprobacion comprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitud.getId());

        switch (solicitud.getEstatusId()) {
            case EstatusSolicitudViatico.REVISADA:
                alertaDefinicionId = AlertaDefinicion.REVISION_FINALIZADA;
                estatus = "Revisada";

                // Solicitud Viatico Revision
                SolicitudViaticoRevision revision = (SolicitudViaticoRevision) solicitudViaticoRevisionService.buscarPorId(solicitud.getId());

                if (revision == null) {

                    // Create and Save Report
                    // Add Array for ID Usuario/Empleado
                    JsonArray ids = new JsonArray();
                    ids.add(solicitud.getId());
                    ids.add(usuario.getEmpleadoId());

                    // Create parameters for Jasper Report
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("SolicitudViaticoId", solicitud.getId());

                    // Check if the report
                    String nombreArchivo = archivoController.getJasperReport("InformeDeComision", "Informe de Comisión", params, dataSource.getConnection(), ids);

                    if (nombreArchivo == null) {
                        throw new RevisionException(RevisionException.STATUS_ERROR_REPORT);
                    }

                    // Create Dir and Get Json Report Data
                    HashMap<String, String> jsonReport = new HashMap<>();

                    if (ids != null) {
                        jsonReport = archivoService.getJsonReport(ids);
                    }

                    String rutaFisicaArchivo = jsonReport.get("rutaGuardar") + File.separator + nombreArchivo;

                    Archivo archivo = new Archivo();
                    archivo.setNombreOriginal(nombreArchivo);
                    archivo.setNombreFisico("Informe_de_Comision");
                    archivo.setReferenciaId((Integer) params.get("SolicitudViaticoId"));
                    archivo.setOrigenArchivoId(Integer.valueOf(jsonReport.get("origenId")));
                    archivo.setRutaFisica(rutaFisicaArchivo);
                    archivo.setTipoArchivoId(ListadoCMM.TipoArchivo.PDF);
                    archivo.setVigente(true);
                    archivo.setExportado(false);
                    archivo.setFechaExportado(fecha);
                    archivo.setFechaCreacion(fecha);
                    archivo.setCreadoPorId(usuarioId);
                    archivo = (Archivo) archivoService.guardar(archivo);

                    if (archivo == null) {
                        throw new RevisionException(RevisionException.STATUS_ERROR_ARCHIVO);
                    }

                    ServidorFTPConfiguracionEnteProjection properties = configuracionEnteService.getDatosServidorFTP();

                    String directorio = "reportes/"
                            + (solicitud.getFechaCreacion().getYear() + 1900) + "/"
                            + solicitud.getEmpleado().getNombreCompleto() + "/"
                            + solicitud.getNumeroSolicitud() + "/";

                    String separador = properties.isDirectorioRemoto() ? "/" : File.separator;

                    directorio = (properties.isDirectorioRemoto() ? properties.getDirectorioFTP() : properties.getDirectorioPublico()) + directorio.replace("\\", separador).replace("/", separador);
                    String pathOriginal = archivoService.getRutaRaizCMM() + File.separator + archivo.getRutaFisica();
                    String archivoFisico = archivo.getNombreFisico() + archivo.getNombreOriginal().substring(archivo.getNombreOriginal().indexOf("."), archivo.getNombreOriginal().length());

                    if (properties.isDirectorioRemoto()) {
                        servidorFTPService.subirArchivos(properties, new DatosArchivoServidorFTP(
                                pathOriginal,
                                directorio,
                                archivoFisico
                        ));
                    } else {
                        Archivos.createDir(directorio);
                        Archivos.copiarArchivo(pathOriginal, directorio + archivoFisico);
                    }

                    solicitud.setArchivoInformeId(archivo.getId());
                    solicitud.setFechaUltimaModificacion(fecha);
                    solicitud.setModificadoPorId(usuarioId);
                    solicitud = (SolicitudViatico) solicitudViaticoService.guardar(solicitud);

                    if (solicitud == null) {
                        throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_ERROR_GUARDAR);
                    }

                    // Poliza Comprobacion SAACG
                    HashMap<String, Object> polizaComprobacion = polizasService.getPolizaComprobacion(json.get("polizaComprobacion").toString(), (Integer) json.get("ejercicio"));

                    if(polizaComprobacion.get("data")==null){
                        throw new SolicitudViaticoException(polizaComprobacion.get("message").toString(),SolicitudViaticoException.STATUS_ERROR_GUARDAR,false);
                    }
                    // Get ID de Poliza
                    JSONArray jsonObject = new JSONArray(polizaComprobacion.get("data").toString());
                    Map<String,Object> poliza = mapper.readValue(jsonObject.get(0).toString(), HashMap.class);


                    // Solicitud Viatico Comprobacion
                    //SolicitudViaticoComprobacion comprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitud.getId());

                    if (comprobacion == null) {
                        throw new RevisionException(RevisionException.STATUS_ERROR_COMPROBACION_NO_ENCONTRADA);
                    }

                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    Date fechaComprobacion = formatter.parse(ObjectComprobacion.get("FechaComprobacion").toString());
                    Timestamp timestampFC = new Timestamp(fechaComprobacion.getTime());

                    // Set ID de Poliza to Solicitud Viatico Comprobacion
                    comprobacion.setPolizaComprobacionId((Integer) poliza.get("PolizaId"));
                    comprobacion.setNumeroPolizaGastoComprobado((String) poliza.get("Poliza"));
                    comprobacion.setFechaPolizaComprobacion(timestampFC);
                    solicitudViaticoComprobacionService.guardar(comprobacion);

                    revision = new SolicitudViaticoRevision();
                    revision.setSolicitudViaticoId(solicitud.getId());
                    revision.setEstatusId(EstatusRegistro.ACTIVO);
                    revision.setCreadoPorId(usuarioId);
                    revision.setFechaCreacion(fecha);
                    solicitudViaticoRevisionService.guardar(revision);
                }else{

                    //Cancelar Poliza
                    // Poliza Comprobacion SAACG
                    ObjectComprobacion.put("Estatus","C");
                    Gson gson = new Gson();
                    String jsonComprobacion=gson.toJson(ObjectComprobacion);

                    HashMap<String, Object> polizaComprobacion = polizasService.getPolizaComprobacion(jsonComprobacion, (Integer) json.get("ejercicio"));


                    //Solicitud
                    solicitud.setEstatusId(EstatusSolicitudViatico.COMPROBACION_FINALIZADA);
                    solicitudViaticoService.guardar(solicitud);

                    //Comprobación
                    comprobacion.setNumeroPolizaGastoComprobado(null);
                    comprobacion.setPolizaComprobacionId(null);
                    comprobacion.setFechaPolizaComprobacion(null);
                    solicitudViaticoComprobacionService.guardar(comprobacion);

                    // Solicitud Viatico Revision
                    revision.setEstatusId(EstatusSolicitudViatico.BORRADA);
                    solicitudViaticoRevisionService.eliminarPorId(revision.getId());
                    //solicitudViaticoRevisionService.guardar(revision);
                }
            break;

            case EstatusSolicitudViatico.EN_PROCESO_AUTORIZACION_REVISION:
                alertaDefinicionId = AlertaDefinicion.REVISION_REVISION_VALIDACION;
                estatus = "En Proceso de Autorización de Revisión" + (motivo != null ? ", Motivo: " + motivo : "");
                if (comprobacion == null) {
                    throw new RevisionException(RevisionException.STATUS_ERROR_COMPROBACION_NO_ENCONTRADA);
                }
                //Solicitud
                solicitud.setEstatusId(EstatusSolicitudViatico.EN_REVISION_COMPROBACION);
                solicitudViaticoService.guardar(solicitud);

                comprobacion.setSolicitanteFinalizoComprobacion(false);
                comprobacion.setFechaSolicitanteFinalizoComprobacion(null);
                comprobacion.setRmFinalizoComprobacion(false);
                comprobacion.setFechaRMFinalizoComprobacion(null);
                comprobacion.setFechaUltimaModificacion(fecha);
                comprobacion.setModificadoPorId(usuarioId);
                solicitudViaticoComprobacionService.guardar(comprobacion);
            break;

            case EstatusSolicitudViatico.AUTORIZACION_REVISION_APROBADA:
                alertaDefinicionId = AlertaDefinicion.REVISION_VALIDACION_AUTORIZACION;
                estatus = "Autorización de Revisión Aprobada";
            break;

            case EstatusSolicitudViatico.EN_REVISION_COMPROBACION:
                alertaDefinicionId = AlertaDefinicion.REVISION_VALIDACION_REVISION;
                estatus = "En Revision Comprobación" + (motivo != null ? ", Motivo: " + motivo : "");

               // SolicitudViaticoComprobacion comprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitud.getId());

                if (comprobacion == null) {
                    throw new RevisionException(RevisionException.STATUS_ERROR_COMPROBACION_NO_ENCONTRADA);
                }

                comprobacion.setSolicitanteFinalizoComprobacion(false);
                comprobacion.setFechaSolicitanteFinalizoComprobacion(null);
                comprobacion.setRmFinalizoComprobacion(false);
                comprobacion.setFechaRMFinalizoComprobacion(null);
                comprobacion.setFechaUltimaModificacion(fecha);
                comprobacion.setModificadoPorId(usuarioId);
                solicitudViaticoComprobacionService.guardar(comprobacion);
            break;

            default:
                throw new RevisionException(RevisionException.STATUS_ERROR_ESTATUS_INCORRECTO);
        }

        if(comprobacion.getPolizaComprobacionId()==null){
            String alertaId = alertaService.iniciarAlerta(alertaDefinicionId, solicitud.getId(), solicitud.getNumeroSolicitud(), "Solicitud: " + solicitud.getNumeroSolicitud() + ", Estatus: " + estatus, usuarioId);

            emailService.enviaCorreo(alertaId);
        }


        return new JsonResponse(true, comprobacion.getPolizaComprobacionId()!=null?"Alerta iniciada.":"Póliza Cancelada");
    }
}
