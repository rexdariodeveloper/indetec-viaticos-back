package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.GrupoJerarquicoPuestosDao;
import com.pixvs.viaticos.model.viaticos.GrupoJerarquicoPuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.*;

@Service
public class GrupoJerarquicoPuestoService extends GenericService {

    @Autowired
    private GrupoJerarquicoPuestosDao grupoJerarquicoPuestoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return grupoJerarquicoPuestoDao.save((GrupoJerarquicoPuesto) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        grupoJerarquicoPuestoDao.saveAll(objetos);
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

    public Object buscarPorGrupoJerarquicoIdAndPuestoId(int grupoId, int puestoId) throws Exception {
        return grupoJerarquicoPuestoDao.findByGrupoJerarquicoIdAndListadoPuestoIdAndEstatusId(grupoId, puestoId, ACTIVO);
    }

    public List<GrupoJerarquicoPuesto> buscarPorGrupoGerarquicoId(int grupoId){
        return grupoJerarquicoPuestoDao.findAllByGrupoJerarquicoIdAndEstatusId(grupoId, ACTIVO);
    }

    public GrupoJerarquicoPuesto searchListadoPuestoId(Integer puestoId) throws Exception{
        return grupoJerarquicoPuestoDao.findGrupoJerarquicoPuestoByListadoPuestoIdAndEstatusId(puestoId, ACTIVO);
    }
}
