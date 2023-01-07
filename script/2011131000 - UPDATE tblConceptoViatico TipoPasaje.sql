USE [ViaticosDatos]
GO
UPDATE tblConceptoViatico SET Codigo = '37101', Concepto = 'Pasaje Aéreo Nacional', ObjetoGastoId = 37101, CategoriaId = 1000059, EstatusId = 1000000 WHERE ConceptoViaticoId = 1
GO
UPDATE tblConceptoViatico SET Codigo = '37301', Concepto = 'Pasaje Marítmo, Lacustre y Fluvial', ObjetoGastoId = 37301, CategoriaId = 1000059, EstatusId = 1000000 WHERE ConceptoViaticoId = 2
GO
UPDATE tblConceptoViatico SET Codigo = '37201', Concepto = 'Pasaje Terrestre Nacional', ObjetoGastoId = 37201, CategoriaId = 1000059, EstatusId = 1000000 WHERE ConceptoViaticoId = 3
GO
UPDATE tblConceptoViatico SET EstatusId = 1000002 WHERE ConceptoViaticoId IN (4, 5)