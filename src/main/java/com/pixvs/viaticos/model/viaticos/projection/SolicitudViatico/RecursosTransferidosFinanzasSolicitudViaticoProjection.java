package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "recursosTransferidosFinanzasSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface RecursosTransferidosFinanzasSolicitudViaticoProjection {

    Integer getId();
    String getNumeroSolicitud();
    int getEstatusId();
}
