package com.pixvs.viaticos.model.viaticos.projection.Archivo;

import com.pixvs.viaticos.model.viaticos.Archivo;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;
import java.util.UUID;

@Projection(name = "infoArchivoProjection", types = {Archivo.class})
public interface InfoArchivoProjection {

    String getId();
    String getNombreOriginal();
    String getNombreFisico();
    Integer getReferenciaId();
    Integer getOrigenArchivoId();
    String getRutaFisica();
    Integer getTipoArchivoId();
    Boolean getVigente();
    Timestamp getFechaCreacion();
    Integer getCreadoPorId();
    Timestamp getFechaUltimaModificacion();
    Integer getModificadoPorId();

}
