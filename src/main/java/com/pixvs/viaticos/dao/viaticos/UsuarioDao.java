package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Usuario;
import com.pixvs.viaticos.model.viaticos.projection.Usuario.RolUsuarioProjection;
import com.pixvs.viaticos.model.viaticos.projection.Usuario.UsuarioLoginProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

    //Guardar
    Usuario save(Usuario usuario);

    //Guardar projection
    UsuarioLoginProjection save(UsuarioLoginProjection usuario);

    //Buscar usuario y estatus
    Usuario findByUsuarioAndEstatusIdNotIn(String usuario, int estatusIds);

    Usuario findByIdNotInAndUsuarioAndEstatusIdNotIn(Integer id, String usuario, int estatusIds);

    //Buscar ID
    Usuario findByEmpleadoId(Integer idEmpleado);

    //Buscar por Nombre de Usuario, Constraseña y por Estatus
    //UsuarioLoginProjection findByUsuarioAndContraseniaAndEstatusId(String usuario, String contrasenia, Integer estatusId);

    //Buscar por Nombre de Usuario, Constraseña y por Estatus
    UsuarioLoginProjection findByUsuarioAndContrasenia(String usuario, String contrasenia);

    List<RolUsuarioProjection> findByRolIdAndEstatusIdNotIn(int rolId, int estatusId);

    Usuario findUsuarioById(Integer id);
}
