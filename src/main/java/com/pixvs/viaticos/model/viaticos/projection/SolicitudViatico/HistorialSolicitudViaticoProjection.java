package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "historialSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface HistorialSolicitudViaticoProjection {

    String getEmpleado();
    String getFecha();
    String getProceso();
    String getMotivo();
}
