package com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte;

import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "servidorFTPConfiguracionEnteProjection", types = {ConfiguracionEnte.class})
public interface ServidorFTPConfiguracionEnteProjection {
    Integer getId();
    String getDirectorioPublico();
    boolean isDirectorioRemoto();
    String getDirectorioFTP();
    Integer getProtocoloFTP();
    String getServidorFTP();
    Integer getPuertoFTP();
    String getUsuarioFTP();
    String getContraseniaFTP();
}
