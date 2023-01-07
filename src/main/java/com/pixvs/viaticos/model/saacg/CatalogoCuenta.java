package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class CatalogoCuenta {

    @JsonProperty("CatalogoCuentaId")
    private Integer id;

    @JsonProperty("Cuenta")
    private String cuenta;

    @JsonProperty("Nombre")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
