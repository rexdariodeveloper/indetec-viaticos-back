ALTER TABLE tblArchivo ADD Exportado BIT
GO

ALTER TABLE tblArchivo ADD FechaExportado DATETIME
GO

UPDATE tblArchivo SET Exportado = 0
GO

ALTER TABLE tblArchivo ALTER COLUMN Exportado BIT NOT NULL
GO

ALTER TABLE tblArchivo ADD CONSTRAINT [DF_tblArchivo_Exportado] DEFAULT (0) FOR Exportado
GO