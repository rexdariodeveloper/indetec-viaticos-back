package com.pixvs.viaticos.exceptions;

public class RolException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_ROL_REPETIDO = 2;
    public static final int STATUS_ROL_USUARIO = 3;
    public static final int STATUS_ERROR_ROL_RELACIONADA = 4;

    private static final String[] MENSAJES = {
            "Excepcion generica de Roles.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "El Rol ya existe.",
            "Primero debe borrar los Usuarios relacionados al Rol.",
            "No se puede eliminar el Rol ya que esta siendo utilizado en otros procesos."
    };

    public RolException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public RolException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private RolException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_ROLES);
    }
}
