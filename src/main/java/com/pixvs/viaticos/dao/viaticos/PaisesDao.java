package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Pais;
import com.pixvs.viaticos.model.viaticos.projection.Pais.ComboPaisesProjection;
import com.pixvs.viaticos.model.viaticos.projection.Pais.ListadoPaisesProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisesDao extends CrudRepository<Pais, Integer> {

    ListadoPaisesProjection findByIdAndEstatusId(Integer id, int estatusId);

    ListadoPaisesProjection findByCodigoAndEstatusId(String codigo, int estatusId);

    List<ListadoPaisesProjection> findAllByEstatusIdOrderByCodigo(int estatusId);

    List<ComboPaisesProjection> findAllByEstatusIdOrderByNombre(int estatusId);
}
