package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.PaisException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.Pais;
import com.pixvs.viaticos.model.viaticos.PasajeUtilizado;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Pais.ListadoPaisesProjection;
import com.pixvs.viaticos.service.EstadosService;
import com.pixvs.viaticos.service.PasajeUtilizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pasajeUtilizado")
public class PasajeUtilizadoController extends GenericController {

    @Autowired
    private PasajeUtilizadoService pasajeUtilizadoService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(pasajeUtilizadoService.getListadoPasajesNoUtilizados());
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody List<Integer> pasajesId, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        List<PasajeUtilizado> pasajes = new ArrayList<>();

        for (Integer id : pasajesId) {
            PasajeUtilizado pasaje = new PasajeUtilizado();
            pasaje.setAsignacionPasajeId(id);
            pasaje.setFechaCreacion(fecha);
            pasaje.setCreadoPorId(usuarioId);
            pasajes.add(pasaje);
        }

        pasajeUtilizadoService.guardar(pasajes);

        return new JsonResponse(true, "Cambios guardados.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }
}
