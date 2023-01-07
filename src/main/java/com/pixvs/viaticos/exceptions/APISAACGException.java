package com.pixvs.viaticos.exceptions;

public class APISAACGException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;

    private static final String[] MENSAJES = {
            "Excepcion generica API SAACG"
    };

    public APISAACGException(String mensaje, boolean enviarCorreo){
        super(mensaje, null, STATUS_ERROR_GENERICO, enviarCorreo, CODIGO_EXCEPCION_API_SAACG);
    }

    public APISAACGException(String mensaje, Object data, int tipoExcepcion, boolean enviarCorreo){
        super(mensaje, data, tipoExcepcion, enviarCorreo, 0);
    }
}
