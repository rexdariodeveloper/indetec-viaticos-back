package com.pixvs.viaticos.model.viaticos.projection.Pais;

import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboPaisesProjection", types = {Pais.class})
public interface ComboPaisesProjection {

    Integer getId();
    String getNombre();
}
