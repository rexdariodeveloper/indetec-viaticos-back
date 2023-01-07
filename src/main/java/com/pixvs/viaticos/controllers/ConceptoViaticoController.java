package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ConceptoViaticoException;
import com.pixvs.viaticos.model.saacg.ObjetoGasto;
import com.pixvs.viaticos.model.viaticos.ConceptoViatico;
import com.pixvs.viaticos.model.viaticos.JsonResponse;

import com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM;
import com.pixvs.viaticos.service.*;
import com.pixvs.viaticos.service.saacg.ObjetoGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/concepto_viatico")
public class ConceptoViaticoController extends GenericController {

    @Autowired
    private ConceptoViaticoService conceptoViaticoService;

    @Autowired
    private ObjetoGastoService objetoGastoService;

    @Autowired
    private ListadoCMMService listadoCMMService;

    @Autowired
    private SistemaService sistemaService;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        HashMap<String, Object> _json = new HashMap<>();
        _json.put("viaticos",conceptoViaticoService.buscarTodos());
        _json.put("objetoGasto", objetoGastoService.buscarTodos());
        _json.put("categoriaViatico", listadoCMMService.getListadoCMMComboByNombreAndActivo("CategoriaViatico"));
        return new JsonResponse(_json);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
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
    public JsonResponse guarda(@RequestBody List<ConceptoViatico> conceptoViaticoList, @RequestHeader HttpHeaders headers) throws Exception {
        int idUsuario = JwtFilter.getUsuarioId(headers);
        for(ConceptoViatico conceptoViatico : conceptoViaticoList){
            if(conceptoViatico.getId() == null){
                conceptoViatico.setFechaCreacion(new Timestamp(new Date().getTime()));
                conceptoViatico.setCreadoPorId(idUsuario);
            }else{

                // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
                ConceptoViatico _conceptoViatico = (ConceptoViatico) conceptoViaticoService.buscarPorId(conceptoViatico.getId());
                if(!conceptoViatico.getTimestamp().equals(_conceptoViatico.getTimestamp())){
                    throw new Exception("El concepto viatico con el código [" + conceptoViatico.getId() + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
                }

                //Si se va a eliminar un registro pero la Categoria es de tipo 1000059 ( pasaje ) no se puede eliminar
                if(conceptoViatico.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO && conceptoViatico.getCategoriaId() == ListadoCMM.CategoriaViatico.PASAJE) {
                    ConceptoViaticoException exception = new ConceptoViaticoException(ConceptoViaticoException.STATUS_ERROR_BORRAR_REGISTRO_PASAJE);
                    exception.setMessage(exception.getMessage().replace("{0}", "\"" + conceptoViatico.getConcepto() + "\""));
                    throw exception;
                }

                // Verificar si el usuario ha sido eliminado pero si tiene relacion de otro proceso y no se puede eliminar
                if (conceptoViatico.getEstatusId() == ListadoCMM.EstatusRegistro.BORRADO) {
                    if(!sistemaService.permiteEliminarRegistro(conceptoViatico.getId(), "tblConceptoViatico"))
                        throw new ConceptoViaticoException(ConceptoViaticoException.STATUS_ERROR_CONCEPTO_VIATICO_RELACION);
                }

                conceptoViatico.setFechaUltimaModificacion(new Timestamp(new Date().getTime()));
                conceptoViatico.setModificadoPorId(idUsuario);
            }
        }
        Object _conceptoViaticoList = conceptoViaticoService.saveAll(conceptoViaticoList);
        if(_conceptoViaticoList == null){
            throw new ConceptoViaticoException(ConceptoViaticoException.STATUS_ERROR_GUARDADOS);
        }
        return new JsonResponse(_conceptoViaticoList);

    }
}
