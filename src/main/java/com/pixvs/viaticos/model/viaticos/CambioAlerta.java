package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblAlertaCambioUsuarioTmp")
public class CambioAlerta extends  Object{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AlertaCambioUsuarioTmpId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Folio", nullable = false)
    private String folio;

    @Column(name = "EmpleadoOrigenId", nullable = false)
    private int empleadoOrigenId;

    @OneToOne
    @JoinColumn(name="EmpleadoOrigenId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado empleadoOrigen;

    @Column(name = "EmpleadoDestinoId", nullable = false)
    private int empleadoDestinoId;

    @OneToOne
    @JoinColumn(name="EmpleadoDestinoId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado empleadoDestino;

    @Column(name = "FechaInicio", nullable = false)
    private Timestamp fechaInicio;

    @Column(name = "FechaFin", nullable = false)
    private Timestamp fechaFin;

    @Column(name = "Borrado", nullable = false)
    private boolean borrado;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false, updatable = false)
    private int creadoPorId;

    @Column(name = "FechaUltimaModificacion")
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId")
    private Integer modificadoPorId;

    @Column(name = "Timestamp", nullable = false, insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getEmpleadoOrigenId() {
        return empleadoOrigenId;
    }

    public void setEmpleadoOrigenId(int empleadoOrigenId) {
        this.empleadoOrigenId = empleadoOrigenId;
    }

    public Empleado getEmpleadoOrigen() {
        return empleadoOrigen;
    }

    public void setEmpleadoOrigen(Empleado empleadoOrigen) {
        this.empleadoOrigen = empleadoOrigen;
    }

    public int getEmpleadoDestinoId() {
        return empleadoDestinoId;
    }

    public void setEmpleadoDestinoId(int empleadoDestinoId) {
        this.empleadoDestinoId = empleadoDestinoId;
    }

    public Empleado getEmpleadoDestino() {
        return empleadoDestino;
    }

    public void setEmpleadoDestino(Empleado empleadoDestino) {
        this.empleadoDestino = empleadoDestino;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCreadoPorId() {
        return creadoPorId;
    }

    public void setCreadoPorId(int creadoPorId) {
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
