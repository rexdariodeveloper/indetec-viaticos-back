package com.pixvs.viaticos.exceptions;

public class EstadoException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_CODIGO_REPETIDO = 2;
    public static final int STATUS_ERROR_ESTADO_CIUDADES = 3;
    public static final int STATUS_ERROR_ESTADO_RELACIONADA = 4;

    private static final String[] MENSAJES = {
            "Excepcion generica de Estado.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "El Estado ya existe en el País.",
            "Primero debe borrar las Ciudades relacionadas al Estado.",
            "No se puede eliminar el Estado ya que esta siendo utilizado en otros procesos."
    };

    public EstadoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public EstadoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private EstadoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_ESTADO);
    }
}
