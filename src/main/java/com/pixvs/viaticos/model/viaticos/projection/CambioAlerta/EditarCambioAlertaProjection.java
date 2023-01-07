package com.pixvs.viaticos.model.viaticos.projection.CambioAlerta;

import com.pixvs.viaticos.model.viaticos.CambioAlerta;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;

@Projection(name = "editarCambioAlertaProjection", types = {CambioAlerta.class})
public interface EditarCambioAlertaProjection {
    Integer getId();
    String getFolio();
    int getEmpleadoOrigenId();
    int getEmpleadoDestinoId();
    Timestamp getFechaInicio();
    Timestamp getFechaFin();
    boolean getBorrado();
    String getTimestamp();
}
