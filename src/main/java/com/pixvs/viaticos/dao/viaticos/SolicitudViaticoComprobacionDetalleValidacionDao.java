package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalleValidacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudViaticoComprobacionDetalleValidacionDao extends CrudRepository<SolicitudViaticoComprobacionDetalleValidacion, Integer> {

    // Save
    SolicitudViaticoComprobacionDetalleValidacion save(SolicitudViaticoComprobacionDetalleValidacion solicitudViaticoComprobacionDetalleValidacion);

    // Search Solicitud Viatico Comprobacion ID
    List<SolicitudViaticoComprobacionDetalleValidacion> findAllBySolicitudViaticoComprobacionIdAndEstatusId(Integer solicitudViaticoComprobacionId, Integer estatusId);
}
