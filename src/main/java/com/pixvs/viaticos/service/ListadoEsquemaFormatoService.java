package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ListadoEsquemaFormatoDao;
import com.pixvs.viaticos.model.viaticos.ListadoEsquemaFormato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ListadoEsquemaFormatoService extends GenericService {

    @Autowired
    private ListadoEsquemaFormatoDao listadoEsquemaFormatoDao;

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

    public List<ListadoEsquemaFormato> buscarTodoEsquemasFormato(List<Integer> esquemaIds) throws Exception {
        return listadoEsquemaFormatoDao.findAllByEsquemaIdInOrderByEsquemaIdAscOrdenAsc(esquemaIds);
    }

    public List<ListadoEsquemaFormato> searchEsquemaId(Integer esquemaId) throws Exception {
        return listadoEsquemaFormatoDao.findListadoEsquemaFormatoByEsquemaIdOrderByOrdenAsc(esquemaId);
    }
}
