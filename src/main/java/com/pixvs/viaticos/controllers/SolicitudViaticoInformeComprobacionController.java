package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.SolicitudViaticoException;
import com.pixvs.viaticos.exceptions.InformeComprobacionException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.PaisService;
import com.pixvs.viaticos.service.saacg.CuentaGastoService;
import com.pixvs.viaticos.service.saacg.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.pixvs.viaticos.model.viaticos.mapeos.MenuPrincipal.*;

@RestController
@RequestMapping("/api/solicitudviaticoinformecomprobacion")
public class SolicitudViaticoInformeComprobacionController extends GenericController {

    @Autowired
    private SolicitudViaticoInformeComprobacionService solicitudViaticoInformeComprobacionService;

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    private SolicitudViaticoService solicitudViaticoService;

    @Autowired
    private SolicitudViaticoInformeService solicitudViaticoInformeService;

    @Autowired
    private AsignacionViaticoService asignacionViaticoService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private SolicitudViaticoComprobacionService solicitudViaticoComprobacionService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleService solicitudViaticoComprobacionDetalleService;

    @Autowired
    private SolicitudViaticoComprobacionPasajeService solicitudViaticoComprobacionPasajeService;

    @Autowired
    private SolicitudViaticoComprobacionCargoService solicitudViaticoComprobacionCargoService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ConceptoViaticoService conceptoViaticoService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    private AsignacionPasajeService asignacionPasajeService;

    @Autowired
    private RolMenuService rolMenuService;

    @Autowired
    private GrupoJerarquicoPuestoService grupoJerarquicoPuestoService;

    @Autowired
    private MatrizViaticoService matrizViaticoService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private ConfiguracionEnteService configuracionEnteService;

    @Autowired
    private PermisoAccesoService permisoAccesoService;

    @Autowired
    private SolicitudViaticoComprobacionDetalleImpuestoService solicitudViaticoComprobacionDetalleImpuestoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private CuentaGastoService cuentaGastoService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Integer solicitudId = json.get("solicitudViaticoId") != null ? new Integer((String) json.get("solicitudViaticoId")) : null;
        HashMap<String, Object> datosFicha = new HashMap<>();

        SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudId);

        if (solicitud == null) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);
        }

        List<Integer> estatus = Arrays.asList(
                ListadoCMM.EstatusSolicitudViatico.RECURSOS_ASIGNADOS,
                ListadoCMM.EstatusSolicitudViatico.COMPROBACION_FINALIZADA,
                ListadoCMM.EstatusSolicitudViatico.EN_REVISION_COMPROBACION,
                ListadoCMM.EstatusSolicitudViatico.REVISADA
        );

        if (!estatus.contains(solicitud.getEstatusId())) {
            throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_SOLICITUD_ESTATUS);
        }

        Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);

        boolean usuarioSolicitante = solicitud.getCreadoPorId().equals(usuarioId)
                || solicitud.getEmpleadoId().equals(usuario.getEmpleadoId());

        boolean permisoAsignarViaticos = rolMenuService.buscarPorRolIdAndMenuId(usuario.getRolId(), ASIGNAR_VIATICOS) != null;

        boolean permisoVerTerceros = permisoAccesoService.isPermisoVerTerceros(usuarioId, solicitud.getAreaAdscripcionId());

        if (!usuarioSolicitante && !permisoAsignarViaticos && !permisoVerTerceros) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_FORBIDEN);
        }

        Asignacion asignacion = asignacionService.buscaPorSolicitudViaticoId(solicitudId);
        SolicitudViaticoComprobacion solicitudViaticoComprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitudId);
        List<SolicitudViaticoComprobacionDetalle> solicitudViaticoComprobacionDetalleList = solicitudViaticoComprobacionDetalleService.searchSolicitudViaticoComprobacionId(solicitudViaticoComprobacion == null ? null : solicitudViaticoComprobacion.getId());
        GrupoJerarquicoPuesto grupoJerarquicoPuesto = grupoJerarquicoPuestoService.searchListadoPuestoId(solicitud.getPuestoId());

        // Solicitud Viatico Comprobacion Detalle Impuesto
        List<SolicitudViaticoComprobacionDetalleImpuesto> solicitudViaticoComprobacionDetalleImpuestoList = new ArrayList<>();

        List<Archivo> archivoList = new ArrayList<>();
        for (SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList) {
            List<Archivo> _archivoList = this.archivoService.searchReferenciaIdAndOrigenArchivoId(solicitudViaticoComprobacionDetalle.getId(), ListadoCMOA.CarpetaArchivoSolicitudes);
            for (Archivo archivo : _archivoList) {
                archivoList.add(archivo);
            }

            // Solicitud Viatico Comprobacion Detalle Impuesto
            List<SolicitudViaticoComprobacionDetalleImpuesto> _solicitudViaticoComprobacionDetalleImpuestoList = this.solicitudViaticoComprobacionDetalleImpuestoService.buscaSolicitudViaticoComprobacionDetalleId(solicitudViaticoComprobacionDetalle.getId());
            for (SolicitudViaticoComprobacionDetalleImpuesto solicitudViaticoComprobacionDetalleImpuesto : _solicitudViaticoComprobacionDetalleImpuestoList){
                solicitudViaticoComprobacionDetalleImpuestoList.add(solicitudViaticoComprobacionDetalleImpuesto);
            }
        }

        List<SolicitudViaticoComprobacionPasaje> solicitudViaticoComprobacionPasajeList = new ArrayList<>();
        for (SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList) {
            SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje = solicitudViaticoComprobacionPasajeService.searchSolicitudViaticoComprobacionDetalleId(solicitudViaticoComprobacionDetalle == null ? null : solicitudViaticoComprobacionDetalle.getId());
            if (solicitudViaticoComprobacionPasaje != null) {
                solicitudViaticoComprobacionPasajeList.add(solicitudViaticoComprobacionPasaje);
            }

        }

        List<SolicitudViaticoComprobacionCargo> solicitudViaticoComprobacionCargoList = new ArrayList<>();
        for (SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje : solicitudViaticoComprobacionPasajeList) {
            SolicitudViaticoComprobacionCargo solicitudViaticoComprobacionCargo = solicitudViaticoComprobacionCargoService.searchSolicitudViaticoComprobacionPasajeId(solicitudViaticoComprobacionPasaje == null ? null : solicitudViaticoComprobacionPasaje.getId());
            if (solicitudViaticoComprobacionCargo != null) {
                solicitudViaticoComprobacionCargoList.add(solicitudViaticoComprobacionCargo);
            }
        }

        datosFicha.put("solicitudViatico", solicitud);
        datosFicha.put("solicitudViaticoComprobacion", solicitudViaticoComprobacion);
        datosFicha.put("solicitudViaticoInforme", solicitudViaticoInformeService.searchSolicitudViaticoComprobacionId(solicitudViaticoComprobacion == null ? null : solicitudViaticoComprobacion.getId()));
        datosFicha.put("asignacionViatico", asignacionViaticoService.searchAsignacionId(asignacion == null ? null : asignacion.getId()));
        datosFicha.put("asignacionPasaje", asignacionPasajeService.searchAsignacionId(asignacion == null ? null : asignacion.getId()));
        datosFicha.put("solicitudViaticoComprobacionDetalle", solicitudViaticoComprobacionDetalleList);
        datosFicha.put("solicitudViaticoComprobacionDetalleImpuesto", solicitudViaticoComprobacionDetalleImpuestoList);
        datosFicha.put("solicitudViaticoComprobacionDetalleRMBorrado", solicitudViaticoComprobacionDetalleService.searchSolicitudViaticoComprobacionIdRMWithRemove(solicitudViaticoComprobacion == null ? null : solicitudViaticoComprobacion.getId()));
        datosFicha.put("archivo", archivoList);
        datosFicha.put("solicitudViaticoComprobacionPasaje", solicitudViaticoComprobacionPasajeList);
        datosFicha.put("solicitudViaticoComprobacionCargo", solicitudViaticoComprobacionCargoList);
        datosFicha.put("categoriaViatico", listadoCMMService.getListadoCMMComboByNombreAndActivo("CategoriaViatico"));
        datosFicha.put("tipoComprobante", listadoCMMService.getListadoCMMComboByNombreAndActivo("TipoComprobante"));
        datosFicha.put("formaComprobacion", listadoCMMService.getListadoCMMComboByNombreAndActivo("FormaComprobacion"));
        datosFicha.put("formaPago", listadoCMMService.getListadoCMMComboByNombreAndActivo("FormaPago"));
        datosFicha.put("moneda", monedaService.buscarTodos());
        datosFicha.put("conceptoViatico", conceptoViaticoService.getListadoConceptoViaticoProjection());
        datosFicha.put("permisoRM", permisoAsignarViaticos);
        datosFicha.put("usuarioSolicitante", usuarioSolicitante);
        datosFicha.put("configuracionEnte", configuracionEnteService.getSolicitudViaticoComprobacionConfiguracionEnte());
        datosFicha.put("matrizViatico", grupoJerarquicoPuesto != null ? matrizViaticoService.searchGrupoJerarquicoIdAndListadoZonaId(grupoJerarquicoPuesto.getGrupoJerarquicoId(), solicitud.getCiudadDestino().getZonaEconomicaId()) : new ArrayList<>());
        datosFicha.put("soloLectura", solicitud.getEstatusId() == ListadoCMM.EstatusSolicitudViatico.COMPROBACION_FINALIZADA
                || solicitud.getEstatusId() == ListadoCMM.EstatusSolicitudViatico.REVISADA
                || (!usuarioSolicitante && !permisoAsignarViaticos && permisoVerTerceros)
                || (!usuarioSolicitante && permisoAsignarViaticos && solicitudViaticoComprobacion != null && solicitudViaticoComprobacion.getRmFinalizoComprobacion())
        );
        datosFicha.put("listadoCMMClavesProductosHospedaje", listadoCMMService.getListadoCMMComboByNombreAndActivo("ClavesProductosHospedaje"));

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(solicitudViaticoInformeComprobacionService.getListadoInformeComprobacionPorUsuarioId(JwtFilter.getUsuarioId(headers)));
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
        Integer idUsuario = JwtFilter.getUsuarioId(headers);

        //Mapper Convert Object to Model
        ObjectMapper mapper = new ObjectMapper();

        //Get Solicitud Viatico Comporbacion
        SolicitudViaticoComprobacion solicitudViaticoComprobacion = mapper.convertValue(json.get("solicitudViaticoComprobacion"), SolicitudViaticoComprobacion.class);

        // Usuario
        Usuario usuario = (Usuario) usuarioService.buscarPorId(idUsuario);

        //Verificar que no se haya guardado ya una comprobacion para no duplicar registros
        if(solicitudViaticoComprobacion.getId() == null){
            SolicitudViaticoComprobacion _comprobacion = solicitudViaticoComprobacionService.buscarPorSolicitudViaticoId(solicitudViaticoComprobacion.getSolicitudViaticoId());
            if(_comprobacion != null)
                throw new Exception("Ya existe una comprobación par la solicitud que intenta guardar. Favor de regresar al listado y buscar la solicitud.");
        }

        // Vertify have data Comprobacion and Recursos Materiales
        if(solicitudViaticoComprobacion != null){

            // Solicitud Viatico Comprobacion
            if(solicitudViaticoComprobacion.getId() == null){
                solicitudViaticoComprobacion.setCreadoPorId(idUsuario);
                solicitudViaticoComprobacion.setFechaCreacion(new Timestamp(new Date().getTime()));
            }else{
                // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
                SolicitudViaticoComprobacion _solicitudViaticoComprobacion = (SolicitudViaticoComprobacion) solicitudViaticoComprobacionService.buscarPorId(solicitudViaticoComprobacion.getId());
                if(!solicitudViaticoComprobacion.getTimestamp().equals(_solicitudViaticoComprobacion.getTimestamp())){
                    throw new Exception("La solicitud viatico comprobación con el código [" + solicitudViaticoComprobacion.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
                }

                solicitudViaticoComprobacion.setModificadoPorId(idUsuario);
                solicitudViaticoComprobacion.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
            }

            solicitudViaticoComprobacion = (SolicitudViaticoComprobacion) solicitudViaticoComprobacionService.guardar(solicitudViaticoComprobacion);

            // Solicitud Viatico Informe
            //Get Solicitud Viatico Informe
            SolicitudViaticoInforme solicitudViaticoInforme = mapper.convertValue(json.get("solicitudViaticoInforme"), SolicitudViaticoInforme.class);
            if(solicitudViaticoInforme != null){
                if(solicitudViaticoInforme.getId() == null){
                    solicitudViaticoInforme.setCreadoPorId(idUsuario);
                    solicitudViaticoInforme.setFechaCreacion(new Timestamp(new Date().getTime()));

                }else{
                    solicitudViaticoInforme.setModificadoPorId(idUsuario);
                    solicitudViaticoInforme.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                }
                // Set ID Solicitud Viatico Comprobacion To Solicitud Viatico Informe
                solicitudViaticoInforme.setSolicitudViaticoComprobacionId(solicitudViaticoComprobacion.getId());

                solicitudViaticoInforme = (SolicitudViaticoInforme) solicitudViaticoInformeService.guardar(solicitudViaticoInforme);

                if(solicitudViaticoInforme == null){
                    throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_GUARDAR_INFORME);
                }
            }

            // Solicitud Viatico Comprobacion Detalle
            //Get Solicitud Viatico Comporbacion Detalle
            ServidorFTPConfiguracionEnteProjection properties = configuracionEnteService.getDatosServidorFTP();
            SolicitudViaticoComprobacionDetalle[] solicitudViaticoComprobacionDetalle = mapper.convertValue(json.get("solicitudViaticoComprobacionDetalle"), SolicitudViaticoComprobacionDetalle[].class);
            for (SolicitudViaticoComprobacionDetalle _solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalle){

                // ArchivoarchivoTipoComprobante
                List<Map<String, Object>> archivoTipoComprobante = mapper.convertValue(json.get("archivoTipoComprobante"), new TypeReference<List<Map<String, Object>>>() {});

                // Verificar si el Tipo Comprobante es Factura Nacional para compartida
                if(_solicitudViaticoComprobacionDetalle.getTipoComprobanteId() != null ? _solicitudViaticoComprobacionDetalle.getTipoComprobanteId() == ListadoCMM.TipoComprobante.FACTURA_NACIONAL && _solicitudViaticoComprobacionDetalle.getEstatusId() != ListadoCMM.EstatusRegistro.BORRADO : false){
                    // Si forma de comprobacion es por detalles para compartida
                    if(_solicitudViaticoComprobacionDetalle.getFormaComprobacionId() == ListadoCMM.FormaComprobacion.POR_DETALLES){
                        List<SolicitudViaticoComprobacionDetalle> solicitudViaticoComprobacionDetalleList = this.solicitudViaticoComprobacionDetalleService.searchSolicitudViaticoComprobacionDetalleWithShareFileImporte(_solicitudViaticoComprobacionDetalle.getTipoComprobanteId(),_solicitudViaticoComprobacionDetalle.getFolio(),_solicitudViaticoComprobacionDetalle.getRfc(),_solicitudViaticoComprobacionDetalle.getRazonSocial(), _solicitudViaticoComprobacionDetalle.getNumeroPartida());
                        if(solicitudViaticoComprobacionDetalleList.size() > 0){
                            double importeFacturaCompartida = 0;
                            for(SolicitudViaticoComprobacionDetalle _solicitudViaticoComprobacionDetalleList : solicitudViaticoComprobacionDetalleList){
                                if(!_solicitudViaticoComprobacionDetalle.getId().equals(_solicitudViaticoComprobacionDetalleList.getId())){
                                    importeFacturaCompartida += _solicitudViaticoComprobacionDetalleList.getSubTotalComprobacion().doubleValue();
                                }
                            }

                            // Sum User for Users
                            importeFacturaCompartida += _solicitudViaticoComprobacionDetalle.getSubTotalComprobacion().doubleValue();

                            if(importeFacturaCompartida > _solicitudViaticoComprobacionDetalle.getConceptoImporte().doubleValue()){
                                Integer referenciaId = _solicitudViaticoComprobacionDetalle.getId();

                                List<Map<String, Object>> fileList = archivoTipoComprobante.stream().filter(file -> file.get("referenciaId").equals(referenciaId) && file.get("tipoArchivoId").equals(ListadoCMM.TipoArchivo.XML) && (boolean)file.get("vigente") == true).collect(Collectors.toList());
                                throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_GUARDAR_COMPROBACION_DETALLE_FACTURA, fileList.get(0).get("nombreArchivoTemporal").toString());
                            }
                        }
                    }
                }

                if(_solicitudViaticoComprobacionDetalle.getId() > 0){
                    _solicitudViaticoComprobacionDetalle.setModificadoPorId(idUsuario);
                    _solicitudViaticoComprobacionDetalle.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                }else{

                    // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                    if (_solicitudViaticoComprobacionDetalle.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                        if(!sistemaService.permiteEliminarRegistro(_solicitudViaticoComprobacionDetalle.getId(), "tblSolicitudViaticoComprobacionDetalle"))
                            throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_COMPROBACION_DETALLE_RELACION);
                    }

                    _solicitudViaticoComprobacionDetalle.setRegistradoPorId(idUsuario);
                    _solicitudViaticoComprobacionDetalle.setFechaRegistro(new Timestamp(new Date().getTime()));
                }
                // Set ID Solicitud Viatico Comprobacion To Solicitu Viatico Comprobacion Detalle
                _solicitudViaticoComprobacionDetalle.setSolicitudViaticoComprobacionId(solicitudViaticoComprobacion.getId());

                Integer idFatherComprobacion = _solicitudViaticoComprobacionDetalle.getId();

                _solicitudViaticoComprobacionDetalle = (SolicitudViaticoComprobacionDetalle) solicitudViaticoComprobacionDetalleService.guardar(_solicitudViaticoComprobacionDetalle);

                // Solicitud Viatico Comprobacion Detalle Impuesto
                SolicitudViaticoComprobacionDetalleImpuesto[] solicitudViaticoComprobacionDetalleImpuesto = mapper.convertValue(json.get("solicitudViaticoComprobacionDetalleImpuesto"), SolicitudViaticoComprobacionDetalleImpuesto[].class);
                for (SolicitudViaticoComprobacionDetalleImpuesto _solicitudViaticoComprobacionDetalleImpuesto : solicitudViaticoComprobacionDetalleImpuesto){
                    if(_solicitudViaticoComprobacionDetalleImpuesto.getSolicitudViaticoComprobacionDetalleId().equals(idFatherComprobacion)){
                        if(_solicitudViaticoComprobacionDetalleImpuesto.getId() == null){
                            _solicitudViaticoComprobacionDetalleImpuesto.setRegistradoPorId(usuario.getEmpleadoId());
                            _solicitudViaticoComprobacionDetalleImpuesto.setFechaRegistro(new Timestamp(new Date().getTime()));
                        }else{
                            // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                            if (_solicitudViaticoComprobacionDetalleImpuesto.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                                if(!sistemaService.permiteEliminarRegistro(_solicitudViaticoComprobacionDetalleImpuesto.getId(), "tblSolicitudViaticoComprobacionDetalleImpuesto"))
                                    throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_COMPROBACION_DETALLE_IMPUESTO_RELACION);
                            }

                            _solicitudViaticoComprobacionDetalleImpuesto.setModificadoPorId(usuario.getEmpleadoId());
                            _solicitudViaticoComprobacionDetalleImpuesto.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                        }
                        // Set ID Solicitud Viatico Comprobacion Detalle
                        _solicitudViaticoComprobacionDetalleImpuesto.setSolicitudViaticoComprobacionDetalleId(_solicitudViaticoComprobacionDetalle.getId());

                        _solicitudViaticoComprobacionDetalleImpuesto = (SolicitudViaticoComprobacionDetalleImpuesto) solicitudViaticoComprobacionDetalleImpuestoService.guardar(_solicitudViaticoComprobacionDetalleImpuesto);
                    }
                };

                // Archivo Tipo Comprobante
                JsonArray ids = new JsonArray();
                ids.add(solicitudViaticoComprobacion.getSolicitudViaticoId());
                ids.add(usuario.getEmpleadoId());
                try {
                    for (Map<String, Object> _archivoTipoComprobante : archivoTipoComprobante) {
                        if (idFatherComprobacion.equals(_archivoTipoComprobante.get("referenciaId"))) {
                            SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudViaticoComprobacion.getSolicitudViaticoId());
                            String directorio = "comprobantes/"
                                    + (solicitud.getFechaCreacion().getYear() + 1900) + "/"
                                    + solicitud.getEmpleado().getNombreCompleto() + "/"
                                    + solicitud.getNumeroSolicitud() + "/";
                            this.archivoService.guardaComprobanteSolicitud(properties, directorio, _archivoTipoComprobante, idUsuario, _solicitudViaticoComprobacionDetalle.getId(), ids);
                        }
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }


                //Get Solicitud Viatico Comporbacion Pasaje
                SolicitudViaticoComprobacionPasaje[] solicitudViaticoComprobacionPasaje = mapper.convertValue(json.get("solicitudViaticoComprobacionPasaje"), SolicitudViaticoComprobacionPasaje[].class);
                for (SolicitudViaticoComprobacionPasaje _solicitudViaticoComprobacionPasaje : solicitudViaticoComprobacionPasaje){
                    if(idFatherComprobacion.equals(_solicitudViaticoComprobacionPasaje.getSolicitudViaticoComprobacionDetalleId())){
                        if(_solicitudViaticoComprobacionPasaje.getId() > 0){
                            _solicitudViaticoComprobacionPasaje.setModificadoPorId(idUsuario);
                            _solicitudViaticoComprobacionPasaje.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                        }else{
                            // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                            if (_solicitudViaticoComprobacionPasaje.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                                if(!sistemaService.permiteEliminarRegistro(_solicitudViaticoComprobacionPasaje.getId(), "tblSolicitudViaticoComprobacionPasaje"))
                                    throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_COMPROBACION_PASAJE_RELACION);
                            }

                            _solicitudViaticoComprobacionPasaje.setRegistradoPorId(idUsuario);
                            _solicitudViaticoComprobacionPasaje.setFechaRegistro(new Timestamp(new Date().getTime()));
                        }
                        // Set ID Solicitud Viatico Comprobacion Detalle To Solicitu Viatico Comprobacion Pasaje
                        _solicitudViaticoComprobacionPasaje.setSolicitudViaticoComprobacionDetalleId(_solicitudViaticoComprobacionDetalle.getId());

                        Integer idFatherComprobacionPasaje = _solicitudViaticoComprobacionPasaje.getId();

                        _solicitudViaticoComprobacionPasaje = (SolicitudViaticoComprobacionPasaje) solicitudViaticoComprobacionPasajeService.guardar(_solicitudViaticoComprobacionPasaje);

                        //Get Solicitud Viatico Comporbacion
                        SolicitudViaticoComprobacionCargo[] solicitudViaticoComprobacionCargo = mapper.convertValue(json.get("solicitudViaticoComprobacionCargo"), SolicitudViaticoComprobacionCargo[].class);
                        for (SolicitudViaticoComprobacionCargo _solicitudViaticoComprobacionCargo : solicitudViaticoComprobacionCargo){
                            if(idFatherComprobacionPasaje.equals(_solicitudViaticoComprobacionCargo.getSolicitudViaticoComprobacionPasajeId())){
                                if(_solicitudViaticoComprobacionCargo.getId() == null){
                                    _solicitudViaticoComprobacionCargo.setRegistradoPorId(idUsuario);
                                    _solicitudViaticoComprobacionCargo.setFechaRegistro(new Timestamp(new Date().getTime()));
                                }else{
                                    // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                                    if (_solicitudViaticoComprobacionCargo.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                                        if(!sistemaService.permiteEliminarRegistro(_solicitudViaticoComprobacionCargo.getId(), "tblSolicitudViaticoComprobacionCargo"))
                                            throw new InformeComprobacionException(InformeComprobacionException.STATUS_ERROR_COMPROBACION_CARGO_RELACION);
                                    }

                                    _solicitudViaticoComprobacionCargo.setModificadoPorId(idUsuario);
                                    _solicitudViaticoComprobacionCargo.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                                }
                                _solicitudViaticoComprobacionCargo.setSolicitudViaticoComprobacionPasajeId(_solicitudViaticoComprobacionPasaje.getId());

                                _solicitudViaticoComprobacionCargo = (SolicitudViaticoComprobacionCargo) solicitudViaticoComprobacionCargoService.guardar(_solicitudViaticoComprobacionCargo);
                            }
                        }
                    }
                }
            }
        }
        return new JsonResponse("");
    }

    @RequestMapping(value = "/buscacompartida", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse buscaCompartida(@RequestBody SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String, Object> datosFicha = new HashMap<>();

        // Compartida: Solicitud Viatico Comprobacion Detalle
        List<SolicitudViaticoComprobacionDetalle> solicitudViaticoComprobacionDetalleList = this.solicitudViaticoComprobacionDetalleService.buscaSolicitudViaticoComprobacionDetalleCompartida(solicitudViaticoComprobacionDetalle.getTipoComprobanteId(), solicitudViaticoComprobacionDetalle.getUuid());
        datosFicha.put("solicitudViaticoComprobacionDetalle", solicitudViaticoComprobacionDetalleList);

        // Compartida: Solicitud Viatico Comprobacion Detalle Impuesto
        List<SolicitudViaticoComprobacionDetalleImpuesto> solicitudViaticoComprobacionDetalleImpuestoList = new ArrayList<>();
        for (SolicitudViaticoComprobacionDetalle _solicitudViaticoComprobacionDetalle : solicitudViaticoComprobacionDetalleList){
            List<SolicitudViaticoComprobacionDetalleImpuesto> _solicitudViaticoComprobacionDetalleImpuestoList = solicitudViaticoComprobacionDetalleImpuestoService.buscaSolicitudViaticoComprobacionDetalleId(_solicitudViaticoComprobacionDetalle.getId());
            for (SolicitudViaticoComprobacionDetalleImpuesto solicitudViaticoComprobacionDetalleImpuesto : _solicitudViaticoComprobacionDetalleImpuestoList){
                solicitudViaticoComprobacionDetalleImpuestoList.add(solicitudViaticoComprobacionDetalleImpuesto);
            }
        }
        datosFicha.put("solicitudViaticoComprobacionDetalleImpuesto", solicitudViaticoComprobacionDetalleImpuestoList);

        return new JsonResponse(datosFicha);
    }

    @RequestMapping(value = "/listadoproveedoresandpaises", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getListadoProveedoresAndPais(@RequestHeader HttpHeaders headers) throws Exception {
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        HashMap<String,HashMap<String, Object>> proveedores = new HashMap<>();

        //Listado de Proveedores
        proveedores.put("proveedores",proveedorService.getListadoProveedores(ejercicio));

        // Listado de Paises
        proveedores.put("paises", paisService.getListadoPaises("",ejercicio));
        return new JsonResponse(proveedores);
    }

    /*@RequestMapping(value = "/listadoproveedoresandpaisesxxx", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getListadoProveedoresAndPaisxxx(@RequestBody String json,@RequestHeader HttpHeaders headers) throws Exception {
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        HashMap<String,HashMap<String, Object>> proveedores = new HashMap<>();

        //Listado de Proveedores
        proveedores.put("proveedores",proveedorService.getListadoProveedores(ejercicio));

        // Listado de Paises
        proveedores.put("paises", paisService.getListadoPaises("",ejercicio));
        return new JsonResponse(proveedores);

        //Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        //HashMap<String,HashMap<String, Object>> proveedores = new HashMap<>();

        // Listado de Proveedores
       // proveedores.put("proveedores",proveedorService.getListadoProveedores(ejercicio);
        // Listado de Paises
        //proveedores.put("paises", paisService.getListadoPaises(json,ejercicio));
        //return new JsonResponse(proveedores);
    }
*/
    @RequestMapping(value = "/listadoproveedores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getListadoProveedores(@RequestHeader HttpHeaders headers) throws Exception {
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        HashMap<String, Object> proveedores = proveedorService.getListadoProveedores(ejercicio);
        return new JsonResponse(proveedores);
    }



    @RequestMapping(value = "/searchProveedor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse searchProveedor(@RequestBody String json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String,HashMap<String, Object>> response = new HashMap<>();
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        // Search Proveedor
        HashMap<String, Object> proveedor = proveedorService.searchProveedor(json);
        response.put("proveedor", proveedor);

        if((Integer)proveedor.get("status") != 30200){
            // Listado de Paises
            response.put("paises", paisService.getListadoPaises(json,ejercicio));
        }
        return new JsonResponse(response);
    }

    @RequestMapping(value = "/registerProveedor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse registerProveedor(@RequestBody String json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        HashMap<String,HashMap<String, Object>> response = new HashMap<>();
        // Register Proveedor
        HashMap<String, Object> proveedor = proveedorService.registerProveedor(json);
        response.put("proveedor", proveedor);

        if((Integer)proveedor.get("status") != 30200){
            // Listado de Paises
            response.put("paises", paisService.getListadoPaises(json,ejercicio));
        }

        return new JsonResponse(response);
    }

    @RequestMapping(value = "/listadocuentacontablerm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse listadoCuentaConTableRM(@RequestHeader HttpHeaders headers) throws Exception {
        Integer ejercicio= Integer.parseInt(headers.get("ejercicio").get(0));
        HashMap<String, Object> proveedor = cuentaGastoService.listadoCuentaConTableRM(ejercicio);
        return new JsonResponse(proveedor);
    }
}
