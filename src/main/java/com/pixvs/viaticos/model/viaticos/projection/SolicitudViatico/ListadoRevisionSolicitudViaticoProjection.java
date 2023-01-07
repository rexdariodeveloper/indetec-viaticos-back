package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "listadoRevisionSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ListadoRevisionSolicitudViaticoProjection {
    Integer getId();
    String getNumeroSolicitud();
    String getSolicitante();
    String getDescripcionComision();
    String getCiudadDestino();
    String getFechaSolicitud();
    String getFechaViaje();
    BigDecimal getMontoAsignado();
    BigDecimal getMontoTransferido();
    String getEstatus();
}
