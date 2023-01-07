package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblorganigrama")
public class Organigrama {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "NodoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NodoPadreId", nullable = true)
    private Integer nodoPadreId;

    @Column(name = "Clave", nullable = false)
    private String clave;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "ResponsableId", nullable = false)
    private Integer responsableId;

    @Column(name = "PermiteAutorizacion", nullable = false)
    private Boolean permiteAutorizacion;

    @Column(name = "Orden", nullable = false)
    private Integer orden;

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

    public Integer getNodoPadreId() {
        return nodoPadreId;
    }

    public void setNodoPadreId(Integer nodoPadreId) {
        this.nodoPadreId = nodoPadreId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Integer responsableId) {
        this.responsableId = responsableId;
    }

    public Boolean getPermiteAutorizacion() {
        return permiteAutorizacion;
    }

    public void setPermiteAutorizacion(Boolean permiteAutorizacion) {
        this.permiteAutorizacion = permiteAutorizacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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
