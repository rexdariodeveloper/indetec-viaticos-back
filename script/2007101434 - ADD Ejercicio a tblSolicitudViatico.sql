ALTER TABLE tblSolicitudViatico ADD Ejercicio INT 
GO

UPDATE tblSolicitudViatico SET Ejercicio = YEAR(FechaCreacion)
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN Ejercicio INT NOT NULL
GO