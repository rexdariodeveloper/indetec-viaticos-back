package com.pixvs.viaticos.exceptions;

public class ConfiguracionEnteException extends GenericException {

    public static final int STATUS_ID_INCORRECTO = 0;
    public static final int STATUS_ENTE_EXIST = 1;

    private static final String[] MENSAJES = {
            "El ID no es correcto",
            "El ente no existe, intenta con otro nombre por favor",
    };

    public ConfiguracionEnteException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public ConfiguracionEnteException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private ConfiguracionEnteException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_EMPLEADO);
    }
}
