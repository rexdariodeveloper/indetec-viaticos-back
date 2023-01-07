package com.pixvs.viaticos.model.viaticos;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblListadoCMM")
public class ListadoCMM {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ControlId", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Valor", nullable = false)
    private String valor;

    @Column(name = "Sistema", nullable = false)
    private Boolean sistema;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

    @Column(name = "ControlSencillo", nullable = false)
    private Boolean controlSencillo;

    @Column(name = "ModuloId", nullable = true)
    private Integer moduloId;

    @Column(name = "FechaCreacion", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = true)
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getSistema() {
        return sistema;
    }

    public void setSistema(Boolean sistema) {
        this.sistema = sistema;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getControlSencillo() {
        return controlSencillo;
    }

    public void setControlSencillo(Boolean controlSencillo) {
        this.controlSencillo = controlSencillo;
    }

    public Integer getModuloId() {
        return moduloId;
    }

    public void setModuloId(Integer moduloId) {
        this.moduloId = moduloId;
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
