package com.pixvs.viaticos.model.viaticos.projection.Estado;

import com.pixvs.viaticos.model.viaticos.Estado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboEstadosProjection", types = {Estado.class})
public interface ComboEstadosProjection {

    Integer getId();
    int getPaisId();
    String getNombre();
}
