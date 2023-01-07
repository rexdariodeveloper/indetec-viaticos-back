package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;

@Projection(name = "editarSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface EditarSolicitudViaticoProjection {

    Integer getId();
    String getNumeroSolicitud();
    int getEmpleadoId();
    int getPuestoId();
    String getPuesto();
    int getCargoId();
    String getCargo();
    int getAreaAdscripcionId();
    String getAreaAdscripcion();
    String getProgramaGobiernoId();
    String getProgramaGobierno();
    String getProyectoId();
    String getProyecto();
    String getRamoId();
    String getRamo();
    String getDependenciaId();
    String getDependencia();
    String getNoOficio();
    int getTipoViajeId();
    String getTipoViaje();
    int getTipoRepresentacionId();
    String getTipoRepresentacion();
    Timestamp getFechaSalida();
    Timestamp getFechaRegreso();
    String getDuracionComision();
    int getPaisOrigenId();
    int getEstadoOrigenId();
    int getCiudadOrigenId();
    int getPaisDestinoId();
    int getEstadoDestinoId();
    int getCiudadDestinoId();
    int getGastoRepresentacionId();
    String getGastoRepresentacion();
    String getSugerencias();
    Timestamp getFechaInicioEvento();
    Timestamp getFechaFinEvento();
    String getDuracionEvento();
    String getMotivo();
    String getDescripcionComision();
    String getOrganizador();
    String getLinkEvento();
    int getEstatusId();
    ComboListadoCMMProjection getEstatus();
    String getTimestamp();
    Integer getCreadoPorId();
}
