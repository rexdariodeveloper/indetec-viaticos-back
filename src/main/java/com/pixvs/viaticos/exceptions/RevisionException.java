package com.pixvs.viaticos.exceptions;

public class RevisionException extends GenericException {

    public static final int STATUS_ERROR_SOLICITUD_NO_ENCONTRADA = 0;
    public static final int STATUS_ERROR_COMPROBACION_NO_ENCONTRADA = 1;
    public static final int STATUS_ERROR_ESTATUS_INCORRECTO = 2;
    public static final int STATUS_ERROR_REPORT = 3;
    public static final int STATUS_ERROR_ARCHIVO = 4;

    private static final String[] MENSAJES = {
            "Solicitud no encontrada.",
            "Comprobación no encontrada.",
            "La Solicitud no tiene Estatus: " +
                    "Comprobación Finalizada, " +
                    "En Proceso de Autorización de Revisión, " +
                    "Autorización de Revisión Aprobada " +
                    "o Revisada.",
            "Error al guardar el reporte.",
            "Error al guardar el archivo."
    };

    public RevisionException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public RevisionException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    public RevisionException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_REVISION);
    }
}