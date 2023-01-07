package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class FuenteFinanciamiento {

    @JsonProperty("RamoId")
    private String id;

    @JsonProperty("ProyectoId")
    private  String proyectoId;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("TipoRecurso")
    private String tipoRecurso;

    @JsonProperty("Anio")
    private Integer anio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
