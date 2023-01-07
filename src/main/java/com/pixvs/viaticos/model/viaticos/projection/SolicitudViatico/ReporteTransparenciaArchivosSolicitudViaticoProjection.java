package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reporteTransparenciaArchivosSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ReporteTransparenciaArchivosSolicitudViaticoProjection {

    String getNumeroSolicitud();
    String getNombreOriginal();
    String getHipervinculo();
    boolean isExportado();
    int getOrden();
}
