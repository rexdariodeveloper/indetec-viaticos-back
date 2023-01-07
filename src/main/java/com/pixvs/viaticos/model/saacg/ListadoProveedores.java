package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListadoProveedores {

    @JsonProperty("ProveedorId")
    private Integer id;

    @JsonProperty("RazonSocial")
    private String razonSocial;

    @JsonProperty("RFC")
    private String rfc;

    @JsonProperty("PaisId")
    private String paisId;

    @JsonProperty("TipoProveedorId")
    private String tipoProveedorId;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("TipoOperacionId")
    private String tipoOperacionId;

    @JsonProperty("DescripcionOperacion")
    private String descripcionOperacion;

    @JsonProperty("TipoComprobanteFiscalId")
    private Integer tipoComprobanteFiscalId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }

    public String getTipoProveedorId() {
        return tipoProveedorId;
    }

    public void setTipoProveedorId(String tipoProveedorId) {
        this.tipoProveedorId = tipoProveedorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoOperacionId() {
        return tipoOperacionId;
    }

    public void setTipoOperacionId(String tipoOperacionId) {
        this.tipoOperacionId = tipoOperacionId;
    }

    public String getDescripcionOperacion() {
        return descripcionOperacion;
    }

    public void setDescripcionOperacion(String descripcionOperacion) {
        this.descripcionOperacion = descripcionOperacion;
    }

    public Integer getTipoComprobanteFiscalId() {
        return tipoComprobanteFiscalId;
    }

    public void setTipoComprobanteFiscalId(Integer tipoComprobanteFiscalId) {
        this.tipoComprobanteFiscalId = tipoComprobanteFiscalId;
    }
}
