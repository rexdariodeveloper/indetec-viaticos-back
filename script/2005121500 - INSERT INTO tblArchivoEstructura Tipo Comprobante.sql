SET IDENTITY_INSERT tblArchivoEstructuraCarpeta ON
INSERT INTO tblArchivoEstructuraCarpeta(EstructuraId,NombreCarpeta, DescripcionCorta, EstructuraReferenciaId, OrigenArchivoId, NombreCalculado, Vigente, FechaCreacion, CreadoPorId)
VALUES(8,'TipoComprobante','Carpeta raiz de Tipo Comprobante',5,4,0,1,GETDATE(), 1)
SET IDENTITY_INSERT tblArchivoEstructuraCarpeta OFF