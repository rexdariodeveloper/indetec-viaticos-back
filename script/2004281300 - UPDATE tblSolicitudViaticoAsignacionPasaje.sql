-- Aéreo
UPDATE tblSolicitudViaticoAsignacionPasaje SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Aereo','Aéreo')) WHERE TipoViajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Aéreo','Aereo'))

-- Marítimo
UPDATE tblSolicitudViaticoAsignacionPasaje SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Maritimo','Marítimo')) WHERE TipoViajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Marítimo','Maritimo'))

-- Terrestre
UPDATE tblSolicitudViaticoAsignacionPasaje SET ConceptoViaticoId = (SELECT ConceptoViaticoId FROM tblConceptoViatico WHERE Concepto IN ('Terrestre')) WHERE TipoViajeId = (SELECT ControlId FROM tblListadoCMM WHERE Nombre = 'TipoPasaje' AND Valor IN ('Terrestre'))