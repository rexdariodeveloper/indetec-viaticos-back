package com.pixvs.viaticos.service.saacg;

import com.google.gson.Gson;
import com.pixvs.viaticos.model.saacg.FuenteFinanciamiento;
import com.pixvs.viaticos.model.saacg.ProgramaGobierno;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.service.GenericService;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FuenteFinanciamientoService extends GenericService {

    String URL_LISTADO_FUENTES_FINANCIAMIENTO = "/api/viaticos/listadofuentesdefinanciamiento";

    @Autowired
    private WebClienteService webClienteService;

    @Override
    public Object guardar(Object objeto) throws Exception {
        return null;
    }

    @Override
    public void guardar(Collection objetos) throws Exception {

    }

    @Override
    public void eliminarPorId(Integer id) throws Exception {

    }

    @Override
    public Object buscarPorId(Integer id) throws Exception {
        return null;
    }

    @Override
    public Object buscarPorCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return null;
    }

    public Collection buscarTodos(int ejercicio) throws Exception {
        //Creamos el Objeto Mapper para mapear el resultado
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //Llamamos a la api del saacg para obtener el listado
        JsonResponse response = webClienteService.post(URL_LISTADO_FUENTES_FINANCIAMIENTO, null, ejercicio, true);

        //Retornamos el resultado
        return mapper.readValue(new Gson().toJson(response.getData()), new TypeReference<ArrayList<FuenteFinanciamiento>>() {});
    }

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }
}
