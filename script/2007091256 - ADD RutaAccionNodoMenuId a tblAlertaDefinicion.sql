UPDATE tblAlertaDefinicion SET RutaAccion = 'app/viaticos/revisiones/revision/{id}' WHERE AlertaDefinicionId = 13
GO

UPDATE tblAlertaDefinicion SET RutaAccion = 'app/viaticos/informes_comprobaciones/informe_comprobacion/{id}' WHERE AlertaDefinicionId = 12
GO

ALTER TABLE tblAlertaDefinicion ADD RutaAccionNodoMenuId INT
GO

UPDATE tblAlertaDefinicion SET RutaAccionNodoMenuId = 13 WHERE AlertaDefinicionId IN (1, 3, 4, 5, 6, 7)
GO

UPDATE tblAlertaDefinicion SET RutaAccionNodoMenuId = 15 WHERE AlertaDefinicionId IN (8, 12)
GO

UPDATE tblAlertaDefinicion SET RutaAccionNodoMenuId = 20 WHERE AlertaDefinicionId IN (13, 9, 10, 11)
GO

UPDATE tblAlertaDefinicion SET RutaAccionNodoMenuId = NodoMenuId WHERE Borrado = 1
GO

ALTER TABLE tblAlertaDefinicion ALTER COLUMN RutaAccionNodoMenuId INT NOT NULL
GO