package com.pixvs.viaticos.service.saacg;

import com.pixvs.viaticos.model.viaticos.JsonResponse;
import com.pixvs.viaticos.service.GenericService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ProveedorService extends GenericService {

    private final String URL_LISTADO_PROVEEDORES = "/api/viaticos/listadoproveedores";
    private final String URL_REGISTER_PROVEEDOR = "/api/viaticos/registrarproveedor";

    @Autowired
    WebClienteService webClienteService;

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

    public HashMap<String, Object> getListadoProveedores(Integer ejercicio) throws  Exception {
        JsonResponse response = webClienteService.post(URL_LISTADO_PROVEEDORES, "",ejercicio, true);
        return new ObjectMapper().convertValue(response, HashMap.class);
    }

    public HashMap<String, Object> searchProveedor(String json) throws  Exception {
        JsonResponse response = webClienteService.postProveedor(URL_LISTADO_PROVEEDORES, json, false);
        return new ObjectMapper().convertValue(response, HashMap.class);
    }

    public HashMap<String, Object> registerProveedor(String json) throws  Exception {
        JsonResponse response = webClienteService.postProveedor(URL_REGISTER_PROVEEDOR, json, true);
        return new ObjectMapper().convertValue(response, HashMap.class);
    }
}
