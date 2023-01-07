package com.pixvs.viaticos.exceptions;

public class GenericException extends Exception{

    /**
     * Mapeos Estaticos de clases que pueden lanzar una excepcion
     */
    protected static final int CODIGO_EXCEPCION_GENERICA = 5000;
    protected static final int CODIGO_EXCEPCION_USUARIO  = 5100;
    protected static final int CODIGO_EXCEPCION_EMPLEADO  = 5200;
    protected static final int CODIGO_EXCEPCION_ORGANIGRAMA  = 5300;
    protected static final int CODIGO_EXCEPCION_PAIS  = 5400;
    protected static final int CODIGO_EXCEPCION_ESTADO  = 5500;
    protected static final int CODIGO_EXCEPCION_CIUDAD  = 5600;
    protected static final int CODIGO_EXCEPCION_LISTADO_PUESTO  = 5700;
    protected static final int CODIGO_EXCEPCION_LISTADO_CARGO  = 5800;
    protected static final int CODIGO_EXCEPCION_GRUPO_JERARQUICO  = 5900;
    protected static final int CODIGO_EXCEPCION_CONCEPTO_VIATICO  = 6000;
    protected static final int CODIGO_EXCEPCION_ROLES  = 6100;
    protected static final int CODIGO_EXCEPCION_MATRIZ_VIATICO  = 6200;
    protected static final int CODIGO_EXCEPCION_SOLICITUD_VIATICO  = 6300;
    protected static final int CODIGO_EXCEPCION_ASIGNACION  = 6400;
    protected static final int CODIGO_EXCEPCION_SOLICITUD_VIATICO_INFORME_COMPROBACION  = 6500;
    protected static final int CODIGO_EXCEPCION_ALERTA = 6600;
    protected static final int CODIGO_EXCEPCION_LISTADO = 6700;
    protected static final int CODIGO_EXCEPCION_SOLICITUD_VIATICO_COMPROBACION = 6800;
    protected static final int CODIGO_EXCEPCION_REVISION = 6900;
    protected static final int CODIGO_EXCEPCION_CAMBIO_ALERTAS = 7000;
    protected static final int CODIGO_EXCEPCION_EMAIL = 8000;
    protected static final int CODIGO_EXCEPCION_FTP = 9000;

    /***
     * API SAACG
     */
    protected static final int CODIGO_EXCEPCION_API_SAACG = 30000;

    /**
     * Propiedades generales
     */
    private String message;
    private Object data;
    private int status;
    private int tipoExcepcion = CODIGO_EXCEPCION_GENERICA;
    private boolean enviarCorreo = false;


    public GenericException(String mensaje, int status){
        this(mensaje, status, false, CODIGO_EXCEPCION_GENERICA);
    }

    private GenericException(String mensaje, Object data, int status, boolean enviarCorreo){
        setMessage(mensaje);
        setStatus(status);
        setEnviarCorreo(enviarCorreo);
        setData(data);
    }

    protected GenericException(String mensaje, int status, boolean enviarCorreo, int tipoExcepcion){
        this(mensaje, null, tipoExcepcion + status, enviarCorreo);
        setTipoExcepcion(tipoExcepcion);
    }

    protected GenericException(String mensaje, Object data, int status, boolean enviarCorreo, int tipoExcepcion){
        this(mensaje, data, tipoExcepcion + status, enviarCorreo);
        setTipoExcepcion(tipoExcepcion);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTipoExcepcion() {
        return tipoExcepcion;
    }

    public void setTipoExcepcion(int tipoExcepcion) {
        this.tipoExcepcion = tipoExcepcion;
    }

    public boolean isEnviarCorreo() {
        return enviarCorreo;
    }

    public void setEnviarCorreo(boolean enviarCorreo) {
        this.enviarCorreo = enviarCorreo;
    }
}
