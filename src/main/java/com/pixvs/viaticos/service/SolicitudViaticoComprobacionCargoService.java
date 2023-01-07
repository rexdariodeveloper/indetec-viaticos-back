package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionCargoDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class SolicitudViaticoComprobacionCargoService extends GenericService {

    @Autowired
    private SolicitudViaticoComprobacionCargoDao solicitudViaticoComprobacionCargoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionCargoDao.save((SolicitudViaticoComprobacionCargo) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoComprobacionCargoDao.findSolicitudViaticoComprobacionCargoById(id);
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

    public SolicitudViaticoComprobacionCargo searchSolicitudViaticoComprobacionPasajeId(Integer solicitudViaticoComprobacionPasajeId) throws  Exception{
        return solicitudViaticoComprobacionCargoDao.findSolicitudViaticoComprobacionCargoBySolicitudViaticoComprobacionPasajeIdAndEstatusId(solicitudViaticoComprobacionPasajeId, ACTIVO);
    }
}
