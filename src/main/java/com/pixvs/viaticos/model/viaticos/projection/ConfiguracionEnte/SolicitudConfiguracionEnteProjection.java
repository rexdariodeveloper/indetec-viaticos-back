package com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte;

import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "solicitudConfiguracionEnteProjection", types = {ConfiguracionEnte.class})
public interface SolicitudConfiguracionEnteProjection {
    Integer getId();
    Integer getPaisId();
    Integer getEstadoId();
    Integer getCiudadId();
}
