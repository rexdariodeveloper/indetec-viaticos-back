package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionDetalleValidacionDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalleValidacion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SolicitudViaticoComprobacionDetalleValidacionService extends GenericService {

    @Autowired
    private SolicitudViaticoComprobacionDetalleValidacionDao solicitudViaticoComprobacionDetalleValidacionDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionDetalleValidacionDao.save((SolicitudViaticoComprobacionDetalleValidacion) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return null;
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

    public List<SolicitudViaticoComprobacionDetalleValidacion> searchSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) throws Exception{
        return solicitudViaticoComprobacionDetalleValidacionDao.findAllBySolicitudViaticoComprobacionIdAndEstatusId(solicitudViaticoComprobacionId, ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
