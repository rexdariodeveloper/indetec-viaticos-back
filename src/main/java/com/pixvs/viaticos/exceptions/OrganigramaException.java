package com.pixvs.viaticos.exceptions;

public class OrganigramaException extends GenericException {

    public static final int STATUS_ERROR_GUARDADO = 0; // Error for add one organigrama
    public static final int STATUS_ERROR_GUARDADOS = 1; // Error for all organigramas
    public static final int STATUS_ERROR_ORGANIGRAMA_RELACIONADA = 2;

    private static final String[] MENSAJES = {
            "Error el nodo no puede guardar",
            "Error los nodos no pueden guardar",
            "No se puede eliminar el Organigrama ya que esta siendo utilizado en otros procesos."
    };


    public OrganigramaException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public OrganigramaException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private OrganigramaException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_ORGANIGRAMA);
    }

    //Como voy a hacer un mensaje de error cuando regresar a Front, y como voy a hacer un mensaje de error
    // ese mensaje es el q se va amostrar en el front? o no necesita enviar mensaje de error. Pero asi como los tienes esta bien . En tu controller es donde los evias segun el error.
}