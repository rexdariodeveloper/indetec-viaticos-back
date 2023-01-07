package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.UsuarioDao;
import com.pixvs.viaticos.model.viaticos.Usuario;
import com.pixvs.viaticos.model.viaticos.projection.Usuario.RolUsuarioProjection;
import com.pixvs.viaticos.model.viaticos.projection.Usuario.UsuarioLoginProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.BORRADO;

@Service
public class UsuarioService extends GenericService{

    @Autowired
    UsuarioDao usuarioDAO;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return usuarioDAO.save((Usuario) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return usuarioDAO.findUsuarioById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return null;
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return usuarioDAO.findByUsuarioAndEstatusIdNotIn(codigo, BORRADO) != null;
    }

    public UsuarioLoginProjection buscarPorUsuarioContrasenia(String usuario, String contrasenia){
        return usuarioDAO.findByUsuarioAndContrasenia(usuario, contrasenia);
    }

    public boolean existeRepetidoPorCodigo(Integer id, String codigo) throws Exception {
        return usuarioDAO.findByIdNotInAndUsuarioAndEstatusIdNotIn(id != null ? id : -1, codigo, BORRADO) != null;
    }

    public boolean existePorRol(int rolId) throws Exception {
        return usuarioDAO.findByRolIdAndEstatusIdNotIn(rolId, BORRADO).toArray().length != 0;
    }

    public Usuario buscaPorEmpleadoId(Integer empleadoId) throws Exception {
        return usuarioDAO.findByEmpleadoId(empleadoId);
    }
}
