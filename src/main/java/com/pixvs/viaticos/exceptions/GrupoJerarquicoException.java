package com.pixvs.viaticos.exceptions;

public class GrupoJerarquicoException extends GenericException {

    public static final int STATUS_ERROR_GENERICO = 0;
    public static final int STATUS_CAMBIO_TIMESTAMP = 1;
    public static final int STATUS_GRUPO_REPETIDO = 2;
    public static final int STATUS_ERROR_GRUPO_JERARQUICO_RELACIONADA = 3;
    public static final int STATUS_ERROR_GRUPO_JERARQUICO_PUESTO_RELACIONADA = 4;

    private static final String[] MENSAJES = {
            "Excepcion generica de Grupos Jerárquicos.",
            "Existen cambios previos, favor de recargar antes de guardar",
            "El Grupo Jerárquico ya existe.",
            "No se puede eliminar el Grupo Jerarquico ya que esta siendo utilizado en otros procesos.",
            "No se puede eliminar el Grupo Jerarquico Puesto ya que esta siendo utilizado en otros procesos."
    };

    public GrupoJerarquicoException(int tipoExcepcion) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, false);
    }

    public GrupoJerarquicoException(int tipoExcepcion, boolean enviarCorreo) {
        this(MENSAJES[tipoExcepcion], tipoExcepcion, enviarCorreo);
    }

    private GrupoJerarquicoException(String mensaje, int tipoExcepcion, boolean enviarCorreo) {
        super(mensaje, tipoExcepcion, enviarCorreo, CODIGO_EXCEPCION_GRUPO_JERARQUICO);
    }
}
