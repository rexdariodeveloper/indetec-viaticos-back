package com.pixvs.viaticos.model.viaticos.projection.Rol;

import com.pixvs.viaticos.model.viaticos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.Rol;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "rolListado", types = {Rol.class})
public interface RolListadoProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    Integer getEstatusId();
    ListadoCMM getEstatus();
}
