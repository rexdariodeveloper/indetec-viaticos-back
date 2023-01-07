package com.pixvs.viaticos.model.viaticos.projection.CambioAlerta;

import com.pixvs.viaticos.model.viaticos.CambioAlerta;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;

@Projection(name = "listadoCambioAlertaProjection", types = {CambioAlerta.class})
public interface ListadoCambioAlertaProjection {
    Integer getId();
    String getFolio();
    int getOrigenId();
    String getEmpleadoOrigen();
    int getDestinoId();
    String getEmpleadoDestino();
    String getPeriodo();
    String getEstatus();
}
