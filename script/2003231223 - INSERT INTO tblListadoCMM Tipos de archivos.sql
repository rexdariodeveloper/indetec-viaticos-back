SET IDENTITY_INSERT tblListadoCMM ON
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000151
		   ,'TipoArchivo'
           ,'Documento de texto'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
--------------
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000152
		   ,'TipoArchivo'
           ,'Hoja de cálculo'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
-------
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000153
		   ,'TipoArchivo'
           ,'PDF'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
--------
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000154
		   ,'TipoArchivo'
           ,'XML'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
--------
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000155
		   ,'TipoArchivo'
           ,'Imagen'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
-----------
INSERT INTO [dbo].[tblListadoCMM]
           ([ControlId]
		   ,[Nombre]
           ,[Valor]
           ,[Sistema]
           ,[Activo]
           ,[ControlSencillo]
           ,[ModuloId]
           ,[FechaCreacion]
           ,[CreadoPorId]
           ,[FechaUltimaModificacion]
           ,[ModificadoPorId])
     VALUES
           (1000156
		   ,'TipoArchivo'
           ,'Otro'
           ,CAST(1 as bit)
           ,CAST(1 as bit)
           ,CAST(0 as bit)
           ,null
           ,GETDATE()
           ,1
           ,null
           ,null)
SET IDENTITY_INSERT tblListadoCMM OFF