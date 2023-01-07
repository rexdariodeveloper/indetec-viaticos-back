package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionCargo;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionPasaje;
import org.springframework.data.repository.CrudRepository;

public interface SolicitudViaticoComprobacionCargoDao extends CrudRepository<SolicitudViaticoComprobacionCargo, Integer> {
    // Solicitud Viatico Comprobacion Cargo
    SolicitudViaticoComprobacionCargo findSolicitudViaticoComprobacionCargoById(Integer id);

    // Search ID Solicitud Viatico Comprobacion Pasaje
    SolicitudViaticoComprobacionCargo findSolicitudViaticoComprobacionCargoBySolicitudViaticoComprobacionPasajeIdAndEstatusId(Integer solicitudViaticoComprobacionPasajeId, int estatusId);

    // Save
    SolicitudViaticoComprobacionCargo save(SolicitudViaticoComprobacionCargo solicitudViaticoComprobacionCargo);
}
