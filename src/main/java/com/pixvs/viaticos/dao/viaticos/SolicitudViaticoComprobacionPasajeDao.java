package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionPasaje;
import org.springframework.data.repository.CrudRepository;

public interface SolicitudViaticoComprobacionPasajeDao extends CrudRepository<SolicitudViaticoComprobacionPasaje, Integer> {
    // Solicitud Viatico Comprobacion Pasaje
    SolicitudViaticoComprobacionPasaje findSolicitudViaticoComprobacionPasajeById(Integer id);

    //Search ID Solicitud Viatico Comprobacion
    SolicitudViaticoComprobacionPasaje findSolicitudViaticoComprobacionPasajeBySolicitudViaticoComprobacionDetalleIdAndEstatusId(Integer solicitudViaticoComprobacionId, int estatusId);

    // Save
    SolicitudViaticoComprobacionPasaje save(SolicitudViaticoComprobacionPasaje solicitudViaticoComprobacionPasaje);
}
