package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblAlerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlertaId", insertable = false, updatable = false)
    private Long id;

    @Column(name = "AlertaDefinicionId" )
    private Integer alertaDefinicionId;

    @Column(name = "ReferenciaProcesoId")
    private Integer referenciaProcesoId;

    @Column(name = "CodigoTramite")
    private String codigoTramite;

    @Column(name = "TextoRepresentativo")
    private String textoRepresentativo;

    @Column(name = "EstatusId")
    private Integer estatusId;

    @Column(name = "CreadoPorId")
    private Integer creadoPorId;

    @Column(name = "FechaCreacion", nullable = true)
    private Timestamp fechaCreacion;

    @Column(name = "FechaFinalizacion", nullable = true)
    private Timestamp fechaFinalizacion;

    @Column ( name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAlertaDefinicionId() {
        return alertaDefinicionId;
    }

    public void setAlertaDefinicionId(Integer alertaDefinicionId) {
        this.alertaDefinicionId = alertaDefinicionId;
    }

    public Integer getReferenciaProcesoId() {
        return referenciaProcesoId;
    }

    public void setReferenciaProcesoId(Integer referenciaProcesoId) {
        this.referenciaProcesoId = referenciaProcesoId;
    }

    public String getCodigoTramite() {
        return codigoTramite;
    }

    public void setCodigoTramite(String codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

    public String getTextoRepresentativo() {
        return textoRepresentativo;
    }

    public void setTextoRepresentativo(String textoRepresentativo) {
        this.textoRepresentativo = textoRepresentativo;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public Integer getCreadoPorId() {
        return creadoPorId;
    }

    public void setCreadoPorId(Integer creadoPorId) {
        this.creadoPorId = creadoPorId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
