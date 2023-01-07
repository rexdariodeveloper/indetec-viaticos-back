package com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte;

import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "correosConfiguracionEnteProjection", types = {ConfiguracionEnte.class})
public interface CorreosConfiguracionEnteProjection {
    Integer getId();
    String getNombreUsuario();
    String getEmail();
    String getContrasenia();
    String getHost();
    Integer getPuerto();
    Integer getProtocolo();
}
