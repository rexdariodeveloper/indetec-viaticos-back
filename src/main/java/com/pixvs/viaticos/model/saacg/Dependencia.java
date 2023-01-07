package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class Dependencia {

    @JsonProperty("DependenciaId")
    private String id;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("ProyectoId")
    private String proyectoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }
}
