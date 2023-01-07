package com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquicoPuesto;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquicoPuesto;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoGrupoJerarquicoPuestoProjection", types = {GrupoJerarquicoPuesto.class})
public interface ListadoGrupoJerarquicoPuestoProjection {

    Integer getId();
    int getGrupoJerarquicoId();
    int getListadoPuestoId();
    String getTimestamp();
}
