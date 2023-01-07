package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.MenuPrincipalDao;
import com.pixvs.viaticos.model.viaticos.MenuPrincipal;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal.MenuPrincipalArbolPermisosProjection;
import com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal.MenuPrincipalArbolProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MenuPrincipalService extends GenericService {

    @Autowired
    MenuPrincipalDao menuPrincipalDao;

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
    public List<MenuPrincipal> buscarTodos() throws Exception {
        return menuPrincipalDao.findAllByEstatusIdIsNotIn(ListadoCMM.EstatusRegistro.BORRADO);
    }

    public List<MenuPrincipalArbolProjection> getArbolMenuPrincipal() throws Exception {
        return menuPrincipalDao.getArbolMenuPrincipal();
    }

    public List<MenuPrincipalArbolPermisosProjection> getArbolMenuPrincipalPermisosUsuario(Integer empleadoId){
        return menuPrincipalDao.getArbolMenuPrincipalPermisosUsuario(empleadoId);
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }
}
