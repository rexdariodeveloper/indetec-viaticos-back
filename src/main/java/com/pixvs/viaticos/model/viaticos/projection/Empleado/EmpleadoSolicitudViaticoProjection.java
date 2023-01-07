package com.pixvs.viaticos.model.viaticos.projection.Empleado;

import com.pixvs.viaticos.model.viaticos.Empleado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "empleadoSolicitudViaticosProjection", types = {Empleado.class})
public interface EmpleadoSolicitudViaticoProjection{

    Integer getId();
    String getNombre();
    String getPrimerApellido();
    String getSegundoApellido();
    Integer getPuestoId();
    Integer getCargoId();
    Integer getAreaAdscripcionId();

}
