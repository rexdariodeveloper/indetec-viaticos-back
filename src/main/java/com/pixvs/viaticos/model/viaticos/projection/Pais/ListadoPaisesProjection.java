package com.pixvs.viaticos.model.viaticos.projection.Pais;

import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoPaisesProjection", types = {Pais.class})
public interface ListadoPaisesProjection {

    Integer getId();
    String getCodigo();
    String getNombre();
    int getEstatusId();
    String getTimestamp();
}
