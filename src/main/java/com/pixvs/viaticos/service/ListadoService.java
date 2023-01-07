package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.repository.ListadoRepository;
import com.pixvs.viaticos.model.viaticos.ListadoEsquema;
import com.pixvs.viaticos.model.viaticos.ListadoEsquemaFormato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ListadoService extends GenericService {

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

    public List<Map<String, Object>> searchTable(ListadoEsquema listadoEsquema, List<ListadoEsquemaFormato> listadoEsquemaFormatoList, String nombreControl) throws Exception {
        return listadoRepository.select(listadoEsquema, listadoEsquemaFormatoList,nombreControl);
    }

    public void saveListadoData(String tabla, String columns, String rows) throws Exception{
        listadoRepository.insert(tabla, columns, rows);
    }

    public void updateListadoData(String tabla, String columns, String rows, Integer id) throws Exception{
        listadoRepository.update(tabla, columns, rows, id);
    }

    public String getTimestamp(String tabla, String rows){
        return listadoRepository.getTimestamp(tabla, rows);
    }
}
