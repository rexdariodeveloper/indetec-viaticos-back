package com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Projection(name = "listadoSolicitudViaticoProjection", types = {SolicitudViatico.class})
public interface ListadoSolicitudViaticoInformeComprobacionProjection{
    Integer getId();
    String getNumeroSolicitud();
    String getEmpleado();
    String getCiudadDestino();
    String getFecha();
    BigDecimal getMontoAsignado();
    BigDecimal getMontoTransferido();
    String getEstatus();
}
