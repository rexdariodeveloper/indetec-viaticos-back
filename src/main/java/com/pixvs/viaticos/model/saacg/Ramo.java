package com.pixvs.viaticos.model.saacg;

import javax.persistence.*;

@Entity
@Table(name = "tblRamo")
public class Ramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RamoId", nullable = false, insertable = false, updatable = false)
    private String id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Editable", nullable = false)
    private boolean editable;

    @Column(name = "FuenteFinanciamientoCONACId", nullable = false)
    private String fuenteFinanciamientoCONACId;

    @Column(name = "TipoGastoDestinoId")
    private String tipoGastoDestinoId;

    @Column(name = "Anio")
    private Integer anio;

    @Column(name = "RamoDetalleId")
    private String ramoDetalleId;

    @Column(name = "TipoRecurso")
    private String tipoRecurso;

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

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getFuenteFinanciamientoCONACId() {
        return fuenteFinanciamientoCONACId;
    }

    public void setFuenteFinanciamientoCONACId(String fuenteFinanciamientoCONACId) {
        this.fuenteFinanciamientoCONACId = fuenteFinanciamientoCONACId;
    }

    public String getTipoGastoDestinoId() {
        return tipoGastoDestinoId;
    }

    public void setTipoGastoDestinoId(String tipoGastoDestinoId) {
        this.tipoGastoDestinoId = tipoGastoDestinoId;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getRamoDetalleId() {
        return ramoDetalleId;
    }

    public void setRamoDetalleId(String ramoDetalleId) {
        this.ramoDetalleId = ramoDetalleId;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}
