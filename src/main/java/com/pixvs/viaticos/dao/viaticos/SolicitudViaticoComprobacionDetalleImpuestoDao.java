package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalleImpuesto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudViaticoComprobacionDetalleImpuestoDao extends CrudRepository<SolicitudViaticoComprobacionDetalleImpuesto, Integer> {
    // Solicitud Viatico Comprobacion Detalle Impuesto
    SolicitudViaticoComprobacionDetalleImpuesto findSolicitudViaticoComprobacionDetalleImpuestoById(Integer id);

    // Save
    SolicitudViaticoComprobacionDetalleImpuesto save(SolicitudViaticoComprobacionDetalleImpuesto solicitudViaticoComprobacionDetalleImpuesto);

    // Search ID Solicitud Viatico Comprobacion Detalle
    List<SolicitudViaticoComprobacionDetalleImpuesto> findSolicitudViaticoComprobacionDetalleImpuestoBySolicitudViaticoComprobacionDetalleIdAndEstatusIdIsNotIn(Integer solicitudViaticoComprobacionDetalleId, List<Integer> estatusIds);
}
