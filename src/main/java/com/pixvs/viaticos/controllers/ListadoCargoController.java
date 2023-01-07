package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ListadoCargoException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.ListadoCargo;
import com.pixvs.viaticos.service.ListadoCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/listadocargo")
public class ListadoCargoController extends GenericController {

    @Autowired
    private ListadoCargoService listadoCargoService;

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

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody List<ListadoCargo> listadoCargoList, @RequestHeader HttpHeaders headers) throws Exception {
        int idUsuario = JwtFilter.getUsuarioId(headers);
        for(ListadoCargo listadoCargo : listadoCargoList){
            if(listadoCargo.getId() == null){
                listadoCargo.setFechaCreacion(new Timestamp(new Date().getTime()));
                listadoCargo.setCreadoPorId(idUsuario);
            }else{
                listadoCargo.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                listadoCargo.setModificadoPorId(idUsuario);
            }
        }
        Object _listadoCargoList = listadoCargoService.saveAll(listadoCargoList);
        if(_listadoCargoList == null){
            throw new ListadoCargoException(ListadoCargoException.STATUS_ERROR_GUARDAR);
        }
        return new JsonResponse(_listadoCargoList);

    }
}
