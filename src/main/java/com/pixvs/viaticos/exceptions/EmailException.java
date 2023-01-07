package com.pixvs.viaticos.exceptions;

public class EmailException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_DATOS_INCOMPLETOS = 1;

    private static final String[] MENSAJES = {
            "Excepcion generica de Email.",
            "Servidor de Correos, Datos incompletos."
    };

    public EmailException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public EmailException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private EmailException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_EMAIL);
    }
}
