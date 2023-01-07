package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.SolicitudViatico;
import com.pixvs.viaticos.model.viaticos.projection.SolicitudViatico.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.*;

public interface SolicitudViaticoDao extends CrudRepository<SolicitudViatico, Integer> {

    SolicitudViatico findSolicitudViaticoById(Integer id);

    EditarSolicitudViaticoProjection findEditarSolicitudViaticoProjectionById(Integer id);

    SolicitudViatico findSolicitudViaticoByIdAndEmpleadoId(Integer solicitudId, Integer empledoId);

    List<SolicitudViatico> findSolicitudViaticoByEstatusIdIsNotIn(List<Integer> estatusIds);

    @Query(value = "SELECT * FROM fn_getListadoSolicitudesPorUsuarioId(:usuarioId) ORDER BY FechaCreacion DESC",  nativeQuery = true)
    List<ListadoSolicitudViaticoProjection> getListadoSolicitudesPorUsuarioId(@Param("usuarioId") int usuarioId);

    @Query(value = "SELECT * FROM fn_getResumenSolicitudViaticoPorId(:id)",  nativeQuery = true)
    ResumenSolicitudViaticoProjection getResumenSolicitudViaticoPorId(@Param("id") Integer id);

    //Listado de solicitudes de informe y comprobacion
    @Query(value = "SELECT * FROM fn_getListadoInformeComprobacionPorUsuarioId(:usuarioId) ORDER BY orden",  nativeQuery = true)
    List<ListadoSolicitudViaticoInformeComprobacionProjection> getListadoInformeComprobacionPorUsuarioId(@Param("usuarioId") Integer usuarioId);

    //Listado de Revision
    @Query(value = "SELECT * FROM fn_getListadoRevisionSolicitudViatico() ORDER BY NumeroSolicitud ASC",  nativeQuery = true)
    List<ListadoRevisionSolicitudViaticoProjection> getListadoRevisionSolicitudViatico();

    @Query(value = "SELECT * FROM fn_getRptTransparencia(:fechaInicio, :fechaFin) ORDER BY NumeroSolicitud", nativeQuery = true)
    List<ReporteTransparenciaSolicitudViaticoProjection> getReporteTransparencia(@Param("fechaInicio") Timestamp fechaInicio, @Param("fechaFin") Timestamp fechaFin);

    @Query(value = "SELECT * FROM fn_getRptTransparenciaPartidas(:solicitudViaticoId) ORDER BY Clave", nativeQuery = true)
    List<ReporteTransparenciaPartidaSolicitudViaticoProjection> getReporteTransparenciaPartidas(@Param("solicitudViaticoId") Integer solicitudViaticoId);

    @Query(value = "SELECT * FROM fn_getRptTransparenciaArchivos(:solicitudViaticoId) ORDER BY Orden", nativeQuery = true)
    List<ReporteTransparenciaArchivosSolicitudViaticoProjection> getReporteTransparenciaArchivos(@Param("solicitudViaticoId") Integer solicitudViaticoId);

    @Query(value = "SELECT * FROM fn_getHistorialSolicitudViatico(:solicitudViaticoId)", nativeQuery = true)
    List<HistorialSolicitudViaticoProjection> getHistorialSolicitudViatico(@Param("solicitudViaticoId") Integer solicitudViaticoId);

    @Query(value = "SELECT dbo.fn_getExisteSolicitud(:id, :empleadoId, :fechaSalida, :fechaRegreso)",  nativeQuery = true)
    String existeRegistro(@Param("id") Integer id, @Param("empleadoId") int empleadoId, @Param("fechaSalida") Timestamp fechaSalida, @Param("fechaRegreso") Timestamp fechaRegreso);

    @Query(value = "SELECT * FROM fn_getRptTransparenciaConcentrado(:solicitudIds) ORDER BY CodigoSolicitud", nativeQuery = true)
    List<List> getReporteTransparenciaConcentrado(@Param("solicitudIds") String solicitudIds);

    RecursosTransferidosFinanzasSolicitudViaticoProjection findRecursosTransferidosFinanzasSolicitudViaticoProjectionById(Integer solicitudId);
}
