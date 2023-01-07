package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblListadoEsquema")
public class ListadoEsquema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "EsquemaId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NodoMenuId", nullable = false)
    private Integer nodoMenuId;

    @Column(name = "ListadoId", nullable = false)
    private Integer listadoId;

    @Column(name = "Etiqueta", nullable = false)
    private String etiqueta;

    @Column(name = "NombreTabla", nullable = false)
    private String nombreTabla;

    @Column(name = "CondicionInicial", nullable = true)
    private String condicionInicial;

    @Column(name = "TablaControl", nullable = false)
    private Boolean tablaControl;

    @Column(name = "NombreControl", nullable = true)
    private String nombreControl;

    @Column(name = "PermiteAgregarRegistros", nullable = false)
    private Boolean permiteAgregarRegistros;

    @Column(name = "PermiteEliminarRegistros", nullable = false)
    private Boolean permiteEliminarRegistros;

    @Column(name = "CampoOrden", nullable = false)
    private String campoOrden;

    @Column(name = "Timestamp", insertable = false,updatable = false, nullable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodoMenuId() {
        return nodoMenuId;
    }

    public void setNodoMenuId(Integer nodoMenuId) {
        this.nodoMenuId = nodoMenuId;
    }

    public Integer getListadoId() {
        return listadoId;
    }

    public void setListadoId(Integer listadoId) {
        this.listadoId = listadoId;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getCondicionInicial() {
        return condicionInicial;
    }

    public void setCondicionInicial(String condicionInicial) {
        this.condicionInicial = condicionInicial;
    }

    public Boolean getTablaControl() {
        return tablaControl;
    }

    public void setTablaControl(Boolean tablaControl) {
        this.tablaControl = tablaControl;
    }

    public String getNombreControl() {
        return nombreControl;
    }

    public void setNombreControl(String nombreControl) {
        this.nombreControl = nombreControl;
    }

    public Boolean getPermiteAgregarRegistros() {
        return permiteAgregarRegistros;
    }

    public void setPermiteAgregarRegistros(Boolean permiteAgregarRegistros) {
        this.permiteAgregarRegistros = permiteAgregarRegistros;
    }

    public Boolean getPermiteEliminarRegistros() {
        return permiteEliminarRegistros;
    }

    public void setPermiteEliminarRegistros(Boolean permiteEliminarRegistros) {
        this.permiteEliminarRegistros = permiteEliminarRegistros;
    }

    public String getCampoOrden() {
        return campoOrden;
    }

    public void setCampoOrden(String campoOrden) {
        this.campoOrden = campoOrden;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
