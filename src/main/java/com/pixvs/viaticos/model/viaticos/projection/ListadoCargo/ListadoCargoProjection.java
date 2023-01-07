package com.pixvs.viaticos.model.viaticos.projection.ListadoCargo;

import com.pixvs.viaticos.model.viaticos.ListadoCargo;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoCargoProjection", types = {ListadoCargo.class})
public interface ListadoCargoProjection {
    Integer getId();
    String getNombre();
}
