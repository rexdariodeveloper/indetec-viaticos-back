package com.pixvs.viaticos.model.viaticos.organigrama;

import com.pixvs.viaticos.model.viaticos.Organigrama;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "organigramaProjection", types = {Organigrama.class})
public interface OrganigramaProjection {

    int getId();
    Integer getNodoPadreId();
    String getDescripcion();
    Integer getResponsableId();
    boolean isPermiteAutorizacion();
    int getOrden();
    int getEstatusId();
    //Timestamp getFechaRegistro();
    //int getRegistradoPorId();
    //Timestamp getFechaUltimaModificacion();
    //Integer getModificadoPorId();
    String getTimestamp();
}
