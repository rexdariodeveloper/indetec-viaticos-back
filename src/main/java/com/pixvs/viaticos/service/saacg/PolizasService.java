package com.pixvs.viaticos.service.saacg;

import com.pixvs.viaticos.dao.viaticos.AsignacionDao;
import com.pixvs.viaticos.model.viaticos.JsonCtasPolizaComprometido;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.service.GenericService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class PolizasService extends GenericService {

    private final String URL_POLIZA_COMPROMETIDO = "/api/viaticos/polizacomprometido";
    private final String URL_POLIZA_COMPROBACION = "/api/viaticos/polizacomprobaciondegasto";

    @Autowired
    private WebClienteService webClienteService;

    @Autowired
    private AsignacionDao asignacionDao;

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

    @Override
    public boolean existePorId(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean existePorCodigo(String codigo) throws Exception {
        return false;
    }

    public HashMap<String, Object> getPolizaComprometido(String jsonPoliza, int ejercicio) throws Exception {
        JsonResponse response = webClienteService.post(URL_POLIZA_COMPROMETIDO, jsonPoliza, ejercicio, true);
        return new ObjectMapper().convertValue(((ArrayList) response.getData()).get(0), HashMap.class);
    }

    public List<JsonCtasPolizaComprometido> getCtasPolizaComprometido(Integer asignacionId) throws Exception {
        List<JsonCtasPolizaComprometido> cuentas = new ArrayList<>();

        for (Object cuenta : asignacionDao.getCtasPolizaComprometido(asignacionId)) {
            cuentas.add(
                    new JsonCtasPolizaComprometido(
                            ((Object[]) cuenta)[0].toString(),
                            Integer.valueOf(((Object[]) cuenta)[1].toString()),
                            ((Object[]) cuenta)[2].toString(),
                            new BigDecimal(((Object[]) cuenta)[3].toString()),
                            Integer.valueOf(((Object[]) cuenta)[4].toString())
                    )
            );
        }

        return cuentas;
    }

    public HashMap<String, Object> getPolizaComprobacion(String jsonPolizaComprobacion, int ejercicio) throws Exception {
        JsonResponse response = webClienteService.post(URL_POLIZA_COMPROBACION, jsonPolizaComprobacion, ejercicio ,true);
        return new ObjectMapper().convertValue(response, HashMap.class);
    }
}
