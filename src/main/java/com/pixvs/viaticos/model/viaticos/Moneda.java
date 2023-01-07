package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblMoneda")
public class Moneda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MonedaId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Abreviacion", nullable = true)
    private String abreviacion;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Simbolo", nullable = true)
    private String simbolo;

    @Column(name = "Predeterminada", nullable = false)
    private Boolean predeterminada;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

    @Column(name = "FechaCreacion", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false)
    private Integer creadoPorId;

    @Column(name = "FechaUltimaModificacion", nullable = true)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId", nullable = true)
    private Integer modificadoPorId;

    @Column(name = "Timestamp", insertable = false,updatable = false, nullable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Boolean getPredeterminada() {
        return predeterminada;
    }

    public void setPredeterminada(Boolean predeterminada) {
        this.predeterminada = predeterminada;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCreadoPorId() {
        return creadoPorId;
    }

    public void setCreadoPorId(Integer creadoPorId) {
        this.creadoPorId = creadoPorId;
    }

    public Timestamp getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Timestamp fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Integer getModificadoPorId() {
        return modificadoPorId;
    }

    public void setModificadoPorId(Integer modificadoPorId) {
        this.modificadoPorId = modificadoPorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
