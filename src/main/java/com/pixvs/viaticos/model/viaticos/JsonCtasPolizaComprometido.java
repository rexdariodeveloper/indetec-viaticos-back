package com.pixvs.viaticos.model.viaticos;

import java.math.BigDecimal;

public class JsonCtasPolizaComprometido {

    private String ObjetoGastoId;
    private Integer ConceptoViaticoId;
    private String ConceptoViaticoNombre;
    private BigDecimal ComprometerMonto;
    private Integer QuienComprueba;

    public JsonCtasPolizaComprometido(String objetoGastoId, Integer conceptoViaticoId, String conceptoViaticoNombre, BigDecimal comprometerMonto, Integer quienComprueba) {
        setObjetoGastoId(objetoGastoId);
        setConceptoViaticoId(conceptoViaticoId);
        setConceptoViaticoNombre(conceptoViaticoNombre);
        setComprometerMonto(comprometerMonto);
        setQuienComprueba(quienComprueba);
    }

    public String getObjetoGastoId() {
        return ObjetoGastoId;
    }

    public void setObjetoGastoId(String objetoGastoId) {
        ObjetoGastoId = objetoGastoId;
    }

    public Integer getConceptoViaticoId() {
        return ConceptoViaticoId;
    }

    public void setConceptoViaticoId(Integer conceptoViaticoId) {
        ConceptoViaticoId = conceptoViaticoId;
    }

    public String getConceptoViaticoNombre() {
        return ConceptoViaticoNombre;
    }

    public void setConceptoViaticoNombre(String conceptoViaticoNombre) {
        ConceptoViaticoNombre = conceptoViaticoNombre;
    }

    public BigDecimal getComprometerMonto() {
        return ComprometerMonto;
    }

    public void setComprometerMonto(BigDecimal comprometerMonto) {
        ComprometerMonto = comprometerMonto;
    }

    public Integer getQuienComprueba() {
        return QuienComprueba;
    }

    public void setQuienComprueba(Integer quienComprueba) {
        QuienComprueba = quienComprueba;
    }
}