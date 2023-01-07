package com.pixvs.viaticos.model.viaticos.projection.Empleado;

import com.pixvs.viaticos.model.viaticos.Empleado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ComboEmpleadoProjection", types = {Empleado.class})
public interface ComboEmpleadoProjection {

    Integer getId();
    String getNombre();
    String getPrimerApellido();
    String getSegundoApellido();

}
