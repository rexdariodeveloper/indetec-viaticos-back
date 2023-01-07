package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.PermisoRegistro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRegistroDao extends CrudRepository <PermisoRegistro, Integer> {

    PermisoRegistro findByPermisoAccesoIdAndRegistroIdAndBorradoFalse(int accesoId, int registroId);

    List<PermisoRegistro> findAllByPermisoAccesoIdAndBorradoFalse(int accesoId);
}
