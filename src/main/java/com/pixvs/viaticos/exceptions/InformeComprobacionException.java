package com.pixvs.viaticos.exceptions;

public class InformeComprobacionException extends GenericException {

    public static final int STATUS_ERROR_GUARDAR_INFORME = 0;
    public static final int STATUS_ERROR_GUARDAR_COMPROBACION = 1;
    public static final int STATUS_ERROR_GUARDAR_COMPROBACION_DETALLE = 2;
    public static final int STATUS_ERROR_GUARDAR_COMPROBACION_PASAJE = 3;
    public static final int STATUS_ERROR_GUARDAR_COMPROBACION_CARGO = 4;
    public static final int STATUS_ERROR_FILE_ALREADY_EXIST = 5;
    public static final int STATUS_ERROR_GUARDAR_COMPROBACION_DETALLE_FACTURA = 6;
    public static final int STATUS_ERROR_SOLICITUD_ESTATUS = 7;
    public static final int STATUS_ERROR_COMPROBACION_DETALLE_RELACION = 8;
    public static final int STATUS_ERROR_COMPROBACION_DETALLE_IMPUESTO_RELACION = 9;
    public static final int STATUS_ERROR_COMPROBACION_PASAJE_RELACION = 10;
    public static final int STATUS_ERROR_COMPROBACION_CARGO_RELACION = 11;

    private static final String[] MENSAJES = {
            "El sistema no puede guardar el informe.",
            "El sistema no puede guardar el comprobación",
            "El sistema no puede guardar el comprobación detalle",
            "El sistema no puede guardar el comprobacion pasaje",
            "El sistema no puede guardar el comprobacion cargo",
            "El archivo ya existe, eliges otro archivo.",
            "El importe es incorrecto, necesitas eliminar a una factura y subes nuevo. El archivo: ",
            "La Solicitud no tiene Estatus: Recursos Asignados, En Revisión de Comprobación, Comprobación Finalizada o Revisada.",
            "No se puede eliminar la comprobación detalle ya que esta siendo utilizado en otros procesos",
            "No se puede eliminar la comprobación detalle impuesto ya que esta siendo utilizado en otros procesos",
            "No se puede eliminar la comprobación pasaje ya que esta siendo utilizado en otros procesos",
            "No se puede eliminar la comprobación cargo ya que esta siendo utilizado en otros procesos"
    };

    public InformeComprobacionException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public InformeComprobacionException(int tipoExcepcion, String cualArchivo) {
        this(MENSAJES[tipoExcepcion] + cualArchivo, tipoExcepcion, false);
    }

    public InformeComprobacionException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private InformeComprobacionException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_SOLICITUD_VIATICO_INFORME_COMPROBACION);
    }
}
