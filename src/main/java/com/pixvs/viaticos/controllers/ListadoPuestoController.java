package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ListadoPuestoException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.ListadoPuesto;
import com.pixvs.viaticos.service.ListadoPuestoService;
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
@RequestMapping("/api/listadopuesto")
public class ListadoPuestoController extends GenericController {

    @Autowired
    private ListadoPuestoService listadoPuestoService;

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
    public JsonResponse guarda(@RequestBody List<ListadoPuesto> listadoPuestoList, @RequestHeader HttpHeaders headers) throws Exception {
        int idUsuario = JwtFilter.getUsuarioId(headers);
        for(ListadoPuesto listadoPuesto : listadoPuestoList){
            if(listadoPuesto.getId() == null){
                listadoPuesto.setFechaCreacion(new Timestamp(new Date().getTime()));
                listadoPuesto.setCreadoPorId(idUsuario);
            }else{
                listadoPuesto.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                listadoPuesto.setModificadoPorId(idUsuario);
            }
        }
        Object _listadoPuestoList = listadoPuestoService.saveAll(listadoPuestoList);
        if(_listadoPuestoList == null){
            throw new ListadoPuestoException(ListadoPuestoException.STATUS_ERROR_GUARDAR);
        }
        return new JsonResponse(_listadoPuestoList);

    }
}
