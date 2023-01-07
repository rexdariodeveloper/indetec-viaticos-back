package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoDao;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.ListadoSolicitudViaticoInformeComprobacionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SolicitudViaticoInformeComprobacionService extends GenericService {

    @Autowired
    private SolicitudViaticoDao solicitudViaticoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return null;
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

    public List<ListadoSolicitudViaticoInformeComprobacionProjection> getListadoInformeComprobacionPorUsuarioId(Integer usuarioId) throws Exception {
        return solicitudViaticoDao.getListadoInformeComprobacionPorUsuarioId(usuarioId);
    }
}
