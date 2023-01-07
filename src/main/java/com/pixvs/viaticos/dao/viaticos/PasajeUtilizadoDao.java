package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.PasajeUtilizado;
import com.pixvs.viaticos.model.viaticos.projection.PasajeUtilizado.ListadoPasajesNoUtilizadosProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasajeUtilizadoDao extends CrudRepository<PasajeUtilizado, Integer> {

    @Query(value = "SELECT * FROM fn_getListadoPasajesNoUtilizados() ORDER BY Orden",  nativeQuery = true)
    List<ListadoPasajesNoUtilizadosProjection> getListadoPasajesNoUtilizados();
}
