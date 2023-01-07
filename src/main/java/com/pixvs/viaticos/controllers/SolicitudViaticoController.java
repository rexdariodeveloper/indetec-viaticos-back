package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.exceptions.GenericException;
import com.pixvs.viaticos.exceptions.RevisionException;
import com.pixvs.viaticos.exceptions.SolicitudViaticoException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.AlertaDefinicion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.*;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoSolicitudProjection;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.ReporteTransparenciaArchivosSolicitudViaticoProjection;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.ReporteTransparenciaPartidaSolicitudViaticoProjection;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.ResumenSolicitudViaticoProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.*;
import com.pixvs.viaticos.util.Archivos;
import com.pixvs.viaticos.util.Email;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.pixvs.viaticos.model.viaticos.mapeos.MenuPrincipal.*;

@RestController
@RequestMapping("/api/solicitudviatico")
public class SolicitudViaticoController extends GenericController {

    @Autowired
    SolicitudViaticoService solicitudViaticoService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ListadoCMMService listadoCMMService;

    @Autowired
    PaisesService paisesService;

    @Autowired
    EstadosService estadosService;

    @Autowired
    CiudadesService ciudadesService;

    @Autowired
    ProgramaGobiernoService programaGobiernoService;

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    DependenciaService dependenciaService;

    @Autowired
    FuenteFinanciamientoService fuenteFinanciamientoService;

    @Autowired
    AlertaService alertaService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    ConfiguracionEnteService configuracionEnteService;

    @Autowired
    Email emailService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ArchivoController archivoController;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private AsignacionViaticoService asignacionViaticoService;

    @Autowired
    private AsignacionPasajeService asignacionPasajeService;

    @Autowired
    private PermisoAccesoService permisoAccesoService;

    @Autowired
    private EjercicioService ejercicioService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Integer empleadoId = 0;
        boolean permisoEmpleados = false;
        ComboEmpleadoSolicitudProjection empleado = null;
        HashMap<Integer, InfoArchivoProjection> fotografias = new HashMap<>();

        HashMap datosFicha = new HashMap();

        //Si se envio un Id de Empleado, buscar el registro
        if (json != null && json.containsKey("empleadoId")) {
            empleadoId = (Integer) json.get("empleadoId");
        }

        if (json != null && json.containsKey("solicitudId")) {
            Integer solicitudId = new Integer(json.get("solicitudId").toString());
            SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudId);

