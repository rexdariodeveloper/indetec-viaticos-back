package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ConceptoViatico;
import com.pixvs.viaticos.model.viaticos.projection.ConceptoViatico.ListadoConceptoViaticoProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConceptoViaticoDao extends CrudRepository<ConceptoViatico, Integer> {
    // Concepto Viatico
    ConceptoViatico findConceptoViaticoById(Integer id);

    List<ConceptoViatico> findByEstatusIdIsNotInOrderByIdDesc(int estatusId);

    List<ListadoConceptoViaticoProjection> findByEstatusId(int estatusId);

    List<ListadoConceptoViaticoProjection> findByCategoriaIdAndEstatusId(int categoriaId, int estatusId);
}
