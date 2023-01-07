package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ListadoCMMDao;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ListadoCMMService extends GenericService {

    @Autowired
    private ListadoCMMDao listadoCMMDao;

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
        return listadoCMMDao.findProjectionComboById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return listadoCMMDao.findAllProjectionComboByNombreAndActivoTrue(codigo);
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

    public Collection getComboZonasEconomicas() {
        return listadoCMMDao.findAllProjectionComboByNombreAndActivoTrue("ZonaEconomica");
    }

    public List<ComboListadoCMMProjection> getListadoCMMComboByNombreAndActivo(String nombreListado){
        return listadoCMMDao.findAllProjectionComboByNombreAndActivoTrue(nombreListado);
    }

    public List<ComboListadoCMMProjection> getListadoCMMComboByIdIsInAndActivo(List<Integer> controlIds) throws Exception {
        return listadoCMMDao.findListadoCMMByIdIsInAndActivoTrue(controlIds);
    }
}
