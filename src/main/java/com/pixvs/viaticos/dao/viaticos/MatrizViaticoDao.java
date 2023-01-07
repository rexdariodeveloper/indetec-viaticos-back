package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.MatrizViatico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatrizViaticoDao extends CrudRepository<MatrizViatico, Integer> {

    List<MatrizViatico> findByGrupoJerarquicoId(Integer grupoJerarquicoId);

    List<MatrizViatico> findMatrizViaticoByGrupoJerarquicoIdAndListadoZonaIdAndConceptoViaticoEstatusId(Integer grupoJerarquicoId, Integer listadoZonaId, Integer estatusId);
}
