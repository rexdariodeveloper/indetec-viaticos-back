package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "tblListadoEsquemaFormato")
public class ListadoEsquemaFormato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "EsquemaFormatoId",insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "EsquemaId", nullable = false)
    private Integer esquemaId;

    @Column(name = "NombreCampoTabla", nullable = false)
    private String nombreCampoTabla;

    @Column(name = "Etiqueta", nullable = false)
    private String etiqueta;

    @Column(name = "TipoDato", nullable = false)
    private String tipoDato;

    @Column(name = "Tamanio", nullable = true)
    private Integer tamanio;

    @Column(name = "Orden", nullable = false)
    private Integer orden;

    @Column(name = "Editable", nullable = false)
    private Boolean editable;

    @Column(name = "Excluyente", nullable = false)
    private Boolean excluyente;

    @Column(name = "Requerido", nullable = false)
    private Boolean requerido;

    @Column(name = "Visible", nullable = false)
    private Boolean visible;

    @Column(name = "Timestamp", insertable = false,updatable = false, nullable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEsquemaId() {
        return esquemaId;
    }

    public void setEsquemaId(Integer esquemaId) {
        this.esquemaId = esquemaId;
    }

    public String getNombreCampoTabla() {
        return nombreCampoTabla;
    }

    public void setNombreCampoTabla(String nombreCampoTabla) {
        this.nombreCampoTabla = nombreCampoTabla;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getExcluyente() {
        return excluyente;
    }

    public void setExcluyente(Boolean excluyente) {
        this.excluyente = excluyente;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
