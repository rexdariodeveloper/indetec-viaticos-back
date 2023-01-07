package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.CiudadException;
import com.pixvs.viaticos.model.viaticos.Ciudad;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.model.viaticos.projection.Ciudad.ListadoCiudadesProjection;
import com.pixvs.viaticos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadesController extends GenericController {

    @Autowired
    private PaisesService paisesService;

    @Autowired
    private EstadosService estadosService;

    @Autowired
    private CiudadesService ciudadesService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String, Object> datosFicha = new HashMap<>();

        datosFicha.put("comboPaises", paisesService.getComboPaises());
        datosFicha.put("comboEstados", estadosService.getComboEstados());
        datosFicha.put("comboZonasEconomicas", listadoCMMService.getComboZonasEconomicas());
        datosFicha.put("listadoCiudades", ciudadesService.getListadoCiudades());

        return new JsonResponse(datosFicha);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(ciudadesService.getListadoCiudades());
    }

    @Override
    public JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse guarda(@RequestBody ArrayList<Ciudad> ciudades, @RequestHeader HttpHeaders headers) throws Exception {
        int usuarioId = JwtFilter.getUsuarioId(headers);
        Timestamp fecha = new Timestamp(new Date().getTime());

        for (Ciudad ciudad : ciudades) {
            if (ciudad.getId() < 0) {
                ListadoCiudadesProjection ciudadTemp = (ListadoCiudadesProjection) ciudadesService.buscaPorEstadoIdAndNombre(ciudad.getEstadoId(), ciudad.getNombre());

                if (ciudadTemp != null) {
                    throw new CiudadException(CiudadException.STATUS_CIUDAD_REPETIDA);
                }

                ciudad.setFechaCreacion(fecha);
                ciudad.setCreadoPorId(usuarioId);
            } else {
                ListadoCiudadesProjection ciudadTemp = (ListadoCiudadesProjection) ciudadesService.buscarPorId(ciudad.getId());

                if (ciudadTemp != null && !ciudad.getTimestamp().equals(ciudadTemp.getTimestamp())) {
                    throw new CiudadException(CiudadException.STATUS_CAMBIO_TIMESTAMP);
                }

                if (ciudad.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if(!sistemaService.permiteEliminarRegistro(ciudad.getId(), "tblCiudad"))
                        throw new CiudadException(CiudadException.STATUS_ERROR_CIUDAD_RELACIONADA);
                }

                ciudad.setFechaUltimaModificacion(fecha);
                ciudad.setModificadoPorId(usuarioId);
            }
        }

        ciudadesService.guardar(ciudades);

        return new JsonResponse(true, "Cambios guardados.");
    }

    @Override
    public JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        return null;
    }
}
