package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblPermisoAcceso")
public class PermisoAcceso extends  Object{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PermisoAccesoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "TipoPermisoId", nullable = false)
    private int tipoPermisoId;

    @Column(name = "UsuarioId", nullable = false)
    private int usuarioId;

    @Column(name = "Borrado", nullable = false)
    private boolean borrado;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false, updatable = false)
    private int creadoPorId;

    @Column(name = "FechaUltimaModificacion")
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId")
    private Integer modificadoPorId;

    @Column(name = "Timestamp", nullable = false, insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoPermisoId() {
        return tipoPermisoId;
    }

    public void setTipoPermisoId(int tipoPermisoId) {
        this.tipoPermisoId = tipoPermisoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCreadoPorId() {
        return creadoPorId;
    }

    public void setCreadoPorId(int creadoPorId) {
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
