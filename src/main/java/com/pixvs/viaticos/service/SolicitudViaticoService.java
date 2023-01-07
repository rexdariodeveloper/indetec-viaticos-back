package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.dao.viaticos.SolicitudViaticoDao;
import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SolicitudViaticoService extends GenericService {

    @Autowired
    SolicitudViaticoDao solicitudViaticoDao;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return solicitudViaticoDao.save((SolicitudViatico) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        solicitudViaticoDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return solicitudViaticoDao.findSolicitudViaticoById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        List<Integer> estatusIds = new ArrayList<>();
        estatusIds.add(ListadoCMM.EstatusSolicitudViatico.BORRADA);
        return solicitudViaticoDao.findSolicitudViaticoByEstatusIdIsNotIn(estatusIds);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public String getSiguienteAutonumerico(int ejercicio) throws Exception {
        return generalScalarDao.fetchSiguienteAutonumerico("CMA_AN_SiguenteNumSolicitudViatico", ejercicio + 1900);
    }

    public SolicitudViatico buscaParaEditarPorId(Integer id) throws Exception {
        return solicitudViaticoDao.findSolicitudViaticoById(id);
    }

    public List<ListadoSolicitudViaticoProjection> getListadoSolicitudesPorUsuarioId(int empleadoId) throws Exception {
        return solicitudViaticoDao.getListadoSolicitudesPorUsuarioId(empleadoId);
    }

    public ResumenSolicitudViaticoProjection buscarSolicitudViaticoResumenPorId(Integer id) throws Exception {
        return solicitudViaticoDao.getResumenSolicitudViaticoPorId(id);
    }

    public SolicitudViatico getSolicitudViatico(Integer solicitudId, Integer empleadoId) throws Exception{
        return solicitudViaticoDao.findSolicitudViaticoByIdAndEmpleadoId(solicitudId, empleadoId);
    }

    public List<ListadoRevisionSolicitudViaticoProjection> getListadoRevisionSolicitudViatico() throws Exception {
        return solicitudViaticoDao.getListadoRevisionSolicitudViatico();
    }

    public List<ReporteTransparenciaSolicitudViaticoProjection> getReporteTransparencia(Timestamp fechaInicio, Timestamp fechaFin) throws Exception {
        return solicitudViaticoDao.getReporteTransparencia(fechaInicio, fechaFin);
    }

    public List<ReporteTransparenciaPartidaSolicitudViaticoProjection> getReporteTransparenciaPartidas(Integer solicitudViaticoId) throws Exception {
        return solicitudViaticoDao.getReporteTransparenciaPartidas(solicitudViaticoId);
    }

    public List<ReporteTransparenciaArchivosSolicitudViaticoProjection> getReporteTransparenciaArchivos(Integer solicitudViaticoId) throws Exception {
        return solicitudViaticoDao.getReporteTransparenciaArchivos(solicitudViaticoId);
    }

    public List<HistorialSolicitudViaticoProjection> getHistorialSolicitudViatico(Integer solicitudViaticoId) throws Exception {
        return solicitudViaticoDao.getHistorialSolicitudViatico(solicitudViaticoId);
    }

    public String existeRegistro(Integer id, int empleadoId, Timestamp fechaSalida, Timestamp fechaRegreso) {
        return solicitudViaticoDao.existeRegistro(id, empleadoId, fechaSalida, fechaRegreso);
    }

    public List<List> getReporteTransparenciaConcentrado(String solicitudIds) throws Exception {
        return solicitudViaticoDao.getReporteTransparenciaConcentrado(solicitudIds);
    }

    public RecursosTransferidosFinanzasSolicitudViaticoProjection getSolicitudRecursosTransferidosPorId(Integer solicitudId) throws Exception {
        return solicitudViaticoDao.findRecursosTransferidosFinanzasSolicitudViaticoProjectionById(solicitudId);
    }
}
