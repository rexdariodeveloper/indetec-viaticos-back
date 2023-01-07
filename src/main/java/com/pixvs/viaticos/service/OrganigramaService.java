package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.OrganigramaDao;
import com.pixvs.viaticos.model.viaticos.Organigrama;
import com.pixvs.viaticos.model.viaticos.projection.Organigrama.ArbolOrganigramaProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.*;

@Service
public class OrganigramaService extends GenericService {

    @Autowired
    private OrganigramaDao organigramaDao;

    @Override
    public Object guardar(Object object) throws Exception {
        return organigramaDao.save((Organigrama) object);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        organigramaDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return organigramaDao.findById(id);
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return organigramaDao.findAllByEstatusIdNotInOrderByOrdenAsc(EstatusRegistro.BORRADO);
    }

    public Object buscarTodosListados() throws Exception {
        return null;
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return organigramaDao.findByIdAndEstatusIdIsNotIn(id, EstatusRegistro.BORRADO) == null ? false : true;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public Object getComboListadoOrganigramaProjection(){
        return organigramaDao.getComboListadoOrganigramaProjection();
    }

    public Organigrama searchForId(Integer id) throws Exception{
        return organigramaDao.findByIdAndEstatusIdIsNotIn(id, EstatusRegistro.BORRADO);
    }

    public Object buscarOrganigramaListadoProjectionById(Integer id) {
        return organigramaDao.findOrganigramaListadoProjectionById(id);
    }

    public Integer getAutorizacionUsuarioInmediatoIdByAreaAdscripcionId(int id) {
        return organigramaDao.getAutorizacionUsuarioInmediatoId(id);
    }

    public List<ArbolOrganigramaProjection> getArbolOrganigramaPermisosUsuario(Integer usuarioId) throws Exception {
        return organigramaDao.getArbolOrganigramaPermisosUsuario(usuarioId);
    }
}
