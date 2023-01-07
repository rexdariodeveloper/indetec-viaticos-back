package com.pixvs.viaticos.exceptions;

public class EmpleadoException extends GenericException {

    public static final int STATUS_ID_INCORRECTO = 0;
    public static final int STATUS_USUARIO_EXIST = 1;
    public static final int STATUS_NUMERO_EMPLEADO_EXIST = 2;
    public static final int STATUS_ELIMINAR_USUARIO = 3;
    public static final int STATUS_ELIMINAR_EMPLEADO = 4;
    public static final int STATUS_ERROR_EMPLEADO_RELACION = 5;

    private static final String[] MENSAJES = {
            "El ID no es correcto",
            "El nombre de Usuario ya existe, intenta con otro por favor",
            "El número de Empleado ya existe, intenta con otro por favor",
            "El usuario no pudo borrar",
            "El empleado no pudo borrar",
            "No se puede eliminar el empleado ya que esta siendo utilizado en otros procesos"
    };

    public EmpleadoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public EmpleadoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private EmpleadoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_EMPLEADO);
    }
}
