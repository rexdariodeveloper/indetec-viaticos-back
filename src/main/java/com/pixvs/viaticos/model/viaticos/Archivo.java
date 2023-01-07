package com.pixvs.viaticos.model.viaticos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tblArchivo")
public class Archivo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ArchivoId", nullable = false, insertable = false, updatable = false)
    private String id;

    @Column(name = "NombreOriginal", nullable = false, updatable = false)
    private String nombreOriginal;

    @Column(name = "NombreFisico", nullable = false, updatable = false)
    private String nombreFisico;

    @Column(name = "ReferenciaId", nullable = false, updatable = false)
    private Integer referenciaId;

    @Column(name = "OrigenArchivoId", nullable = false, updatable = false)
    private Integer origenArchivoId;

    @Column(name = "RutaFisica", nullable = false, updatable = false)
    private String rutaFisica;

    @Column(name = "TipoArchivoId", nullable = false, updatable = false)
    private Integer tipoArchivoId;

    @Column(name = "Vigente", nullable = false)
    private Boolean vigente;

    @Column(name = "Exportado", nullable = false)
    private boolean exportado;

    @Column(name = "FechaExportado")
    private Timestamp fechaExportado;

    @Column(name = "FechaCreacion", nullable = false, insertable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false, updatable = false)
    private Integer creadoPorId;

    @OneToOne
    @JoinColumn(name = "CreadoPorId", referencedColumnName = "UsuarioId", insertable = false, updatable = false)
    private Usuario creadoPor;

    @Column(name = "FechaUltimaModificacion", nullable = false, insertable = false)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId", nullable = false, insertable = false)
    private Integer modificadoPorId;

    @Column(name = "Timestamp", nullable = false, insertable = false, updatable = false)
    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getNombreFisico() {
        return nombreFisico;
    }

    public void setNombreFisico(String nombreFisico) {
        this.nombreFisico = nombreFisico;
    }

    public Integer getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(Integer referenciaId) {
        this.referenciaId = referenciaId;
    }

    public Integer getOrigenArchivoId() {
        return origenArchivoId;
    }

    public void setOrigenArchivoId(Integer origenArchivoId) {
        this.origenArchivoId = origenArchivoId;
    }

    public String getRutaFisica() {
        return rutaFisica;
    }

    public void setRutaFisica(String rutaFisica) {
        this.rutaFisica = rutaFisica;
    }

    public Integer getTipoArchivoId() {
        return tipoArchivoId;
    }

    public void setTipoArchivoId(Integer tipoArchivoId) {
        this.tipoArchivoId = tipoArchivoId;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isExportado() {
        return exportado;
    }

    public void setExportado(boolean exportado) {
        this.exportado = exportado;
    }

    public Timestamp getFechaExportado() {
        return fechaExportado;
    }

    public void setFechaExportado(Timestamp fechaExportado) {
        this.fechaExportado = fechaExportado;
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

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
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
