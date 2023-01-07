DELETE FROM tblArchivoEstructuraCarpeta WHERE EstructuraId = (SELECT EstructuraId FROM tblArchivoEstructuraCarpeta WHERE NombreCarpeta = 'Solicitudes')
GO

SET IDENTITY_INSERT tblArchivoEstructuraCarpeta ON
GO

INSERT INTO tblArchivoEstructuraCarpeta(EstructuraId, NombreCarpeta, DescripcionCorta, EstructuraReferenciaId, NombreCalculado, Vigente, FechaCreacion, CreadoPorId)
VALUES(8,'Solicitudes','Solicitudes del Usuarios',2,0,1,GETDATE(),1)
GO

INSERT INTO tblArchivoEstructuraCarpeta(EstructuraId, DescripcionCorta, EstructuraReferenciaId, NombreCalculado, TipoCalculo, Vigente, FechaCreacion, CreadoPorId)
VALUES(9,'Carpeta del solicitudes',8,1,1000045,1,GETDATE(),1)
GO

INSERT INTO tblArchivoEstructuraCarpeta(EstructuraId, NombreCarpeta, DescripcionCorta, EstructuraReferenciaId, OrigenArchivoId, NombreCalculado, Vigente, FechaCreacion, CreadoPorId)
VALUES(10,'Comprobantes','Comprobantes del Solicitudes',9,4,0,1,GETDATE(),1)
GO

SET IDENTITY_INSERT tblArchivoEstructuraCarpeta OFF
GO