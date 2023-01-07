package com.pixvs.viaticos.model.viaticos.projection.Alerta;

import com.pixvs.viaticos.model.viaticos.Alerta;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "alertaListadoProjection", types = {Alerta.class})
public interface AlertaListadoProjection {
    Integer getAlertaId();
    String getAlertaIniciadaPor();
    String getFecha();
    String getTipoMovimiento();
    String getTramite();
    String getEstatus();
    String getRutaAccion();
    Integer getTipoAlertaId();
    Integer getEtapaAccionAlAutorizarId();
    Integer getEtapaAccionAlRevisionId();
    Integer getEtapaAccionAlRechazarId();
}
