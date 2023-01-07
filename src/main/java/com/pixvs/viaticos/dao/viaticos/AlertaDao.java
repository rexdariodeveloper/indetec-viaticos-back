package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Alerta;
import com.pixvs.viaticos.model.viaticos.projection.Alerta.AlertaListadoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AlertaDao extends CrudRepository<Alerta,Integer> {

    Alerta findAlertaById(Integer id);

    @Query(value= "SELECT * FROM fn_getListadoAlertasPorUsuario(:usuarioId)", nativeQuery = true)
    ArrayList<AlertaListadoProjection> getListadoAlertas(@Param("usuarioId") Integer usuarioId);

    @Query(value = "SELECT * FROM fn_getPermisoAutorizacionAlerta(:menuId, :solicitudId, :usuarioId)", nativeQuery = true)
    List<List> getPermisoAutorizacionAlerta(@Param("menuId") int menuId, @Param("solicitudId") int solicitudId, @Param("usuarioId") int usuarioId);
}
