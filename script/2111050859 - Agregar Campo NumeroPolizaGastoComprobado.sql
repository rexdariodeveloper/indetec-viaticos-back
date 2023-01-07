 IF NOT EXISTS(SELECT * FROM sys.columns WHERE Name = N'NumeroPolizaGastoComprobado' and Object_ID = Object_ID(N'tblSolicitudViaticoComprobacion'))
 BEGIN
	ALTER TABLE tblSolicitudViaticoComprobacion ADD NumeroPolizaGastoComprobado nvarchar(6)
	IF NOT EXISTS (SELECT * FROM sys.fn_listextendedproperty(N'MS_Description' , N'SCHEMA',N'dbo', N'TABLE',N'tblSolicitudViaticoComprobacion', N'COLUMN',N'NumeroPolizaGastoComprobado'))
		EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Numero de Folio de la Póliza' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tblSolicitudViaticoComprobacion', @level2type=N'COLUMN',@level2name=N'NumeroPolizaGastoComprobado'

 END