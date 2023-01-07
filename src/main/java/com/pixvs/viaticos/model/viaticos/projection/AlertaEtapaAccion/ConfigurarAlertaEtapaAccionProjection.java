package com.pixvs.viaticos.model.viaticos.projection.AlertaEtapaAccion;

import com.pixvs.viaticos.model.viaticos.AlertaEtapaAccion;
import com.pixvs.viaticos.model.viaticos.projection.ListadoCMM.ComboListadoCMMProjection;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "configurarAlertaEtapaAccionProjection", types = {AlertaEtapaAccion.class})
public interface ConfigurarAlertaEtapaAccionProjection {

    Integer getId();
    ComboListadoCMMProjection getEtapa();
    ComboListadoCMMProjection getAccion();
    Boolean getPermiteAutorizacion();
    Boolean getPermiteNotificacion();

}
