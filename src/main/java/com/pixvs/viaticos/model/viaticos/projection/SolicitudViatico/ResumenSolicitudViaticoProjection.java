package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "resumenSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ResumenSolicitudViaticoProjection {

    Integer getId();
    String getNumeroSolicitud();
    Integer getEmpleadoId();
    String getSolicitante();
    String getPuesto();
    String getCargo();
    Integer getAreaAdscripcionId();
    String getAreaAdscripcion();
    String getProgramaGobierno();
    String getProyecto();
    String getRamo();
    String getDependencia();
    String getNoOficio();
    String getTipoViaje();
    String getTipoRepresentacion();
    String getFechaSalida();
    String getFechaRegreso();
    String getDuracionComision();
    String getGastoRepresentacion();
    String getSugerencias();
    String getFechaInicioEvento();
    String getFechaFinEvento();
    String getDuracionEvento();
    String getMotivo();
    String getDescripcionComision();
    String getOrganizador();
    String getLinkEvento();
    int getEstatusId();
    String getEstatus();
    String getOrigen();
    String getDestino();
    String getNumeroPolizaComprometido();
    String getNumeroPolizaGastoPorComprobarId();
    String getNumeroPolizaGastoComprobado();
}
