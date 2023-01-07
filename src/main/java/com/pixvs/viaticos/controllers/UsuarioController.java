package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.exceptions.UsuarioException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.MenuPrincipal;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.MenuPrincipal.MenuPrincipalArbolPermisosProjection;
import com.pixvs.viaticos.model.viaticos.projection.Usuario.UsuarioLoginProjection;
import com.pixvs.viaticos.service.ArchivoService;
import com.pixvs.viaticos.service.MenuPrincipalService;
import com.pixvs.viaticos.service.RolService;
import com.pixvs.viaticos.service.UsuarioService;
import com.pixvs.viaticos.util.JSEncriptador;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends GenericController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MenuPrincipalService menuPrincipalService;

    @Autowired
    private Environment environment;

    @Autowired
    private ArchivoService archivoService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public JsonResponse login(@RequestBody Map<String, String> json) throws UsuarioException, UnsupportedEncodingException {
        String usuario;
        String contrasenia;

        //Validar que el JSON contenga informacion.
        if(json != null){

            //Verificar que vengan los dos parametros que necesitamos, Usuario y Contrasenia.
            if(!json.containsKey("usuario") || !json.containsKey("contrasenia")){
                throw new UsuarioException(UsuarioException.STATUS_PARAMETROS_INVALIDOS, false);
            }

            //Obtenemos los valores para buscar el usuario
            usuario = json.get("usuario");
            contrasenia = json.get("contrasenia");

            //Buscamos el usuario
            UsuarioLoginProjection user = usuarioService.buscarPorUsuarioContrasenia(usuario, contrasenia);

            //Si el usuario no se encontro, enviar error
            if(user == null){
                throw new UsuarioException(UsuarioException.STATUS_USUARIO_CONTRASEÑA_INCORRECTOS, false);
            }

            //Si el usuario se encontro, verificar que su estatus sea Activo
            if (user.getEstatusId() != ListadoCMM.EstatusRegistro.ACTIVO
                    || user.getEmpleado().getEstatusId() != ListadoCMM.EstatusRegistro.ACTIVO) {
                throw new UsuarioException(UsuarioException.STATUS_USUARIO_INACTIVO, false);
            }

            //Si se encontro el usuario, generamos su token
            String jwtToken = Jwts.builder()
                                  .setIssuer("viaticos")
                                  .setSubject(usuario)
                                  .claim("id", user.getId())
                                  .claim("usuario", user.getUsuario())
                                  .claim("idRol", user.getRolId())
                                  .setIssuedAt(new Date())
                                  .signWith(SignatureAlgorithm.HS256, environment.getProperty("environments.jwt.secret").getBytes("UTF-8"))
                                  .compact();


            //Obtener el Menu que corresponde al rol del usuario
            List<MenuPrincipalArbolPermisosProjection> listMenuPrincipal = menuPrincipalService.getArbolMenuPrincipalPermisosUsuario(user.getId());

            //Buscar la fotografía del usuario
            user.getEmpleado().setArchivoFotografia(
                    user.getEmpleado().getFotografia() != null
                    ? archivoService.buscarArchivoProjectionById(user.getEmpleado().getFotografia()) : null);

            //Llenamos el objeto a regresar con toda la informacion del Usuario
            HashMap<String, Object> hmLogin = new HashMap<>();
            hmLogin.put("usuario", user);
            hmLogin.put("token", jwtToken);
            hmLogin.put("athid", JSEncriptador.aesEncrypt(user.getId().toString()));
            hmLogin.put("menu", listMenuPrincipal);

            //Regresamos
            return new JsonResponse(hmLogin, "Usuario");
        }
        //Si el JSON esta vacio, lanzar excepcion.
        else{
            throw  new UsuarioException(UsuarioException.STATUS_PARAMETROS_INVALIDOS, false);
        }
    }

    @Override
    public JsonResponse getDatosFichas(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse buscaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse eliminaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }
}
