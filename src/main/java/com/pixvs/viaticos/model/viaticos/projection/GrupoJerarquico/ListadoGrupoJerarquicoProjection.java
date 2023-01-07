package com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoGrupoJerarquicoProjection", types = {GrupoJerarquico.class})
public interface ListadoGrupoJerarquicoProjection {

    int noPuestos = 0;
    Integer getId();
    String getNombre();
    String getDescripcion();
    int getEstatusId();
    String getEstatus();
    int getNoPuestos();
}
