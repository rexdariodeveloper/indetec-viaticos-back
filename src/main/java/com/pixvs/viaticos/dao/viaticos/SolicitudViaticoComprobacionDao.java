package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudViaticoComprobacionDao extends CrudRepository<SolicitudViaticoComprobacion, Integer> {
    // Solicitud Viatico Comprobacion
    SolicitudViaticoComprobacion findSolicitudViaticoComprobacionById(Integer id);
    //Search ID Solicitud Viatico
    SolicitudViaticoComprobacion findSolicitudViaticoComprobacionBySolicitudViaticoIdAndEstatusId(Integer solicitudViaticoId, int estatusId);

    // Save
    SolicitudViaticoComprobacion save(SolicitudViaticoComprobacion solicitudViaticoComprobacion);
}
