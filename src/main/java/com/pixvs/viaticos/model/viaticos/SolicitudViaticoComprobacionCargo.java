package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacionCargo")
public class SolicitudViaticoComprobacionCargo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoComprobacionCargoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionPasajeId", nullable = false)
    private Integer solicitudViaticoComprobacionPasajeId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionPasajeId", referencedColumnName = "SolicitudViaticoComprobacionPasajeId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje;

    @Column(name = "FechaCargoSalida", nullable = false)
    private Timestamp fechaCargoSalida;

    @Column(name = "FechaCargoRegreso", nullable = true)
    private Timestamp fechaCargoRegreso;

    @Column(name = "MontoCargoSalida", nullable = false)
    private BigDecimal montoCargoSalida;

    @Column(name = "MontoCargoRegreso", nullable = true)
    private BigDecimal montoCargoRegreso;

    @Column(name = "SolicitudCambio", nullable = true)
    private String solicitudCambio;

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

    public Integer getSolicitudViaticoComprobacionPasajeId() {
        return solicitudViaticoComprobacionPasajeId;
    }

    public void setSolicitudViaticoComprobacionPasajeId(Integer solicitudViaticoComprobacionPasajeId) {
        this.solicitudViaticoComprobacionPasajeId = solicitudViaticoComprobacionPasajeId;
    }

    public SolicitudViaticoComprobacionPasaje getSolicitudViaticoComprobacionPasaje() {
        return solicitudViaticoComprobacionPasaje;
    }

    public void setSolicitudViaticoComprobacionPasaje(SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje) {
        this.solicitudViaticoComprobacionPasaje = solicitudViaticoComprobacionPasaje;
    }

    public Timestamp getFechaCargoSalida() {
        return fechaCargoSalida;
    }

    public void setFechaCargoSalida(Timestamp fechaCargoSalida) {
        this.fechaCargoSalida = fechaCargoSalida;
    }

    public Timestamp getFechaCargoRegreso() {
        return fechaCargoRegreso;
    }

    public void setFechaCargoRegreso(Timestamp fechaCargoRegreso) {
        this.fechaCargoRegreso = fechaCargoRegreso;
    }

    public BigDecimal getMontoCargoSalida() {
        return montoCargoSalida;
    }

    public void setMontoCargoSalida(BigDecimal montoCargoSalida) {
        this.montoCargoSalida = montoCargoSalida;
    }

    public BigDecimal getMontoCargoRegreso() {
        return montoCargoRegreso;
    }

    public void setMontoCargoRegreso(BigDecimal montoCargoRegreso) {
        this.montoCargoRegreso = montoCargoRegreso;
    }

    public String getSolicitudCambio() {
        return solicitudCambio;
    }

    public void setSolicitudCambio(String solicitudCambio) {
        this.solicitudCambio = solicitudCambio;
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
