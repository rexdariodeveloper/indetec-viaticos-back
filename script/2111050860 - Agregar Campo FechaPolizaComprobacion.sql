 IF NOT EXISTS(SELECT * FROM sys.columns WHERE Name = N'FechaPolizaComprobacion' and Object_ID = Object_ID(N'tblSolicitudViaticoComprobacion'))
 BEGIN
	ALTER TABLE tblSolicitudViaticoComprobacion ADD FechaPolizaComprobacion DATETIME
	IF NOT EXISTS (SELECT * FROM sys.fn_listextendedproperty(N'MS_Description' , N'SCHEMA',N'dbo', N'TABLE',N'tblSolicitudViaticoComprobacion', N'COLUMN',N'FechaPolizaComprobacion'))
		EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Fecha en la que se genero la Póliza Gasto Comprobado' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tblSolicitudViaticoComprobacion', @level2type=N'COLUMN',@level2name=N'FechaPolizaComprobacion'

 END