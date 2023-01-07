package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.PermisoAccesoDao;
import com.pixvs.viaticos.model.viaticos.PermisoAcceso;
import com.pixvs.viaticos.model.viaticos.PermisoRegistro;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PermisoAccesoService extends GenericService {

    @Autowired
    private PermisoAccesoDao permisoAccesoDao;

    @Autowired
    private PermisoRegistroService permisoRegistroService;

    @Override
    public Object guardar(Object object) throws Exception {
        return permisoAccesoDao.save((PermisoAcceso) object);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        permisoAccesoDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return permisoAccesoDao.findById(id);
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

    public PermisoAcceso getPermisoAccesoPorTipoIdUsuarioId(int tipoId, int usuarioId) throws Exception {
        return permisoAccesoDao.findByTipoPermisoIdAndUsuarioIdAndBorradoFalse(tipoId, usuarioId);
    }

    public List<PermisoAcceso> getPermisosAccesoPorUsuarioId(int usuarioId) throws Exception {
        return permisoAccesoDao.findAllByUsuarioIdAndBorradoFalse(usuarioId);
    }

    public boolean isPermisoVerTerceros(int usuarioId, int areaAdscripcionId) throws Exception {
        PermisoAcceso permisoVerTerceros = getPermisoAccesoPorTipoIdUsuarioId(ListadoCMM.TipoPermisoAcceso.VISUALIZAR_SOLICITUDES_TERCEROS, usuarioId);

        return permisoVerTerceros != null
                && permisoRegistroService.getPermisoRegistroPorAccesoIdRegistroId(permisoVerTerceros.getId(), areaAdscripcionId) != null;
    }
}
