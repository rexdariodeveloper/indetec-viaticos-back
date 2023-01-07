package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.CiudadesDao;
import com.pixvs.viaticos.model.viaticos.Ciudad;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CiudadesService extends GenericService {

    @Autowired
    private CiudadesDao ciudadesDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return ciudadesDao.save((Ciudad) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        ciudadesDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return ciudadesDao.findByIdAndEstatusId(id, ListadoCMM.EstatusRegistro.ACTIVO);
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

    public Object buscaPorEstadoId(int id) {
        return ciudadesDao.findByEstadoIdAndEstatusId(id, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Object buscaPorEstadoIdAndNombre(int estadoId, String nombre) {
        return ciudadesDao.findByEstadoIdAndNombreAndEstatusId(estadoId, nombre, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getListadoCiudades() throws Exception {
        return ciudadesDao.findAllByEstatusIdOrderByPaisNombreAscEstadoNombreAscNombreAsc(ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Collection getComboCiudades(){
        return ciudadesDao.findAllProjectionComboByEstatusIdOrderByNombre(ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
