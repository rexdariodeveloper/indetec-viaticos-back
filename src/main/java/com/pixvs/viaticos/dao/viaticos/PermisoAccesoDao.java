package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.PermisoAcceso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoAccesoDao extends CrudRepository <PermisoAcceso, Integer> {

    PermisoAcceso save(PermisoAcceso permisoAcceso);

    PermisoAcceso findByTipoPermisoIdAndUsuarioIdAndBorradoFalse(int tipoId, int usuarioId);

    List<PermisoAcceso> findAllByUsuarioIdAndBorradoFalse(int usuarioId);
}
