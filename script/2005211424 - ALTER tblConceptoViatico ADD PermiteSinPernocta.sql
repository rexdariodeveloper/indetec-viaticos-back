UPDATE tblConceptoViatico SET PermiteSinPernocta = 0
GO

EXECUTE sp_rename 'tblConceptoViatico.PermiteSinPernocta', 'NoPermitirSinPernocta', 'COLUMN';