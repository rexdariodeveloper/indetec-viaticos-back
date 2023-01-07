package com.pixvs.viaticos.model.viaticos;

/**
 * Created by PixvsChevy on 2/15/2017.
 */

public class JsonResponse {

    /**
     *  Estatus de la respuesta
     */
    public static final int STATUS_OK = 200;

    /**
     * Codigo Estatus para identificar la respuesta enviada al Cliente.
     */
    private int status;

    /**
     * Mensaje informativo el Cliente.
     */
    private String message;

    /**
     * Objeto por lo regular de tipo <b>JSON</b> con datos necesarios para cargar alguna Ficha, listado o
     * un registro en particular.
     */
    private Object data;

    /**
     * Nombre de la ficha o proceso que genera el Response.
     */
    private String title;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonResponse() {}

    public JsonResponse(Object data) {
        this(data, null,"OK", STATUS_OK);
    }

    public JsonResponse(Object data, String message) {
        this(data, message, null, STATUS_OK);
    }

    public JsonResponse(Object data, String message, int status) {
        this(data, message, null, status);
    }

    public JsonResponse(Object data, String message, String title) {
        this(data, message, title, STATUS_OK);
    }

    public JsonResponse(Object data, String message, String title, int status) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.title = title;
    }
}