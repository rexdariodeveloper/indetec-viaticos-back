package com.pixvs.viaticos.model.viaticos.projection.ListadoCMM;

import com.pixvs.viaticos.model.viaticos.ListadoCMM;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoCMMComboProjection", types = {ListadoCMM.class})
public interface ComboListadoCMMProjection {
    Integer getId();
    String getValor();
}