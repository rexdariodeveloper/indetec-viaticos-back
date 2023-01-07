package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoInforme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudViaticoInformeDao extends CrudRepository<SolicitudViaticoInforme, Integer> {
    // Solicitud Viatico Informe
    SolicitudViaticoInforme findSolicitudViaticoInformeById(Integer id);

    SolicitudViaticoInforme findSolicitudViaticoInformeBySolicitudViaticoComprobacionIdAndEstatusIdIsIn(Integer solicitudId, List<Integer> estatusIds);

    SolicitudViaticoInforme save(SolicitudViaticoInforme solicitudViaticoInforme);
}
