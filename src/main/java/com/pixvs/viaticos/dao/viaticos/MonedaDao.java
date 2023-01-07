package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Moneda;
import com.pixvs.viaticos.model.viaticos.projection.Moneda.ComboMonedaProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonedaDao extends CrudRepository<Moneda, Integer> {
    // Get All Moneda With Activo
    List<Moneda> findMonedaByActivoTrue();
    List<ComboMonedaProjection> findAllByActivoIs(Boolean activo);

}
