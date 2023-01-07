package com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "editarGrupoJerarquicoProjection", types = {GrupoJerarquico.class})
public interface EditarGrupoJerarquicoProjection {

    Integer getId();
    String getNombre();
    String getDescripcion();
    int getEstatusId();
    String getTimestamp();
}
