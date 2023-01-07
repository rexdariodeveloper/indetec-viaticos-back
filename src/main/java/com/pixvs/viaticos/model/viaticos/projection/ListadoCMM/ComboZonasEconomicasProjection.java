package com.pixvs.viaticos.model.viaticos.projection.ListadoCMM;

import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboZonasEconomicasProjection", types = {Pais.class})
public interface ComboZonasEconomicasProjection {

    Integer getId();
    String getValor();
}
