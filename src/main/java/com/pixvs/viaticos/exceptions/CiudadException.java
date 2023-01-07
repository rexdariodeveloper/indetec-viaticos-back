package com.pixvs.viaticos.exceptions;

public class CiudadException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_CIUDAD_REPETIDA = 2;
    public static final int STATUS_ERROR_CIUDAD_RELACIONADA = 3;

    private static final String[] MENSAJES = {
            "Excepcion generica de Ciudad.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "La Ciudad ya existe en el Estado.",
            "No se puede eliminar el Ciudad ya que esta siendo utilizado en otros procesos."
    };

    public CiudadException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public CiudadException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private CiudadException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_CIUDAD);
    }
}
