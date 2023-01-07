package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoInformeDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoInforme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class SolicitudViaticoInformeService extends GenericService {

    @Autowired
    private SolicitudViaticoInformeDao solicitudViaticoInformeDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoInformeDao.save((SolicitudViaticoInforme) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoInformeDao.findSolicitudViaticoInformeById(id);
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

    public SolicitudViaticoInforme searchSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ACTIVO);
        return solicitudViaticoInformeDao.findSolicitudViaticoInformeBySolicitudViaticoComprobacionIdAndEstatusIdIsIn(solicitudViaticoComprobacionId, estatusIds);
    }
}
