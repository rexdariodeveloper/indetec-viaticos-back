package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoRevisionDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoRevision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SolicitudViaticoRevisionService extends GenericService {

    @Autowired
    private SolicitudViaticoRevisionDao solicitudViaticoRevisionDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoRevisionDao.save((SolicitudViaticoRevision) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {
        solicitudViaticoRevisionDao.deleteById(id);
    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoRevisionDao.findSolicitudViaticoRevisionBySolicitudViaticoId(id);
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
}
