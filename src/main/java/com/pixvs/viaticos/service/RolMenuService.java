package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.RolMenuDao;
import com.pixvs.viaticos.model.viaticos.RolMenu;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RolMenuService extends GenericService {

    @Autowired
    RolMenuDao rolMenuDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return rolMenuDao.save((RolMenu) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {
        rolMenuDao.saveAll(objetos);
    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return rolMenuDao.findById(id);
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

    public List<RolMenu> buscaTodosPorRolId(int rolId){
        return rolMenuDao.findAllByRolIdAndEstatusId(rolId, ListadoCMM.EstatusRegistro.ACTIVO);
    }

    public Object buscarPorRolIdAndMenuId(int rolId, int menuId) throws Exception {
        return rolMenuDao.findByRolIdAndAndNodoMenuIdAndEstatusId(rolId, menuId, ListadoCMM.EstatusRegistro.ACTIVO);
    }
}
