package com.pixvs.viaticos.model.viaticos.projection.ConceptoViatico;

import com.pixvs.viaticos.model.viaticos.ConceptoViatico;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listadoConceptoViaticoProjection", types = {ConceptoViatico.class})
public interface ListadoConceptoViaticoProjection {

    Integer getId();
    Integer getCategoriaId();
    String getCodigo();
    String getConcepto();
    Integer getObjetoGastoId();
    boolean isNoPermitirSinPernocta();
}
