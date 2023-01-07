package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.RolException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.Rol;
import com.pixvs.viaticos.model.viaticos.RolMenu;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Rol.RolEditarProjection;
import com.pixvs.viaticos.service.MenuPrincipalService;
import com.pixvs.viaticos.service.RolMenuService;
import com.pixvs.viaticos.service.RolService;
import com.pixvs.viaticos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/rol")
public class RolController extends GenericController {

    @Autowired
    RolService rolService;

    @Autowired
    RolMenuService rolMenuService;

    @Autowired
    MenuPrincipalService menuPrincipalService;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {

        HashMap datosFicha  = new HashMap();

        //Si se envio un Id de Rol, buscar el registro
        if(json != null && json.containsKey("rolId")) {
            Integer rolId =  new Integer((String)json.get("rolId"));
            datosFicha.put("rol", rolService.buscarParaEditarPorId(rolId));
            datosFicha.put("rolMenus", rolMenuService.buscaTodosPorRolId(rolId));
        }

        datosFicha.put("menuprincipal", menuPrincipalService.getArbolMenuPrincipal());
        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(rolService.buscarTodos(), "Roles");
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody Rol rol, @RequestHeader HttpHeaders headers) throws Exception {
        Integer usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        List<Integer> menusId = rol.getMenus();
        List<RolMenu> menus;

        if(rol.getId() == null) {
            RolEditarProjection rolTemp = rolService.buscarPorNombre(rol.getNombre());

            if (rolTemp != null) {
                throw new RolException(RolException.STATUS_ROL_REPETIDO);
            }

            rol.setFechaCreacion(fecha);
            rol.setCreadoPorId(usuarioId);
        } else {
            Rol rolTemp = (Rol) rolService.buscarPorId(rol.getId());

            if (!rol.getTimestamp().equals(rolTemp.getTimestamp())) {
                throw new RolException(RolException.STATUS_CAMBIO_TIMESTAMP);
            }

            rol.setFechaUltimaModificacion(fecha);
            rol.setModificadoPorId(usuarioId);

            menus = rolMenuService.buscaTodosPorRolId(rol.getId());
            List<RolMenu> menusTemp = new ArrayList<>();

            for (RolMenu menu : menus) {
                if (!menusId.contains(menu.getNodoMenuId())) {
                    RolMenu menuTemp = menu;

                    menuTemp.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
                    menuTemp.setFechaUltimaModificacion(fecha);
                    menuTemp.setModificadoPorId(usuarioId);

                    menusTemp.add(menu);
                }
            }

            if (!menusTemp.isEmpty()) {
                rolMenuService.guardar(menusTemp);
            }
        }

        menus = new ArrayList<>();

        rol = (Rol) rolService.guardar(rol);

        for (int menuId : menusId) {
            RolMenu menu = (RolMenu) rolMenuService.buscarPorRolIdAndMenuId(rol.getId(), menuId);

            if (menu == null) {
                menu = new RolMenu();
                menu.setRolId(rol.getId());
                menu.setNodoMenuId(menuId);
                menu.setEstatusId(ListadoCMM.EstatusRegistro.ACTIVO);
                menu.setFechaCreacion(fecha);
                menu.setCreadoPorId(usuarioId);
                menus.add(menu);
            }
        }

        if (!menus.isEmpty()) {
            rolMenuService.guardar(menus);
        }

        return new JsonResponse(true,"Registro guardado.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());
        Integer rolId = (Integer) json.get("id");

        if (usuarioService.existePorRol(rolId)) {
            throw new RolException(RolException.STATUS_ROL_USUARIO);
        }

        Rol rol = (Rol) rolService.buscarPorId(rolId);

        rol.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
        rol.setFechaUltimaModificacion(fecha);
        rol.setModificadoPorId(usuarioId);

        rolService.guardar(rol);

        List<RolMenu> menus = rolMenuService.buscaTodosPorRolId(rol.getId());

        for (RolMenu menu : menus) {
            menu.setEstatusId(ListadoCMM.EstatusRegistro.BORRADO);
            menu.setFechaUltimaModificacion(fecha);
            menu.setModificadoPorId(usuarioId);
        }

        if (!menus.isEmpty()) {
            rolMenuService.guardar(menus);
        }

        return new JsonResponse(true, "Registro borrado.");
    }
}
