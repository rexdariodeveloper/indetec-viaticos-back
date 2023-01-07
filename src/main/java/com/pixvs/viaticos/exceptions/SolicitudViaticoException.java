package com.pixvs.viaticos.exceptions;

public class SolicitudViaticoException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_SOLICITUD_REPETIDA = 2;
    public static final int STATUS_FORBIDEN = 3;
    public static final int STATUS_SIN_AUTONUMERICO = 4;
    public static final int STATUS_ERROR_SOLICITUD_NO_ENCONTRADA = 5;
    public static final int STATUS_ERROR_RANGO_FECHAS = 6;
    public static final int STATUS_ERROR_GUARDAR = 7;

    private static final String[] MENSAJES = {
            "Excepcion generica de Solicitudes.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "La Solicitud ya existe.",
            "Opps, lo sentimos, pero no tiene acceso a esta pagina.",
            "No existe autonumerico activo para la fecha de la solicitud.",
            "Solicitud no encontrada.",
            "Ya existe la Solicitud @folio elaborada para el Empleado seleccionado.",
            "Error al guardar la solicitud viatico"
    };

    public SolicitudViaticoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public SolicitudViaticoException(int tipoExcepcion, String folio) {
        this(MENSAJES[tipoExcepcion].replace("@folio", folio), tipoExcepcion, false);
    }

    public SolicitudViaticoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    public SolicitudViaticoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_SOLICITUD_VIATICO);
    }
}
