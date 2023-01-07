package com.pixvs.viaticos.service;

import com.google.gson.JsonArray;
import com.pixvs.viaticos.dao.viaticos.ArchivoDao;
import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.dao.viaticos.ListadoCMMDao;
import com.pixvs.viaticos.model.viaticos.Archivo;
import com.pixvs.viaticos.model.viaticos.DatosArchivoServidorFTP;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.util.Archivos;
import com.pixvs.viaticos.util.ServidorFTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ArchivoService extends GenericService {

    @Autowired
    private ArchivoDao archivoDao;

    @Autowired
    private ListadoCMMDao listadoCMMDao;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Autowired
    private ServidorFTP servidorFTPService;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return archivoDao.save((Archivo) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {
    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return null;
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return null;
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public String getSiguienteAutonumericoTemporal() {
        return generalScalarDao.fetchSigAutonumericoArchivo("");
    }

    public InfoArchivoProjection buscarInfoProjection(String id) {
        return archivoDao.findInfoProjectionById(id);
    }

    public InfoArchivoProjection buscarInfoProjectionTop1(Integer referenciaId, Integer origenArchivoId, Integer tipoArchivoId) {
        List<InfoArchivoProjection> listadoArchivos = archivoDao.findAllInfoProjectionByReferenciaIdAndOrigenArchivoIdAndTipoArchivoIdAndVigenteIsTrue(referenciaId, origenArchivoId, tipoArchivoId);
        if (listadoArchivos.size() > 0) {
            return listadoArchivos.get(0);
        }
        return null;
    }

    public List<InfoArchivoProjection> buscarListadoInfoProjection(Integer referenciaId, Integer origenArchivoId, Integer tipoArchivoId) {
        return archivoDao.findAllInfoProjectionByReferenciaIdAndOrigenArchivoIdAndTipoArchivoIdAndVigenteIsTrue(referenciaId, origenArchivoId, tipoArchivoId);
    }

    public InfoArchivoProjection buscarArchivoProjectionById(String id) {
        return archivoDao.findProjectionArchivoById(id);
    }

    public Archivo buscarArchivoById(String id) {
        return archivoDao.findArchivoById(id);
    }

    public String getRutaArchivo(Integer origen, JsonArray ids) {
        return archivoDao.getRutaArchivo(origen, ids.toString()).replace("/", File.separator);
    }
    public Archivo getLogoRutaActivo() {
        return archivoDao.getRutaLogoActivo();
    }

    public String getRutaTmpCMM() {
        return listadoCMMDao.findProjectionComboById(ListadoCMM.RutaTemporalArchivo).getValor().replace("/", File.separator);
    }

    public String getRutaRaizCMM() {
        return listadoCMMDao.findProjectionComboById(ListadoCMM.RutaRaizArchivo).getValor().replace("/", File.separator);
    }

    public String guardarArchivo(Integer usuarioId, String archivoAnteriorId, String nombreArchivoTmp, int origenId, JsonArray ids, int referenciaId, int tipoArchivoId) throws Exception {
        String rutaGuardar = getRutaArchivo(origenId, ids);
        String rutaFisicaArchivo = rutaGuardar + File.separator + nombreArchivoTmp;
        if (archivoAnteriorId != null) {
            Archivo archivoBorrar = buscarArchivoById(archivoAnteriorId);
            archivoBorrar.setVigente(false);
            guardar(archivoBorrar);
            Archivos.deleteArchivo(getRutaRaizCMM() + File.separator + archivoBorrar.getRutaFisica());
        }

        if (nombreArchivoTmp != null) {
            String nombreOriginalArchivo = nombreArchivoTmp.replaceAll("^[^_]+_", "");
            String nombreFisicoArchivo = nombreArchivoTmp.substring(0, nombreArchivoTmp.length() - nombreOriginalArchivo.length());
            Archivo archivo = new Archivo();
            archivo.setNombreOriginal(nombreOriginalArchivo);
            archivo.setNombreFisico(nombreFisicoArchivo);
            archivo.setReferenciaId(referenciaId);
            archivo.setOrigenArchivoId(origenId);
            archivo.setRutaFisica(rutaFisicaArchivo);
            archivo.setTipoArchivoId(tipoArchivoId);
            archivo.setVigente(true);
            archivo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            archivo.setCreadoPorId(usuarioId);
            String idArchivo = archivoDao.save(archivo).getId();

            Archivos.createDir(getRutaRaizCMM() + File.separator + rutaGuardar);
            Archivos.moveArchivo(
                    getRutaTmpCMM() + File.separator + nombreArchivoTmp,
                    getRutaRaizCMM() + File.separator + rutaFisicaArchivo
            );

            return idArchivo;
        }

        return null;
    }

    public List<Archivo> searchReferenciaIdAndOrigenArchivoId(int referenciaId, int origenArchivoId) throws Exception {
        return this.archivoDao.findAllByReferenciaIdAndOrigenArchivoIdAndVigenteIsTrue(referenciaId, origenArchivoId);
    }

    public void guardaComprobanteSolicitud(ServidorFTPConfiguracionEnteProjection properties, String directorio, Map<String, Object> file, int usuarioId, int referenciaId, JsonArray ids) throws Exception {
        Timestamp fecha = new Timestamp(new Date().getTime());
        String separador = properties.isDirectorioRemoto() ? "/" : File.separator;
        directorio = (properties.isDirectorioRemoto() ? properties.getDirectorioFTP() : properties.getDirectorioPublico()) + directorio.replace("\\", separador).replace("/", separador);

        if ((Boolean) file.get("vigente")) {
            int origenId = ListadoCMOA.CarpetaArchivoSolicitudes;

            String rutaTmpCMM = getRutaTmpCMM();
            String rutaRaizCMM = getRutaRaizCMM();

            String nombreArchivoTemporal = file.get("nombreArchivoTemporal").toString();
            String rutaGuardar = getRutaArchivo(origenId, ids);

            String nombreOriginalArchivo = nombreArchivoTemporal.replaceAll("^[^_]+_", "");
            String nombreFisicoArchivo = nombreArchivoTemporal.substring(0, nombreArchivoTemporal.length() - nombreOriginalArchivo.length());
            String rutaFisicaArchivo = rutaGuardar + File.separator + nombreArchivoTemporal;

            String pathOriginal = rutaTmpCMM + File.separator + nombreArchivoTemporal;
            String pathDestino = rutaRaizCMM + File.separator + rutaFisicaArchivo;

            String archivoFisico = nombreFisicoArchivo + nombreOriginalArchivo.substring(nombreOriginalArchivo.indexOf("."), nombreOriginalArchivo.length());

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

            Archivo archivo = new Archivo();
            archivo.setNombreOriginal(nombreOriginalArchivo);
            archivo.setNombreFisico(nombreFisicoArchivo);
            archivo.setReferenciaId(referenciaId);
            archivo.setOrigenArchivoId(origenId);
            archivo.setRutaFisica(rutaFisicaArchivo);
            archivo.setTipoArchivoId((Integer) file.get("tipoArchivoId"));
            archivo.setVigente(true);
            archivo.setExportado(true);
            archivo.setFechaExportado(fecha);
            archivo.setFechaCreacion(fecha);
            archivo.setCreadoPorId(usuarioId);
            archivoDao.save(archivo).getId();

            Archivos.createDir(rutaRaizCMM + File.separator + rutaGuardar);
            Archivos.moveArchivo(pathOriginal, pathDestino);
        } else {
            Archivo archivo = buscarArchivoById(file.get("id").toString());

            String archivoFisico = archivo.getNombreFisico() + archivo.getNombreOriginal().substring(archivo.getNombreOriginal().indexOf("."), archivo.getNombreOriginal().length());

            if (properties.isDirectorioRemoto()) {
                servidorFTPService.eliminarArchivos(properties, new DatosArchivoServidorFTP(
                        null,
                        directorio,
                        archivoFisico
                ));
            } else {
                Archivos.deleteArchivo(directorio + archivoFisico);
            }

            archivo.setVigente(false);
            archivo.setExportado(false);
            archivo.setFechaExportado(null);
            archivo.setFechaUltimaModificacion(fecha);
            archivo.setModificadoPorId(usuarioId);
            guardar(archivo);
        }
    }

    public HashMap<String, String> getJsonReport(JsonArray ids) throws Exception {
        // Create Json Data
        HashMap<String, String> jsonReport = new HashMap<>();

        // Get Origen Id
        int origenId = ListadoCMOA.CarpetaArchivoSolicitudes;
        jsonReport.put("origenId", (String.valueOf(origenId)));

        String rutaRaizCMM = getRutaRaizCMM();

        // Get Ruta Reportes
        String rutaGuardar = getRutaArchivo(origenId, ids);
        rutaGuardar = rutaGuardar.replace("Comprobantes", "Reportes");
        jsonReport.put("rutaGuardar", rutaGuardar);

        // Get Complete Ruta
        String pathReportes = rutaRaizCMM + File.separator + rutaGuardar;
        jsonReport.put("pathReportes", pathReportes);

        // Create Dir
        Archivos.createDir(pathReportes);

        return jsonReport;
    }
}