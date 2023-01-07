package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Asignacion;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViaticoAsignacion.ListadoSolicitudViaticoAsignacionProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionDao extends CrudRepository<Asignacion, Integer> {

    Asignacion findAsignacionById(Integer id);

    //Search ID
    Asignacion findAsignacionBySolicitudViaticoIdAndEstatusIdIsIn(Integer solicitudId, List<Integer> estatusIds);

    List<Asignacion> findAsignacionByEstatusIdIsNotIn(List<Integer> estatusIds);

    //Save Asignacion
    Asignacion save(Asignacion asignacion);

    // Listado de Solicitud Viatico Asignacion
    @Query(value = "SELECT * FROM fn_getListadoAsignacionViaticos() ORDER BY orden", nativeQuery = true)
    List<ListadoSolicitudViaticoAsignacionProjection> getListadoSolicitudViaticoAsignacionProjection();

    @Query(value = "SELECT * FROM fn_getCtasPolizaComprometido(:asignacionId) ORDER BY Tipo, ConceptoViaticoNombre, QuienComprueba",  nativeQuery = true)
    List<Object> getCtasPolizaComprometido(@Param("asignacionId") int asignacionId);
}
