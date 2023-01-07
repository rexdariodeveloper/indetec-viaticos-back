package com.pixvs.viaticos.model.viaticos;

import com.sun.corba.se.spi.ior.ObjectKeyTemplate;

/**
 * Created by PixvsChevy on 2/15/2017.
 */

public class JsonResponseError {

    /**
     *  Estatus de Error
     */
    public static final int STATUS_ERROR = 500;
    public static final int STATUS_ERROR_SERVIDOR = 1500;
    public static final int STATUS_ERROR_SQL = 1501;
    public static final int STATUS_ERROR_NULL_POINTER = 1502;

    /**
     * Codigo Estatus para identificar la respuesta enviada al Cliente.
     */
    private int status;

    /**
     * Mensaje informativo describiendo brevemente un posible error o algun mensaje informativo para
     * el Cliente.
     */
    private String message;

    private Object data;

    /**
     * URL de donde se origino el error.
     */
    private String path;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public JsonResponseError() {}

    public JsonResponseError(String message, int status) {
        this(message, null, null, status);
    }

    public JsonResponseError(String message, Object data, String path, int status) {
        setMessage(message);
        setData(data);
        setPath(path);
        setStatus(status);
    }
}