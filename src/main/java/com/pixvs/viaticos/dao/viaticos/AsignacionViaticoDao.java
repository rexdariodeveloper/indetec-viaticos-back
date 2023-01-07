package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.AsignacionViatico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.ACTIVO;
import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.BORRADO;
import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.INACTIVO;

public interface AsignacionViaticoDao extends CrudRepository<AsignacionViatico, Integer> {

    AsignacionViatico findAsignacionViaticoById(Integer id);

    @Query(value =
            "SELECT cv.ConceptoViatico as ConceptoViaticoId, cv.Codigo as ConceptoViaticoCodigo ,cv.Concepto as ConceptoViaticoNombre, (mv.Monto*(DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso))) AS MontoConPernocta, (mv.Monto*((DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso)+1)-(DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso)))) AS MontoSinPernocta, ((mv.Monto*(DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso)))+(mv.Monto*((DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso)+1)-(DATEDIFF(D, sv.FechaSalida,sv.FechaRegreso))))) AS MontoPorTransferir \n" +
                    "FROM tblSolicitudViatico sv \n" +
                    "INNER JOIN tblCiudad c ON c.CiudadId = sv.CiudadOrigenId \n" +
                    "INNER JOIN tblGrupoJerarquicoPuesto gjp ON gjp.ListadoPuestoId = sv.PuestoId \n" +
                    "INNER JOIN tblMatrizViatico mv ON mv.GrupoJerarquicoId = gjp.GrupoJerarquicoId AND mv.ListadoZonaId = c.ZonaEconomicaId \n" +
                    "INNER JOIN tblConceptoViatico cv ON cv.ConceptoViatico = mv.ConceptoViaticoId \n" +
                    "WHERE sv.EstatusId = "+ACTIVO+" AND sv.SolicitudViaticoId = :solicitudId \n "+
                    "ORDER BY sv.SolicitudViaticoId ASC", nativeQuery = true)
    List<AsignacionViatico> getAsignacionViatico(@Param("solicitudId") Integer solicitudId);

    List<AsignacionViatico> findAsignacionViaticoByEstatusIdIsNotIn(List<Integer> estatusIds);

    List<AsignacionViatico> findAsignacionViaticoByAsignacionIdAndEstatusIdIsIn(Integer asignacionId, List<Integer> estatusIds);

    //Save
    AsignacionViatico save(AsignacionViatico asignacionViatico);
}
