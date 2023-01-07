UPDATE tblEmpleado SET SegundoApellido = NULL WHERE SegundoApellido = ''
GO

ALTER TABLE tblConfiguracionEnte ADD URLPublica NVARCHAR(200)
GO

UPDATE tblConfiguracionEnte SET URLPublica = ''
GO

ALTER TABLE tblConfiguracionEnte ALTER COLUMN URLPublica NVARCHAR(200) NOT NULL
GO