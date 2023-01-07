package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ListadoCargo;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCargo.ListadoCargoProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListadoCargoDao  extends CrudRepository<ListadoCargo, Integer> {
    // Get listado de cargo (opciones)
    List<ListadoCargoProjection> findListadoPuestoListadoProjectionByActivo(boolean activo);

    List<ListadoCargo> findAllByActivoOrderByIdDesc(boolean activo);

	ListadoCargoProjection findListadoCargoProjectionById(int id);
}
