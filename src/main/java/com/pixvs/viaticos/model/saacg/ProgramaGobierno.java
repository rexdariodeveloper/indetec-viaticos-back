package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProgramaGobierno {

    @JsonProperty("ProgramaGobiernoId")
    private String id;

    @JsonProperty("Nombre")
    private String nombre;

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
}
