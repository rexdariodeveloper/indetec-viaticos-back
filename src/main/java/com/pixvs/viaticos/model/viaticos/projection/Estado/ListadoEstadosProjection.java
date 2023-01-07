package com.pixvs.viaticos.model.viaticos.projection.Estado;

import com.pixvs.viaticos.model.viaticos.Estado;
import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoEstadosProjection", types = {Estado.class})
public interface ListadoEstadosProjection {

    Integer getId();
    int getPaisId();
    Pais getPais();
    String getCodigo();
    String getNombre();
    int getEstatusId();
    String getTimestamp();
}
