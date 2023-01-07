package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquicoPuesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoJerarquicoPuestosDao extends CrudRepository<GrupoJerarquicoPuesto, Integer> {

    GrupoJerarquicoPuesto findByGrupoJerarquicoIdAndListadoPuestoIdAndEstatusId(int grupoId, int puestoId, int estatusId);

    //List<ListadoGrupoJerarquicoPuestoProjection> findAllByGrupoJerarquicoIdAndEstatusId(int grupoId, int estatusId);
    List<GrupoJerarquicoPuesto> findAllByGrupoJerarquicoIdAndEstatusId(int grupoId, int estatusId);

    //Search Puesto ID
    GrupoJerarquicoPuesto findGrupoJerarquicoPuestoByListadoPuestoIdAndEstatusId(Integer puestoId, int estatusId);
}
