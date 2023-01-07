ALTER TABLE tblListadoEsquema
ADD CampoOrden NVARCHAR(200) NULL
GO

UPDATE tblListadoEsquema SET CampoOrden = 'Clave' WHERE EsquemaId = 1 
UPDATE tblListadoEsquema SET CampoOrden = 'Nombre' WHERE EsquemaId = 2 
UPDATE tblListadoEsquema SET CampoOrden = 'Valor' WHERE EsquemaId = 3 
UPDATE tblListadoEsquema SET CampoOrden = 'Valor' WHERE EsquemaId = 4 
UPDATE tblListadoEsquema SET CampoOrden = 'Valor' WHERE EsquemaId = 5
UPDATE tblListadoEsquema SET CampoOrden = 'Nombre' WHERE EsquemaId = 6  
GO

ALTER TABLE tblListadoEsquema
ALTER COLUMN CampoOrden NVARCHAR(200) NOT NULL
GO
