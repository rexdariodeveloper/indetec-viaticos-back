package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacionDetalleImpuesto")
public class SolicitudViaticoComprobacionDetalleImpuesto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoCompDetImpuestoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionDetalleId", nullable = false)
    private Integer solicitudViaticoComprobacionDetalleId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionDetalleId", referencedColumnName = "SolicitudViaticoComprobacionDetalleId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle;

    @Column(name = "TipoImpuestoId", nullable = true)
    private Integer tipoImpuestoId;

    @OneToOne
    @JoinColumn(name="TipoImpuestoId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM tipoImpuesto;

    @Column(name = "ImpuestoId", nullable = false)
    private Integer impuestoId;

    @OneToOne
    @JoinColumn(name="ImpuestoId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM impuesto;

    @Column(name = "ImpuestoImporte", nullable = false)
    private BigDecimal impuestoImporte;

    @Column(name = "TasaOCuota", nullable = false)
    private BigDecimal tasaOCuota;

    @Column(name = "ImpuestoComprobado", nullable = false)
    private BigDecimal impuestoComprobado;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaRegistro", nullable = false)
    private Timestamp fechaRegistro;

    @Column(name = "RegistradoPorId", nullable = false)
    private Integer registradoPorId;

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

    public Integer getTipoImpuestoId() {
        return tipoImpuestoId;
    }

    public void setTipoImpuestoId(Integer tipoImpuestoId) {
        this.tipoImpuestoId = tipoImpuestoId;
    }

    public ListadoCMM getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(ListadoCMM tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public Integer getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(Integer impuestoId) {
        this.impuestoId = impuestoId;
    }

    public ListadoCMM getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(ListadoCMM impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getImpuestoImporte() {
        return impuestoImporte;
    }

    public void setImpuestoImporte(BigDecimal impuestoImporte) {
        this.impuestoImporte = impuestoImporte;
    }

    public BigDecimal getTasaOCuota() {
        return tasaOCuota;
    }

    public void setTasaOCuota(BigDecimal tasaOCuota) {
        this.tasaOCuota = tasaOCuota;
    }

    public BigDecimal getImpuestoComprobado() {
        return impuestoComprobado;
    }

    public void setImpuestoComprobado(BigDecimal impuestoComprobado) {
        this.impuestoComprobado = impuestoComprobado;
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

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getRegistradoPorId() {
        return registradoPorId;
    }

    public void setRegistradoPorId(Integer registradoPorId) {
        this.registradoPorId = registradoPorId;
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
