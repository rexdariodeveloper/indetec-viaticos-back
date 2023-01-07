package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.AsignacionPasaje;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AsignacionPasajeDao extends CrudRepository<AsignacionPasaje, Integer> {
    AsignacionPasaje findAsignacionPasajeById(Integer id);

    List<AsignacionPasaje> findAsignacionPasajeByAsignacionIdAndEstatusIdIsIn(Integer asignacionId, List<Integer> estatusIds);

    AsignacionPasaje save(AsignacionPasaje asignacionPasaje);
}
