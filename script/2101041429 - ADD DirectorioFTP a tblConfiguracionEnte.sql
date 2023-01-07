ALTER TABLE tblConfiguracionEnte ALTER COLUMN DirectorioPublico NVARCHAR(200) NULL
GO

ALTER TABLE tblConfiguracionEnte ADD DirectorioFTP NVARCHAR(200) NULL
GO

UPDATE tblConfiguracionEnte SET DirectorioFTP = '/'
GO