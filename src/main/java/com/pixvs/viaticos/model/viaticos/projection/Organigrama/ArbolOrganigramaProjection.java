package com.pixvs.viaticos.model.viaticos.projection.Organigrama;

import com.pixvs.viaticos.model.viaticos.Organigrama;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "arbolOrganigramaProjection", types = {Organigrama.class})
public interface ArbolOrganigramaProjection {

    Integer getId();
    Integer getNodoPadreId();
    String getNombre();
    String getOrdenamiento();
    boolean getTienePermiso();
}
