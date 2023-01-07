package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class Proyecto {

    @JsonProperty("ProyectoId")
    private String id;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("ProgramaGobiernoId")
    private String programaId;

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

    public String getProgramaId() {
        return programaId;
    }

    public void setProgramaId(String programaId) {
        this.programaId = programaId;
    }
}
