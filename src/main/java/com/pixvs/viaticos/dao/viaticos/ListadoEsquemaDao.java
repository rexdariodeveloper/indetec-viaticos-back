package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ListadoEsquema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.StringJoiner;

public interface ListadoEsquemaDao extends CrudRepository<ListadoEsquema, Integer> {

    List<ListadoEsquema> findAllByListadoIdIn(List<Integer> listadoIds);

    List<ListadoEsquema> findListadoEsquemaBy();

    //Search ID Listado Esquema
    ListadoEsquema findListadoEsquemaById(Integer esquemaId);
}
