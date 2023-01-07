package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ListadoPuestoDao;
import com.pixvs.viaticos.model.viaticos.projection.ListadoPuesto.ListadoPuestoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ListadoPuestoService extends GenericService {

    @Autowired
    ListadoPuestoDao listadoPuestoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        listadoPuestoDao.saveAll(objetos);
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
        return listadoPuestoDao.findAllByActivoOrderByIdDesc(true);
    }

    public ListadoPuestoProjection buscarListadoPuestoProjection(Integer id) throws Exception{
        return listadoPuestoDao.findListadoPuestoProjectionById(id);
    }
    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Object saveAll (Collection listadoPuesto){
        return listadoPuestoDao.saveAll(listadoPuesto);
    }

    public Object getListadoPuestoWithActive(boolean activo){
        return listadoPuestoDao.findListadoPuestoListadoProjectionByActivo(true);
    }

    public Collection getPuestosGrupoJerarquico(Integer id) throws Exception {
        return listadoPuestoDao.getPuestosGrupoJerarquico(id);
    }
}
