package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;

@Entity
@Table(name = "tblAlertaEtapaAccion")
public class AlertaEtapaAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlertaEtapaAccionId", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "EtapaId", insertable = false, updatable = false)
    private Integer etapaId;

    @OneToOne
    @JoinColumn(name="EtapaId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM etapa;

    @Column(name = "AccionId", insertable = false, updatable = false)
    private Integer accionId;

    @OneToOne
    @JoinColumn(name="AccionId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM accion;

    @Column(name = "PermiteAutorizacion", insertable = false, updatable = false)
    private Boolean permiteAutorizacion;

    @Column(name = "PermiteNotificacion", insertable = false, updatable = false)
    private Boolean permiteNotificacion;

    @Column(name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Integer etapaId) {
        this.etapaId = etapaId;
    }

    public ListadoCMM getEtapa() {
        return etapa;
    }

    public void setEtapa(ListadoCMM etapa) {
        this.etapa = etapa;
    }

    public Integer getAccionId() {
        return accionId;
    }

    public void setAccionId(Integer accionId) {
        this.accionId = accionId;
    }

    public ListadoCMM getAccion() {
        return accion;
    }

    public void setAccion(ListadoCMM accion) {
        this.accion = accion;
    }

    public Boolean getPermiteAutorizacion() {
        return permiteAutorizacion;
    }

    public void setPermiteAutorizacion(Boolean permiteAutorizacion) {
        this.permiteAutorizacion = permiteAutorizacion;
    }

    public Boolean getPermiteNotificacion() {
        return permiteNotificacion;
    }

    public void setPermiteNotificacion(Boolean permiteNotificacion) {
        this.permiteNotificacion = permiteNotificacion;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
