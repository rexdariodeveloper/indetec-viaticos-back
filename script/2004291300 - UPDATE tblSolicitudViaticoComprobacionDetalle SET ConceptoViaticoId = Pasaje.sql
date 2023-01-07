-- Aéreo
UPDATE tblSolicitudViaticoComprobacionDetalle SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Aereo','Aéreo')) WHERE TipoPasajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Aéreo','Aereo'))

-- Marítimo
UPDATE tblSolicitudViaticoComprobacionDetalle SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Maritimo','Marítimo')) WHERE TipoPasajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Marítimo','Maritimo'))

-- Terrestre
UPDATE tblSolicitudViaticoComprobacionDetalle SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Terrestre')) WHERE TipoPasajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Terrestre'))