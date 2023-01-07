package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.AlertaEtapaAccionDao;
import com.pixvs.viaticos.model.viaticos.projection.AlertaEtapaAccion.ConfigurarAlertaEtapaAccionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AlertaEtapaAccionService extends GenericService {

    @Autowired
    private AlertaEtapaAccionDao alertaEtapaAccionDao;

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

    public List<ConfigurarAlertaEtapaAccionProjection> buscarListadoConfigurarProjection(Integer etapaId) throws Exception {
        return alertaEtapaAccionDao.findConfigurarProjectionByEtapaId(etapaId);
    }

    public List<Integer> buscarListadoIds(Integer etapaId) throws Exception {
        return alertaEtapaAccionDao.findIdsByEtapaId(etapaId);
    }

}
