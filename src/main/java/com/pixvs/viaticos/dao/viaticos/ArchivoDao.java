package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Archivo;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoDao extends CrudRepository<Archivo, Integer> {

    InfoArchivoProjection findProjectionArchivoById(String id);

    Archivo findArchivoById(String id);

    InfoArchivoProjection findInfoProjectionById(String id);

    Archivo save(Archivo archivo);

    List<InfoArchivoProjection> findAllInfoProjectionByReferenciaIdAndOrigenArchivoIdAndTipoArchivoIdAndVigenteIsTrue(Integer referenciaId, Integer origenArchivoId, Integer tipoArchivoId);

    List<Archivo> findAllByReferenciaIdAndOrigenArchivoIdAndVigenteIsTrue(int referenciaId, int origenArchivoId);

    @Query(value = "SELECT dbo.fn_getRutaArchivo(:origenId,:ids)", nativeQuery = true)
    String getRutaArchivo(@Param("origenId") Integer origenId, @Param("ids") String ids);

    @Query(value = "select * from tblArchivo where ReferenciaId=3 and OrigenArchivoId=2 and Vigente=1", nativeQuery = true)
    Archivo getRutaLogoActivo();
}
