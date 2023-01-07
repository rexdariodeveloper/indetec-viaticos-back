package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AlertaConfiguracionDao;
import com.pixvs.viaticos.model.viaticos.AlertaConfiguracion;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.AlertaConfiguracion.EditarAlertaConfiguracionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class AlertaConfiguracionService extends GenericService {

    @Autowired
    private AlertaConfiguracionDao alertaConfiguracionDao;

    @Override
    public Object guardar(Object alertaConfiguracion) throws Exception {
        return alertaConfiguracionDao.save((AlertaConfiguracion) alertaConfiguracion);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {
    }

    @Override
    public AlertaConfiguracion buscarPorId(Integer id) throws Exception {
        return alertaConfiguracionDao.findAlertaConfiguracionById(id);
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

    public List<EditarAlertaConfiguracionProjection> buscarListadoEditarProjection(List<Integer> etapaAccionIds){
        return alertaConfiguracionDao.findListadoEditarProjectionByEtapaAccionIdInAndEstatusIdNotIn(etapaAccionIds,new ArrayList<>(Arrays.asList(ListadoCMM.EstatusRegistro.BORRADO)));
    }

    public void eliminar(Integer id, Integer empleadoBorroId) throws Exception {
        AlertaConfiguracion alertaConfiguracionBorrar = alertaConfiguracionDao.findAlertaConfiguracionById(id);
        alertaConfiguracionBorrar.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
        alertaConfiguracionBorrar.setModificadoPorId(empleadoBorroId);
        alertaConfiguracionBorrar.setFechaUltimaModificacion(new Timestamp(System.currentTimeMillis()));
        alertaConfiguracionDao.save(alertaConfiguracionBorrar);
    }

}
