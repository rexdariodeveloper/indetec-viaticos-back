package com.pixvs.viaticos.controllers;

import com.google.gson.JsonArray;
import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.exceptions.RevisionException;
import com.pixvs.viaticos.exceptions.SolicitudViaticoException;
import com.pixvs.viaticos.model.viaticos.Archivo;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.service.ArchivoService;
import com.pixvs.viaticos.service.ListadoCMMService;
import com.pixvs.viaticos.service.SolicitudViaticoService;
import com.pixvs.viaticos.util.Archivos;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/archivos")
public class ArchivoController extends GenericController {

    private String MULTIPART_FILE_NAME = "file";

    public String getExMessage() {
        return exMessage;
    }

    private String exMessage="";

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Autowired
    private SolicitudViaticoService solicitudViaticoService;

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

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/subir", method = RequestMethod.POST)
    public JsonResponse subirArchivo(HttpServletRequest request) throws Exception {
        StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile(MULTIPART_FILE_NAME);

        String idAutonumerico = archivoService.getSiguienteAutonumericoTemporal();
        String nombreArchivoTmp = idAutonumerico + "_" + multipartFile.getOriginalFilename();
        String ruta = archivoService.getRutaTmpCMM() + File.separator + nombreArchivoTmp;

        Archivos.createDir(archivoService.getRutaTmpCMM());
        Archivos.uploadArchivo(ruta, multipartFile);

        return new JsonResponse(nombreArchivoTmp, "Archivo Subido", "Archivos");
    }

    @RequestMapping(value = "/descargar/raiz", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void descargarArchivoRaiz(@RequestBody HashMap<String,String> body, HttpServletRequest request, HttpServletResponse response) {
        String archivoId = body.get("archivoId");
        try {
            InfoArchivoProjection archivo = archivoService.buscarInfoProjection(archivoId);

            File file = new File(archivoService.getRutaRaizCMM() + File.separator + archivo.getRutaFisica());
            InputStream is = new FileInputStream(file);
            response.setHeader("Content-disposition", "attachment; filename=" + archivo.getNombreOriginal());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {}

    }

    @RequestMapping(value = "/descargar/tmp", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void descargarArchivoTmp(@RequestBody String nombreArchivo, HttpServletRequest request, HttpServletResponse response) {
        String nombreOriginal = nombreArchivo.substring(nombreArchivo.indexOf("_")+1);
        try {
            File file = new File(archivoService.getRutaTmpCMM() + File.separator + nombreArchivo);
            InputStream is = new FileInputStream(file);
            response.setHeader("Content-disposition", "attachment; filename=" + nombreOriginal);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (Exception e) {}
    }

    public String getJasperReport(String nombreReporte, HashMap<String, Object> params, Connection connection) {
        return getJasperReport(nombreReporte, null, params, connection, null);
    }

    public String getJasperReport(String nombreReporte, String nombreArchivo, HashMap<String, Object> params, Connection connection) {
        return getJasperReport(nombreReporte, nombreArchivo, params, connection, null);
    }

    public String getJasperReport(String nombreReporte, String nombreArchivo, HashMap<String, Object> params, Connection connection, JsonArray ids) {
        try {
            String path = "/jasper/reports/" + nombreReporte + ".jasper";
            InputStream jasperStream = this.getClass().getResourceAsStream(path);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            HashMap<String, String> jsonReport = new HashMap<>();

            if(ids != null) {
                jsonReport = archivoService.getJsonReport(ids);
            }

            nombreArchivo = (nombreArchivo != null ? nombreArchivo : nombreReporte + "_" + generalScalarDao.newId()) + ".pdf";
            OutputStream outputStream = new FileOutputStream(new File((ids != null ? jsonReport.get("pathReportes") : archivoService.getRutaTmpCMM()) + File.separator + nombreArchivo));
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            outputStream.close();

            return nombreArchivo;

       } catch (Exception ex) {
            exMessage=ex.getMessage();
            return null;
        }
    }


}