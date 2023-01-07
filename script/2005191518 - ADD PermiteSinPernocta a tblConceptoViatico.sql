ALTER TABLE tblConceptoViatico ADD PermiteSinPernocta BIT
GO

UPDATE tblConceptoViatico SET PermiteSinPernocta = 1
GO

ALTER TABLE tblConceptoViatico ALTER COLUMN PermiteSinPernocta BIT NOT NULL
GO