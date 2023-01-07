package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ListadoEsquemaFormato;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListadoEsquemaFormatoDao extends CrudRepository<ListadoEsquemaFormato, Integer> {
    List<ListadoEsquemaFormato> findAllByEsquemaIdInOrderByEsquemaIdAscOrdenAsc(List<Integer> esquemaIds);

    //Search Esquema ID in Listado Esquema Formato
    List<ListadoEsquemaFormato> findListadoEsquemaFormatoByEsquemaIdOrderByOrdenAsc(Integer esquemaId);
}
