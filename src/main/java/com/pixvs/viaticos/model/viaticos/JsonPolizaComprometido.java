package com.pixvs.viaticos.model.viaticos;

import java.util.List;

public class JsonPolizaComprometido {

    private String FechaComprometido;
    private String FechaCreacion;
    private String FechaSalida;
    private String FechaRegreso;
    private String NumeroSolicitud;
    private String NoOficio;
    private String DescripcionComision;
    private Integer EmpleadoId;
    private String Nombre;
    private String ApellidoPat;
    private String ApellidoMat;
    private char Estatus;
    private String RamoId;
    private String ProyectoId;
    private String DependenciaId;
    private Integer CatalogoCuentaId;
    private Integer SolicitudViaticoId;
    private String FechaCancelacionPoliza;
    private List<JsonCtasPolizaComprometido> Ctas;
    
    public JsonPolizaComprometido(Asignacion asignacion, List<JsonCtasPolizaComprometido> ctas) {
        setFechaComprometido(asignacion.getFechaComprometido().toString());
        setFechaCreacion(asignacion.getSolicitudViatico().getFechaCreacion().toString());
        setFechaSalida(asignacion.getSolicitudViatico().getFechaSalida().toString());
        setFechaRegreso(asignacion.getSolicitudViatico().getFechaRegreso().toString());
        setNumeroSolicitud(asignacion.getSolicitudViatico().getNumeroSolicitud());
        setNoOficio(asignacion.getSolicitudViatico().getNoOficio());
        setDescripcionComision(asignacion.getSolicitudViatico().getDescripcionComision());
        setEmpleadoId(asignacion.getSolicitudViatico().getEmpleadoId());
        setNombre(asignacion.getSolicitudViatico().getEmpleado().getNombre());
        setApellidoPat(asignacion.getSolicitudViatico().getEmpleado().getPrimerApellido());
        setApellidoMat(asignacion.getSolicitudViatico().getEmpleado().getSegundoApellido() != null ? asignacion.getSolicitudViatico().getEmpleado().getSegundoApellido() : "");
        setEstatus('A');
        setRamoId(asignacion.getSolicitudViatico().getRamoId());
        setProyectoId(asignacion.getSolicitudViatico().getProyectoId());
        setCatalogoCuentaId(asignacion.getSolicitudViatico().getEmpleado().getCatalogoCuentaId());
        setSolicitudViaticoId(asignacion.getSolicitudViaticoId());
        setDependenciaId(asignacion.getSolicitudViatico().getDependenciaId());
        setCtas(ctas);
    }
    public String getFechaCancelacionPoliza() {
        return FechaCancelacionPoliza;
    }

    public void setFechaCancelacionPoliza(String fechaCancelacionPoliza) {
        FechaCancelacionPoliza = fechaCancelacionPoliza;
    }
    public String getFechaComprometido() {
        return FechaComprometido;
    }

    public void setFechaComprometido(String fechaComprometido) {
        FechaComprometido = fechaComprometido;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        FechaSalida = fechaSalida;
    }

    public String getFechaRegreso() {
        return FechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        FechaRegreso= fechaRegreso;
    }

    public String getNumeroSolicitud() {
        return NumeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        NumeroSolicitud = numeroSolicitud;
    }

    public String getNoOficio() {
        return NoOficio;
    }

    public void setNoOficio(String noOficio) {
        NoOficio = noOficio;
    }

    public String getDescripcionComision() {
        return DescripcionComision;
    }

    public void setDescripcionComision(String descripcionComision) {
        DescripcionComision = descripcionComision;
    }

    public Integer getEmpleadoId() {
        return EmpleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.EmpleadoId = empleadoId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPat() {
        return ApellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        ApellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return ApellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        ApellidoMat = apellidoMat;
    }

    public char getEstatus() {
        return Estatus;
    }

    public void setEstatus(char estatus) {
        Estatus = estatus;
    }

    public String getRamoId() {
        return RamoId;
    }

    public void setRamoId(String ramoId) {
        RamoId = ramoId;
    }

    public String getProyectoId() {
        return ProyectoId;
    }

    public void setProyectoId(String proyectoId) {
        ProyectoId = proyectoId;
    }

    public String getDependenciaId() {
        return DependenciaId;
    }

    public void setDependenciaId(String dependenciaId) {
        DependenciaId = dependenciaId;
    }

    public Integer getCatalogoCuentaId() {
        return CatalogoCuentaId;
    }

    public void setCatalogoCuentaId(Integer catalogoCuentaId) {
        CatalogoCuentaId = catalogoCuentaId;
    }

    public Integer getSolicitudViaticoId() {
        return SolicitudViaticoId;
    }

    public void setSolicitudViaticoId(Integer solicitudViaticoId) {
        SolicitudViaticoId = solicitudViaticoId;
    }

    public List<JsonCtasPolizaComprometido> getCtas() {
        return Ctas;
    }

    public void setCtas(List<JsonCtasPolizaComprometido> ctas) {
        Ctas = ctas;
    }
}