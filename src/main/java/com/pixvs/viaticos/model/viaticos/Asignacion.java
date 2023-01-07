package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoAsignacion")
public class Asignacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AsignacionId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoId", nullable = false)
    private Integer solicitudViaticoId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoId", referencedColumnName = "SolicitudViaticoId", insertable = false, updatable = false)
    private SolicitudViatico solicitudViatico;

    @Column(name = "FechaComprometido")
    private Timestamp fechaComprometido;

    @Column(name = "PolizaComprometidoId")
    private Integer polizaComprometidoId;

    @Column(name = "NumeroPolizaComprometido")
    private String numeroPolizaComprometido;

    @Column(name = "PolizaGastoPorComprobarId")
    private Integer polizaGastoPorComprobarId;

    @Column(name = "NumeroPolizaGastoPorComprobarId")
    private String numeroPolizaGastoPorComprobarId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSolicitudViaticoId() {
        return solicitudViaticoId;
    }

    public void setSolicitudViaticoId(Integer solicitudViaticoId) {
        this.solicitudViaticoId = solicitudViaticoId;
    }

    public SolicitudViatico getSolicitudViatico() {
        return solicitudViatico;
    }

    public void setSolicitudViatico(SolicitudViatico solicitudViatico) {
        this.solicitudViatico = solicitudViatico;
    }

    public Timestamp getFechaComprometido() {
        return fechaComprometido;
    }

    public void setFechaComprometido(Timestamp fechaComprometido) {
        this.fechaComprometido = fechaComprometido;
    }

    public Integer getPolizaComprometidoId() {
        return polizaComprometidoId;
    }

    public void setPolizaComprometidoId(Integer polizaComprometidoId) {
        this.polizaComprometidoId = polizaComprometidoId;
    }

    public String getNumeroPolizaComprometido() {
        return numeroPolizaComprometido;
    }

    public void setNumeroPolizaComprometido(String numeroPolizaComprometido) {
        this.numeroPolizaComprometido = numeroPolizaComprometido;
    }

    public Integer getPolizaGastoPorComprobarId() {
        return polizaGastoPorComprobarId;
    }

    public void setPolizaGastoPorComprobarId(Integer polizaGastoPorComprobarId) {
        this.polizaGastoPorComprobarId = polizaGastoPorComprobarId;
    }

    public String getNumeroPolizaGastoPorComprobarId() {
        return numeroPolizaGastoPorComprobarId;
    }

    public void setNumeroPolizaGastoPorComprobarId(String numeroPolizaGastoPorComprobarId) {
        this.numeroPolizaGastoPorComprobarId = numeroPolizaGastoPorComprobarId;
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
}
