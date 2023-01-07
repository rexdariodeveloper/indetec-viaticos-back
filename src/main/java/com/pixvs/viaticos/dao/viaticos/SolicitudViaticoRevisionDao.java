package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoRevision;
import org.springframework.data.repository.CrudRepository;

public interface SolicitudViaticoRevisionDao extends CrudRepository<SolicitudViaticoRevision, Integer> {

    SolicitudViaticoRevision findSolicitudViaticoRevisionBySolicitudViaticoId(Integer solicitudViaticoRevisionId);
}
