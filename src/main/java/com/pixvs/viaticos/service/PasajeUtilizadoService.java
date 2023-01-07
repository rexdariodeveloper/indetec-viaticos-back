package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.PasajeUtilizadoDao;
import com.pixvs.viaticos.model.viaticos.PasajeUtilizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PasajeUtilizadoService extends GenericService {

    @Autowired
    private PasajeUtilizadoDao pasajeUtilizadoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return pasajeUtilizadoDao.save((PasajeUtilizado) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        pasajeUtilizadoDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return null;
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

    public Collection getListadoPasajesNoUtilizados() throws Exception {
        return pasajeUtilizadoDao.getListadoPasajesNoUtilizados();
    }
}
