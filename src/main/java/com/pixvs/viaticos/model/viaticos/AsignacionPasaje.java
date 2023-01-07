package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoAsignacionPasaje")
public class AsignacionPasaje {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AsignacionPasajeId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "AsignacionId", nullable = false)
    private Integer asignacionId;

    @OneToOne
    @JoinColumn(name="AsignacionId", referencedColumnName = "AsignacionId", insertable = false, updatable = false)
    private Asignacion asignacion;

    @Column(name = "ConceptoViaticoId", nullable = false)
    private Integer conceptoViaticoId;

    @OneToOne
    @JoinColumn(name="ConceptoViaticoId", referencedColumnName = "ConceptoViaticoId", insertable = false, updatable = false)
    private ConceptoViatico conceptoViatico;

    @Column(name = "ViajeRedondo", nullable = true)
    private Boolean viajeRedondo;

    @Column(name = "FechaCompra", nullable = true)
    private Timestamp fechaCompra;

    @Column(name = "CodigoReservacion", nullable = true)
    private String codigoReservacion;

    @Column(name = "NombreLinea", nullable = true)
    private String nombreLinea;

    @Column(name = "FechaSalida", nullable = true)
    private Timestamp fechaSalida;

    @Column(name = "FechaRegreso", nullable = true)
    private Timestamp fechaRegreso;

    @Column(name = "Costo", nullable = true)
    private BigDecimal costo;

    @Column(name = "AsignadoAFuncionario", nullable = false)
    private Boolean asignadoAFuncionario;

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

    public Integer getAsignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(Integer asignacionId) {
        this.asignacionId = asignacionId;
    }

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    public Integer getConceptoViaticoId() {
        return conceptoViaticoId;
    }

    public void setConceptoViaticoId(Integer conceptoViaticoId) {
        this.conceptoViaticoId = conceptoViaticoId;
    }

    public ConceptoViatico getConceptoViatico() {
        return conceptoViatico;
    }

    public void setConceptoViatico(ConceptoViatico conceptoViatico) {
        this.conceptoViatico = conceptoViatico;
    }

    public Boolean getViajeRedondo() {
        return viajeRedondo;
    }

    public void setViajeRedondo(Boolean viajeRedondo) {
        this.viajeRedondo = viajeRedondo;
    }

    public Timestamp getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Timestamp fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getCodigoReservacion() {
        return codigoReservacion;
    }

    public void setCodigoReservacion(String codigoReservacion) {
        this.codigoReservacion = codigoReservacion;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
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

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Boolean getAsignadoAFuncionario() {
        return asignadoAFuncionario;
    }

    public void setAsignadoAFuncionario(Boolean asignadoAFuncionario) {
        this.asignadoAFuncionario = asignadoAFuncionario;
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
