
ALTER TABLE tblSolicitudViaticoComprobacion
ADD FechaSolicitanteFinalizoComprobacion DATETIME,
    FechaRMFinalizoComprobacion DATETIME,
	ComisionNoRealizada BIT,
	MotivoNoRealizada NVARCHAR(500)
