package com.pixvs.viaticos.dao.viaticos;

import com.pixvs.viaticos.model.viaticos.FnScalar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GeneralScalarDao extends CrudRepository<FnScalar, String> {

    @Procedure(name = "sp_AutonumericoArcSigIncr")
    String fetchSigAutonumericoArchivo(@Param("valor") String valor);

    @Transactional
    @Procedure(name = "iniciarAlerta")
    String iniciarAlerta(@Param("accionId") Integer accionId,
                         @Param("alertaDefinicionId") Integer alertaDefinicionId,
                         @Param("referenciaProcesoId") Integer referenciaProcesoId,
                         @Param("codigoTramite") String codigoTramite,
                         @Param("textoRepresentativo") String textoRepresentativo,
                         @Param("creadoPorId") Integer alertaIniciadaPorId
    );

    @Transactional
    @Procedure(name = "autorizarAlerta")
    String autorizarAlerta(@Param("accionId") Integer accionId,
                           @Param("alertaId") Long alertaId,
                           @Param("creadoPorId") Integer autorizadoPorId
    );

    @Transactional
    @Procedure(name = "revisionRechazarAlerta")
    String revisionRechazarAlerta(@Param("accionId") Integer accionId,
                                  @Param("alertaId") Long alertaId,
                                  @Param("motivo") String motivo,
                                  @Param("creadoPorId") Integer autorizadoPorId
    );

    @Transactional
    @Procedure(name = "ocultarAlertas")
    String ocultarAlertas(@Param("accionId") Integer accionId,
                          @Param("alertasId") String alertasId,
                          @Param("creadoPorId") Integer ocultoPorId
    );

    @Transactional
    @Procedure(name = "cancelarAlertas")
    String cancelarAlertas(@Param("accionId") Integer accionId,
                           @Param("alertaDefinicionId") Integer alertaDefinicionId,
                           @Param("referenciaProcesoId") Integer referenciaProcesoId
    );

    @Transactional
    @Procedure(name = "siguienteAutonumerico")
    String fetchSiguienteAutonumerico(@Param("nombre") String nombre,
                                      @Param("ejercicio") Integer ejercicio);

    @Query(value = "SELECT * FROM fn_getDatosEnviarCorreo(:alertaId)", nativeQuery = true)
    List<List> getDatosEnviarCorreo(@Param("alertaId") int alertaId);

    @Query(value = "SELECT NEWID()", nativeQuery = true)
    String newId();

    @Query(value = "SELECT dbo.getFechaConFormato(:date, :mostrarHoras)", nativeQuery = true)
    String getFechaConFormato(@Param("date") Timestamp date, @Param("mostrarHoras") boolean mostrarHoras);

    @Procedure(name = "permiteEliminarRegistro")
    Boolean permiteEliminarRegistro(@Param("RegistroId") Integer registroId, @Param("NombreTabla") String nombreTabla);

}
