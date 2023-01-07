package com.pixvs.viaticos.exceptions;

public class ListadoException extends GenericException {

    public static final int STATUS_ERROR_GUARDAR_EL_DATO = 0;
    public static final int STATUS_ERROR_LISTADO_PERMITIDO = 1;
    public static final int STATUS_ERROR_LISTADO_RELACIONADA = 2;
    public static final int STATUS_ERROR_LISTADO_SISTEMA = 3;

    public  static  String msj="El sistema no puede guardar el dato";

    private static final String[] MENSAJES = {
            "El sistema no puede guardar el dato",
            "Algún dato de los que quiere registrar supera los caracteres permitidos",
            "No se puede eliminar el Listado ya que esta siendo utilizado en otros procesos.",
            "No se puede eliminar el registro proque el sistema esta activo."
    };

    public ListadoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public ListadoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private ListadoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_LISTADO);
    }
}
