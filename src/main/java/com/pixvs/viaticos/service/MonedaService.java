package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.MonedaDao;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MonedaService extends GenericService {

    @Autowired
    private MonedaDao monedaDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

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
        return monedaDao.findMonedaByActivoTrue();
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Collection getComboMonedas() {
        return monedaDao.findAllByActivoIs(true);
    }
}
