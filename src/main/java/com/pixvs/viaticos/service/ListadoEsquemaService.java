package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ListadoEsquemaDao;
import com.pixvs.viaticos.dao.viaticos.repository.ListadoRepository;
import com.pixvs.viaticos.model.viaticos.ListadoEsquema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

@Service
public class ListadoEsquemaService extends GenericService {

    @Autowired
    private ListadoEsquemaDao listadoEsquemaDao;

    @Autowired
    private ListadoRepository listadoRepository;

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
        return listadoEsquemaDao.findListadoEsquemaById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return listadoEsquemaDao.findListadoEsquemaBy();
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public List<ListadoEsquema> buscarTodoEsquemas(List<Integer> listados) throws Exception {
        return listadoEsquemaDao.findAllByListadoIdIn(listados);
    }

    /*public void saveListadoData(String nameTabla, String columnsData, String rowsData) throws Exception{
        listadoEsquemaDao.saveListado(nameTabla, columnsData, rowsData);
    }*/

}
