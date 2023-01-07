package com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal;

import com.pixvs.viaticos.model.viaticos.MenuPrincipal;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "menuPrincipalArbolPermisosProjection", types = {MenuPrincipal.class})
public interface MenuPrincipalArbolPermisosProjection {

    Integer getId();
    String getEtiqueta();
    String getDescripcion();
    Integer getTipoNodoId() ;
    Integer getNodoPadreId();
    String getUrl() ;
    String getIcono();

}
