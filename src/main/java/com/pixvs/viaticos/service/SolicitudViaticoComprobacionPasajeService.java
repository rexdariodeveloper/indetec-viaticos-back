package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionPasajeDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionPasaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class SolicitudViaticoComprobacionPasajeService extends GenericService {

    @Autowired
    private SolicitudViaticoComprobacionPasajeDao solicitudViaticoComprobacionPasajeDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionPasajeDao.save((SolicitudViaticoComprobacionPasaje) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoComprobacionPasajeDao.findSolicitudViaticoComprobacionPasajeById(id);
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

    public SolicitudViaticoComprobacionPasaje searchSolicitudViaticoComprobacionDetalleId(Integer solicitudViaticoComprobacionDetalleId) throws  Exception{
        return solicitudViaticoComprobacionPasajeDao.findSolicitudViaticoComprobacionPasajeBySolicitudViaticoComprobacionDetalleIdAndEstatusId(solicitudViaticoComprobacionDetalleId, ACTIVO);
    }
}
