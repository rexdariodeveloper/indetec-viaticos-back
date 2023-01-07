package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.ConfiguracionEnteDao;
import com.pixvs.viaticos.dao.viaticos.RolDao;
import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.CorreosConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.SolicitudConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.SolicitudViaticoComprobacionConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConfiguracionEnteService extends GenericService {

    @Autowired
    ConfiguracionEnteDao configuracionEnteDao;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return configuracionEnteDao.save((ConfiguracionEnte) objeto);
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return configuracionEnteDao.findRolById(id);
    }


    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
         return configuracionEnteDao.findAllBy();
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public SolicitudConfiguracionEnteProjection getSolicitudConfiguracionEnte() {
        return configuracionEnteDao.findSolicitudConfiguracionEnteProjectionByIdNotIn(-1);
    }

    public CorreosConfiguracionEnteProjection getDatosServidorCorreos() {
        return configuracionEnteDao.findCorreosConfiguracionEnteProjectionByIdNotIn(-1);
    }

    public SolicitudViaticoComprobacionConfiguracionEnteProjection getSolicitudViaticoComprobacionConfiguracionEnte() {
        return configuracionEnteDao.findSolicitudViaticoComprobacionConfiguracionEnteProjectionByIdNotIn(-1);
    }

    public ServidorFTPConfiguracionEnteProjection getDatosServidorFTP() {
        return configuracionEnteDao.findServidorFTPConfiguracionEnteProjectionByIdNotIn(-1);
    }
}
