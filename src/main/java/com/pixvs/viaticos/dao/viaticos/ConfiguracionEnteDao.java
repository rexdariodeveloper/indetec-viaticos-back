package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.ConfiguracionEnte;
import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.CorreosConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.ServidorFTPConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.SolicitudConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.ConfiguracionEnte.SolicitudViaticoComprobacionConfiguracionEnteProjection;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolListadoProjection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConfiguracionEnteDao extends CrudRepository<ConfiguracionEnte, Integer> {

    ConfiguracionEnte save(ConfiguracionEnte rol);

    ConfiguracionEnte findRolById(Integer id);

    List<ConfiguracionEnte> findAllBy();

    SolicitudConfiguracionEnteProjection findSolicitudConfiguracionEnteProjectionByIdNotIn(Integer id);

    CorreosConfiguracionEnteProjection findCorreosConfiguracionEnteProjectionByIdNotIn(Integer id);

    SolicitudViaticoComprobacionConfiguracionEnteProjection findSolicitudViaticoComprobacionConfiguracionEnteProjectionByIdNotIn(Integer id);

    ServidorFTPConfiguracionEnteProjection findServidorFTPConfiguracionEnteProjectionByIdNotIn(Integer id);
}
