package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.MatrizViaticoDao;
import com.pixvs.viaticos.model.viaticos.MatrizViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MatrizViaticoService extends GenericService {

    @Autowired
    private MatrizViaticoDao matrizViaticoDao;

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
        return matrizViaticoDao.findByGrupoJerarquicoId(id);
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

    public Object saveAll (Collection listadoPuesto){
        return matrizViaticoDao.saveAll(listadoPuesto);
    }

    public List<MatrizViatico> searchGrupoJerarquicoIdAndListadoZonaId(Integer grupoJerarquicoId, Integer listadoZonaId) throws  Exception{
        return matrizViaticoDao.findMatrizViaticoByGrupoJerarquicoIdAndListadoZonaIdAndConceptoViaticoEstatusId(grupoJerarquicoId, listadoZonaId, ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
