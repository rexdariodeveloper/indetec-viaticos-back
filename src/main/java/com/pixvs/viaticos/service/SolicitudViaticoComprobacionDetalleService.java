package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoComprobacionDetalleDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacion;
import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalle;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.ACTIVO;
import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.BORRADO;

@Service
public class SolicitudViaticoComprobacionDetalleService extends GenericService {

    @Autowired
    private SolicitudViaticoComprobacionDetalleDao solicitudViaticoComprobacionDetalleDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoComprobacionDetalleDao.save((SolicitudViaticoComprobacionDetalle) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoComprobacionDetalleDao.findSolicitudViaticoComprobacionDetalleById(id);
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

    public List<SolicitudViaticoComprobacionDetalle> searchSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) throws Exception{
        return solicitudViaticoComprobacionDetalleDao.findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndEstatusId(solicitudViaticoComprobacionId, ACTIVO);
    }

    public List<SolicitudViaticoComprobacionDetalle> searchSolicitudViaticoComprobacionIdRMWithRemove(Integer solicitudViaticoComprobacionId) throws Exception{
        return solicitudViaticoComprobacionDetalleDao.findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndEstatusId(solicitudViaticoComprobacionId, BORRADO);
    }

    public SolicitudViaticoComprobacionDetalle searchSolicitudViaticoComprobacionDetalleWithFileExist(Integer solicitudViaticoComprobacionId, Integer tipoComprobanteId, String folio, String rfc, String razonSocial) throws  Exception{
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ListadoCMM.EstatusSolicitudViatico.BORRADA);
        return solicitudViaticoComprobacionDetalleDao.findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndEstatusIdIsNotIn(solicitudViaticoComprobacionId, tipoComprobanteId, folio, rfc, razonSocial, estatusIds);
    }

    //public List<SolicitudViaticoComprobacionDetalle> buscaSolicitudViaticoComprobacionDetalleCompartida(Integer tipoComprobanteId, String folio, String rfc, String razonSocial) throws  Exception{
    public List<SolicitudViaticoComprobacionDetalle> buscaSolicitudViaticoComprobacionDetalleCompartida(Integer tipoComprobanteId, String uuid) throws  Exception{
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ListadoCMM.EstatusRegistro.BORRADO);
        //return solicitudViaticoComprobacionDetalleDao.findAllByTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndEstatusIdIsNotIn(tipoComprobanteId, folio, rfc, razonSocial, estatusIds);
        return solicitudViaticoComprobacionDetalleDao.findAllByTipoComprobanteIdAndUuidAndEstatusIdIsNotIn(tipoComprobanteId, uuid, estatusIds);
    }

    //
    public List<SolicitudViaticoComprobacionDetalle> searchSolicitudViaticoComprobacionDetalleWithShareFileImporte(Integer tipoComprobanteId, String folio, String rfc, String razonSocial, Integer numeroPartida) throws  Exception{
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ListadoCMM.EstatusRegistro.BORRADO);
        return solicitudViaticoComprobacionDetalleDao.findAllByTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndNumeroPartidaAndEstatusIdIsNotIn(tipoComprobanteId, folio, rfc, razonSocial, numeroPartida, estatusIds);
    }

}
