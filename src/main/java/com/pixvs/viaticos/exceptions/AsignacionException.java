package com.pixvs.viaticos.exceptions;

public class AsignacionException extends GenericException {

    public static final int STATUS_ERROR_ID_INCORRECTO = 0;
    public static final int STATUS_ERROR_GUARDAR_ASIGNACION = 1;
    public static final int STATUS_ERROR_GUARDAR_ASIGNACION_VIATICO = 2;
    public static final int STATUS_ERROR_GUARDAR_ASIGNACION_PASAJE = 3;
    public static final int STATUS_ERROR_SOLICITUD_NO_ENCONTRADA = 4;
    public static final int STATUS_ERROR_GUARDAR_SOLICITUD_VIATICO = 5;
    public static final int STATUS_ERROR_SOLICITUD_NO_AUTORIZADA_RECURSOS_ASIGNADOS_ENVIADA_FINANZAS = 6;
    public static final int STATUS_ERROR_GUARDAR_OFICIO_COMISION = 7;
    public static final int STATUS_ERROR_PARAMETROS_INVALIDOS_RECURSOS_ASIGNADOS_SAACG = 8;
    public static final int STATUS_ERROR_SOLICITUD_ESTATUS_DIFERENTE_A_ENVIADA_A_FINANZAS = 9;
    public static final int STATUS_ERROR_ASIGNACION_NO_ENCONTRADA = 10;
    public static final int STATUS_ERROR_ACTUALIZAR_ASIGNACION_RECURSOS_ASIGNADOS = 11;
    public static final int STATUS_ERROR_EXISTE_POLIZA_GPC = 12;
    public static final int STATUS_ERROR_ASIGNACION_VIATICO_RELACION = 13;
    public static final int STATUS_ERROR_ASIGNACION_PASAJE_RELACION = 14;


    private static final String[] MENSAJES = {
            "El ID no es correcto",
            "El sistema no puede guardar la asignación",
            "El sistema no puede guardar la asignación viático",
            "El sistema no puede guardar la asignación pasaje",
            "Solicitud no encontrada.",
            "El sistema no puede guardar la solicitud viatico",
            "La Solicitud no tiene Estatus: Autorizada, Recursos Asignados, Enviada a Finanzas o En Revisión Comprobacion.",
            "Al intentar guardar el Oficio de Comisión. ",

            //Errores que pueden ocurrir cuando desde el SAACG intenten marcar una Solicitud como "Con Recursos Asignados"
            "Parametros Invalidos. Se esperaba SolicitudId, PolizaId y Número Poliza.",
            "Solicitud con Estatus diferente a Enviada a Finanzas.",
            "Asignacion no encontrada.",
            "Error al Actualizar Estatus a Recursos Asignados.",
            "No se puede Cancelar la Póliza Gasto Comprometido porque ya existe una Póliza Gasto por Comprobar.",

            "No se puede eliminar la asignación viatico ya que esta siendo utilizado en otros procesos",
            "No se puede eliminar la asignación pasaje ya que esta siendo utilizado en otros procesos"
    };

    public AsignacionException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public AsignacionException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    public AsignacionException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_ASIGNACION);
    }
}
