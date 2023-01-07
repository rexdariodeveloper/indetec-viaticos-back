package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolListadoProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolDao extends CrudRepository<Rol, Integer> {

    Rol save(Rol rol);

    Rol findRolById(Integer id);

    List<RolListadoProjection> findAllByEstatusIdIsNotIn(int estatusId);

    RolEditarProjection findRolEditarProjectionById(Integer rolId);

    RolEditarProjection findByNombreAndEstatusIdNotIn(String nombre, int estatusId);

    List<RolListadoProjection> findAllByEstatusIdIn(int estatusId);
}
