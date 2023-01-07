package com.pixvs.viaticos.model.viaticos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViatico")
public class SolicitudViatico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NumeroSolicitud", updatable = false, nullable = false)
    private String numeroSolicitud;

    @Column(name = "EmpleadoId", updatable = false, nullable = false)
    private Integer empleadoId;

    @OneToOne
    @JoinColumn(name="EmpleadoId", referencedColumnName = "EmpleadoId", insertable = false, updatable = false)
    private Empleado empleado;

    @Column(name = "PuestoId" , nullable = false)
    private Integer puestoId;

    @Column(name = "Puesto" , nullable = false)
    private String puesto;

    @Column(name = "CargoId", nullable = false)
    private Integer cargoId;

    @Column(name = "Cargo" , nullable = false)
    private String cargo;

    @Column(name = "AreaAdscripcionId", nullable = false)
    private Integer areaAdscripcionId;

    @Column(name = "AreaAdscripcion")
    private String areaAdscripcion;

    @Column(name = "Ejercicio", nullable = false)
    private int ejercicio;

    @Column(name = "ProgramaGobiernoId", nullable = false)
    private String programaGobiernoId;

    @Column(name = "ProgramaGobierno", nullable = false)
    private String programaGobierno;

    @Column(name = "ProyectoId", nullable = false)
    private String proyectoId;

    @Column(name = "Proyecto", nullable = false)
    private String proyecto;

    @Column(name = "RamoId", nullable = false)
    private String ramoId;

    @Column(name = "Ramo", nullable = false)
    private String ramo;

    @Column(name = "DependenciaId", nullable = false)
    private String dependenciaId;

    @Column(name = "Dependencia", nullable = false)
    private String dependencia;

    @Column(name = "NoOficio", nullable = false)
    private String noOficio;

    @Column(name = "TipoViajeId", nullable = false)
    private Integer tipoViajeId;

    @Column(name = "TipoViaje", nullable = false)
    private String tipoViaje;

    @Column(name = "TipoRepresentacionId", nullable = false)
    private Integer tipoRepresentacionId;

    @Column(name = "TipoRepresentacion", nullable = false)
    private String tipoRepresentacion;

    @Column(name = "FechaSalida", nullable = false)
    private Timestamp fechaSalida;

    @Column(name = "FechaRegreso", nullable = false)
    private Timestamp fechaRegreso;

    @Column(name = "DuracionComision")
    private String duracionComision;

    @Column(name = "PaisOrigenId", nullable = false)
    private Integer paisOrigenId;

    @Column(name = "EstadoOrigenId", nullable = false)
    private Integer estadoOrigenId;

    @Column(name = "CiudadOrigenId", nullable = false)
    private Integer ciudadOrigenId;

    @OneToOne
    @JoinColumn(name="CiudadOrigenId", referencedColumnName = "CiudadId", insertable = false, updatable = false)
    private Ciudad ciudadOrigen;

    @Column(name = "PaisDestinoId", nullable = false)
    private Integer paisDestinoId;

    @Column(name = "EstadoDestinoId", nullable = false)
    private Integer estadoDestinoId;

    @OneToOne
    @JoinColumn(name="EstadoDestinoId", referencedColumnName = "EstadoId", insertable = false, updatable = false)
    private Estado estadoDestino;

    @Column(name = "CiudadDestinoId", nullable = false)
    private Integer ciudadDestinoId;

    @OneToOne
    @JoinColumn(name="CiudadDestinoId", referencedColumnName = "CiudadId", insertable = false, updatable = false)
    private Ciudad ciudadDestino;

    @Column(name = "GastoRepresentacionId", nullable = true)
    private Integer gastoRepresentacionId;

    @Column(name = "GastoRepresentacion", nullable = true)
    private String gastoRepresentacion;

    @Column(name = "Sugerencias")
    private String sugerencias;

    @Column(name = "FechaInicioEvento", nullable = false)
    private Timestamp fechaInicioEvento;

    @Column(name = "FechaFinEvento", nullable = false)
    private Timestamp fechaFinEvento;

    @Column(name = "DuracionEvento")
    private String duracionEvento;

    @Column(name = "Motivo", nullable = false)
    private String motivo;

    @Column(name = "DescripcionComision", nullable = false)
    private String descripcionComision;

    @Column(name = "Organizador", nullable = false)
    private String organizador;

    @Column(name = "LinkEvento")
    private String linkEvento;

    @Column(name = "ArchivoInformeId", nullable = true)
    private String archivoInformeId;

    @Column(name = "OficioComisionArchivoId", nullable = true)
    private String oficioComisionArchivoId;

    @Column(name = "EstatusId", nullable = false)
    private int estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "AutorizadoPorId")
    private Integer autorizadoPorId;

    @Column(name = "FechaAutorizacion")
    private Timestamp fechaAutorizacion;

    @Column(name = "FechaCreacion", insertable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false , updatable = false)
    private Integer creadoPorId;

    @Column(name = "FechaUltimaModificacion")
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId")
    private Integer modificadoPorId;

    @Column ( name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Integer getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(Integer puestoId) {
        this.puestoId = puestoId;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getAreaAdscripcionId() {
        return areaAdscripcionId;
    }

    public void setAreaAdscripcionId(Integer areaAdscripcionId) {
        this.areaAdscripcionId = areaAdscripcionId;
    }

    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getProgramaGobiernoId() {
        return programaGobiernoId;
    }

    public void setProgramaGobiernoId(String programaGobiernoId) {
        this.programaGobiernoId = programaGobiernoId;
    }

    public String getProgramaGobierno() {
        return programaGobierno;
    }

    public void setProgramaGobierno(String programaGobierno) {
        this.programaGobierno = programaGobierno;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getRamoId() {
        return ramoId;
    }

    public void setRamoId(String ramoId) {
        this.ramoId = ramoId;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(String dependenciaId) {
        this.dependenciaId = dependenciaId;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getNoOficio() {
        return noOficio;
    }

    public void setNoOficio(String noOficio) {
        this.noOficio = noOficio;
    }

    public Integer getTipoViajeId() {
        return tipoViajeId;
    }

    public void setTipoViajeId(Integer tipoViajeId) {
        this.tipoViajeId = tipoViajeId;
    }

    public Integer getTipoRepresentacionId() {
        return tipoRepresentacionId;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public void setTipoRepresentacionId(Integer tipoPresentacionId) {
        this.tipoRepresentacionId = tipoPresentacionId;
    }

    public String getTipoRepresentacion() {
        return tipoRepresentacion;
    }

    public void setTipoRepresentacion(String tipoRepresentacion) {
        this.tipoRepresentacion = tipoRepresentacion;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Timestamp getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Timestamp fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String getDuracionComision() {
        return duracionComision;
    }

    public void setDuracionComision(String duracionComision) {
        this.duracionComision = duracionComision;
    }

    public Integer getPaisOrigenId() {
        return paisOrigenId;
    }

    public void setPaisOrigenId(Integer paisOrigenId) {
        this.paisOrigenId = paisOrigenId;
    }

    public Integer getEstadoOrigenId() {
        return estadoOrigenId;
    }

    public void setEstadoOrigenId(Integer estadoOrigenId) {
        this.estadoOrigenId = estadoOrigenId;
    }

    public Integer getCiudadOrigenId() {
        return ciudadOrigenId;
    }

    public void setCiudadOrigenId(Integer ciudadOrigenId) {
        this.ciudadOrigenId = ciudadOrigenId;
    }

    public Integer getPaisDestinoId() {
        return paisDestinoId;
    }

    public void setPaisDestinoId(Integer paisDestinoId) {
        this.paisDestinoId = paisDestinoId;
    }

    public Integer getEstadoDestinoId() {
        return estadoDestinoId;
    }

    public void setEstadoDestinoId(Integer estadoDestinoId) {
        this.estadoDestinoId = estadoDestinoId;
    }

    public Integer getCiudadDestinoId() {
        return ciudadDestinoId;
    }

    public void setCiudadDestinoId(Integer ciudadDestinoId) {
        this.ciudadDestinoId = ciudadDestinoId;
    }

    public Integer getGastoRepresentacionId() {
        return gastoRepresentacionId;
    }

    public void setGastoRepresentacionId(Integer gastoRepresentacionId) {
        this.gastoRepresentacionId = gastoRepresentacionId;
    }

    public String getGastoRepresentacion() {
        return gastoRepresentacion;
    }

    public void setGastoRepresentacion(String gastoRepresentacion) {
        this.gastoRepresentacion = gastoRepresentacion;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public Timestamp getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    public void setFechaInicioEvento(Timestamp fechaInicioEvento) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    public Timestamp getFechaFinEvento() {
        return fechaFinEvento;
    }

    public void setFechaFinEvento(Timestamp fechaFinEvento) {
        this.fechaFinEvento = fechaFinEvento;
    }

    public String getDuracionEvento() {
        return duracionEvento;
    }

    public void setDuracionEvento(String duracionEvento) {
        this.duracionEvento = duracionEvento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcionComision() {
        return descripcionComision;
    }

    public void setDescripcionComision(String descripcionComision) {
        this.descripcionComision = descripcionComision;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getLinkEvento() {
        return linkEvento;
    }

    public void setLinkEvento(String linkEvento) {
        this.linkEvento = linkEvento;
    }

    public String getArchivoInformeId() {
        return archivoInformeId;
    }

    public void setArchivoInformeId(String archivoInformeId) {
        this.archivoInformeId = archivoInformeId;
    }

    public String getOficioComisionArchivoId() {
        return oficioComisionArchivoId;
    }

    public void setOficioComisionArchivoId(String oficioComisionArchivoId) {
        this.oficioComisionArchivoId = oficioComisionArchivoId;
    }

    public int getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(int estatusId) {
        this.estatusId = estatusId;
    }

    public ListadoCMM getEstatus() {
        return estatus;
    }

    public void setEstatus(ListadoCMM estatus) {
        this.estatus = estatus;
    }

    public Integer getAutorizadoPorId() {
        return autorizadoPorId;
    }

    public void setAutorizadoPorId(Integer autorizadoPorId) {
        this.autorizadoPorId = autorizadoPorId;
    }

    public Timestamp getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Timestamp fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Estado getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(Estado estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }
}
