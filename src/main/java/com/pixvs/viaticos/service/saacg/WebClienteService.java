package com.pixvs.viaticos.service.saacg;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixvs.viaticos.exceptions.APISAACGException;
import com.pixvs.viaticos.exceptions.GenericException;
import com.pixvs.viaticos.model.viaticos.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebClienteService {

    private static final String METHOD_POST = "post";
    private static final String METHOD_GET = "get";

    private JsonParser o =null;
    private JsonObject proveedor=null;

    @Autowired
    private Environment environment;

    private JsonResponse request(String path, String dataJson, Integer ejercicio, boolean auth, String method) throws GenericException {
        JsonResponse response = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            WebClient webClient = WebClient.builder()
                    .baseUrl(environment.getProperty("saacg.api"))
                    .build();

            if(ejercicio == null){
                headers.add("Ejercicio", environment.getProperty("saacg.ejercicio"));
            }

            //Si lleva autorizacion, agregar TOKEN al header
            if (auth) {
                headers.add("Authorization", "Bearer" + environment.getProperty("saacg.token"));
            }

            //Agregar al header el ejercicio sobre la cual se hara la consulta
            if (ejercicio != null) {
                headers.add("ejercicio", String.valueOf(ejercicio));
            }

            headers.add("Accept", "application/json");

            switch (method) {
                case METHOD_POST:
                    dataJson = dataJson == null || dataJson.isEmpty() ? new Gson().toJson("") : dataJson;

                    response = webClient.post()
                            .uri(path)
                            .headers(header -> header.setAll(headers.toSingleValueMap()))
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(dataJson)
                            .retrieve()
                            .bodyToMono(JsonResponse.class)
                            .block();

                break;

                case METHOD_GET:
                    response = webClient.get()
                            .uri(path)
                            .headers(head -> headers.setAll(headers.toSingleValueMap()))
                            .retrieve()
                            .bodyToMono(JsonResponse.class)
                            .block();
                break;
            }
        } catch (Exception ex) {
            throw new APISAACGException(ex.getMessage(), false);
        }

        return response;
    }

    private JsonResponse request(String path, String rfcOJson, boolean register, String method) throws GenericException {
        JsonResponse response = null;

        try {
            //Convertir el string como objeto By:AGG
            /*if(rfcOJson!=""){
                JsonParser o =new JsonParser();
                JsonObject proveedor=(JsonObject) o.parse(rfcOJson.replace('\n',' '));
                rfcOJson = proveedor.get("rfc").toString();
            }*/
            //End By:AGG
            HttpHeaders headers = new HttpHeaders();

            WebClient webClient = WebClient.builder()
                    .baseUrl(environment.getProperty("saacg.api"))
                    .build();

            headers.add("Accept", "application/json");
            headers.add("Authorization", "Bearer" + environment.getProperty("saacg.token"));

            // Search RFC
            if(!rfcOJson.isEmpty()){
               o =new JsonParser();
               proveedor=(JsonObject) o.parse(rfcOJson.replace('\n',' '));
               if(proveedor.has("ejercicio")){
                   headers.add("Ejercicio",proveedor.get("ejercicio").toString());
               }
            }

            if(!register){
                    if(proveedor.has("rfc")){
                        rfcOJson = proveedor.get("rfc").toString();
                        headers.add("RFC",proveedor.get("rfc").toString());
                    }else if(proveedor.has("RFC")){
                        headers.add("RFC",proveedor.get("RFC").toString());
                    }
            }



            switch (method) {
                case METHOD_POST:
                    response = webClient.post()
                            .uri(path)
                            .headers(header -> header.setAll(headers.toSingleValueMap()))
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(rfcOJson)
                            .retrieve()
                            .bodyToMono(JsonResponse.class)
                            .block();

                    break;

                case METHOD_GET:
                    response = webClient.get()
                            .uri(path)
                            .headers(head -> headers.setAll(headers.toSingleValueMap()))
                            .retrieve()
                            .bodyToMono(JsonResponse.class)
                            .block();
                    break;
            }
        } catch (Exception ex) {
            throw new APISAACGException(ex.getMessage(), false);
        }

        return response;
    }

    public JsonResponse get(String path, boolean auth) throws Exception {
        return get(path, null, auth);
    }

    public JsonResponse get(String path, Integer ejercicio, boolean auth) throws Exception {
        return handleResponse(this.request(path, null, ejercicio, auth, METHOD_GET));
    }

    public JsonResponse post(String path, String dataJSON, boolean auth) throws Exception {
        return post(path, dataJSON, null, auth);
    }

    public JsonResponse post(String path, String dataJSON, Integer ejercicio, boolean auth) throws Exception {
        return handleResponse(this.request(path, dataJSON, ejercicio, auth, METHOD_POST));
    }

    public JsonResponse postProveedor(String path, String rfcOJson, boolean register) throws Exception {
        return this.request(path, rfcOJson, register, METHOD_POST);
    }

    private JsonResponse handleResponse(JsonResponse response) throws GenericException {
       /* if (response.getStatus() != 200) {
            throw new APISAACGException(response.getMessage(), response.getData(), response.getStatus(), false);
        }
*/
        return response;
    }
}
