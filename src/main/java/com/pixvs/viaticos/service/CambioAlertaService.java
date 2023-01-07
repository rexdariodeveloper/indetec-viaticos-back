package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.CambioAlertaDao;
import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import com.pixvs.viaticos.model.viaticos.CambioAlerta;
import com.pixvs.viaticos.model.viaticos.projection.CambioAlerta.EditarCambioAlertaProjection;
import com.pixvs.viaticos.model.viaticos.projection.CambioAlerta.ListadoCambioAlertaProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Service
public class CambioAlertaService extends GenericService {

    @Autowired
    CambioAlertaDao cambioAlertaDao;

    @Autowired
    private GeneralScalarDao generalScalarDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return cambioAlertaDao.save((CambioAlerta) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return cambioAlertaDao.findCambioAlertaById(id);
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

    public String getSiguienteAutonumerico() throws Exception {
        return generalScalarDao.fetchSiguienteAutonumerico("CMA_AN_SiguenteNumAlertaCambioEmpleado", 0);
    }

    public EditarCambioAlertaProjection buscarParaEditarPorId(Integer id) {
        return cambioAlertaDao.findEditarCambioAlertaProjectionById(id);
    }

    public List<ListadoCambioAlertaProjection> getListadoCambioAlertas() {
        return cambioAlertaDao.getListadoCambioAlertas();
    }

    public boolean existeRegistro(Integer id, int empleadoOrigen, Timestamp fechaInicio, Timestamp fechaFin) {
        return cambioAlertaDao.existeRegistro(id, empleadoOrigen, fechaInicio, fechaFin) != null;
    }
}
