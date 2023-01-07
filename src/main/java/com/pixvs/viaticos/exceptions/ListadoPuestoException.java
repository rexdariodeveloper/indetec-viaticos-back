package com.pixvs.viaticos.exceptions;

public class ListadoPuestoException extends GenericException {

    public static final int STATUS_ERROR_GUARDAR = 0;

    private static final String[] MENSAJES = {
            "Error al guardar",
    };

    public ListadoPuestoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public ListadoPuestoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private ListadoPuestoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_LISTADO_PUESTO);
    }
}
