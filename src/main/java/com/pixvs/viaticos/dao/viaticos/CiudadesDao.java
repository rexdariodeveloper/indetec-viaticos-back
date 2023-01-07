package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Ciudad;
import com.pixvs.viaticos.model.viaticos.projection.Ciudad.ComboCiudadesProjection;
import com.pixvs.viaticos.model.viaticos.projection.Ciudad.ListadoCiudadesProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadesDao extends CrudRepository<Ciudad, Integer> {

    ListadoCiudadesProjection findByIdAndEstatusId(Integer codigo, int estatusId);

    List<ListadoCiudadesProjection> findAllByEstatusIdOrderByPaisNombreAscEstadoNombreAscNombreAsc(int estatusId);

    ListadoCiudadesProjection findByEstadoIdAndEstatusId(int estadoId, int estatusId);

    ListadoCiudadesProjection findByEstadoIdAndNombreAndEstatusId(int estadoId, String nombre, int estatusId);

    List<ComboCiudadesProjection> findAllProjectionComboByEstatusIdOrderByNombre(int estatusId);
}
