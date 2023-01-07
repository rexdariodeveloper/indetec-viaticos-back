package com.pixvs.viaticos.exceptions;

public class PaisException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_CODIGO_REPETIDO = 2;
    public static final int STATUS_ERROR_PAIS_ESTADOS = 3;

    private static final String[] MENSAJES = {
            "Excepcion generica de País.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "El código ya existe.",
            "No se puede eliminar el País ya que esta siendo utilizado en otros procesos"
    };

    public PaisException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public PaisException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private PaisException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_PAIS);
    }
}
