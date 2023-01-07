package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ListadoPuestoException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.model.viaticos.MatrizViatico;
import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.service.ConceptoViaticoService;
import com.pixvs.viaticos.service.GrupoJerarquicoService;
import com.pixvs.viaticos.service.ListadoCMMService;
import com.pixvs.viaticos.service.MatrizViaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matrizviatico")
public class MatrizViaticoController extends GenericController {

    @Autowired
    private MatrizViaticoService matrizViaticoService;

    @Autowired
    private GrupoJerarquicoService grupoJerarquicoService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private ConceptoViaticoService conceptoViaticoService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json,@RequestHeader HttpHeaders headers) throws Exception {
        Integer grupoJerarquicoId = json.get("grupoJerarquicoId") != null ? (Integer) json.get("grupoJerarquicoId") : null;
        HashMap<String, Object> _json = new HashMap<>();
        _json.put("matrizViatico", matrizViaticoService.buscarPorId(grupoJerarquicoId));
        _json.put("zonas",listadoCMMService.getComboZonasEconomicas());
        _json.put("viaticos", conceptoViaticoService.getListadoConceptoViaticoProjectionByCategoriaId(ListadoCMM.CategoriaViatico.VIATICO));

        return new JsonResponse(_json);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        return new JsonResponse(grupoJerarquicoService.buscarTodos());
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
    public JsonResponse guarda(@RequestBody List<MatrizViatico> matrizViaticoList, @RequestHeader HttpHeaders headers) throws Exception {
        Integer idUsuario = JwtFilter.getUsuarioId(headers);
        Integer grupoJerarquicoId = matrizViaticoList.get(0).getGrupoJerarquicoId();
        for(MatrizViatico matrizViatico : matrizViaticoList){
            if(matrizViatico.getId() == null){
                matrizViatico.setFechaCreacion(new Timestamp(new Date().getTime()));
                matrizViatico.setCreadoPorId(idUsuario);
            }else{
                matrizViatico.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                matrizViatico.setModificadoPorId(idUsuario);
            }
        }
        Object _matrizViaticoList = matrizViaticoService.saveAll(matrizViaticoList);
        if(_matrizViaticoList == null){
            throw new ListadoPuestoException(ListadoPuestoException.STATUS_ERROR_GUARDAR);
        }
        return new JsonResponse(matrizViaticoService.buscarPorId(grupoJerarquicoId));

    }
}
