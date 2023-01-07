package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Estado;
import com.pixvs.viaticos.model.viaticos.projection.Estado.ComboEstadosProjection;
import com.pixvs.viaticos.model.viaticos.projection.Estado.ListadoEstadosProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadosDao extends CrudRepository<Estado, Integer> {

    ListadoEstadosProjection findByIdAndEstatusId(Integer codigo, int estatusId);

    ListadoEstadosProjection findByCodigoAndEstatusId(String codigo, int estatusId);

    List<ListadoEstadosProjection> findAllByEstatusIdOrderByPaisNombreAscCodigoAsc(int estatusId);

    ListadoEstadosProjection findByPaisIdAndCodigoAndEstatusId(int paisId, String codigo, int estatusId);

    ListadoEstadosProjection findByPaisIdAndEstatusId(int paisId, int estatusId);

    List<ComboEstadosProjection> findAllByEstatusIdOrderByNombre(int estatusId);
}
