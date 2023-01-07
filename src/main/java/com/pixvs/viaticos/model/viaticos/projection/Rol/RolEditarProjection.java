package com.pixvs.viaticos.model.viaticos.projection.Rol;

import com.pixvs.viaticos.model.viaticos.Rol;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "rolListado", types = {Rol.class})
public interface RolEditarProjection {
    Integer getId();
    String getNombre();
    String getDescripcion();
    Integer getEstatusId();
    String getTimestamp();
}
