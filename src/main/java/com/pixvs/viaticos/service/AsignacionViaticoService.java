package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AsignacionViaticoDao;
import com.pixvs.viaticos.model.viaticos.AsignacionViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class AsignacionViaticoService extends GenericService {

    @Autowired
    private AsignacionViaticoDao asignacionViaticoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return asignacionViaticoDao.save((AsignacionViatico) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        asignacionViaticoDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return asignacionViaticoDao.findAsignacionViaticoById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(BORRADO);
        return asignacionViaticoDao.findAsignacionViaticoByEstatusIdIsNotIn(estatusIds);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public List<AsignacionViatico> getAsignacionViatico(Integer solicitudId,Integer asignacionId) throws Exception{
        if(asignacionId != null){
            return asignacionViaticoDao.getAsignacionViatico(solicitudId);
        }else{
            return asignacionViaticoDao.getAsignacionViatico(solicitudId);
        }
    }

    public List<AsignacionViatico> searchAsignacionId(Integer asignacionId) throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ACTIVO);
        return asignacionViaticoDao.findAsignacionViaticoByAsignacionIdAndEstatusIdIsIn(asignacionId,estatusIds);
    }
}
