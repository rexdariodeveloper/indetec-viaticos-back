package com.pixvs.viaticos.model.viaticos.projection.AlertaConfiguracion;

import com.pixvs.viaticos.model.viaticos.AlertaConfiguracion;
import com.pixvs.viaticos.model.viaticos.projection.AlertaEtapaAccion.ConfigurarAlertaEtapaAccionProjection;
import com.pixvs.viaticos.model.viaticos.projection.Empleado.ComboEmpleadoProjection;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "editarAlertaConfiguracionProjection", types = {AlertaConfiguracion.class})
public interface EditarAlertaConfiguracionProjection {

    Integer getId();
    ConfigurarAlertaEtapaAccionProjection getEtapaAccion();
    ComboEmpleadoProjection getEmpleado();
    ComboListadoCMMProjection getFigura();
    ComboListadoCMMProjection getTipoNotificacion();
    Boolean getEnPlataforma();
    Integer getEstatusId();
    Boolean getEnCorreoElectronico();
    String getTimestamp();

}
