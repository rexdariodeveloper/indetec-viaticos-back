package com.pixvs.viaticos.controllers;

import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ConfiguracionEnteException;
import com.pixvs.viaticos.exceptions.RolException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.util.Archivos;
import com.pixvs.viaticos.util.ServidorFTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/configuracion_ente")
public class ConfiguracionEnteController extends GenericController {

    @Autowired
    ConfiguracionEnteService configuracionEnteService;

    @Autowired
    private ServidorFTP servidorFTPService;

    @Autowired
    PaisesService paisesService;

    @Autowired
    EstadosService estadosService;

    @Autowired
    CiudadesService ciudadesService;

    @Autowired
    MonedaService monedasService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    ListadoCMMService listadoCMMService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap datosFicha  = new HashMap();
        ConfiguracionEnte newEnte = new ConfiguracionEnte();

        Collection ente = configuracionEnteService.buscarTodos();
        if(!ente.isEmpty()){
            newEnte = (ConfiguracionEnte) ente.toArray()[0];
            newEnte.setArchivoFotografia(archivoService.buscarArchivoProjectionById(newEnte.getFotografia()));
            newEnte.setArchivoNormativaViaticos(archivoService.buscarArchivoProjectionById(newEnte.getNormativaViaticos()));
        }

        datosFicha.put("ente", newEnte);
        datosFicha.put("paises", paisesService.getComboPaises());
        datosFicha.put("estados", estadosService.getComboEstados());
        datosFicha.put("ciudades", ciudadesService.getComboCiudades());
        datosFicha.put("monedas", monedasService.getComboMonedas());
        datosFicha.put("protocolos", listadoCMMService.getListadoCMMComboByNombreAndActivo("ProtocoloSeguridadEmail"));
        datosFicha.put("protocolosFTP", listadoCMMService.getListadoCMMComboByNombreAndActivo("ProtocoloServidorFTP"));
        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        Collection ente = configuracionEnteService.buscarTodos();
        return new JsonResponse(configuracionEnteService.buscarTodos(), "Roles");
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody ConfiguracionEnte ente, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());
        String separador = ente.isDirectorioRemoto() ? "/" : File.separator;

        if (ente == null) {
            throw new ConfiguracionEnteException(ConfiguracionEnteException.STATUS_ENTE_EXIST);
        }

        String directorioPublico = null;

        if (ente.getDirectorioPublico() != null) {
            directorioPublico = ente.getDirectorioPublico().replace("\\", separador).replace("/", separador);
            directorioPublico = directorioPublico + (!directorioPublico.endsWith(separador) ? separador : "");
        }

        String directorioFTP = null;

        if (ente.getDirectorioFTP() != null) {
            directorioFTP = ente.getDirectorioFTP().replace("\\", separador).replace("/", separador);
            directorioFTP = directorioFTP + (!directorioFTP.endsWith(separador) ? separador : "");
            directorioFTP = (!directorioFTP.startsWith(separador) ? separador : "") + directorioFTP;
        }

        ente.setDirectorioPublico(directorioPublico);
        ente.setDirectorioFTP(directorioFTP);

        String urlPublica = ente.getUrlPublica().replace("\\", "/");

        ente.setUrlPublica(urlPublica + (!urlPublica.endsWith("/") ? "/" : ""));

        if (ente.getId() == null) {
            ente.setId(1);
            ente.setFechaCreacion(fecha);
            ente.setCreadoPorId(usuarioId);
        } else {
            ente.setFechaUltimaModificacion(fecha);
            ente.setModificadoPorId(usuarioId);
        }

        String archivoNombreTemporal = ente.getNombreArchivoTemporal();
        Boolean fotografiaEliminada = ente.getFotografiaEliminada() == null ? false : ente.getFotografiaEliminada();
        String archivoNormativaViaticosTemp = ente.getNombreNormativaViaticosArchivoTemp();

        ente = (ConfiguracionEnte) configuracionEnteService.guardar(ente);

        if (archivoNombreTemporal != null) {
            String extensionArchivo = archivoNombreTemporal.substring(archivoNombreTemporal.lastIndexOf("."));
            Integer tipoArchivo = Archivos.obtenerTipoArchivo(extensionArchivo);

            //Buscamos que no haya un archivo asociado al empleado
            InfoArchivoProjection archivoAnterior = archivoService.buscarInfoProjectionTop1(ente.getId(), ListadoCMOA.CarpetaLogoEnte, tipoArchivo);
            String archivoAnteriorId = archivoAnterior == null ? null : archivoAnterior.getId();
            JsonArray ids = new JsonArray();
            ids.add(ente.getId());
            ente.setFotografia(this.archivoService.guardarArchivo(usuarioId, archivoAnteriorId, archivoNombreTemporal, ListadoCMOA.CarpetaLogoEnte, ids, ente.getId(), tipoArchivo));
        } else if (fotografiaEliminada) {
            ente.setFotografia(null);
        }

        if (archivoNormativaViaticosTemp != null) {
            String extensionArchivo = archivoNormativaViaticosTemp.substring(archivoNormativaViaticosTemp.lastIndexOf("."));
            Integer tipoArchivo = Archivos.obtenerTipoArchivo(extensionArchivo);

            //Buscamos que no haya un archivo asociado al empleado
            InfoArchivoProjection archivoAnterior = archivoService.buscarInfoProjectionTop1(ente.getId(), ListadoCMOA.CarpetaArchivoNormativaViaticos, tipoArchivo);
            String archivoAnteriorId = archivoAnterior == null ? null : archivoAnterior.getId();
            JsonArray ids = new JsonArray();
            ids.add(ente.getId());

            String archivoId = archivoService.guardarArchivo(usuarioId, archivoAnteriorId, archivoNormativaViaticosTemp, ListadoCMOA.CarpetaArchivoNormativaViaticos, ids, ente.getId(), tipoArchivo);

            ente.setNormativaViaticos(archivoId);

            Archivo normativa = archivoService.buscarArchivoById(archivoId);

            ServidorFTPConfiguracionEnteProjection properties = configuracionEnteService.getDatosServidorFTP();

            String directorio = properties.isDirectorioRemoto() ? properties.getDirectorioFTP() : properties.getDirectorioPublico();

            directorio = directorio + "normativas/".replace("\\", separador).replace("/", separador);
            String pathOriginal = archivoService.getRutaRaizCMM() + File.separator + normativa.getRutaFisica();
            String archivoFisico = normativa.getNombreFisico() + normativa.getNombreOriginal().substring(normativa.getNombreOriginal().indexOf("."), normativa.getNombreOriginal().length());

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
        }

        configuracionEnteService.guardar(ente);

        return new JsonResponse(true, "Registro guardado.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {


        return new JsonResponse(true, "Registro borrado.");
    }
}
