package com.pixvs.viaticos.model.viaticos;

import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tblEmpleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "EmpleadoId", insertable = false,updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NumeroEmpleado",  nullable = false)
    private String numeroEmpleado;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "PrimerApellido", nullable = false)
    private String primerApellido;

    @Column(name = "SegundoApellido", nullable = true)
    private String segundoApellido;

    @Transient
    String nombreCompleto;

    @Column(name = "TipoEmpleadoId", nullable = true)
    private Integer tipoEmpleadoId;

    @Column(name = "AreaAdscripcionId", nullable = false)
    private Integer areaAdscripcionId;

    @OneToOne
    @JoinColumn(name="AreaAdscripcionId", referencedColumnName = "NodoId", insertable = false, updatable = false)
    private Organigrama areaAdscripcion;

    @Column(name = "PuestoId", nullable = false)
    private Integer puestoId;

    @OneToOne
    @JoinColumn(name="PuestoId", referencedColumnName = "PuestoId", insertable = false, updatable = false)
    private ListadoPuesto puesto;

    @Column(name = "CargoId", nullable = false)
    private Integer cargoId;

    @OneToOne
    @JoinColumn(name="CargoId", referencedColumnName = "CargoId", insertable = false, updatable = false)
    private ListadoCargo cargo;

    @Column(name = "EmailInstitucional", nullable = true)
    private String emailInstitucional;

    @Column(name = "EmailPersonal", nullable = true)
    private String emailPersonal;

    @Column(name = "Fotografia", nullable = true)
    private String fotografia;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false, updatable = false)
    private Integer creadoPorId;

    @Column(name = "FechaUltimaModificacion", nullable = true)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId", nullable = true)
    private Integer modificadoPorId;

    @Column(name = "RFC", nullable = false)
    private String rfc;

    @Column(name = "Cuenta", nullable = false)
    private String cuenta;

    @Column(name = "CatalogoCuentaId", nullable = false)
    private Integer catalogoCuentaId;

    @Column(name = "NombreCuenta", nullable = false)
    private String nombreCuenta;

    @Column ( name = "Timestamp", insertable = false, updatable = false)
    private String timestamp;

    @Transient
    private String nombreArchivoTemporal;

    @Transient
    private InfoArchivoProjection archivoFotografia;

    @Transient
    private boolean fotografiaEliminada;

    @Transient
    private boolean crearSolicitudesTerceros;

    @Transient
    private boolean visualizarSolicitudesTerceros;

    @Transient
    private List<Integer> listadoOrganigrama;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + primerApellido + (segundoApellido != null && !segundoApellido.isEmpty() ? " " + segundoApellido : "");
    }

    public Integer getTipoEmpleadoId() {
        return tipoEmpleadoId;
    }

    public void setTipoEmpleadoId(Integer tipoEmpleadoId) {
        this.tipoEmpleadoId = tipoEmpleadoId;
    }

    public Integer getAreaAdscripcionId() {
        return areaAdscripcionId;
    }

    public void setAreaAdscripcionId(Integer areaAdscripcionId) {
        this.areaAdscripcionId = areaAdscripcionId;
    }

    public Organigrama getAreaAdscripcion() {
        return areaAdscripcion;
    }

    public void setAreaAdscripcion(Organigrama areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    public Integer getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(Integer puestoId) {
        this.puestoId = puestoId;
    }

    public ListadoPuesto getPuesto() {
        return puesto;
    }

    public void setPuesto(ListadoPuesto puesto) {
        this.puesto = puesto;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public ListadoCargo getCargo() {
        return cargo;
    }

    public void setCargo(ListadoCargo cargo) {
        this.cargo = cargo;
    }

    public String getEmailInstitucional() {
        return emailInstitucional.replace(',','.').trim();
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional.replace(',','.').trim();
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal.replace(',','.').trim();
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getCatalogoCuentaId() {
        return catalogoCuentaId;
    }

    public void setCatalogoCuentaId(Integer catalogoCuentaId) {
        this.catalogoCuentaId = catalogoCuentaId;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNombreArchivoTemporal() {
        return nombreArchivoTemporal;
    }

    public void setNombreArchivoTemporal(String nombreArchivoTemporal) {
        this.nombreArchivoTemporal = nombreArchivoTemporal;
    }

    public InfoArchivoProjection getArchivoFotografia() {
        return archivoFotografia;
    }

    public void setArchivoFotografia(InfoArchivoProjection archivoFotografia) {
        this.archivoFotografia = archivoFotografia;
    }

    public boolean isFotografiaEliminada() {
        return fotografiaEliminada;
    }

    public void setFotografiaEliminada(boolean fotografiaEliminada) {
        this.fotografiaEliminada = fotografiaEliminada;
    }

    public boolean isCrearSolicitudesTerceros() {
        return crearSolicitudesTerceros;
    }

    public void setCrearSolicitudesTerceros(boolean crearSolicitudesTerceros) {
        this.crearSolicitudesTerceros = crearSolicitudesTerceros;
    }

    public boolean isVisualizarSolicitudesTerceros() {
        return visualizarSolicitudesTerceros;
    }

    public void setVisualizarSolicitudesTerceros(boolean visualizarSolicitudesTerceros) {
        this.visualizarSolicitudesTerceros = visualizarSolicitudesTerceros;
    }

    public List<Integer> getListadoOrganigrama() {
        return listadoOrganigrama;
    }

    public void setListadoOrganigrama(List<Integer> listadoOrganigrama) {
        this.listadoOrganigrama = listadoOrganigrama;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
