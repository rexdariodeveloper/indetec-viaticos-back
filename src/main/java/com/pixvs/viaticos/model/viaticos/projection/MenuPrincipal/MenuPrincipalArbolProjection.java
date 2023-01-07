package com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal;

import com.pixvs.viaticos.model.viaticos.MenuPrincipal;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "menuPrincipalArbol", types = {MenuPrincipal.class})
public interface MenuPrincipalArbolProjection {

    Integer getId();
    String getEtiqueta();
    String getDescripcion();
    Integer getTipoNodoId() ;
    Integer getNodoPadreId();
    //Integer getSistemaAccesoId();
    //String getUrl() ;
    //String getIcono();
    Boolean getAdmitePermiso();
    //Integer getEstatusId();
    //Integer getOrden();


}



