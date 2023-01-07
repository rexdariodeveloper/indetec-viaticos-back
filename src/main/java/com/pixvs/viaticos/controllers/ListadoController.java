package com.pixvs.viaticos.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.exceptions.ListadoException;
import com.pixvs.viaticos.model.viaticos.*;

import com.pixvs.viaticos.service.*;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/listado")
public class ListadoController extends GenericController {

    @Autowired
    private ListadoService listadoService;

    @Autowired
    private ListadoEsquemaService listadoEsquemaService;

    @Autowired
    private ListadoEsquemaFormatoService listadoEsquemaFormatoService;

    @Autowired
    private ListadoPuestoService listadoPuestoService;

    @Autowired
    private ListadoCargoService listadoCargoService;

    @Autowired
    private SistemaService sistemaService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer esquemaId = json.get("esquemaId") != null ? (Integer) json.get("esquemaId") : null;
        ListadoEsquema listadoEsquema = (ListadoEsquema) listadoEsquemaService.buscarPorId(esquemaId);
        List<ListadoEsquemaFormato> listadoEsquemaFormatoList = listadoEsquemaFormatoService.searchEsquemaId(esquemaId);
        HashMap<String, Object> _listados = new HashMap<>();
        _listados.put("esquemasFormato", listadoEsquemaFormatoService.searchEsquemaId(esquemaId));
        _listados.put("listadoData", listadoService.searchTable(listadoEsquema, listadoEsquemaFormatoList,listadoEsquema.getNombreControl()));
        return new JsonResponse(_listados);
    }

    @Override
    public JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception {
        //List<Integer> listados = new ArrayList<>();
        //listados.add(ListadoCMM.EstatusRegistro.LISTADO_PUESTO); // Puesto
        //listados.add(ListadoCMM.EstatusRegistro.LISTADO_CARGO); // Cargo

        // Get Listado Esquema
        List<ListadoEsquema> listadoEsquemas = (List<ListadoEsquema>) listadoEsquemaService.buscarTodos();

        return new JsonResponse(listadoEsquemas);
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
    public JsonResponse guarda(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception {
        Integer idUsuario = JwtFilter.getUsuarioId(headers);

        //Mapper Convert Object to Model
        ObjectMapper mapper = new ObjectMapper();

        ListadoEsquema listadoEsquema = mapper.convertValue(json.get("listadoEsquema"), ListadoEsquema.class);
        ListadoEsquemaFormato[] listadoEsquemaFormato = mapper.convertValue(json.get("listadoEsquemaFormato"), ListadoEsquemaFormato[].class);
        List<Map<String, Object>> listadoData = mapper.convertValue(json.get("listadoData"),  new TypeReference<List<Map<String,Object>>>() {});


        //Listado Data
        for(Map<String, Object> maps : listadoData){
            String columns = "", rows = "";
            Boolean update = false;

            if(maps.get(listadoEsquemaFormato[0].getNombreCampoTabla()) == ""){
                maps.put(""+listadoEsquemaFormato[0].getNombreCampoTabla()+"", null);
                //maps.put("FechaCreacion",new Timestamp(new Date().getTime()));
                maps.put("CreadoPorId",idUsuario);

                //Fecha Creacion
                //columns += "FechaCreacion, ";
                //rows += "'" + maps.get("FechaCreacion") + "', ";

                // Creado Por Id
                columns += "CreadoPorId, ";
                rows += maps.get("CreadoPorId") + ", ";

                for(int i = 0; i < listadoEsquemaFormato.length; i++){
                    if(maps.get(listadoEsquemaFormato[i].getNombreCampoTabla()) != null){

                        switch (listadoEsquemaFormato[i].getTipoDato()){
                            case "NVARCHAR":
                                if(!listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("Nombre")){
                                    rows += "'"+listadoEsquema.getNombreControl() + "'";
                                }else {
                                    rows += "'" + maps.get(listadoEsquemaFormato[i].getNombreCampoTabla()) + "'";
                                }
                                break;
                            default:
                                if(listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("Predeterminada")){
                                    rows+=0;
                                }
                                //Condiciones para la tabla CMM
                                else if(!listadoEsquema.getTablaControl() && (listadoEsquemaFormato[i].getNombreCampoTabla().equals("ControlSencillo") || listadoEsquemaFormato[i].getNombreCampoTabla().equals("Sistema"))){
                                    rows += 0;
                                }
                                else if(!listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("ModuloId")){
                                    rows += null;
                                }else {
                                    rows += maps.get(listadoEsquemaFormato[i].getNombreCampoTabla());
                                }
                                break;
                        }
                        columns += listadoEsquemaFormato[i].getNombreCampoTabla();
                    /*if(listadoEsquemaFormato[i].getTipoDato().equals("NVARCHAR")){
                        output += "'"+ maps.get(listadoEsquemaFormato[i].getNombreCampoTabla()) + "'";
                    }*/
                        if (i != listadoEsquemaFormato.length - 1) {
                            columns += ", ";
                            rows += ", ";
                        }
                    }
                }

                //Activo
                columns += ", Activo";
                rows += ", " + ((Boolean)maps.get("Activo") ? 1 : 0);

                if (!(Boolean)maps.get("Activo")) {
                    if(listadoEsquema.getNombreTabla().equals("tblListadoCMM")){
                        if((Boolean) maps.get("Sistema") == true){
                            throw new ListadoException(ListadoException.STATUS_ERROR_LISTADO_SISTEMA);
                        }
                    }
                }

                try{
                    //int d=1/0;
                    listadoService.saveListadoData(listadoEsquema.getNombreTabla(), columns, rows);
                }catch (Exception e) {
                   int error=ListadoException.STATUS_ERROR_GUARDAR_EL_DATO;

                    if(e.getCause()!=null && e.getCause().getCause()!=null && e.getCause().getCause().toString().contains("truncar")){
                       error=1;
                       ListadoException.msj= "Algún dato de los que quiere registrar supera los caracteres permitidos";
                        //throw new ListadoException(ListadoException.STATUS_ERROR_CANTIDA_CARACTERES);
                    }else{
                        ListadoException.msj=e.getMessage();
                    }


                    throw new ListadoException(ListadoException.STATUS_ERROR_GUARDAR_EL_DATO);

//System.out.println("------------"+e.getCause().getCause());
                   // System.out.println("------------"+error);
                    //throw new ListadoException(error);

                }
            }else{
                Integer id = (Integer) maps.get(listadoEsquemaFormato[0].getNombreCampoTabla());

                // Verificar si el usuario ha sido modificado en mismo y regresa mensaje de error
                String timestamp = listadoService.getTimestamp(listadoEsquema.getNombreTabla(), listadoEsquemaFormato[0].getNombreCampoTabla() + " = " + id);
                if(!maps.get("Timestamp").equals(timestamp)){
                    throw new Exception(listadoEsquema.getEtiqueta() + " con el código [" + id + "] ha sido modificado por otro usuario. Favor de recargar la vista antes de guardar.");
                }

                maps.put("FechaUltimaModificacion", new Timestamp(new Date().getTime()));
                maps.put("ModificadoPorId", idUsuario);

                //Fecha Ultima Modificacion
                rows += "FechaUltimaModificacion = getdate(), ";

                // Creado Por Id
                rows +=  "ModificadoPorId = " + maps.get("ModificadoPorId") + ", ";

                for(int i = 1; i < listadoEsquemaFormato.length; i++){

                    if(maps.get(listadoEsquemaFormato[i].getNombreCampoTabla()) != null){

                        switch (listadoEsquemaFormato[i].getTipoDato()){
                            case "NVARCHAR":
                                if(!listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("Nombre")){
                                    rows += listadoEsquemaFormato[i].getNombreCampoTabla() + "='" +listadoEsquema.getNombreControl() + "'";
                                }else {
                                    rows += listadoEsquemaFormato[i].getNombreCampoTabla() + "='" + maps.get(listadoEsquemaFormato[i].getNombreCampoTabla()) + "'";
                                }
                                break;
                            default:
                                if(listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("Predeterminada")){
                                    rows+=listadoEsquemaFormato[i].getNombreCampoTabla() + "=" + 0;
                                }
                                //Condiciones para la tabla CMM
                                else if(!listadoEsquema.getTablaControl() && (listadoEsquemaFormato[i].getNombreCampoTabla().equals("ControlSencillo") || listadoEsquemaFormato[i].getNombreCampoTabla().equals("Sistema"))){
                                    rows += listadoEsquemaFormato[i].getNombreCampoTabla() + "=" + 0;
                                }
                                else if(!listadoEsquema.getTablaControl() && listadoEsquemaFormato[i].getNombreCampoTabla().equals("ModuloId")){
                                    rows += listadoEsquemaFormato[i].getNombreCampoTabla() + "=" + null;
                                }else {
                                    rows += listadoEsquemaFormato[i].getNombreCampoTabla() + "=" + maps.get(listadoEsquemaFormato[i].getNombreCampoTabla());
                                }
                                break;
                        }
                        if (i != listadoEsquemaFormato.length - 1) {
                            columns += ", ";
                            rows += ", ";
                        }
                    }
                }

                //Activo
                columns += ", Activo";
                rows += ", Activo = " + ((Boolean)maps.get("Activo") ? 1 : 0) + " ";

                if (!(Boolean)maps.get("Activo")) {
                    if(listadoEsquema.getNombreTabla().equals("tblListadoCMM")){
                        if((Boolean) maps.get("Sistema") == true){
                            throw new ListadoException(ListadoException.STATUS_ERROR_LISTADO_SISTEMA);
                        }
                    }

                    if(!sistemaService.permiteEliminarRegistro(id, listadoEsquema.getNombreTabla()))
                        throw new Exception("No se puede eliminar " + listadoEsquema.getEtiqueta() + " ya que esta siendo utilizado en otros procesos.");
                }

                try{
                    listadoService.updateListadoData(listadoEsquema.getNombreTabla(), listadoEsquemaFormato[0].getNombreCampoTabla(), rows.replaceAll(", ,",","), id);
                }catch (Exception e){
                    throw new ListadoException(ListadoException.STATUS_ERROR_GUARDAR_EL_DATO);
                }

            }

        }

        return new JsonResponse("");
    }

}
