SET IDENTITY_INSERT tblListadoCMOA ON
INSERT INTO [dbo].[tblListadoCMOA]
           ([ControlOrigenArchivoId]
		   ,[Descripcion]
           ,[Vigente]
           ,[FechaCreacion]
           ,[CreadoPorId])
     VALUES
           (2
		   ,'Logos del ente'
           ,Cast(1 as bit)
           ,GETDATE()
           ,1)
SET IDENTITY_INSERT tblListadoCMOA OFF
INSERT INTO [dbo].[tblArchivoEstructuraCarpeta]
           ([NombreCarpeta]
           ,[DescripcionCorta]
           ,[EstructuraReferenciaId]
           ,[OrigenArchivoId]
           ,[NombreCalculado]
           ,[TipoCalculo]
           ,[Vigente]
           ,[FechaCreacion]
           ,[CreadoPorId])
     VALUES
           ('Ente'
           ,'Carpeta raiz del ente'
           ,null
           ,null
           ,CAST(0 as bit)
           ,null
           ,CAST(1 as bit)
           ,GETDATE()
           ,1);
INSERT INTO [dbo].[tblArchivoEstructuraCarpeta]
           ([NombreCarpeta]
           ,[DescripcionCorta]
           ,[EstructuraReferenciaId]
           ,[OrigenArchivoId]
           ,[NombreCalculado]
           ,[TipoCalculo]
           ,[Vigente]
           ,[FechaCreacion]
           ,[CreadoPorId])
     VALUES
           (null
           ,'Carpeta del ente'
           ,(select EstructuraId from tblArchivoEstructuraCarpeta where NombreCarpeta='Ente')
           ,null
           ,CAST(1 as bit)
           ,1000045
           ,CAST(1 as bit)
           ,GETDATE()
           ,1);
INSERT INTO [dbo].[tblArchivoEstructuraCarpeta]
           ([NombreCarpeta]
           ,[DescripcionCorta]
           ,[EstructuraReferenciaId]
           ,[OrigenArchivoId]
           ,[NombreCalculado]
           ,[TipoCalculo]
           ,[Vigente]
           ,[FechaCreacion]
           ,[CreadoPorId])
     VALUES
           ('Logos'
           ,'Logos del ente'
           ,(select EstructuraId from tblArchivoEstructuraCarpeta where DescripcionCorta='Carpeta del ente')
           ,(select ControlOrigenArchivoId from tblListadoCMOA where Descripcion = 'Logos del ente')
           ,CAST(0 as bit)
           ,null
           ,CAST(1 as bit)
           ,GETDATE()
           ,1);