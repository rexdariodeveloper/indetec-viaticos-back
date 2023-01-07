package com.pixvs.viaticos.exceptions;

public class AlertaException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_REGISTRO_NO_EXISTENTE = 1;
    public static final int STATUS_CAMBIO_TIMESTAMP = 2;

    private static final String[] MENSAJES = {
            "Excepcion generica de Alertas.",
            "El registro que se intenta editar no se encuentró",
            "Existen cambios previos, favor de recargar antes de guardar"
    };

    public AlertaException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public AlertaException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private AlertaException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_ALERTA);
    }

}
