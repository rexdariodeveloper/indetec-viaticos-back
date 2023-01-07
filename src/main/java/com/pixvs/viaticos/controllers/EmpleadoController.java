package com.pixvs.viaticos.controllers;

import com.google.gson.JsonArray;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.EmpleadoException;
import com.pixvs.viaticos.model.viaticos.*;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMOA;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.*;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.CatalogoCuentaService;
import com.pixvs.viaticos.util.Archivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController extends GenericController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrganigramaService organigramaService;

    @Autowired
    private ListadoPuestoService listadoPuestoService;

    @Autowired
    private ListadoCargoService listadoCargoService;

    @Autowired
    private RolService rolService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private CatalogoCuentaService catalogoCuentaService;
    
    @Autowired
    private PermisoAccesoService permisoAccesoService;

    @Autowired
    private PermisoRegistroService permisoRegistroService;

    @Autowired
    private SistemaService sistemaService;

    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer empleadoId = json.get("empleadoId") != null ? new Integer (json.get("empleadoId").toString()) : null;
        Integer usuarioId = null;

        HashMap<String, Object> datosFicha = new HashMap<>();

        if (empleadoId != null) {
            Usuario usuario = usuarioService.buscaPorEmpleadoId(empleadoId);
            Empleado empleado = usuario.getEmpleado();

            usuarioId = usuario != null ? usuario.getId() : null;
            empleado.setArchivoFotografia(archivoService.buscarArchivoProjectionById(empleado.getFotografia()));

            datosFicha.put("usuario", usuario);
            datosFicha.put("crearTerceros", permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.CREAR_SOLICITUDES_TERCEROS, usuarioId) != null);
            datosFicha.put("visualizarTerceros", permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.VISUALIZAR_SOLICITUDES_TERCEROS, usuarioId) != null);
        }

        datosFicha.put("listadoPuesto", listadoPuestoService.getListadoPuestoWithActive(true));
        datosFicha.put("listadoCargo", listadoCargoService.getListadoCargoWithActive(true));
        datosFicha.put("area", organigramaService.getComboListadoOrganigramaProjection());
        datosFicha.put("tipoEmpleado", listadoCMMService.buscarPorCodigo("TipoEmpleado"));
        datosFicha.put("roles", rolService.getRolWithStatus(EstatusRegistro.ACTIVO));
        datosFicha.put("catalogoCuentas", catalogoCuentaService.buscarTodos());
        datosFicha.put("organigrama", organigramaService.getArbolOrganigramaPermisosUsuario(usuarioId));

        return new JsonResponse(datosFicha);
    }

    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        Collection<Empleado> empleados = empleadoService.buscarTodos();
        for(Empleado empleado: empleados){
            //Obtenemos todas las fotografías del empleado para el listado
            if(empleado.getFotografia()!=null){
                empleado.setArchivoFotografia(archivoService.buscarArchivoProjectionById(empleado.getFotografia()));
            }
        }
        return new JsonResponse(empleados);
    }

    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody Usuario usuario, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        Empleado empleado = usuario.getEmpleado();
        String nombreArchivoTemporal = empleado.getNombreArchivoTemporal();
        Boolean fotografiaEliminada = empleado.isFotografiaEliminada();
        boolean crearTerceros = empleado.isCrearSolicitudesTerceros();
        boolean visualizarTerceros = empleado.isVisualizarSolicitudesTerceros();
        List<Integer> nodosOrganigrama = empleado.getListadoOrganigrama();

        if (empleadoService.existeRepetidoPorCodigo(empleado.getId(), empleado.getNumeroEmpleado())) {
            throw new EmpleadoException(EmpleadoException.STATUS_NUMERO_EMPLEADO_EXIST);
        }

        if (usuarioService.existeRepetidoPorCodigo(usuario.getId(), usuario.getUsuario())) {
            throw new EmpleadoException(EmpleadoException.STATUS_USUARIO_EXIST);
        }

        if (empleado.getId() == null) {
            empleado.setFechaCreacion(fecha);
            empleado.setCreadoPorId(usuarioId);
        } else {
            // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
            Empleado _empleado = (Empleado) empleadoService.buscarPorId(empleado.getId());
            if(!empleado.getTimestamp().equals(_empleado.getTimestamp())){
                throw new Exception("El empleado con el código [" + empleado.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
            }

            // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
            if (empleado.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                if(!sistemaService.permiteEliminarRegistro(empleado.getId(), "tblEmpleado"))
                    throw new EmpleadoException(EmpleadoException.STATUS_ERROR_EMPLEADO_RELACION);
            }

            empleado.setFotografia(fotografiaEliminada ? null : empleado.getFotografia());
            empleado.setFechaUltimaModificacion(fecha);
            empleado.setModificadoPorId(usuarioId);
        }

        empleado = (Empleado) empleadoService.guardar(empleado);

        //Guardar Si se cambió la fotografía
        if (nombreArchivoTemporal != null) {
            String extensionArchivo = nombreArchivoTemporal.substring(nombreArchivoTemporal.lastIndexOf("."));
            Integer tipoArchivo = Archivos.obtenerTipoArchivo(extensionArchivo);

            //Buscamos que no haya un archivo asociado al empleado
            InfoArchivoProjection archivoAnterior = archivoService.buscarInfoProjectionTop1(empleado.getId(), ListadoCMOA.CarpetaFotografiaEmpleado, tipoArchivo);
            String archivoAnteriorId = archivoAnterior == null ? null : archivoAnterior.getId();
            JsonArray ids = new JsonArray();
            ids.add(empleado.getId());
            empleado.setFotografia(this.archivoService.guardarArchivo(usuarioId, archivoAnteriorId, nombreArchivoTemporal, ListadoCMOA.CarpetaFotografiaEmpleado, ids, empleado.getId(), tipoArchivo));

            empleadoService.guardar(empleado);
        }

        if (usuario.getId() == null) {
            usuario.setEmpleadoId(empleado.getId());
            usuario.setFechaCreacion(fecha);
            usuario.setCreadoPorId(usuarioId);
        } else {
            usuario.setFechaUltimaModificacion(fecha);
            usuario.setModificadoPorId(usuarioId);
        }

        usuario.setEstatusId(empleado.getEstatusId());

        usuario = (Usuario) usuarioService.guardar(usuario);

        PermisoAcceso permisoCrearTerceros = permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.CREAR_SOLICITUDES_TERCEROS, usuario.getId());

        if (permisoCrearTerceros == null && crearTerceros) {
            permisoCrearTerceros = new PermisoAcceso();
            permisoCrearTerceros.setTipoPermisoId(TipoPermisoAcceso.CREAR_SOLICITUDES_TERCEROS);
            permisoCrearTerceros.setUsuarioId(usuario.getId());
            permisoCrearTerceros.setBorrado(false);
            permisoCrearTerceros.setFechaCreacion(fecha);
            permisoCrearTerceros.setCreadoPorId(usuarioId);
            permisoAccesoService.guardar(permisoCrearTerceros);
        } else if (permisoCrearTerceros != null && !crearTerceros) {
            permisoCrearTerceros.setBorrado(true);
            permisoCrearTerceros.setFechaUltimaModificacion(fecha);
            permisoCrearTerceros.setModificadoPorId(usuarioId);
            permisoAccesoService.guardar(permisoCrearTerceros);
        }

        PermisoAcceso permisoVisualizarTerceros = permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.VISUALIZAR_SOLICITUDES_TERCEROS, usuario.getId());
        List<PermisoRegistro> permisosRegistro = permisoVisualizarTerceros != null ? permisoRegistroService.getPermisosRegistroPorAccesoId(permisoVisualizarTerceros.getId()) : new ArrayList<>();

        if (visualizarTerceros) {
            if (permisoVisualizarTerceros == null) {
                permisoVisualizarTerceros = new PermisoAcceso();
                permisoVisualizarTerceros.setTipoPermisoId(TipoPermisoAcceso.VISUALIZAR_SOLICITUDES_TERCEROS);
                permisoVisualizarTerceros.setUsuarioId(usuario.getId());
                permisoVisualizarTerceros.setBorrado(false);
                permisoVisualizarTerceros.setFechaCreacion(fecha);
                permisoVisualizarTerceros.setCreadoPorId(usuarioId);
                permisoVisualizarTerceros = (PermisoAcceso) permisoAccesoService.guardar(permisoVisualizarTerceros);
            }

            for (PermisoRegistro registro : permisosRegistro) {
                if (!nodosOrganigrama.contains(registro.getRegistroId())) {
                    registro.setBorrado(true);
                    registro.setFechaUltimaModificacion(fecha);
                    registro.setModificadoPorId(usuarioId);
                }
            }

            for (Integer nodoId : nodosOrganigrama) {
                PermisoRegistro permisoRegistro = permisoRegistroService.getPermisoRegistroPorAccesoIdRegistroId(permisoVisualizarTerceros.getId(), nodoId);

                if (permisoRegistro == null) {
                    permisoRegistro = new PermisoRegistro();
                    permisoRegistro.setPermisoAccesoId(permisoVisualizarTerceros.getId());
                    permisoRegistro.setRegistroId(nodoId);
                    permisoRegistro.setBorrado(false);
                    permisoRegistro.setFechaCreacion(fecha);
                    permisoRegistro.setCreadoPorId(usuarioId);
                    permisosRegistro.add(permisoRegistro);
                }
            }
        } else if (permisoVisualizarTerceros != null && !visualizarTerceros) {
            permisoVisualizarTerceros.setBorrado(true);
            permisoVisualizarTerceros.setFechaUltimaModificacion(fecha);
            permisoVisualizarTerceros.setModificadoPorId(usuarioId);
            permisoAccesoService.guardar(permisoVisualizarTerceros);

            for (PermisoRegistro registro : permisosRegistro) {
                registro.setBorrado(true);
                registro.setFechaUltimaModificacion(fecha);
                registro.setModificadoPorId(usuarioId);
            }
        }

        if (!permisosRegistro.isEmpty()) {
            permisoRegistroService.guardar(permisosRegistro);
        }

        return new JsonResponse(true,"Empleado guardado.");
    }

    public JsonResponse eliminaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/removeforid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse eliminaPorId(@RequestBody Integer id,@RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        //First remove Usuario for estatus BORRADO
        Usuario usuario = usuarioService.buscaPorEmpleadoId(id);
        usuario.setEstatusId(EstatusRegistro.BORRADO);
        usuario.setModificadoPorId(usuarioId);
        usuario.setFechaUltimaModificacion(fecha);
        usuario = (Usuario) usuarioService.guardar(usuario);
        if(usuario == null){
            throw new EmpleadoException(EmpleadoException.STATUS_ELIMINAR_USUARIO);
        }

        //Second remove Empleado for estatus BORRADO
        Empleado empleado = (Empleado) empleadoService.buscarPorId(id);
        empleado.setEstatusId(EstatusRegistro.BORRADO);
        empleado.setModificadoPorId(usuarioId);
        empleado.setFechaUltimaModificacion(fecha);
        empleado = (Empleado) empleadoService.guardar(empleado);
        if(empleado == null){
            throw new EmpleadoException(EmpleadoException.STATUS_ELIMINAR_EMPLEADO);
        }

        // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
        Empleado _empleado = (Empleado) empleadoService.buscarPorId(empleado.getId());
        if(!empleado.getTimestamp().equals(_empleado.getTimestamp())){
            throw new Exception("El empleado con el código [" + empleado.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
        }

        // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
        if (empleado.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
            if(!sistemaService.permiteEliminarRegistro(empleado.getId(), "tblEmpleado"))
                throw new EmpleadoException(EmpleadoException.STATUS_ERROR_EMPLEADO_RELACION);
        }

        PermisoAcceso permisoCrearTerceros = permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.CREAR_SOLICITUDES_TERCEROS, usuario.getId());

        if (permisoCrearTerceros != null) {
            permisoCrearTerceros.setBorrado(true);
            permisoCrearTerceros.setFechaUltimaModificacion(fecha);
            permisoCrearTerceros.setModificadoPorId(usuarioId);
            permisoAccesoService.guardar(permisoCrearTerceros);
        }

        PermisoAcceso permisoVisualizarTerceros = permisoAccesoService.getPermisoAccesoPorTipoIdUsuarioId(TipoPermisoAcceso.VISUALIZAR_SOLICITUDES_TERCEROS, usuario.getId());

        if (permisoVisualizarTerceros != null) {
            permisoVisualizarTerceros.setBorrado(true);
            permisoVisualizarTerceros.setFechaUltimaModificacion(fecha);
            permisoVisualizarTerceros.setModificadoPorId(usuarioId);
            permisoAccesoService.guardar(permisoVisualizarTerceros);

            List<PermisoRegistro> permisosRegistro = permisoVisualizarTerceros != null ? permisoRegistroService.getPermisosRegistroPorAccesoId(permisoVisualizarTerceros.getId()) : new ArrayList<>();

            for (PermisoRegistro registro : permisosRegistro) {
                registro.setBorrado(true);
                registro.setFechaUltimaModificacion(fecha);
                registro.setModificadoPorId(usuarioId);
            }

            if (!permisosRegistro.isEmpty()) {
                permisoRegistroService.guardar(permisosRegistro);
            }
        }

        return new JsonResponse(true, "Empleado borrado.");
    }
}
