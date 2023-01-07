package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblAlertaConfiguracion")
public class AlertaConfiguracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlertaConfiguracionId", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "AlertaEtapaAccionId")
    private Integer etapaAccionId;

    @OneToOne
    @JoinColumn(name="AlertaEtapaAccionId", referencedColumnName = "AlertaEtapaAccionId", insertable = false, updatable = false)
    private AlertaEtapaAccion etapaAccion;

    @Column(name = "EmpleadoId")
    private Integer empleadoId;

    @OneToOne
    @JoinColumn(name="EmpleadoId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado empleado;

    @Column(name = "FiguraId")
    private Integer figuraId;

    @OneToOne
    @JoinColumn(name="FiguraId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM figura;

    @Column(name = "TipoNotificacionId")
    private Integer tipoNotificacionId;

    @OneToOne
    @JoinColumn(name="TipoNotificacionId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM tipoNotificacion;

    @Column(name = "EnPlataforma")
    private Boolean enPlataforma;

    @Column(name = "EnCorreoElectronico")
    private Boolean enCorreoElectronico;

    @Column(name = "EstatusId")
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaCreacion", insertable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", updatable = false)
    private Integer creadoPorId;

    @OneToOne
    @JoinColumn(name="CreadoPorId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado CreadoPor;

    @Column(name = "FechaUltimaModificacion", insertable = false)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId", insertable = false)
    private Integer modificadoPorId;

    @OneToOne
    @JoinColumn(name="ModificadoPorId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado modificadoPor;

    @Column(name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEtapaAccionId() {
        return etapaAccionId;
    }

    public void setEtapaAccionId(Integer etapaAccionId) {
        this.etapaAccionId = etapaAccionId;
    }

    public AlertaEtapaAccion getEtapaAccion() {
        return etapaAccion;
    }

    public void setEtapaAccion(AlertaEtapaAccion etapaAccion) {
        this.etapaAccion = etapaAccion;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getFiguraId() {
        return figuraId;
    }

    public void setFiguraId(Integer figuraId) {
        this.figuraId = figuraId;
    }

    public ListadoCMM getFigura() {
        return figura;
    }

    public void setFigura(ListadoCMM figura) {
        this.figura = figura;
    }

    public Integer getTipoNotificacionId() {
        return tipoNotificacionId;
    }

    public void setTipoNotificacionId(Integer tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
    }

    public ListadoCMM getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(ListadoCMM tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Boolean getEnPlataforma() {
        return enPlataforma;
    }

    public void setEnPlataforma(Boolean enPlataforma) {
        this.enPlataforma = enPlataforma;
    }

    public Boolean getEnCorreoElectronico() {
        return enCorreoElectronico;
    }

    public void setEnCorreoElectronico(Boolean enCorreoElectronico) {
        this.enCorreoElectronico = enCorreoElectronico;
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

    public Empleado getCreadoPor() {
        return CreadoPor;
    }

    public void setCreadoPor(Empleado creadoPor) {
        CreadoPor = creadoPor;
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

    public Empleado getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Empleado modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
