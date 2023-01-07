package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblCiudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CiudadId", nullable = false, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "EstadoId", nullable = false)
    private int estadoId;

    @OneToOne
    @JoinColumn(name="EstadoId", referencedColumnName = "EstadoId", insertable = false, updatable = false)
    private Estado estado;

    @Column(name = "PaisId", nullable = false)
    private int paisId;

    @OneToOne
    @JoinColumn(name="PaisId", referencedColumnName = "PaisId", insertable = false, updatable = false)
    private Pais pais;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "ZonaEconomicaId", nullable = false)
    private int zonaEconomicaId;

    @OneToOne
    @JoinColumn(name="ZonaEconomicaId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM zonaEconomica;

    @Column(name = "EstatusId", nullable = false)
    private int estatusId;

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

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getZonaEconomicaId() {
        return zonaEconomicaId;
    }

    public void setZonaEconomicaId(int zonaEconomicaId) {
        this.zonaEconomicaId = zonaEconomicaId;
    }

    public ListadoCMM getZonaEconomica() {
        return zonaEconomica;
    }

    public void setZonaEconomica(ListadoCMM zonaEconomica) {
        this.zonaEconomica = zonaEconomica;
    }

    public int getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(int estatusId) {
        this.estatusId = estatusId;
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
