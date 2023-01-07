package com.pixvs.viaticos.model.viaticos.projection.Moneda;

import com.pixvs.viaticos.model.viaticos.Moneda;
import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboPaisesProjection", types = {Moneda.class})
public interface ComboMonedaProjection {

    Integer getId();
    String getNombre();
}