            if (solicitud == null) {
                throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_ERROR_SOLICITUD_NO_ENCONTRADA);
            }

            Usuario usuario = (Usuario) usuarioService.buscarPorId(usuarioId);

            boolean usuarioSolicitante = solicitud.getCreadoPorId().equals(usuarioId)
                    || solicitud.getEmpleadoId().equals(usuario.getEmpleadoId());

            boolean permisoVerTerceros = permisoAccesoService.isPermisoVerTerceros(usuarioId, solicitud.getAreaAdscripcionId());

            if (!usuarioSolicitante && !permisoVerTerceros) {
                throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_FORBIDEN);
            }

            empleadoId = solicitud.getEmpleadoId();

            datosFicha.put("solicitud", solicitud);
            datosFicha.put("historial", solicitudViaticoService.getHistorialSolicitudViatico(solicitudId));
            datosFicha.put("datosPrograma", getDatosPrograma(solicitud.getEjercicio(), headers));
            datosFicha.put("soloLectura", !usuarioSolicitante && permisoVerTerceros);

            Asignacion asignacion = asignacionService.buscaPorSolicitudViaticoId(solicitudId);

            if (asignacion != null) {
                datosFicha.put("asignacion", asignacion);
                datosFicha.put("asignacionViatico", asignacionViaticoService.searchAsignacionId(asignacion.getId()));
                datosFicha.put("asignacionPasaje", asignacionPasajeService.searchAsignacionId(asignacion.getId()));
            }
        } else {
            permisoEmpleados = permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.CREAR_SOLICITUDES_TERCEROS, usuarioId) != null;

            if (permisoEmpleados) {
                List<ComboEmpleadoSolicitudProjection> empleados = empleadoService.getComboEmpleadoSolicitudProjection(null);

                for (ComboEmpleadoSolicitudProjection empleadoTmp : empleados) {
                    if (empleadoTmp.getId().equals(empleadoId)) {
                        empleado = empleadoTmp;
                    }

                    //Buscar la fotografía del empleado
                    if (empleadoTmp.getFotografia() != null) {
                        fotografias.put(empleadoTmp.getId(), archivoService.buscarArchivoProjectionById(empleadoTmp.getFotografia()));
                    }
                }
                datosFicha.put("empleados", empleados);
            }
        }

        if (empleado == null) {
            empleado = (empleadoService.getComboEmpleadoSolicitudProjection(empleadoId)).get(0);

            //Buscar la fotografía del empleado
            if (empleado.getFotografia() != null) {
                fotografias.put(empleado.getId(), archivoService.buscarArchivoProjectionById(empleado.getFotografia()));
            }
        }

        datosFicha.put("empleado", empleado);
        datosFicha.put("tipoviaje", listadoCMMService.getListadoCMMComboByNombreAndActivo("TipoViaje"));
        datosFicha.put("tiporepresentacion", listadoCMMService.getListadoCMMComboByNombreAndActivo("TipoRepresentacion"));
        datosFicha.put("paises", paisesService.getComboPaises());
        datosFicha.put("estados", estadosService.getComboEstados());
        datosFicha.put("ciudades", ciudadesService.getComboCiudades());
        datosFicha.put("ejercicios", ejercicioService.buscarTodos());
        datosFicha.put("ente", configuracionEnteService.getSolicitudConfiguracionEnte());
        datosFicha.put("gastoRepresentacion", listadoCMMService.getListadoCMMComboByNombreAndActivo("GastoRepresentacion"));
        datosFicha.put("permisoEmpleados", permisoEmpleados);
        datosFicha.put("fotografias", fotografias);

        return new JsonResponse(datosFicha, "Solicitud Viaticos");
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(solicitudViaticoService.getListadoSolicitudesPorUsuarioId(JwtFilter.getUsuarioId(headers)), "Solicitudes");
    }

    @Override
    public JsonResponse buscaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody SolicitudViatico solicitud, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        if (solicitud.getId() == null) {
            solicitud.setNumeroSolicitud(solicitudViaticoService.getSiguienteAutonumerico(fecha.getYear()));

            if (solicitud.getNumeroSolicitud() == null) {
                throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_SIN_AUTONUMERICO);
            }

            solicitud.setFechaCreacion(fecha);
            solicitud.setCreadoPorId(usuarioId);
            solicitud.setEstatusId(EstatusSolicitudViatico.ACTIVA);
        } else {
            solicitud.setFechaUltimaModificacion(fecha);
            solicitud.setModificadoPorId(usuarioId);
        }

        String folio = solicitudViaticoService.existeRegistro(solicitud.getId(), solicitud.getEmpleadoId(), solicitud.getFechaSalida(), solicitud.getFechaRegreso());
        if (folio != null && !folio.isEmpty()) {
            throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_ERROR_RANGO_FECHAS, folio);
        }

        solicitudViaticoService.guardar(solicitud);

        return new JsonResponse(true,"Cambios guardados.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());
        Integer solicitudId = (Integer) json.get("id");

        SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(solicitudId);
        solicitud.setEstatusId(EstatusSolicitudViatico.BORRADA);
        solicitud.setFechaUltimaModificacion(fecha);
        solicitud.setModificadoPorId(usuarioId);
        solicitudViaticoService.guardar(solicitud);

        return new JsonResponse(true, "Registro borrado.");
    }

    @RequestMapping(value = "/datosprograma", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getDatosPrograma(@RequestBody int ejercicio, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap datosPrograma = new HashMap();

        datosPrograma.put("programas", programaGobiernoService.buscarTodos(ejercicio));
        datosPrograma.put("proyectos", proyectoService.buscarTodos(ejercicio));
        datosPrograma.put("dependencias", dependenciaService.buscarTodos(ejercicio));
        datosPrograma.put("fuentesFinanciamiento", fuenteFinanciamientoService.buscarTodos(ejercicio));

        return new JsonResponse(datosPrograma);
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/enviar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse enviarAutorizar(@RequestBody int solicitudId, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        ResumenSolicitudViaticoProjection resumenSolicitud = solicitudViaticoService.buscarSolicitudViaticoResumenPorId(solicitudId);

        String alertaId = alertaService.iniciarAlerta(AlertaDefinicion.SOLICITUD_VIATICOS_AUTORIZACION, solicitudId, resumenSolicitud.getNumeroSolicitud(), "Solicitud: " + resumenSolicitud.getNumeroSolicitud() + ", a Nombre de: " + resumenSolicitud.getSolicitante(), usuarioId);

        emailService.enviaCorreo(alertaId);

        return new JsonResponse(true, "Alerta iniciada.");
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/cancelar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse enviarCancelar(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());
        Integer solicitudId = (Integer) json.get("id");

        ResumenSolicitudViaticoProjection resumenSolicitud = solicitudViaticoService.buscarSolicitudViaticoResumenPorId(solicitudId);

        alertaService.cancelarAlertas(solicitudId, AlertaDefinicion.SOLICITUD_VIATICOS_AUTORIZACION);
//        String correos = alertaService.iniciarAlerta(2, solicitudId, resumenSolicitud.getNumeroSolicitud(), "Solicitud Cancelada: " + resumenSolicitud.getNumeroSolicitud() + ", a Nombre de: " + resumenSolicitud.getSolicitante(), usuarioId);
        cambiarEstatusSolicitud(solicitudId, EstatusSolicitudViatico.CANCELADA, null, usuarioId);

        return new JsonResponse(true, "Notificación Cancelada.");
    }

    @RequestMapping(value = "/resumen/detalle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getSolicitudResumenDetalle(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String, Object> datos = new HashMap<>();

        int usuarioId = JwtFilter.getUsuarioId(headers);

        Integer id = new Integer(json.get("id").toString());
        boolean formularioCompleto = (boolean) json.get("formularioCompleto");

        ResumenSolicitudViaticoProjection solicitud = solicitudViaticoService.buscarSolicitudViaticoResumenPorId(id);

        if (formularioCompleto) {
            List<List> tblPermiso = alertaService.getPermisoAutorizacionAlerta(SOLICITUDES, solicitud.getId(), usuarioId);

            Integer alertaId = tblPermiso.get(0).get(0) != null ? Integer.valueOf(tblPermiso.get(0).get(0).toString()) : null;
            Boolean mostrarAcciones = tblPermiso.get(0).get(1) != null ? Boolean.valueOf(tblPermiso.get(0).get(1).toString()) : null;

            if (mostrarAcciones == null ) {
                throw new SolicitudViaticoException(SolicitudViaticoException.STATUS_FORBIDEN);
            }

            datos.put("mostrarAcciones", mostrarAcciones && solicitud.getEstatusId() == EstatusSolicitudViatico.PROCESO_AUTORIZACION);
            datos.put("alertaId", alertaId);
        }
        datos.put("historial", solicitudViaticoService.getHistorialSolicitudViatico(solicitud.getId()));

        Empleado empleado = (Empleado) empleadoService.buscarPorId(solicitud.getEmpleadoId());
        if(empleado.getFotografia() != null) {
            empleado.setArchivoFotografia(archivoService.buscarArchivoProjectionById(empleado.getFotografia()));
        }

        datos.put("solicitud", solicitud);
        datos.put("empleado", empleado);

        return new JsonResponse(datos);
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/resumen/detalle/estatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse cambiarEstatusSolicitud(Integer id, int estatusId, String motivo, int usuarioId) throws Exception {
        Timestamp fecha = new Timestamp(new Date().getTime());

        SolicitudViatico solicitud = (SolicitudViatico) solicitudViaticoService.buscarPorId(id);
        solicitud.setEstatusId(estatusId);

        String mensaje = "";

        switch (estatusId) {
            case EstatusSolicitudViatico.AUTORIZADA:
                mensaje = "Autorizada.";
                solicitud.setAutorizadoPorId(usuarioId);
                solicitud.setFechaAutorizacion(fecha);
                break;
            case EstatusSolicitudViatico.RECHAZADA:
                mensaje = "Rechazada.";
                break;
            case EstatusSolicitudViatico.EN_REVISION:
                mensaje = "envíada a Revisión.";
                break;
            case EstatusSolicitudViatico.CANCELADA:
                mensaje = "Cancelada.";
                break;
        }

        solicitud.setFechaUltimaModificacion(fecha);
        solicitud.setModificadoPorId(usuarioId);
        solicitudViaticoService.guardar(solicitud);

        return new JsonResponse(true, "La solicitud ha sido " + mensaje);
    }

    @RequestMapping(value = "/imprimir", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void imprimirSolicitud(@RequestBody Integer solicitudId, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResumenSolicitudViaticoProjection resumenSolicitud = solicitudViaticoService.buscarSolicitudViaticoResumenPorId(solicitudId);

        HashMap<String, Object> params = new HashMap<>();
        params.put("SolicitudId", solicitudId);

        String nombreArchivo = archivoController.getJasperReport("SolicitudViatico", "SolicitudViatico_" + resumenSolicitud.getNumeroSolicitud(), params, dataSource.getConnection());

        if (nombreArchivo == null) {
            throw new RevisionException(RevisionException.STATUS_ERROR_REPORT);
        }

        archivoController.descargarArchivoTmp(nombreArchivo, request, response);

        Archivos.deleteArchivo(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
    }

    @RequestMapping(value = "/reporte_transparencia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getReporteTransparencia(@RequestBody Map<String, Timestamp> fechas, @RequestHeader HttpHeaders headers) throws Exception {
        Timestamp fechaInicio = fechas.get("fechaInicio");
        Timestamp fechaFin = fechas.get("fechaFin");

        return new JsonResponse(solicitudViaticoService.getReporteTransparencia(fechaInicio, fechaFin), "Reporte Transparencia");
    }

    @RequestMapping(value = "/reporte_transparencia/descargar", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getReporteTransparencia(@RequestBody Map<String, Object> json, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fechaInicio = getFechaFormato(json.get("fechaInicio"));
        String fechaFin = getFechaFormato(json.get("fechaFin"));
        String fechaValidacion = getFechaFormato(json.get("fechaValidacion"));
        String fechaActualizacion = getFechaFormato(json.get("fechaActualizacion"));
        List<LinkedHashMap> rows = (List<LinkedHashMap>) json.get("rows");
        List<ComboListadoCMMProjection> tiposEmpleado = (List<ComboListadoCMMProjection>) listadoCMMService.buscarPorCodigo("TipoEmpleado");
        List<ComboListadoCMMProjection> tiposGasto = (List<ComboListadoCMMProjection>) listadoCMMService.buscarPorCodigo("GastoRepresentacion");
        List<ComboListadoCMMProjection> tiposViaje = (List<ComboListadoCMMProjection>) listadoCMMService.buscarPorCodigo("TipoViaje");

        HSSFWorkbook workbook = new HSSFWorkbook(this.getClass().getResourceAsStream("/xls/ReporteTransparencia.xls"));

        Font font = workbook.createFont();
        font.setUnderline(Font.U_SINGLE);
        font.setColor(HSSFColor.BLUE.index);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        Sheet informacion = workbook.getSheetAt(0);
        Sheet hidden1 = workbook.getSheetAt(1);
        Sheet hidden2 = workbook.getSheetAt(2);
        Sheet hidden3 = workbook.getSheetAt(3);
        Sheet tabla333806 = workbook.getSheetAt(4);
        Sheet tabla333807 = workbook.getSheetAt(5);

        int contadorPartidas = 3;
        int contadorArchivos = 3;
        ObjectMapper mapper = new ObjectMapper();

        ReporteTransparencia registro = mapper.convertValue(rows.get(0), ReporteTransparencia.class);
        Row row = informacion.getRow(0);
        row.createCell(1).setCellValue(registro.getNombreEnte());

        for (int i = 0; i < rows.size(); i++) {
            registro = mapper.convertValue(rows.get(i), ReporteTransparencia.class);
            row = informacion.createRow(6 + i);
            row.createCell(0).setCellValue(registro.getNumeroSolicitud());
            row.createCell(1).setCellValue(registro.getEjercicio().toString());
            row.createCell(2).setCellValue(fechaInicio);
            row.createCell(3).setCellValue(fechaFin);
            row.createCell(4).setCellValue(registro.getTipoIntegrante());
            row.createCell(5).setCellValue(registro.getClavePuesto());
            row.createCell(6).setCellValue(registro.getPuesto());
            row.createCell(7).setCellValue(registro.getCargo());
            row.createCell(8).setCellValue(registro.getAreaAdscripcion());
            row.createCell(9).setCellValue(registro.getNombre());
            row.createCell(10).setCellValue(registro.getPrimerApellido());
            row.createCell(11).setCellValue(registro.getSegundoApellido());
            row.createCell(12).setCellValue(registro.getTipoGasto());
            row.createCell(13).setCellValue(registro.getDescripcionComision());
            row.createCell(14).setCellValue(registro.getTipoViaje());
            row.createCell(15).setCellValue(registro.getAcompanantes());
            row.createCell(16).setCellValue(registro.getMontoAcompanantes());
            row.createCell(17).setCellValue(registro.getPaisOrigen());
            row.createCell(18).setCellValue(registro.getEstadoOrigen());
            row.createCell(19).setCellValue(registro.getCiudadOrigen());
            row.createCell(20).setCellValue(registro.getPaisDestino());
            row.createCell(21).setCellValue(registro.getEstadoDestino());
            row.createCell(22).setCellValue(registro.getCiudadDestino());
            row.createCell(23).setCellValue(registro.getMotivo());
            row.createCell(24).setCellValue(registro.getFechaSalida());
            row.createCell(25).setCellValue(registro.getFechaRegreso());
            row.createCell(26).setCellValue(registro.getNumeroSolicitud());
            row.createCell(27).setCellValue(registro.getTotalErogado());
            row.createCell(28).setCellValue(registro.getNoErogado());
            row.createCell(29).setCellValue(registro.getFechaFinalizacionFormato());

            Cell cell = row.createCell(30);
            cell.setCellValue(registro.getLinkInforme());

            Hyperlink link = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_FILE);
            link.setAddress(registro.getLinkInforme());
            cell.setHyperlink(link);
            cell.setCellStyle(style);

            row.createCell(31).setCellValue(registro.getNumeroSolicitud());

            cell = row.createCell(32);
            cell.setCellValue(registro.getLinkNormativa());

            link = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_FILE);
            link.setAddress(registro.getLinkNormativa());
            cell.setHyperlink(link);
            cell.setCellStyle(style);

            row.createCell(33).setCellValue(registro.getAreaResponsableTransparencia());
            row.createCell(34).setCellValue(fechaValidacion);
            row.createCell(35).setCellValue(fechaActualizacion);
            row.createCell(36).setCellValue(registro.getNotas());

            List<ReporteTransparenciaPartidaSolicitudViaticoProjection> partidas = solicitudViaticoService.getReporteTransparenciaPartidas(registro.getId());

            for (ReporteTransparenciaPartidaSolicitudViaticoProjection partida : partidas) {
                row = tabla333806.createRow(contadorPartidas);
                row.createCell(0).setCellValue(partida.getNumeroSolicitud());
                row.createCell(1).setCellValue(partida.getClave());
                row.createCell(2).setCellValue(partida.getDenominacion());
                row.createCell(3).setCellValue(new Double(partida.getImporte()));
                contadorPartidas++;
            }

            List<ReporteTransparenciaArchivosSolicitudViaticoProjection> archivos = solicitudViaticoService.getReporteTransparenciaArchivos(registro.getId());

            for (ReporteTransparenciaArchivosSolicitudViaticoProjection archivo : archivos) {
                row = tabla333807.createRow(contadorArchivos);
                row.createCell(0).setCellValue(archivo.getNumeroSolicitud());

                cell = row.createCell(1);
                cell.setCellValue(archivo.getHipervinculo());

                if (archivo.isExportado()) {
                    link = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_FILE);
                    link.setAddress(archivo.getHipervinculo());
                    cell.setHyperlink(link);
                    cell.setCellStyle(style);
                }

                contadorArchivos++;
            }
        }

        for (int i = 0; i < tiposEmpleado.size(); i++) {
            hidden1.createRow(i).createCell(0).setCellValue(tiposEmpleado.get(i).getValor());
        }

        for (int i = 0; i < tiposGasto.size(); i++) {
            hidden2.createRow(i).createCell(0).setCellValue(tiposGasto.get(i).getValor());
        }

        for (int i = 0; i < tiposViaje.size(); i++) {
            hidden3.createRow(i).createCell(0).setCellValue(tiposViaje.get(i).getValor());
        }

        String nombreArchivo = "ReporteTransparencia_" + generalScalarDao.newId() + ".xls";
        FileOutputStream outputStream = new FileOutputStream(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        archivoController.descargarArchivoTmp(nombreArchivo, request, response);
        Archivos.deleteArchivo(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
    }

    @RequestMapping(value = "/reporte_transparencia_concentrado/descargar", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getReporteTransparenciaConcentrado(@RequestBody String solicitudIds, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<List> rows = solicitudViaticoService.getReporteTransparenciaConcentrado(solicitudIds);

        InputStream file = this.getClass().getResourceAsStream("/xls/ReporteTransparenciaConcentrado.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(file);
        Sheet sheet1 = workbook.getSheetAt(0);

        for (int i = 0; i < rows.size(); i++) {
            List registro = rows.get(i);
            Row row = sheet1.createRow(1 + i);
            for (int j = 0; j < registro.size(); j++) {
                String val = registro.get(j) != null ? registro.get(j).toString() : "";

                if (!val.isEmpty() && registro.get(j).getClass().equals(BigDecimal.class)) {
                    row.createCell(j).setCellValue(new Double(val));
                } else {
                    row.createCell(j).setCellValue(val);
                }
            }
        }

        String nombreArchivo = "ReporteTransparenciaConcentrado_" + generalScalarDao.newId() + ".xls";
        FileOutputStream outputStream = new FileOutputStream(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        archivoController.descargarArchivoTmp(nombreArchivo, request, response);
        Archivos.deleteArchivo(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
    }

    private String getFechaFormato(Object fecha) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(new ObjectMapper().convertValue(fecha, Timestamp.class));
    }
}
