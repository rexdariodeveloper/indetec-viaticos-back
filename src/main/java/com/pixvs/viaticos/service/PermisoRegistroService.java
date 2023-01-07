package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.PermisoRegistroDao;
import com.pixvs.viaticos.model.viaticos.PermisoRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PermisoRegistroService extends GenericService {

    @Autowired
    private PermisoRegistroDao permisoRegistroDao;

    @Override
    public Object guardar(Object object) throws Exception {
        return permisoRegistroDao.save((PermisoRegistro) object);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        permisoRegistroDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return permisoRegistroDao.findById(id);
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
        return false;
    }

    public PermisoRegistro getPermisoRegistroPorAccesoIdRegistroId(int accesoId, int registroId) throws Exception {
        return permisoRegistroDao.findByPermisoAccesoIdAndRegistroIdAndBorradoFalse(accesoId, registroId);
    }

    public List<PermisoRegistro> getPermisosRegistroPorAccesoId(int accesoId) throws Exception {
        return permisoRegistroDao.findAllByPermisoAccesoIdAndBorradoFalse(accesoId);
    }
}
