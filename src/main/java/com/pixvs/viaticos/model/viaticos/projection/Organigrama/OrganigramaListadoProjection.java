package com.pixvs.viaticos.model.viaticos.projection.Organigrama;

import com.pixvs.viaticos.model.viaticos.Organigrama;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "organigramaListadoProjection", types = {Organigrama.class})
public interface OrganigramaListadoProjection {
    Integer getId();
    String getClave();
    String getDescripcion();
}
