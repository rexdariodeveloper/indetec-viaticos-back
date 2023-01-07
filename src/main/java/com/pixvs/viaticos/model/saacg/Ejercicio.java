package com.pixvs.viaticos.model.saacg;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ejercicio {

    @JsonProperty("Ejercicio")
    private String ejercicio;

    @JsonProperty("EntidadViatico")
    private String entidadViatico;

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getEntidadViatico() {
        return entidadViatico;
    }

    public void setEntidadViatico(String entidadViatico) {
        this.entidadViatico = entidadViatico;
    }
}
