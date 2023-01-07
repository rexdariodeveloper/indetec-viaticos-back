package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.PaisesDao;
import com.pixvs.viaticos.model.viaticos.Pais;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PaisesService extends GenericService {

    @Autowired
    private PaisesDao paisesDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return paisesDao.save((Pais) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        paisesDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return paisesDao.findByIdAndEstatusId(id, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return paisesDao.findByCodigoAndEstatusId(codigo, ListadoCMM.EstatusRegistro.ACTIVO);
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

    public Collection getListadoPaises() throws Exception {
        return paisesDao.findAllByEstatusIdOrderByCodigo(ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getComboPaises() {
        return paisesDao.findAllByEstatusIdOrderByNombre(ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
