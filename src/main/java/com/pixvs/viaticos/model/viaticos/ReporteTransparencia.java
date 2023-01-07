package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.sql.Timestamp;

public class ReporteTransparencia {

    private Integer Id;
    private String NombreEnte;
    private String NumeroSolicitud;
    private Integer Ejercicio;
    private String FechaInicio;
    private String FechaFin;
    private String Solicitante;
    private String TipoIntegrante;
    private String ClavePuesto;
    private String Puesto;
    private String Cargo;
    private String AreaAdscripcion;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String TipoGasto;
    private String DescripcionComision;
    private String TipoViaje;
    private String Acompanantes;
    private String MontoAcompanantes;
    private String CiudadOrigen;
    private String EstadoOrigen;
    private String PaisOrigen;
    private String CiudadDestino;
    private String EstadoDestino;
    private String PaisDestino;
    private String Motivo;
    private String FechaSalida;
    private String FechaRegreso;
    private String TotalErogado;
    private String NoErogado;
    private String FechaSalidaRegreso;
    private String FechaFinalizacion;
    private String FechaFinalizacionFormato;
    private String Informe;
    private String LinkInforme;
    private String Normativa;
    private String LinkNormativa;
    private String AreaResponsableTransparencia;
    private String FechaValidacion;
    private String FechaActualizacion;
    private String Notas;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombreEnte() {
        return NombreEnte;
    }

    public void setNombreEnte(String nombreEnte) {
        NombreEnte = nombreEnte;
    }

    public String getNumeroSolicitud() {
        return NumeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        NumeroSolicitud = numeroSolicitud;
    }

    public Integer getEjercicio() {
        return Ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        Ejercicio = ejercicio;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public String getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(String solicitante) {
        Solicitante = solicitante;
    }

    public String getTipoIntegrante() {
        return TipoIntegrante;
    }

    public void setTipoIntegrante(String tipoIntegrante) {
        TipoIntegrante = tipoIntegrante;
    }

    public String getClavePuesto() {
        return ClavePuesto;
    }

    public void setClavePuesto(String clavePuesto) {
        ClavePuesto = clavePuesto;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public String getAreaAdscripcion() {
        return AreaAdscripcion;
    }

    public void setAreaAdscripcion(String areaAdscripcion) {
        AreaAdscripcion = areaAdscripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        PrimerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public String getTipoGasto() {
        return TipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        TipoGasto = tipoGasto;
    }

    public String getDescripcionComision() {
        return DescripcionComision;
    }

    public void setDescripcionComision(String descripcionComision) {
        DescripcionComision = descripcionComision;
    }

    public String getTipoViaje() {
        return TipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        TipoViaje = tipoViaje;
    }

    public String getAcompanantes() {
        return Acompanantes;
    }

    public void setAcompanantes(String acompanantes) {
        Acompanantes = acompanantes;
    }

    public String getMontoAcompanantes() {
        return MontoAcompanantes;
    }

    public void setMontoAcompanantes(String montoAcompanantes) {
        MontoAcompanantes = montoAcompanantes;
    }

    public String getCiudadOrigen() {
        return CiudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        CiudadOrigen = ciudadOrigen;
    }

    public String getEstadoOrigen() {
        return EstadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        EstadoOrigen = estadoOrigen;
    }

    public String getPaisOrigen() {
        return PaisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        PaisOrigen = paisOrigen;
    }

    public String getCiudadDestino() {
        return CiudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        CiudadDestino = ciudadDestino;
    }

    public String getEstadoDestino() {
        return EstadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        EstadoDestino = estadoDestino;
    }

    public String getPaisDestino() {
        return PaisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        PaisDestino = paisDestino;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
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
        FechaRegreso = fechaRegreso;
    }

    public String getTotalErogado() {
        return TotalErogado;
    }

    public void setTotalErogado(String totalErogado) {
        TotalErogado = totalErogado;
    }

    public String getNoErogado() {
        return NoErogado;
    }

    public void setNoErogado(String noErogado) {
        NoErogado = noErogado;
    }

    public String getFechaSalidaRegreso() {
        return FechaSalidaRegreso;
    }

    public void setFechaSalidaRegreso(String fechaSalidaRegreso) {
        FechaSalidaRegreso = fechaSalidaRegreso;
    }

    public String getFechaFinalizacion() {
        return FechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        FechaFinalizacion = fechaFinalizacion;
    }

    public String getFechaFinalizacionFormato() {
        return FechaFinalizacionFormato;
    }

    public void setFechaFinalizacionFormato(String fechaFinalizacionFormato) {
        FechaFinalizacionFormato = fechaFinalizacionFormato;
    }

    public String getInforme() {
        return Informe;
    }

    public void setInforme(String informe) {
        Informe = informe;
    }

    public String getLinkInforme() {
        return LinkInforme;
    }

    public String getNormativa() {
        return Normativa;
    }

    public void setNormativa(String normativa) {
        Normativa = normativa;
    }

    public void setLinkInforme(String linkInforme) {
        LinkInforme = linkInforme;
    }

    public String getLinkNormativa() {
        return LinkNormativa;
    }

    public void setLinkNormativa(String linkNormativa) {
        LinkNormativa = linkNormativa;
    }

    public String getAreaResponsableTransparencia() {
        return AreaResponsableTransparencia;
    }

    public void setAreaResponsableTransparencia(String areaResponsableTransparencia) {
        AreaResponsableTransparencia = areaResponsableTransparencia;
    }

    public String getFechaValidacion() {
        return FechaValidacion;
    }

    public void setFechaValidacion(String fechaValidacion) {
        FechaValidacion = fechaValidacion;
    }

    public String getFechaActualizacion() {
        return FechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        FechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }
}
