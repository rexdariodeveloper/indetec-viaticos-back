package com.pixvs.viaticos.model.viaticos.projection.SolicitudViaticoAsignacion;

import com.pixvs.viaticos.model.viaticos.Asignacion;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "listadoSolicitudViaticoAsignacionProjection", types = {Asignacion.class})
public interface ListadoSolicitudViaticoAsignacionProjection {
    Integer getId();
    String getNumeroSolicitud();
    String getNombre();
    String getFecha();
    String getCiudadDestino();
    BigDecimal getMontoAsignado();
    BigDecimal getMontoTransferido();
    String getEstatus();
    int getEmpleadoId();
}
