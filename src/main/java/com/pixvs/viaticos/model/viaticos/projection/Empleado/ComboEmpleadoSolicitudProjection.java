package com.pixvs.viaticos.model.viaticos.projection.Empleado;

import com.pixvs.viaticos.model.viaticos.Empleado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboEmpleadoSolicitudProjection", types = {Empleado.class})
public interface ComboEmpleadoSolicitudProjection {

    Integer getId();
    String getNombre();
    Integer getPuestoId();
    String getPuesto();
    Integer getCargoId();
    String getCargo();
    Integer getAreaAdscripcionId();
    String getAreaAdscripcion();
    String getFotografia();
}
