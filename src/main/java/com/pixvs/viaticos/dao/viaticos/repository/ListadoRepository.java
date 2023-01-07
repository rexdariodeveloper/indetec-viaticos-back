package com.pixvs.viaticos.dao.viaticos.repository;

import com.pixvs.viaticos.model.viaticos.ListadoEsquema;
import com.pixvs.viaticos.model.viaticos.ListadoEsquemaFormato;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.ByteBuffer;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class ListadoRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void insert(String tabla, String columns, String rows){
        entityManager.createNativeQuery("INSERT INTO " + tabla + "(" + columns + ") VALUES (" + rows + ")").executeUpdate();
    }

    public void update(String tabla,String columns, String rows, Integer id){
        entityManager.createNativeQuery("UPDATE " + tabla + " SET " + rows + " WHERE " + columns + " = " + id).executeUpdate();
    }

    public List<Map<String, Object>> select(ListadoEsquema listadoEsquema, List<ListadoEsquemaFormato> listadoEsquemaFormatoList, String nombreControl){
        List<Map<String, Object>> resultMap = new ArrayList<>();


        String columns = "";
        for(int i = 0; i < listadoEsquemaFormatoList.size(); i++){
            columns += listadoEsquemaFormatoList.get(i).getNombreCampoTabla();
            if (i != listadoEsquemaFormatoList.size() - 1) {
                columns += ", ";
            }
        }
        //Add column Activo
        columns += ", Activo";
        List<Object[]> resultList;
        if(nombreControl!= null && nombreControl!=""){
            resultList = entityManager.createNativeQuery("SELECT " + columns + ", CAST(CAST(Timestamp AS BIGINT) AS VARCHAR(MAX)) AS Timestamp FROM " + listadoEsquema.getNombreTabla() + " WHERE"+" Activo"+" = 1 "+ "AND"+" Nombre"+"="+"'"+nombreControl+"' ORDER BY " + listadoEsquema.getCampoOrden() + " ASC").getResultList();
        }else {
            resultList = entityManager.createNativeQuery("SELECT " + columns + ", CAST(CAST(Timestamp AS BIGINT) AS VARCHAR(MAX)) AS Timestamp FROM " + listadoEsquema.getNombreTabla() + " WHERE Activo = 1 ORDER BY " + listadoEsquema.getCampoOrden() + " ASC").getResultList();
        }
        if(resultList.size() > 0){
            for (int y= 0; y < resultList.size(); y++){
                Map<String, Object> stringObjectMap = new HashMap<>();
                for(int i = 0; i < listadoEsquemaFormatoList.size(); i++){

                    stringObjectMap.put(listadoEsquemaFormatoList.get(i).getNombreCampoTabla(),resultList.get(y)[i]);
                }
                stringObjectMap.put("Activo", resultList.get(y)[resultList.get(y).length-2]);
                stringObjectMap.put("Timestamp", resultList.get(y)[resultList.get(y).length-1]);
                resultMap.add(y,stringObjectMap);
            }
        }

        return resultMap;

    }

    public String getTimestamp(String tabla, String rows){
        return (String) entityManager.createNativeQuery("SELECT CAST(CAST(Timestamp AS BIGINT) AS VARCHAR(MAX)) AS Timestamp FROM " + tabla + " WHERE " + rows).getSingleResult();
    }
}
