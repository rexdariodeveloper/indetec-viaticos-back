package com.pixvs.viaticos.exceptions;

public class SolicitudViaticoComprobacionException extends GenericException {

    public static final int STATUS_ERROR_GUARDAR_SOLICITUD_VIATICO_COMPROBACION = 0;
    public static final int STATUS_ERROR_GUARDAR_SOLICITUD_VIATICO_COMPROBACION_DETALLE_VALIDACION = 1;

    private static final String[] MENSAJES = {
            "El sistema no puede guardar el solicitud viatico comprobacion.",
            "El sistema no puede guardar el solicitud viatico comprobacion detalle validacion.",
    };

    public SolicitudViaticoComprobacionException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public SolicitudViaticoComprobacionException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private SolicitudViaticoComprobacionException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_SOLICITUD_VIATICO_COMPROBACION);
    }
}
