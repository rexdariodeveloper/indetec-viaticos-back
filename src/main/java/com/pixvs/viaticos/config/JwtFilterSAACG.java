package com.pixvs.viaticos.config;

import com.pixvs.viaticos.exceptions.UsuarioException;
import com.pixvs.viaticos.model.viaticos.JsonResponseError;
import com.pixvs.viaticos.util.JSEncriptador;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilterSAACG extends GenericFilterBean {

    private static String secret = "secreto";
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String PREFIX = "Bearer ";

    public JwtFilterSAACG(String secret){
        this.secret = secret;
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException{

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        }

        else {
            try {
                //Validar que vengan los encabezados necesarios
                if(!validarHeader(request)){
                    response.sendError(UsuarioException.STATUS_ENCABEZADOS_INVALIDOS,"Encabezados invalidos");
                    return;
                }

                //Obtenemos el token enviado en el Header
                final String token = request.getHeader(HEADER_AUTHORIZATION).split(PREFIX)[1];
                final Claims claims = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();

                //Agregamos la informacion al request
                request.setAttribute("claims", claims);

                //Y se continua con la peticion
                chain.doFilter(req, res);
            }catch (SignatureException se) {
                response.sendError(JsonResponseError.STATUS_ERROR,"Error de autentificación.\n" + se.getMessage());
            }catch(MalformedJwtException mje){
                response.sendError(JsonResponseError.STATUS_ERROR,"Error de autentificación.\n" + mje.getMessage());
            }catch(Exception e){
                response.sendError(JsonResponseError.STATUS_ERROR,"Error de autentificación.\n" + e.getMessage());
            }
        }
    }

    /**
     * Metodo para verificar que los encabezados de la peticion esten completos y sean los correctos. Se verifica que
     * en los encabezados venga uno con el token ( Bearer ) y otro con el ID del usuario que esta haciendo la peticion.
     * @param request
     * @return Verdadero si todos los encabezados son correctos; Falso de lo contrario.
     */
    private boolean validarHeader(HttpServletRequest request){

        final String authHeader = request.getHeader(HEADER_AUTHORIZATION);

        //Si no viene el encabezado de Autorizacion o si su valor no comienza con "Bearer ", lanzamos error
        if (authHeader == null || !authHeader.startsWith(PREFIX)) {
            return false;
        }

        return true;
    }
}