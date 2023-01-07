package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ConceptoViaticoDao;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConceptoViaticoService extends GenericService {

    @Autowired
    private ConceptoViaticoDao conceptoViaticoDao;

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
        return conceptoViaticoDao.findConceptoViaticoById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return conceptoViaticoDao.findByEstatusIdIsNotInOrderByIdDesc(ListadoCMM.EstatusRegistro.BORRADO);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Object saveAll (Collection conceptoViatico){
        return conceptoViaticoDao.saveAll(conceptoViatico);
    }

    public Collection getListadoConceptoViaticoProjection(){
        return conceptoViaticoDao.findByEstatusId(ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getListadoConceptoViaticoProjectionByCategoriaId(int categoriaId) {
        return conceptoViaticoDao.findByCategoriaIdAndEstatusId(categoriaId, ListadoCMM.EstatusRegistro.ACTIVO);
    }
}

