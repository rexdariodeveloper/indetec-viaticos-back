package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblMenuPrincipal")
public class MenuPrincipal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "NodoMenuId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Etiqueta" , nullable = false)
    private String etiqueta;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "TipoNodoId", nullable = false)
    private Integer tipoNodoId;

    @Column(name = "NodoPadreId")
    private Integer nodoPadreId;

    @OneToMany
    @JoinColumn(name="NodoPadreId", referencedColumnName = "NodoMenuId", insertable = false, updatable = false)
    private List<MenuPrincipal>  nodosHijos;

    @Column(name = "SistemaAccesoId", nullable = false)
    private Integer sistemaAccesoId;

    @Column(name = "Url")
    private String url;

    @Column(name = "Icono")
    private String icono;

    @Column(name = "AdmitePermiso", nullable = false)
    private Boolean admitePermiso;

    @Column(name = "Orden", nullable = false)
    private Integer orden;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @Column ( name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipoNodoId() {
        return tipoNodoId;
    }

    public void setTipoNodoId(Integer tipoNodoId) {
        this.tipoNodoId = tipoNodoId;
    }

    public Integer getNodoPadreId() {
        return nodoPadreId;
    }

    public void setNodoPadreId(Integer nodoPadreId) {
        this.nodoPadreId = nodoPadreId;
    }

    public List<MenuPrincipal> getNodosHijos() {
        return nodosHijos;
    }

    public void setNodosHijos(List<MenuPrincipal> nodosHijos) {
        this.nodosHijos = nodosHijos;
    }

    public Integer getSistemaAccesoId() {
        return sistemaAccesoId;
    }

    public void setSistemaAccesoId(Integer sistemaAccesoId) {
        this.sistemaAccesoId = sistemaAccesoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean getAdmitePermiso() {
        return admitePermiso;
    }

    public void setAdmitePermiso(Boolean admitePermiso) {
        this.admitePermiso = admitePermiso;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
