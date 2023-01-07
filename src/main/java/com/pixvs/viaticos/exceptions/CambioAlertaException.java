package com.pixvs.viaticos.exceptions;

public class CambioAlertaException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_ERROR_RANGO_FECHAS = 2;

    private static final String[] MENSAJES = {
            "Excepcion generica de Cambio de Alertas.",
            "Existen cambios previos, favor de recargar antes de guardar.",
            "Ya existe un Empleado configurado para recibir Alertas en el periodo seleccionado."
    };

    public CambioAlertaException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public CambioAlertaException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private CambioAlertaException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_CAMBIO_ALERTAS);
    }
}
