package com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listado2GrupoJerarquicoProjection", types = {GrupoJerarquico.class})
public interface Listado2GrupoJerarquicoProjection {
    Integer getId();
    String getNombre();
}
