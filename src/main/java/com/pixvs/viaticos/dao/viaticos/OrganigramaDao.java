package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.Organigrama;
import com.pixvs.viaticos.model.viaticos.projection.Organigrama.ArbolOrganigramaProjection;
import com.pixvs.viaticos.model.viaticos.projection.Organigrama.ComboListadoOrganigramaProjection;
import com.pixvs.viaticos.model.viaticos.projection.Organigrama.OrganigramaListadoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.BORRADO;
import static com.pixvs.viaticos.model.viaticos.mapeos.ListadoCMM.EstatusRegistro.INACTIVO;

@Repository
public interface OrganigramaDao extends CrudRepository<Organigrama, Integer> {
    List<Organigrama> findAllByEstatusIdNotInOrderByOrdenAsc(int estatusId);

    // Get listado de organigrama (Combo)
    @Query(value =
            "SELECT NodoId as id, CONCAT(Clave, ' - ', Descripcion) AS Descripcion \n" +
                    "FROM tblOrganigrama \n" +
                    "WHERE NOT EstatusId IN ("+INACTIVO+","+BORRADO+") \n" +
                    "ORDER BY Clave ASC", nativeQuery = true)
    List<ComboListadoOrganigramaProjection> getComboListadoOrganigramaProjection();

    //Search id exist
    Organigrama findByIdAndEstatusIdIsNotIn(Integer id,int estatusId);

    OrganigramaListadoProjection findOrganigramaListadoProjectionById(Integer id);

    @Query(value = "SELECT dbo.getAutorizacionUsuarioInmediatoId(:id)", nativeQuery = true)
    Integer getAutorizacionUsuarioInmediatoId(@Param("id") int id);

    @Query(value = "SELECT * FROM fn_getArbolOrganigramaPermisosUsuario(:usuarioId) ORDER BY Ordenamiento", nativeQuery = true)
    List<ArbolOrganigramaProjection> getArbolOrganigramaPermisosUsuario(@Param("usuarioId") Integer usuarioId);
}
