package com.pixvs.viaticos.model.viaticos.projection.Ciudad;

import com.pixvs.viaticos.model.viaticos.Ciudad;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboCiudadesProjection", types = {Ciudad.class})
public interface ComboCiudadesProjection {

    Integer getId();
    String getNombre();
    int getEstadoId();

}
