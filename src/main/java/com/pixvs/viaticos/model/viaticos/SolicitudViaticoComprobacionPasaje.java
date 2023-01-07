package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacionPasaje")
public class SolicitudViaticoComprobacionPasaje {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoComprobacionPasajeId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionDetalleId", nullable = false)
    private Integer solicitudViaticoComprobacionDetalleId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionDetalleId", referencedColumnName = "SolicitudViaticoComprobacionDetalleId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle;

    @Column(name = "FechaCompra", nullable = false)
    private Timestamp fechaCompra;

    @Column(name = "NombreLinea", nullable = false)
    private String nombreLinea;

    @Column(name = "ViajeRedondo", nullable = false)
    private Boolean viajeRedondo;

    @Column(name = "FechaSalida", nullable = true)
    private Timestamp fechaSalida;

    @Column(name = "FechaRegreso", nullable = true)
    private Timestamp fechaRegreso;

    @Column(name = "NumeroBoletoIda", nullable = true)
    private String numeroBoletoIda;

    @Column(name = "NumeroBoletoRegreso", nullable = true)
    private String numeroBoletoRegreso;

    @Column(name = "CodigoReservacion", nullable = true)
    private String codigoReservacion;

    @Column(name = "Comentarios", nullable = true)
    private String comentarios;

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

    @Column(name = "BoletoUtilizadoIda", nullable = true)
    private Boolean boletoUtilizadoIda;

    @Column(name = "BoletoUtilizadoRegreso", nullable = true)
    private Boolean boletoUtilizadoRegreso;

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

    public Timestamp getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Timestamp fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public Boolean getViajeRedondo() {
        return viajeRedondo;
    }

    public void setViajeRedondo(Boolean viajeRedondo) {
        this.viajeRedondo = viajeRedondo;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Timestamp getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Timestamp fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String getNumeroBoletoIda() {
        return numeroBoletoIda;
    }

    public void setNumeroBoletoIda(String numeroBoletoIda) {
        this.numeroBoletoIda = numeroBoletoIda;
    }

    public String getNumeroBoletoRegreso() {
        return numeroBoletoRegreso;
    }

    public void setNumeroBoletoRegreso(String numeroBoletoRegreso) {
        this.numeroBoletoRegreso = numeroBoletoRegreso;
    }

    public String getCodigoReservacion() {
        return codigoReservacion;
    }

    public void setCodigoReservacion(String codigoReservacion) {
        this.codigoReservacion = codigoReservacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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

    public Boolean getBoletoUtilizadoIda() {
        return boletoUtilizadoIda;
    }

    public void setBoletoUtilizadoIda(Boolean boletoUtilizadoIda) {
        this.boletoUtilizadoIda = boletoUtilizadoIda;
    }

    public Boolean getBoletoUtilizadoRegreso() {
        return boletoUtilizadoRegreso;
    }

    public void setBoletoUtilizadoRegreso(Boolean boletoUtilizadoRegreso) {
        this.boletoUtilizadoRegreso = boletoUtilizadoRegreso;
    }
}
