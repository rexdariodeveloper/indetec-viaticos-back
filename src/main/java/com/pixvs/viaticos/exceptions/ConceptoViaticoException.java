package com.pixvs.viaticos.exceptions;

public class ConceptoViaticoException extends GenericException {

    public static final int STATUS_ERROR_GUARDADOS = 0; // Error for add all conceptos viaticos
    public static final int STATUS_ERROR_BORRAR_REGISTRO_PASAJE = 1; // Error for add all conceptos viaticos
    public static final int STATUS_ERROR_CONCEPTO_VIATICO_RELACION = 2;

    private static final String[] MENSAJES = {
            "Conceptos Viaticos no pudo guardan",
            "No se puede Eliminar el registro {0}, ya que es un registro de sistema",
            "No se puede eliminar el concepto viatico ya que esta siendo utilizado en otros procesos"
    };


    public ConceptoViaticoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public ConceptoViaticoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private ConceptoViaticoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_CONCEPTO_VIATICO);
    }
}
