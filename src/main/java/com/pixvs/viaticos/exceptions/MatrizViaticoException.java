package com.pixvs.viaticos.exceptions;

public class MatrizViaticoException extends GenericException {

    public static final int STATUS_ERROR_GUARDAR = 0;

    private static final String[] MENSAJES = {
            "Error al guardar",
    };

    public MatrizViaticoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public MatrizViaticoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private MatrizViaticoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_MATRIZ_VIATICO);
    }
}
