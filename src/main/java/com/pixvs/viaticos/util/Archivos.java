package com.pixvs.viaticos.util;

import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.service.ArchivoService;
import com.pixvs.viaticos.service.ListadoCMMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by AngelHdz on 16/05/2019.
 */
public class Archivos {

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    public static boolean uploadArchivo(String path, MultipartFile multipartFile) throws IOException {
        File file = new File(path);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        file.setWritable(true, false);

        multipartFile.transferTo(file);

        return true;
    }

    public static boolean moveArchivo(String pathOrigen, String pathDestino) throws IOException {
        if (Files.exists(Paths.get(pathDestino))) {
            deleteArchivo(pathDestino);
        }

        Path temp = Files.move(Paths.get(pathOrigen), Paths.get(pathDestino));

        if (temp == null) {
            return false;
        }

        return true;
    }

    public static boolean copiarArchivo(String pathOrigen, String pathDestino) throws IOException {
        if (Files.exists(Paths.get(pathDestino))) {
            deleteArchivo(pathDestino);
        }

        return Files.copy(Paths.get(pathOrigen), Paths.get(pathDestino)) != null;
    }

    public static boolean deleteArchivo(String path) {
        File file = new File(path);

        if (file.exists()) {
            file.delete();
        } else {
            return false;
        }

        return true;
    }

    public static void createDir(String dir) {
        File theDir = new File(dir);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    public static int obtenerTipoArchivo(String extension) {
        switch (extension) {
            case ".doc":
            case ".docm":
            case ".docx":
            case ".dot":
            case ".dotm":
            case ".dotx":
            case ".txt":
                return ListadoCMM.TipoArchivo.DOCUMENTOTEXTO;
            case ".xlsx":
            case ".xlsm":
            case ".xlsb":
            case ".xltx":
            case ".xltm":
            case ".xls":
            case ".xlt":
            case ".xlam":
            case ".xla":
            case ".xlw":
            case ".xlr":
            case ".csv":
                return ListadoCMM.TipoArchivo.HOJACALCULO;
            case ".xml":
                return ListadoCMM.TipoArchivo.XML;
            case ".pdf":
                return ListadoCMM.TipoArchivo.PDF;
            case ".bmp":
            case ".gif":
            case ".jpg":
            case ".jpeg":
            case ".tif":
            case ".tiff":
            case ".png":
                return ListadoCMM.TipoArchivo.IMAGEN;
            default:
                return ListadoCMM.TipoArchivo.OTRO;
        }
    }
}