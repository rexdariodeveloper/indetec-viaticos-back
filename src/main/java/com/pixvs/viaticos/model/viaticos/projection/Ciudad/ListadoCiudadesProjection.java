package com.pixvs.viaticos.model.viaticos.projection.Ciudad;

import com.pixvs.viaticos.model.viaticos.Ciudad;
import com.pixvs.viaticos.model.viaticos.Estado;
import com.pixvs.viaticos.model.viaticos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.Pais;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoCiudadesProjection", types = {Ciudad.class})
public interface ListadoCiudadesProjection {

    Integer getId();
    int getEstadoId();
    Estado getEstado();
    int getPaisId();
    Pais getPais();
    String getNombre();
    int getZonaEconomicaId();
    ListadoCMM getZonaEconomica();
    int getEstatusId();
    String getTimestamp();
}
