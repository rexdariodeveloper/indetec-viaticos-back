package com.pixvs.viaticos.model.viaticos.projection.Empleado;

import com.pixvs.viaticos.model.viaticos.Empleado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "empleadoListadoProjection", types = {Empleado.class})
public interface EmpleadoListadoProjection {
    Integer getId();
    String getNombre();
}
