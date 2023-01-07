package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.EstadosDao;
import com.pixvs.viaticos.model.viaticos.Estado;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EstadosService extends GenericService {

    @Autowired
    private EstadosDao estadosDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return estadosDao.save((Estado) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        estadosDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return estadosDao.findByIdAndEstatusId(id, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return estadosDao.findByCodigoAndEstatusId(codigo, ListadoCMM.EstatusRegistro.ACTIVO);
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

    public Object buscaPorPaisId(int id) {
        return estadosDao.findByPaisIdAndEstatusId(id, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Object buscaPorPaisIdAndCodigo(int id, String codigo) {
        return estadosDao.findByPaisIdAndCodigoAndEstatusId(id, codigo, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getListadoEstados() throws Exception {
        return estadosDao.findAllByEstatusIdOrderByPaisNombreAscCodigoAsc(ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getComboEstados() {
        return estadosDao.findAllByEstatusIdOrderByNombre(ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
