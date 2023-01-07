package com.pixvs.viaticos.exceptions;

public class UsuarioException extends GenericException {


    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_ENCABEZADOS_INVALIDOS = 1;
    public static final int STATUS_TOKEN_ID_INVALIDO = 2;
    public static final int STATUS_PARAMETROS_INVALIDOS = 3;
    public static final int STATUS_USUARIO_CONTRASEÑA_INCORRECTOS = 4;
    public static final int STATUS_USUARIO_INACTIVO = 5;
    public static final int STATUS_ERROR_USUARIO_RELACIONADA = 6;

    private static final String[] MENSAJES = {
            "Excepcion generica de Usuarios",
            "Encabezados Invalidados.",
            "El ID de Usuario y Token proporcionados no corresponden.",
            "Usuario o Contraseña no proporcionada",
            "Usuario o Contraseña Incorrecto",
            "Usuario no se encuentra activo.",
            "No se puede eliminar el Usuario ya que esta siendo utilizado en otros procesos."
    };


    public UsuarioException(String mensaje, boolean enviarCorreo){
        this(mensaje, STATUS_ERROR_GENERICO, enviarCorreo);
    }

    public UsuarioException(int tipoExcepcion, boolean enviarCorreo){
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private UsuarioException(String mensaje, int tipoExcepcion, boolean enviarCorreo){
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_USUARIO);

    }
}