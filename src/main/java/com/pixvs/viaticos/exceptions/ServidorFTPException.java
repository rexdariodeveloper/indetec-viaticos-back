package com.pixvs.viaticos.exceptions;

public class ServidorFTPException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_ERROR_PROTOCOLO = 1;
    public static final int STATUS_DATOS_INCOMPLETOS = 2;
    public static final int STATUS_ERROR_LOGIN = 3;

    private static final String[] MENSAJES = {
            "Excepcion generica de Email.",
            "El Protocolo del Servidor FTP es incorrecto.",
            "Servidor FTP, Datos incompletos.",
            "Error al intentar conectarse al Servidor FTP.",
    };

    public ServidorFTPException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public ServidorFTPException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private ServidorFTPException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_FTP);
    }
}
