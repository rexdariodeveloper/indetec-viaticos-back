package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionDetalleImpuestoDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalleImpuesto;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SolicitudViaticoComprobacionDetalleImpuestoService extends GenericService {

    @Autowired
    SolicitudViaticoComprobacionDetalleImpuestoDao solicitudViaticoComprobacionDetalleImpuestoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionDetalleImpuestoDao.save((SolicitudViaticoComprobacionDetalleImpuesto) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoComprobacionDetalleImpuestoDao.findSolicitudViaticoComprobacionDetalleImpuestoById(id);
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

    public List<SolicitudViaticoComprobacionDetalleImpuesto> buscaSolicitudViaticoComprobacionDetalleId(Integer solicitudViaticoComprobacionDetalleId) throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ListadoCMM.EstatusRegistro.BORRADO);
        return solicitudViaticoComprobacionDetalleImpuestoDao.findSolicitudViaticoComprobacionDetalleImpuestoBySolicitudViaticoComprobacionDetalleIdAndEstatusIdIsNotIn(solicitudViaticoComprobacionDetalleId, estatusIds);
    }
}
