package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.MenuPrincipal;
import com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal.MenuPrincipalArbolPermisosProjection;
import com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal.MenuPrincipalArbolProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuPrincipalDao extends CrudRepository<MenuPrincipal, Integer> {

    List<MenuPrincipal> findAllByEstatusIdIsNotIn(int estatusId);

    @Query(value = "SELECT NodoMenuId as Id, Etiqueta, Descripcion, TipoNodoId, NodoPadreId, AdmitePermiso FROM fn_getArbolMenuPrincipal()", nativeQuery = true)
    List<MenuPrincipalArbolProjection> getArbolMenuPrincipal();

    @Query(value = "SELECT NodoMenuId as Id, Etiqueta, Descripcion, TipoNodoId, NodoPadreId, Url, Icono FROM fn_getArbolMenuPermisosUsuario(:empleadoId)", nativeQuery = true)
    List<MenuPrincipalArbolPermisosProjection> getArbolMenuPrincipalPermisosUsuario(@Param("empleadoId") Integer empleadoId);
}
