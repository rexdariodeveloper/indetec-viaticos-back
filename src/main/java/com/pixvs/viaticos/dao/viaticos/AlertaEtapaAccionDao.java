package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.AlertaEtapaAccion;
import com.pixvs.viaticos.model.viaticos.projection.AlertaEtapaAccion.ConfigurarAlertaEtapaAccionProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaEtapaAccionDao extends CrudRepository<AlertaEtapaAccion, Integer> {

    List<ConfigurarAlertaEtapaAccionProjection> findConfigurarProjectionByEtapaId(Integer etapaId);

    @Query(value = "SELECT AlertaEtapaAccionId FROM tblAlertaEtapaAccion WHERE EtapaId = :etapaId", nativeQuery = true)
    List<Integer> findIdsByEtapaId(@Param("etapaId") Integer etapaId);

}
