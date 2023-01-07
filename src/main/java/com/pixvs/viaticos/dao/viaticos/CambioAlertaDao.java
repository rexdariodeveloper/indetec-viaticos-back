package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.CambioAlerta;
import com.pixvs.viaticos.model.viaticos.projection.CambioAlerta.EditarCambioAlertaProjection;
import com.pixvs.viaticos.model.viaticos.projection.CambioAlerta.ListadoCambioAlertaProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CambioAlertaDao extends CrudRepository<CambioAlerta, Integer> {

    CambioAlerta save(CambioAlerta cambioAlerta);

    CambioAlerta findCambioAlertaById(Integer id);

    EditarCambioAlertaProjection findEditarCambioAlertaProjectionById(Integer rolId);

    @Query(value = "SELECT * FROM fn_getListadoCambioAlertas() ORDER BY Folio",  nativeQuery = true)
    List<ListadoCambioAlertaProjection> getListadoCambioAlertas();

    @Query(value = "SELECT dbo.fn_getExisteCambioAlerta(:id, :empleadoOrigenId, :fechaInicio, :fechaFin)",  nativeQuery = true)
    Integer existeRegistro(@Param("id") Integer id, @Param("empleadoOrigenId") int empleadoOrigenId, @Param("fechaInicio") Timestamp fechaInicio, @Param("fechaFin") Timestamp fechaFin);
}
