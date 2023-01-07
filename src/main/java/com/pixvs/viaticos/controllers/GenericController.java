package com.pixvs.viaticos.controllers;

import com.pixvs.viaticos.model.viaticos.JsonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@RestController
public abstract class GenericController {

    /** Obtiene los datos necesarios para el correcto
     * funcionamiento de una ficha específica.
     * @RequestBody - La anotación debe especificarse en los parámetros
     * al sobreescribir el método, en caso contrario el json será null.
     * @param json - JSon con los datos que permitirán la búsqueda
     *             de los bjetos modelo para la ficha.
     * @param headers - HttpHeaders con datos de la cabecera.
     * @return - JsonResponse con los datos consultados dentro del método.
     **/
    @RequestMapping(value = "/datosficha", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract JsonResponse getDatosFichas(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception;


    /** Obtiene un Listado de registros que se utilizan por lo general en la vista principal de una ficha.
     * @RequestBody - La anotación debe especificarse en los parámetros
     * al sobreescribir el método, en caso contrario el json será null.
     * @param headers - HttpHeaders con datos de la cabecera.
     * @return - JsonResponse con los listados consultados dentro del método.
     **/
    @RequestMapping(value = "/listado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract JsonResponse getListado(@RequestHeader HttpHeaders headers) throws Exception;

    /** Busca un registro mediante su id en una tabla especifica.
     * @RequestBody - La anotación debe especificarse en los parámetros
     * al sobreescribir el método, en caso contrario el json será null.
     * @Transactional para hacer rollback en caso de ocurrir una excepción
     * @param json - JSon con los objetos a guardar, así como los
     *             datos necesarios para crear o modificar los registros.
     * @param headers - HttpHeaders con datos de la cabecera.
     * @return - JsonResponse objeto modelo con los datos del registro buscado.
     **/
    @RequestMapping(value = "/buscaporid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract JsonResponse buscaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception;


    /** Guarda los registros generados desde una ficha específica.
     * @RequestBody - La anotación debe especificarse en los parámetros
     * al sobreescribir el método, en caso contrario el json será null.
     * @Transactional para hacer rollback en caso de ocurrir una excepción
     * @param objeto - Objeto a guardar.
     * @param headers - HttpHeaders con datos de la cabecera.
     * @return - JsonResponse con objetos guardados o modificados,
     * también podrá retornar la confirmación de la información guardada.
     **/
    //@Transactional(rollbackOn = Exception.class)
    //@RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    //public abstract JsonResponse guarda(@RequestBody Object objeto, @RequestHeader HttpHeaders headers) throws Exception;

    /** Eliminar registros generados especificados desde una ficha.
     * @RequestBody - La anotación debe especificarse en los parámetros
     * al sobreescribir el método, en caso contrario el json será null.
     * @Transactional para hacer rollback en caso de ocurrir una excepción
     * @param json - JSon con los objetos a eliminar, así como los
     *             datos necesarios para eliminar registros especificados.
     * @param headers - HttpHeaders con datos de la cabecera.
     * @return - JsonResponse con los registros que se eliminaron o
     * la confirmación de los registros eliminados.
     **/
    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/eliminaporid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract JsonResponse eliminaPorId(@RequestBody Map<String, Object> json, @RequestHeader HttpHeaders headers) throws Exception;
}