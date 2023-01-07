package com.pixvs.viaticos.model.viaticos.projection.Usuario;

import com.pixvs.viaticos.model.viaticos.Usuario;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "rolUsuarioProjection", types = {Usuario.class})
public interface RolUsuarioProjection {

    Integer getId();
    String getUsuario();
}
