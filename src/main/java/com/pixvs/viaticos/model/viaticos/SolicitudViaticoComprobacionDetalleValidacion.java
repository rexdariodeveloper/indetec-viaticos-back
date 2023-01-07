package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacionDetalleValidacion")
public class SolicitudViaticoComprobacionDetalleValidacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoCompDetalleValidacionId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionDetalleId", nullable = true)
    private Integer solicitudViaticoComprobacionDetalleId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionDetalleId", referencedColumnName = "SolicitudViaticoComprobacionDetalleId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle;

    @Column(name = "TextoValidacion", nullable = false)
    private String textoValidacion;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

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

    @Column(name = "SolicitudViaticoComprobacionId", nullable = true)
    private Integer solicitudViaticoComprobacionId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionId", referencedColumnName = "SolicitudViaticoComprobacionId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacion solicitudViaticoComprobacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSolicitudViaticoComprobacionDetalleId() {
        return solicitudViaticoComprobacionDetalleId;
    }

    public void setSolicitudViaticoComprobacionDetalleId(Integer solicitudViaticoComprobacionDetalleId) {
        this.solicitudViaticoComprobacionDetalleId = solicitudViaticoComprobacionDetalleId;
    }

    public SolicitudViaticoComprobacionDetalle getSolicitudViaticoComprobacionDetalle() {
        return solicitudViaticoComprobacionDetalle;
    }

    public void setSolicitudViaticoComprobacionDetalle(SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle) {
        this.solicitudViaticoComprobacionDetalle = solicitudViaticoComprobacionDetalle;
    }

    public String getTextoValidacion() {
        return textoValidacion;
    }

    public void setTextoValidacion(String textoValidacion) {
        this.textoValidacion = textoValidacion;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public ListadoCMM getEstatus() {
        return estatus;
    }

    public void setEstatus(ListadoCMM estatus) {
        this.estatus = estatus;
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

    public Integer getSolicitudViaticoComprobacionId() {
        return solicitudViaticoComprobacionId;
    }

    public void setSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) {
        this.solicitudViaticoComprobacionId = solicitudViaticoComprobacionId;
    }

    public SolicitudViaticoComprobacion getSolicitudViaticoComprobacion() {
        return solicitudViaticoComprobacion;
    }

    public void setSolicitudViaticoComprobacion(SolicitudViaticoComprobacion solicitudViaticoComprobacion) {
        this.solicitudViaticoComprobacion = solicitudViaticoComprobacion;
    }
}
