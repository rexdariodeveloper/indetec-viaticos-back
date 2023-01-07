package com.pixvs.viaticos.model.viaticos.projection.ListadoPuesto;

import com.pixvs.viaticos.model.viaticos.ListadoPuesto;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "grupoJerarquicoListadoPuestoProjection", types = {ListadoPuesto.class})
public interface GrupoJerarquicoListadoPuestoProjection {
    Integer getId();
    String getNombre();
    String getTimestamp();
}
