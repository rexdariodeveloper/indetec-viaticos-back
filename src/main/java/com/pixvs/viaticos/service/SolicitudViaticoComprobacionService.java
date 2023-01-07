package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class SolicitudViaticoComprobacionService extends GenericService {

    @Autowired
    private SolicitudViaticoComprobacionDao solicitudViaticoComprobacionDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionDao.save((SolicitudViaticoComprobacion) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoComprobacionDao.findSolicitudViaticoComprobacionById(id);
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

    public SolicitudViaticoComprobacion buscarPorSolicitudViaticoId(Integer solicitudViaticoId) throws Exception{
        return solicitudViaticoComprobacionDao.findSolicitudViaticoComprobacionBySolicitudViaticoIdAndEstatusId(solicitudViaticoId, ACTIVO);
    }
}
