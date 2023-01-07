package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblConceptoViatico")
public class ConceptoViatico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ConceptoViaticoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "CategoriaId", nullable = true)
    private Integer categoriaId;

    @OneToOne
    @JoinColumn(name="CategoriaId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM categoria;

    @Column(name = "Codigo", nullable = false)
    private String codigo;

    @Column(name = "Concepto", nullable = false)
    private String concepto;

    @Column(name = "ObjetoGastoId", nullable = true)
    private Integer objetoGastoId;

    @Column(name = "NoPermitirSinPernocta", nullable = false)
    private boolean noPermitirSinPernocta;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaCreacion", nullable = false)
    private Timestamp FechaCreacion;

    @Column(name = "CreadoPorId", nullable = false)
    private Integer CreadoPorId;

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

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public ListadoCMM getCategoria() {
        return categoria;
    }

    public void setCategoria(ListadoCMM categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getObjetoGastoId() {
        return objetoGastoId;
    }

    public void setObjetoGastoId(Integer objetoGastoId) {
        this.objetoGastoId = objetoGastoId;
    }

    public boolean isNoPermitirSinPernocta() {
        return noPermitirSinPernocta;
    }

    public void setNoPermitirSinPernocta(boolean noPermitirSinPernocta) {
        this.noPermitirSinPernocta = noPermitirSinPernocta;
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
        return FechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public Integer getCreadoPorId() {
        return CreadoPorId;
    }

    public void setCreadoPorId(Integer creadoPorId) {
        CreadoPorId = creadoPorId;
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
