ALTER TABLE tblConfiguracionEnte ADD ProtocoloFTP INT
GO

ALTER TABLE tblConfiguracionEnte ADD DirectorioPublico NVARCHAR(200)
GO

UPDATE tblConfiguracionEnte SET DirectorioPublico = ''
GO

ALTER TABLE tblConfiguracionEnte ALTER COLUMN DirectorioPublico NVARCHAR(200) NOT NULL
GO

ALTER TABLE tblConfiguracionEnte ADD DirectorioRemoto BIT
GO

UPDATE tblConfiguracionEnte SET DirectorioRemoto = 0
GO

ALTER TABLE tblConfiguracionEnte ALTER COLUMN DirectorioRemoto BIT NOT NULL
GO