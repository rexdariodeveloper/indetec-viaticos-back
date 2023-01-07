package com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte;

import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "solicitudViaticoComprobacionConfiguracionEnteProjection", types = {ConfiguracionEnte.class})
public interface SolicitudViaticoComprobacionConfiguracionEnteProjection {
    Integer getId();
    Integer getPorcentajeSinComprobante();
    Integer getMontoAnualSinComprobante();
}