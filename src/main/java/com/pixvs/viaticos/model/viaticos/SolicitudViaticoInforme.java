package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoInforme")
public class SolicitudViaticoInforme {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoInformeId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionId", nullable = false)
    private Integer solicitudViaticoComprobacionId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionId", referencedColumnName = "SolicitudViaticoComprobacionId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacion solicitudViaticoComprobacion;

    @Column(name = "ObjetivoEstrategico", nullable = true)
    private String objetivoEstrategico;

    @Column(name = "ObjetivoEspecifico", nullable = true)
    private String objetivoEspecifico;

    @Column(name = "ActividadesRealizadas", nullable = true)
    private String actividadesRealizadas;

    @Column(name = "ResultadosObtenidos", nullable = true)
    private String resultadosObtenidos;

    @Column(name = "Contribuciones", nullable = true)
    private String contribuciones;

    @Column(name = "VinculosANotas", nullable = true)
    private String vinculosANotas;

    @Column(name = "ListadoDocumentos", nullable = true)
    private String listadoDocumentos;

    @Column(name = "Conclusiones", nullable = true)
    private String conclusiones;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaCreacion", nullable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false)
    private Integer creadoPorId;

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

    public Integer getSolicitudViaticoComprobacionId() {
        return solicitudViaticoComprobacionId;
    }

    public void setSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) {
        this.solicitudViaticoComprobacionId = solicitudViaticoComprobacionId;
    }

    public SolicitudViaticoComprobacion getSolicitudViaticoComprobacion() {
        return solicitudViaticoComprobacion;
    }

    public void setSolicitudViaticoComprobacion(SolicitudViaticoComprobacion solicitudViaticoComprobacion) {
        this.solicitudViaticoComprobacion = solicitudViaticoComprobacion;
    }

    public String getObjetivoEstrategico() {
        return objetivoEstrategico;
    }

    public void setObjetivoEstrategico(String objetivoEstrategico) {
        this.objetivoEstrategico = objetivoEstrategico;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getActividadesRealizadas() {
        return actividadesRealizadas;
    }

    public void setActividadesRealizadas(String actividadesRealizadas) {
        this.actividadesRealizadas = actividadesRealizadas;
    }

    public String getResultadosObtenidos() {
        return resultadosObtenidos;
    }

    public void setResultadosObtenidos(String resultadosObtenidos) {
        this.resultadosObtenidos = resultadosObtenidos;
    }

    public String getContribuciones() {
        return contribuciones;
    }

    public void setContribuciones(String contribuciones) {
        this.contribuciones = contribuciones;
    }

    public String getVinculosANotas() {
        return vinculosANotas;
    }

    public void setVinculosANotas(String vinculosANotas) {
        this.vinculosANotas = vinculosANotas;
    }

    public String getListadoDocumentos() {
        return listadoDocumentos;
    }

    public void setListadoDocumentos(String listadoDocumentos) {
        this.listadoDocumentos = listadoDocumentos;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
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
