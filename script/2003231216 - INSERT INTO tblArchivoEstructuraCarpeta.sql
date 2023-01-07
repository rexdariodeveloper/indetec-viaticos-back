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
           ('Usuarios'
           ,'Carpeta raiz de usuarios'
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
           ,'Carpeta del empleado'
           ,(select EstructuraId from tblArchivoEstructuraCarpeta where NombreCarpeta='Usuarios')
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
           ('Fotografías'
           ,'Fotografias del usuario'
           ,(select EstructuraId from tblArchivoEstructuraCarpeta where DescripcionCorta='Carpeta del empleado')
           ,(select ControlOrigenArchivoId from tblListadoCMOA where Descripcion = 'Fotografías de los empleados')
           ,CAST(0 as bit)
           ,null
           ,CAST(1 as bit)
           ,GETDATE()
           ,1);