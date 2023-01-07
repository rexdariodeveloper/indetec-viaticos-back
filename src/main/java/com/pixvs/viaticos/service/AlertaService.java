package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AlertaDao;
import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.model.viaticos.Alerta;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Alerta.AlertaListadoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaDao alertaDao;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    public Alerta buscaAlertaPorId(int id) {
        return alertaDao.findAlertaById(id);
    }

    public ArrayList<AlertaListadoProjection> getListadoAlertas(Integer usuarioId) throws Exception{
        return alertaDao.getListadoAlertas(usuarioId);
    }

    @Transactional
    public String iniciarAlerta(int alertaDefinicionId, int referenciaProcesoId, String codigoTramite, String textoRepresentativo, int alertaCreadaPorId) throws Exception{
        return generalScalarDao.iniciarAlerta(ListadoCMM.AccionesAlerta.INICIAR, alertaDefinicionId, referenciaProcesoId, codigoTramite, textoRepresentativo, alertaCreadaPorId);
    }

    @Transactional
    public String autorizarAlerta(Long alertaId, Integer usuarioId)  throws Exception{
        return generalScalarDao.autorizarAlerta(ListadoCMM.AccionesAlerta.AUTORIZAR, alertaId, usuarioId);
    }

    @Transactional
    public String revisionAlerta(Long alertaId, String motivo, Integer usuarioId)  throws Exception{
        return generalScalarDao.revisionRechazarAlerta(ListadoCMM.AccionesAlerta.REVISION, alertaId, motivo == null ? "" : motivo, usuarioId);
    }

    @Transactional
    public String rechazarAlerta(Long alertaId, String motivo, Integer usuarioId)  throws Exception{
        return generalScalarDao.revisionRechazarAlerta(ListadoCMM.AccionesAlerta.RECHAZAR, alertaId, motivo == null ? "" : motivo, usuarioId);
    }

    @Transactional
    public String ocultarAlertas(String alertasId, Integer usuarioId)  throws Exception{
        return generalScalarDao.ocultarAlertas(ListadoCMM.AccionesAlerta.OCULTAR, alertasId, usuarioId);
    }

    @Transactional
    public String cancelarAlertas(int referenciaProcesoId, int alertaDefinicionId) throws Exception{
        return generalScalarDao.cancelarAlertas(ListadoCMM.AccionesAlerta.CANCELAR, alertaDefinicionId, referenciaProcesoId);
    }

    public List<List> getPermisoAutorizacionAlerta(int menuId, int solicitudId, int usuarioId) throws Exception {
        return alertaDao.getPermisoAutorizacionAlerta(menuId, solicitudId, usuarioId);
    }
}
