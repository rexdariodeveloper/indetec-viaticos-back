package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.GruposJerarquicosDao;
import com.pixvs.viaticos.model.viaticos.GrupoJerarquico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.GrupoJerarquico.EditarGrupoJerarquicoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GrupoJerarquicoService extends GenericService {

    @Autowired
    private GruposJerarquicosDao grupoJerarquicoDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return grupoJerarquicoDao.save((GrupoJerarquico) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        grupoJerarquicoDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return grupoJerarquicoDao.findGrupoJerarquicoById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return grupoJerarquicoDao.findByEstatusId(ListadoCMM.EstatusRegistro.ACTIVO);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public EditarGrupoJerarquicoProjection buscarPorNombre(String nombre) throws Exception {
        return grupoJerarquicoDao.findByNombreAndEstatusIdNotIn(nombre, ListadoCMM.EstatusRegistro.BORRADO);
    }

    public EditarGrupoJerarquicoProjection buscarParaEditarPorId(Integer id){
        return grupoJerarquicoDao.findEditarGrupoJerarquicoProjectionById(id);
    }

    public Collection getListado() throws Exception {
        return grupoJerarquicoDao.getListadoGrupoJerarquico();
    }
}
