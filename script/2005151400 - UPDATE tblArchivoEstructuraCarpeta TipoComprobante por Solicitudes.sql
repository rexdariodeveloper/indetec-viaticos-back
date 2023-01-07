UPDATE tblArchivoEstructuraCarpeta SET NombreCarpeta = 'Solicitudes', OrigenArchivoId = 4, EstructuraReferenciaId = 2, DescripcionCorta = 'Solicitudes del Usuarios' WHERE EstructuraId = (SELECT EstructuraId FROM tblArchivoEstructuraCarpeta WHERE NombreCarpeta = 'TipoComprobante')
GO

UPDATE tblListadoCMOA SET Descripcion = 'Archivo de Solicitudes' WHERE ControlOrigenArchivoId = 4
GO