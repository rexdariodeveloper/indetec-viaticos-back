package com.pixvs.viaticos.controllers;

import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ConfiguracionEnteException;
import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.util.Archivos;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    ConfiguracionEnteService configuracionEnteService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ArchivoService archivoService;

    @RequestMapping(value = "/getLogo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getLogo() throws Exception {
        HashMap logo  = new HashMap();
        Collection ente = configuracionEnteService.buscarTodos();
        if(ente.size() == 0){
            logo.put("logo",null);
        }
        else {
            ConfiguracionEnte newEnte = (ConfiguracionEnte) ente.toArray()[0];
            logo.put("logo",archivoService.buscarArchivoProjectionById(newEnte.getFotografia()));
        }
        return new JsonResponse(logo,"Registro guardado.");
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

}
