package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListadoCMMDao extends CrudRepository<ListadoCMM, Integer> {

    List<ComboListadoCMMProjection> findAllProjectionComboByNombreAndActivoTrue(String nombreListado);

    ComboListadoCMMProjection findProjectionComboById(Integer id);

    List<ComboListadoCMMProjection> findListadoCMMByIdIsInAndActivoTrue(List<Integer> ControlIds);

}
