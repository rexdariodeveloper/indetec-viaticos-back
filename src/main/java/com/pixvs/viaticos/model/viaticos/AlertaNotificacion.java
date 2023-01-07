package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblAlertaNotificacion")
public class AlertaNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlertaNotificacionId", insertable = false, updatable = false)
    private Long id;

    @Column(name = "AlertaId" )
    private Long alertaId;

    @Column(name = "EmpleadoId" )
    private Integer empleadoId;

    @Column(name = "FechaCreacion" )
    private Timestamp fechaCreacion;

    @Column(name = "FechaAtentido" )
    private Timestamp fechaAtendido;

    @Column(name = "Vista" )
    private Boolean vista;

    @Column(name = "Timestamp" )
    private String timestap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlertaId() {
        return alertaId;
    }

    public void setAlertaId(Long alertaId) {
        this.alertaId = alertaId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaAtendido() {
        return fechaAtendido;
    }

    public void setFechaAtendido(Timestamp fechaAtendido) {
        this.fechaAtendido = fechaAtendido;
    }

    public Boolean getVista() {
        return vista;
    }

    public void setVista(Boolean vista) {
        this.vista = vista;
    }

    public String getTimestap() {
        return timestap;
    }

    public void setTimestap(String timestap) {
        this.timestap = timestap;
    }
}
