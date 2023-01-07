package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblMatrizViatico")
public class MatrizViatico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MatrizViaticoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "GrupoJerarquicoId", nullable = false)
    private Integer grupoJerarquicoId;

    @OneToOne
    @JoinColumn(name="GrupoJerarquicoId", referencedColumnName = "GrupoJerarquicoId", insertable = false, updatable = false)
    private GrupoJerarquico grupoJerarquico;

    @Column(name = "ConceptoViaticoId", nullable = false)
    private Integer conceptoViaticoId;

    @OneToOne
    @JoinColumn(name="ConceptoViaticoId", referencedColumnName = "ConceptoViaticoId", insertable = false, updatable = false)
    private ConceptoViatico conceptoViatico;

    @Column(name = "ListadoZonaId", nullable = false)
    private Integer listadoZonaId;

    @OneToOne
    @JoinColumn(name="ListadoZonaId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM listadoZona;

    @Column(name = "Monto", nullable = false)
    private BigDecimal monto;

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

    public Integer getGrupoJerarquicoId() {
        return grupoJerarquicoId;
    }

    public void setGrupoJerarquicoId(Integer grupoJerarquicoId) {
        this.grupoJerarquicoId = grupoJerarquicoId;
    }

    public GrupoJerarquico getGrupoJerarquico() {
        return grupoJerarquico;
    }

    public void setGrupoJerarquico(GrupoJerarquico grupoJerarquico) {
        this.grupoJerarquico = grupoJerarquico;
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

    public Integer getListadoZonaId() {
        return listadoZonaId;
    }

    public void setListadoZonaId(Integer listadoZonaId) {
        this.listadoZonaId = listadoZonaId;
    }

    public ListadoCMM getListadoZona() {
        return listadoZona;
    }

    public void setListadoZona(ListadoCMM listadoZona) {
        this.listadoZona = listadoZona;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
