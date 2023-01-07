package com.pixvs.viaticos.model.viaticos.projection.PasajeUtilizado;

import com.pixvs.viaticos.model.viaticos.PasajeUtilizado;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "listadoPasajesNoUtilizadosProjection", types = {PasajeUtilizado.class})
public interface ListadoPasajesNoUtilizadosProjection {

    Integer getId();
    String getTipoViaje();
    String getFechaCompra();
    String getNombreLinea();
    String getCodigoReservacion();
    String getFechaSalidaRegreso();
    BigDecimal getCosto();
}
