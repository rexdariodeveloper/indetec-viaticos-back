package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViaticoComprobacionDetalle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudViaticoComprobacionDetalleDao extends CrudRepository<SolicitudViaticoComprobacionDetalle, Integer> {
    // Solicitud Viatico Comprobacion Detalle
    SolicitudViaticoComprobacionDetalle findSolicitudViaticoComprobacionDetalleById(Integer id);

    //Search ID Solicitud Viatico Comprobacion
    List<SolicitudViaticoComprobacionDetalle> findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndEstatusId(Integer solicitudViaticoComprobacionId, int estatusId);

    //Search ID Solicitud Viatico Comprobacion RM Borrado
    List<SolicitudViaticoComprobacionDetalle> findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndEstatusIdAndEsComprobadoPorRMIsTrue(Integer solicitudViaticoComprobacionId, int estatusId);

    // Save
    SolicitudViaticoComprobacionDetalle save(SolicitudViaticoComprobacionDetalle solicitudViaticoComprobacionDetalle);

    // Search Usuario With File Exist
    SolicitudViaticoComprobacionDetalle findSolicitudViaticoComprobacionDetalleBySolicitudViaticoComprobacionIdAndTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndEstatusIdIsNotIn(Integer solicitudViaticoComprobacionId, Integer tipoComprobanteId, String folio, String rfc, String razonSocial, List<Integer> estatusIds);

    // Search Users With Share File
    List<SolicitudViaticoComprobacionDetalle> findAllByTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndEstatusIdIsNotIn(Integer tipoComprobanteId, String folio, String rfc, String razonSocial, List<Integer> estatusIds);

    // Busca el UUID para compartida
    List<SolicitudViaticoComprobacionDetalle> findAllByTipoComprobanteIdAndUuidAndEstatusIdIsNotIn(Integer tipoComprobanteId, String uuid, List<Integer> estatusIds);

    // Search Users With Share File Importe
    List<SolicitudViaticoComprobacionDetalle> findAllByTipoComprobanteIdAndFolioAndRfcAndRazonSocialAndNumeroPartidaAndEstatusIdIsNotIn(Integer tipoComprobanteId, String folio, String rfc, String razonSocial, Integer numeroPartida, List<Integer> estatusIds);
}
