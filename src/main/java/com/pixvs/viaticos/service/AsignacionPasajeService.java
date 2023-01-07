package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AsignacionPasajeDao;
import com.pixvs.viaticos.model.viaticos.AsignacionPasaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.ACTIVO;

@Service
public class AsignacionPasajeService extends GenericService {

    @Autowired
    private AsignacionPasajeDao asignacionPasajeDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return asignacionPasajeDao.save((AsignacionPasaje) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return asignacionPasajeDao.findAsignacionPasajeById(id);
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

    public List<AsignacionPasaje> searchAsignacionId(Integer asignacionId) throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ACTIVO);
        return asignacionPasajeDao.findAsignacionPasajeByAsignacionIdAndEstatusIdIsIn(asignacionId,estatusIds);
    }
}
