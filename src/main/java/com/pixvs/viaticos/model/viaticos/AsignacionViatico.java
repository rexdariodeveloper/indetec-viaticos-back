package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoAsignacionViatico")
public class AsignacionViatico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "AsignacionViaticoId", insertable = false, updatable = false, nullable = false)
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

    @Column(name = "ConceptoViaticoCodigo", nullable = false)
    private String conceptoViaticoCodigo;

    @Column(name = "ConceptoViaticoNombre", nullable = false)
    private String conceptoViaticoNombre;

    @Column(name = "MontoConPernocta", nullable = false)
    private BigDecimal montoConPernocta;

    @Column(name = "MontoSinPernocta", nullable = false)
    private BigDecimal montoSinPernocta;

    @Column(name = "MontoPorTransferir", nullable = false)
    private BigDecimal montoPorTransferir;

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

    public String getConceptoViaticoCodigo() {
        return conceptoViaticoCodigo;
    }

    public void setConceptoViaticoCodigo(String conceptoViaticoCodigo) {
        this.conceptoViaticoCodigo = conceptoViaticoCodigo;
    }

    public String getConceptoViaticoNombre() {
        return conceptoViaticoNombre;
    }

    public void setConceptoViaticoNombre(String conceptoViaticoNombre) {
        this.conceptoViaticoNombre = conceptoViaticoNombre;
    }

    public BigDecimal getMontoConPernocta() {
        return montoConPernocta;
    }

    public void setMontoConPernocta(BigDecimal montoConPernocta) {
        this.montoConPernocta = montoConPernocta;
    }

    public BigDecimal getMontoSinPernocta() {
        return montoSinPernocta;
    }

    public void setMontoSinPernocta(BigDecimal montoSinPernocta) {
        this.montoSinPernocta = montoSinPernocta;
    }

    public BigDecimal getMontoPorTransferir() {
        return montoPorTransferir;
    }

    public void setMontoPorTransferir(BigDecimal montoPorTransferir) {
        this.montoPorTransferir = montoPorTransferir;
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
