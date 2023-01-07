package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reporteTransparenciaSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ReporteTransparenciaSolicitudViaticoProjection {

    Integer getId();
    String getNombreEnte();
    String getNumeroSolicitud();
    Integer getEjercicio();
    String getFechaInicio();
    String getFechaFin();
    String getSolicitante();
    String getTipoIntegrante();
    String getClavePuesto();
    String getPuesto();
    String getCargo();
    String getAreaAdscripcion();
    String getNombre();
    String getPrimerApellido();
    String getSegundoApellido();
    String getTipoGasto();
    String getDescripcionComision();
    String getTipoViaje();
    String getAcompanantes();
    String getMontoAcompanantes();
    String getCiudadOrigen();
    String getEstadoOrigen();
    String getPaisOrigen();
    String getCiudadDestino();
    String getEstadoDestino();
    String getPaisDestino();
    String getMotivo();
    String getFechaSalida();
    String getFechaRegreso();
    String getTotalErogado();
    String getNoErogado();
    String getFechaSalidaRegreso();
    String getFechaFinalizacion();
    String getFechaFinalizacionFormato();
    String getInforme();
    String getLinkInforme();
    String getNormativa();
    String getLinkNormativa();
    String getAreaResponsableTransparencia();
    String getFechaValidacion();
    String getFechaActualizacion();
    String getNotas();
}
