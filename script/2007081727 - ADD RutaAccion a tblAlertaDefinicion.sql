UPDATE tblAlertaDefinicion SET RutaAccion = 'app/viaticos/solicitudes/editar/{id}' WHERE AlertaDefinicionId = 6
GO

UPDATE tblAlertaDefinicion SET RutaAccion = 'app/viaticos/informes_comprobaciones/informe_comprobacion/{id}' WHERE AlertaDefinicionId IN (13, 8)
GO

UPDATE tblAlertaDefinicion SET RutaAccion = 'app/viaticos/revisiones/revision/{id}' WHERE AlertaDefinicionId IN (9, 11, 12)
GO