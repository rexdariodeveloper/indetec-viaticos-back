package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.ACTIVO;
import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.BORRADO;

@Repository
public interface GruposJerarquicosDao extends CrudRepository<GrupoJerarquico, Integer> {

    GrupoJerarquico findGrupoJerarquicoById(Integer id);

    EditarGrupoJerarquicoProjection findByNombreAndEstatusIdNotIn(String nombre, int estatusId);

    EditarGrupoJerarquicoProjection findEditarGrupoJerarquicoProjectionById(Integer id);

    List<Listado2GrupoJerarquicoProjection> findByEstatusId(int estatusId);

    @Query(value = "SELECT * FROM fn_getListadoGrupoJerarquico()",  nativeQuery = true)
    List<ListadoGrupoJerarquicoProjection> getListadoGrupoJerarquico();
}
