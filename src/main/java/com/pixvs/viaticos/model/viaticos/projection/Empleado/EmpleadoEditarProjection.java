package com.pixvs.viaticos.model.viaticos.projection.Empleado;

import com.pixvs.viaticos.model.viaticos.Empleado;
import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "empleadoEditarProjection", types = {Empleado.class})
public interface EmpleadoEditarProjection {

    Integer getId();

    String getNombre();

    String getPrimerApellido();

    String getSegundoApellido();

    String getFotografia();

    InfoArchivoProjection getArchivoFotografia();

    Integer getEstatusId();

    void setArchivoFotografia(InfoArchivoProjection archivoFotografia);
}
