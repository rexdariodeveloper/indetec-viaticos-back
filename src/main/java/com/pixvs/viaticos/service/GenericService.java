package com.pixvs.viaticos.service;

import com.pixvs.viaticos.dao.viaticos.GeneralScalarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public abstract class GenericService {

    /** Inserta un registro en una tabla especifica de la base de datos.
     * La tabla se especifica mediante la sobrescritura del metodo.
     * @param objeto - Objeto modelo con los datos del registro.
     * @return - Un objeto modelo con los datos adicionales del registro que se
     * generan en la base de datos despues de ha berlo insertado.
     **/
    public abstract Object guardar(Object objeto) throws Exception;

    /** Inserta una colección de objetos en una tabla especifica de la base de datos.
     * La tabla se especifica mediante la sobrescritura del metodo.
     * @param objetos - Colección de objetos modelo con los datos del registro.
     **/
    public abstract void guardar(Collection objetos) throws Exception;

    /** Elimina un registro mediante su id en una tabla especifica de la base de
     * datos. La tabla se especifica mediante la sobrescritura del metodo.
     * @param id - Id modelo con los datos del registro.
     **/
    public abstract void eliminarPorId(Integer id) throws Exception;

    /** Busca un registro mediante su id en una tabla especifica de la base de
     * datos. La tabla se especifica mediante la sobrescritura del metodo.
     * @param id - Id del registro buscado.
     * @return - El objeto modelo con los datos del registro buscado.
     **/
    public abstract Object buscarPorId(Integer id) throws Exception;

    /** Busca un registro mediante su id en una tabla especifica de la base de
     * datos. La tabla se especifica mediante la sobrescritura del metodo.
     * @param codigo - Código del registro buscado.
     * @return - El objeto modelo con los datos del registro buscado.
     **/
    public abstract Object buscarPorCodigo(String codigo) throws Exception;

    /** Busca todos los registros en una tabla especifica de la base de datos.
     * La tabla se especifica mediante la sobrescritura del metodo.
     * @return - Una coleccion de objetos modelo con los registros de la tabla.
     **/
    public abstract Collection buscarTodos() throws Exception;

    /** Permite comprobar si existe un registro mediante su id, en una tabla
     * determinada de la base de datos. La tabla se especifica mediante la
     * sobrescritura del metodo.
     * @param id - Id del registro buscado.
     * @return - <code>true</code> Si el registro con el id buscado existe en la
     * base de datos, <code>false</code> si el registro no existe.
     **/
    public abstract boolean existePorId(Integer id) throws Exception;

    /** Permite comprobar si existe un registro mediante su código, en una tabla
     * determinada de la base de datos. La tabla se especifica mediante la
     * sobrescritura del metodo.
     * @param codigo - Código del registro buscado.
     * @return - <code>true</code> Si el registro con el código buscado existe en la
     * base de datos, <code>false</code> si el registro no existe.
     **/
    public abstract boolean existePorCodigo(String codigo) throws Exception;

}