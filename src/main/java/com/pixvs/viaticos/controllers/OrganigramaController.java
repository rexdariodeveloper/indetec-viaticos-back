package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.OrganigramaException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.Organigrama;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.service.EmpleadoService;
import com.pixvs.viaticos.service.OrganigramaService;
import com.pixvs.viaticos.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api/organigrama")
public class OrganigramaController extends GenericController {

    @Autowired
    private OrganigramaService organigramaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        //Estatus
        List<Integer> estatus = new ArrayList<>();
        estatus.add(ListadoCMM.EstatusRegistro.BORRADO);
        estatus.add(ListadoCMM.EstatusRegistro.INACTIVO);
        //Crear json para listados
        HashMap<String, Object> json = new HashMap<>();
        json.put("empleados", empleadoService.getEmpleadoListadoProjectionWithStatus(estatus));
        json.put("organigramas",organigramaService.buscarTodos());
        return new JsonResponse(json);
    }

    @Override
    public JsonResponse buscaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse eliminaPorId(Map<String, Object> json, HttpHeaders headers) throws Exception {
        return null;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody List<Organigrama> organigramaList, @RequestHeader HttpHeaders headers) throws Exception {
        Integer idUsuario = JwtFilter.getUsuarioId(headers);

        for(Organigrama organigrama : organigramaList){
            Organigrama getOrganigrama = organigramaService.searchForId(organigrama.getId());
            if(getOrganigrama == null){
                organigrama.setRegistradoPorId(idUsuario);
                organigrama.setFechaRegistro(new Timestamp(new Date().getTime()));

            }else {
                if (organigrama.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if(!sistemaService.permiteEliminarRegistro(organigrama.getId(), "tblOrganigrama"))
                        throw new OrganigramaException(OrganigramaException.STATUS_ERROR_ORGANIGRAMA_RELACIONADA);
                }

                organigrama.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                organigrama.setModificadoPorId(idUsuario);
            }

            try{
                getOrganigrama = (Organigrama) organigramaService.guardar(organigrama);
                for(Organigrama setOrganigrama : organigramaList){
                    if(setOrganigrama.getNodoPadreId() != null){
                        if(setOrganigrama.getNodoPadreId().toString().equals(organigrama.getId().toString())){
                            setOrganigrama.setNodoPadreId(getOrganigrama.getId());
                        }
                    }

                }
            }catch (Exception e){
                throw new OrganigramaException(OrganigramaException.STATUS_ERROR_GUARDADOS);
            }

        }

        return new JsonResponse(organigramaService.buscarTodos(),"organigrama");
    }
}
