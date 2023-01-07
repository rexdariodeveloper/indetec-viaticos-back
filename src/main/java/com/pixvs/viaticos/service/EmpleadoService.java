package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.*;
import com.pixvs.viaticos.model.viaticos.Empleado;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoSolicitudProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.EmpleadoSolicitudViaticoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmpleadoService extends GenericService {

    @Autowired
    private EmpleadoDao empleadoDao;

    public Object guardar(Object objeto) throws Exception {
        return empleadoDao.save((Empleado) objeto);
    }

    public void guardar(Collection objetos) throws Exception {
        System.out.println("aqui service void guarda");
        return ;
    }

    public void eliminarPorId(Integer id) throws Exception {

    }

    public EmpleadoSolicitudViaticoProjection buscarPorIdParaSolicitud(Integer id) throws  Exception{
        return empleadoDao.findEmpleadoSolicitudViaticoProjectionById(id);
    }

    public Object buscarPorId(Integer id) throws Exception {
        return empleadoDao.findEmpleadoById(id);
    }

    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    public Collection buscarTodos() throws Exception {
        return empleadoDao.findByEstatusIdNotIn(ListadoCMM.EstatusRegistro.BORRADO);
    }

    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return empleadoDao.findByNumeroEmpleadoAndEstatusIdNotIn(codigo, ListadoCMM.EstatusRegistro.BORRADO) != null;
    }

    public boolean existeRepetidoPorCodigo(Integer id, String codigo) throws Exception {
        return empleadoDao.findByIdNotInAndNumeroEmpleadoAndEstatusIdNotIn(id != null ? id : -1, codigo, ListadoCMM.EstatusRegistro.BORRADO) != null;
    }

    public Object getEmpleadoListadoProjectionWithStatus(List<Integer> estatus){
        return empleadoDao.findEmpleadoListadoProjectionByEstatusIdIsNotIn(estatus);
    }

    public List<ComboEmpleadoProjection> getComboProjection(List<Integer> estatusIgnore){
        return empleadoDao.findComboProjectionByEstatusIdIsNotIn(estatusIgnore);
    }

    public List<ComboEmpleadoProjection> getComboProjectionActivos(){
        List<Integer> estatusIgnoreEmpleado = new ArrayList<>();
        estatusIgnoreEmpleado.add(ListadoCMM.EstatusRegistro.BORRADO);
        return getComboProjection(estatusIgnoreEmpleado);
    }

    public List<ComboEmpleadoSolicitudProjection> getComboEmpleadoSolicitudProjection(Integer empleadoId) throws Exception {
        return empleadoDao.getComboEmpleadoSolicitudProjection(empleadoId);
    }
}
