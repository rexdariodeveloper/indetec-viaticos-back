package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Empleado;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoSolicitudProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.EmpleadoListadoProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.EmpleadoSolicitudViaticoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoDao extends CrudRepository<Empleado, Integer> {

    //Guardar
    Empleado save(Empleado empleado);

    //Listado
    List<Empleado> findByEstatusIdNotIn(int estatusId);

    //Buscar id para enviar los datos
    Empleado findEmpleadoById(Integer empleadoId);

    //Get listado de empelado
    List<EmpleadoListadoProjection> findEmpleadoListadoProjectionByEstatusIdIsNotIn(List<Integer> estatusIds);

    EmpleadoSolicitudViaticoProjection findEmpleadoSolicitudViaticoProjectionById(Integer empleadoId);

    //Search exist numero de empleado
    Empleado findByNumeroEmpleadoAndEstatusIdNotIn(String numeroEmpleado, int estatusId);

    Empleado findByIdNotInAndNumeroEmpleadoAndEstatusIdNotIn(Integer id, String numeroEmpleado, int estatusId);

    //Get listado combo de empelado
    List<ComboEmpleadoProjection> findComboProjectionByEstatusIdIsNotIn(List<Integer> estatusIds);

    @Query(value = "SELECT * FROM fn_getCboEmpleadoSolicitudProjection(:empleadoId) ORDER BY Nombre", nativeQuery = true)
    List<ComboEmpleadoSolicitudProjection> getComboEmpleadoSolicitudProjection(@Param("empleadoId") Integer empleadoId);
}
