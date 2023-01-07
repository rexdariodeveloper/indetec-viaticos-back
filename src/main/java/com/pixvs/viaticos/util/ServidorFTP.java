package com.pixvs.viaticos.util;

import com.pixvs.viaticos.exceptions.ServidorFTPException;
import com.pixvs.viaticos.model.viaticos.DatosArchivoServidorFTP;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class ServidorFTP {

    private Integer protocolo;
    private String servidor;
    private Integer puerto;
    private String usuario;
    private String contrasenia;

    private void setProperties(ServidorFTPConfiguracionEnteProjection properties) throws ServidorFTPException {
        protocolo = properties.getProtocoloFTP();
        servidor = properties.getServidorFTP();
        puerto = properties.getPuertoFTP();
        usuario = properties.getUsuarioFTP();
        contrasenia = JSEncriptador.aesDecrypt(properties.getContraseniaFTP());

        if (protocolo == null || servidor == null || usuario == null || contrasenia == null) {
            throw new ServidorFTPException(ServidorFTPException.STATUS_DATOS_INCOMPLETOS);
        }
    }

    public void subirArchivos(ServidorFTPConfiguracionEnteProjection properties, DatosArchivoServidorFTP archivo) throws Exception {
        subirArchivos(properties, Arrays.asList(archivo));
    }

    public void subirArchivos(ServidorFTPConfiguracionEnteProjection properties, List<DatosArchivoServidorFTP> archivos) throws Exception {
        setProperties(properties);

        switch (protocolo) {
            case ListadoCMM.ProtocoloServidorFTP.FTP:
            case ListadoCMM.ProtocoloServidorFTP.FTPS:
                subirFTP(archivos);
            break;

            case ListadoCMM.ProtocoloServidorFTP.SFTP:
                subirSFTP(archivos);
            break;

            default:
                throw new ServidorFTPException(ServidorFTPException.STATUS_ERROR_PROTOCOLO);
        }
    }

    public void eliminarArchivos(ServidorFTPConfiguracionEnteProjection properties, DatosArchivoServidorFTP archivo) throws Exception {
        eliminarArchivos(properties, Arrays.asList(archivo));
    }

    public void eliminarArchivos(ServidorFTPConfiguracionEnteProjection properties, List<DatosArchivoServidorFTP> archivos) throws Exception {
        setProperties(properties);

        switch (protocolo) {
            case ListadoCMM.ProtocoloServidorFTP.FTP:
            case ListadoCMM.ProtocoloServidorFTP.FTPS:
                eliminarFTP(archivos);
            break;

            case ListadoCMM.ProtocoloServidorFTP.SFTP:
                eliminarSFTP(archivos);
            break;

            default:
                throw new ServidorFTPException(ServidorFTPException.STATUS_ERROR_PROTOCOLO);
        }
    }

    private void subirFTP(List<DatosArchivoServidorFTP> archivos) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpClient.connect(servidor, puerto != null ? puerto : ftpClient.getDefaultPort());
        ftpClient.login(usuario, contrasenia);
        ftpClient.setStrictReplyParsing(false);

        for (DatosArchivoServidorFTP archivo : archivos) {
            for (String singleDir : archivo.getRutaDestino().split("/")) {
                if (!ftpClient.changeWorkingDirectory(singleDir)) {
                    if (ftpClient.makeDirectory(singleDir)) {
                        ftpClient.changeWorkingDirectory(singleDir);
                    }
                }
            }

            FileInputStream file = new FileInputStream(archivo.getArchivoOriginal());
            ftpClient.storeFile(archivo.getRutaDestino() + archivo.getArchivoDestino(), file);
            file.close();
        }

        ftpClient.logout();
        ftpClient.disconnect();
    }

    private void eliminarFTP(List<DatosArchivoServidorFTP> archivos) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpClient.connect(servidor, puerto != null ? puerto : ftpClient.getDefaultPort());
        ftpClient.login(usuario, contrasenia);

        try {
            for (DatosArchivoServidorFTP archivo : archivos) {
                ftpClient.deleteFile(archivo.getRutaDestino() + archivo.getArchivoDestino());
            }
        } catch (Exception ex) {}

        ftpClient.logout();
        ftpClient.disconnect();
    }

    private SSHClient getSSHClient() throws Exception {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());

        if (puerto != null) {
            client.connect(servidor, puerto);
        } else {
            client.connect(servidor);
        }

        client.authPassword(usuario, contrasenia);
        return client;
    }

    private void subirSFTP(List<DatosArchivoServidorFTP> archivos) throws Exception {
        SSHClient sshClient = getSSHClient();
        SFTPClient sftpClient = sshClient.newSFTPClient();

        for (DatosArchivoServidorFTP archivo : archivos) {
            sftpClient.mkdirs(archivo.getRutaDestino());
            sftpClient.put(archivo.getArchivoOriginal(), archivo.getRutaDestino() + archivo.getArchivoDestino());
        }

        sftpClient.close();
        sshClient.disconnect();
    }

    private void eliminarSFTP(List<DatosArchivoServidorFTP> archivos) throws Exception {
        SSHClient sshClient = getSSHClient();
        SFTPClient sftpClient = sshClient.newSFTPClient();

        try {
            for (DatosArchivoServidorFTP archivo : archivos) {
                sftpClient.rm(archivo.getRutaDestino() + archivo.getArchivoDestino());
            }
        } catch (Exception ex) {}

        sftpClient.close();
        sshClient.disconnect();
    }
}
