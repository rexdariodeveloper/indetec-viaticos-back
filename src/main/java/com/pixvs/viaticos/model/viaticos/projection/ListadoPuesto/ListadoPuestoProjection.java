package com.pixvs.viaticos.model.viaticos.projection.ListadoPuesto;

import com.pixvs.viaticos.model.viaticos.ListadoPuesto;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoPuestoListadoProjection", types = {ListadoPuesto.class})
public interface ListadoPuestoProjection {
    Integer getId();
    String getClave();
    String getNombre();
}
