package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.AlertaConfiguracion;
import com.pixvs.viaticos.model.viaticos.projection.AlertaConfiguracion.EditarAlertaConfiguracionProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaConfiguracionDao extends CrudRepository<AlertaConfiguracion, Integer> {

    AlertaConfiguracion findAlertaConfiguracionById(Integer id);

    List<EditarAlertaConfiguracionProjection> findListadoEditarProjectionByEtapaAccionIdInAndEstatusIdNotIn(List<Integer> etapaAccionIds, List<Integer> estatusIdsNot);

    AlertaConfiguracion save(AlertaConfiguracion alertaConfiguracion);

}
