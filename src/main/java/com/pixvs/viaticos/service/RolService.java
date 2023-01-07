package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.RolDao;
import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RolService extends GenericService {

    @Autowired
    RolDao rolDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return rolDao.save((Rol) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return rolDao.findRolById(id);
    }

    public RolEditarProjection buscarPorNombre(String nombre) throws Exception {
        return rolDao.findByNombreAndEstatusIdNotIn(nombre, ListadoCMM.EstatusRegistro.BORRADO);
    }

    public RolEditarProjection buscarParaEditarPorId(Integer id){
        return rolDao.findRolEditarProjectionById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
         return rolDao.findAllByEstatusIdIsNotIn(ListadoCMM.EstatusRegistro.BORRADO);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Object getRolWithStatus(int estatus){
        return rolDao.findAllByEstatusIdIn(estatus);
    }
}
