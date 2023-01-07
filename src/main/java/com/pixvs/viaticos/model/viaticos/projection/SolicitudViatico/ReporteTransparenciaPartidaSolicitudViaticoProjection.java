package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reporteTransparenciaPartidaSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ReporteTransparenciaPartidaSolicitudViaticoProjection {

    String getNumeroSolicitud();
    String getClave();
    String getDenominacion();
    String getImporte();
}
