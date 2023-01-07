package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ListadoCargoDao;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCargo.ListadoCargoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ListadoCargoService extends GenericService {

    @Autowired
    private ListadoCargoDao listadoCargoDao;

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
        return listadoCargoDao.findAllByActivoOrderByIdDesc(true);
    }

    public ListadoCargoProjection buscaListadoProjectionPorId(Integer id){
        return listadoCargoDao.findListadoCargoProjectionById(id);
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
        return listadoCargoDao.saveAll(listadoPuesto);
    }

    public Object getListadoCargoWithActive(boolean activo){
        return listadoCargoDao.findListadoPuestoListadoProjectionByActivo(activo);
    }
}
