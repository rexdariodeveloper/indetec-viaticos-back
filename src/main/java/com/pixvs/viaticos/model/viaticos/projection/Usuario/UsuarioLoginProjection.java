package com.pixvs.viaticos.model.viaticos.projection.Usuario;

import com.pixvs.viaticos.model.viaticos.Usuario;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.EmpleadoEditarProjection;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "usuarioLogin", types = {Usuario.class})
public interface UsuarioLoginProjection {

    Integer getId();
    String getUsuario();
    EmpleadoEditarProjection getEmpleado();
    Integer getRolId();
    Integer getEstatusId();

}
