package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ListadoPuesto;
import com.pixvs.viaticos.model.viaticos.projection.ListadoPuesto.GrupoJerarquicoListadoPuestoProjection;
import com.pixvs.viaticos.model.viaticos.projection.ListadoPuesto.ListadoPuestoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.ACTIVO;

public interface ListadoPuestoDao extends CrudRepository<ListadoPuesto, Integer> {
    // Get listado de organigrama (opciones)
    List<ListadoPuestoProjection> findListadoPuestoListadoProjectionByActivo(boolean acitvo);

    List<ListadoPuesto> findAllByActivoOrderByIdDesc(boolean activo);

    ListadoPuestoProjection findListadoPuestoProjectionById(int id);

    @Query(value = "SELECT * FROM fn_getPuestosGrupoJerarquico(:id) ORDER BY Nombre",  nativeQuery = true)
    List<GrupoJerarquicoListadoPuestoProjection> getPuestosGrupoJerarquico(@Param("id") Integer id);
}
