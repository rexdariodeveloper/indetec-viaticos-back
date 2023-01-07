package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AsignacionDao;
import com.pixvs.viaticos.model.viaticos.Asignacion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViaticoAsignacion.ListadoSolicitudViaticoAsignacionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class AsignacionService extends GenericService {

    @Autowired
    private AsignacionDao asignacionDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return asignacionDao.save((Asignacion) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return asignacionDao.findAsignacionById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(BORRADO);
        return asignacionDao.findAsignacionByEstatusIdIsNotIn(estatusIds);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Asignacion buscaPorSolicitudViaticoId(Integer solicitudViaticoId) throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ACTIVO);
        return asignacionDao.findAsignacionBySolicitudViaticoIdAndEstatusIdIsIn(solicitudViaticoId, estatusIds);
    }

    public List<ListadoSolicitudViaticoAsignacionProjection> getListadoSolicitudViaticoAsignacionProjection() throws  Exception{
        return  asignacionDao.getListadoSolicitudViaticoAsignacionProjection();
    }
}
