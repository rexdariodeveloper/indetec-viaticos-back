package com.pixvs.viaticos.model.viaticos.projection.Organigrama;

import com.pixvs.viaticos.model.viaticos.Organigrama;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comboListadoOrganigramaProjection", types = {Organigrama.class})
public interface ComboListadoOrganigramaProjection {
    Integer getId();
    String getClave();
    String getDescripcion();
}
