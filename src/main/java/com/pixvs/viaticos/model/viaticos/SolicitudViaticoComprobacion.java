package com.pixvs.viaticos.model.viaticos;

import org.exolab.castor.types.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacion")
public class SolicitudViaticoComprobacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoComprobacionId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoId", nullable = false)
    private Integer solicitudViaticoId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoId", referencedColumnName = "SolicitudViaticoId", insertable = false, updatable = false)
    private SolicitudViatico solicitudViatico;

    @Column(name = "SolicitanteFinalizoComprobacion", nullable = true)
    private Boolean solicitanteFinalizoComprobacion;

    @Column(name = "FechaSolicitanteFinalizoComprobacion", nullable = true)
    private Timestamp fechaSolicitanteFinalizoComprobacion;

    @Column(name = "RMFinalizoComprobacion", nullable = true)
    private Boolean rmFinalizoComprobacion;

    @Column(name = "FechaRMFinalizoComprobacion", nullable = true)
    private Timestamp fechaRMFinalizoComprobacion;

    @Column(name = "FechaPolizaComprobacion", nullable = true)
    private Timestamp fechaPolizaComprobacion;

    @Column(name = "ComisionNoRealizada", nullable = true)
    private Boolean comisionNoRealizada;

    @Column(name = "MotivoNoRealizada", nullable = true)
    private String motivoNoRealizada;

    @Column(name = "PolizaComprobacionId", nullable = true)
    private Integer polizaComprobacionId;

    @Column(name = "NumeroPolizaGastoComprobado", nullable = true)
    private String numeroPolizaGastoComprobado;

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

    public Boolean getSolicitanteFinalizoComprobacion() {
        return solicitanteFinalizoComprobacion;
    }

    public void setSolicitanteFinalizoComprobacion(Boolean solicitanteFinalizoComprobacion) {
        this.solicitanteFinalizoComprobacion = solicitanteFinalizoComprobacion;
    }

    public Timestamp getFechaSolicitanteFinalizoComprobacion() {
        return fechaSolicitanteFinalizoComprobacion;
    }

    public void setFechaSolicitanteFinalizoComprobacion(Timestamp fechaSolicitanteFinalizoComprobacion) {
        this.fechaSolicitanteFinalizoComprobacion = fechaSolicitanteFinalizoComprobacion;
    }

    public Boolean getRmFinalizoComprobacion() {
        return rmFinalizoComprobacion;
    }

    public void setRmFinalizoComprobacion(Boolean rmFinalizoComprobacion) {
        this.rmFinalizoComprobacion = rmFinalizoComprobacion;
    }

    public Timestamp getFechaRMFinalizoComprobacion() {
        return fechaRMFinalizoComprobacion;
    }

    public void setFechaRMFinalizoComprobacion(Timestamp fechaRMFinalizoComprobacion) {
        this.fechaRMFinalizoComprobacion = fechaRMFinalizoComprobacion;
    }

    public Timestamp getFechaPolizaComprobacion() {
        return fechaPolizaComprobacion;
    }

    public void setFechaPolizaComprobacion(Timestamp fechaPolizaComprobacion) {
        this.fechaPolizaComprobacion = fechaPolizaComprobacion;
    }

    public Boolean getComisionNoRealizada() {
        return comisionNoRealizada;
    }

    public void setComisionNoRealizada(Boolean comisionNoRealizada) {
        this.comisionNoRealizada = comisionNoRealizada;
    }

    public String getMotivoNoRealizada() {
        return motivoNoRealizada;
    }

    public void setMotivoNoRealizada(String motivoNoRealizada) {
        this.motivoNoRealizada = motivoNoRealizada;
    }

    public Integer getPolizaComprobacionId() {
        return polizaComprobacionId;
    }

    public void setPolizaComprobacionId(Integer polizaComprobacionId) {
        this.polizaComprobacionId = polizaComprobacionId;
    }

    public String getNumeroPolizaGastoComprobado() {
        return numeroPolizaGastoComprobado;
    }

    public void setNumeroPolizaGastoComprobado(String numeroPolizaGastoComprobado) {
        this.numeroPolizaGastoComprobado = numeroPolizaGastoComprobado;
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
