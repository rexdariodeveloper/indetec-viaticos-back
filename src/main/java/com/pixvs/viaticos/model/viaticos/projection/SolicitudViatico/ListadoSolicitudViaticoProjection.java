package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ListadoSolicitudViaticoProjection {

    Integer getId();
    String getNumeroSolicitud();
    String getDescripcionComision();
    String getSolicitante();
    String getNoOficio();
    String getTipoViaje();
    String getFechaSalida();
    String getFechaRegreso();
    String getDuracionComision();
    String getOrigen();
    String getDestino();
    String getEstatus();
}
