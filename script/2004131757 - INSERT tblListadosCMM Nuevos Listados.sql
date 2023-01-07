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
           (1000109
		   ,'ListadoTipo'
           ,'Listado Monedas'
           ,CAST(1 AS BIT)
           ,CAST(1 AS BIT)
           ,CAST(0 AS BIT)
           ,null
           ,GETDATE()
           ,null
           ,null
           ,null)
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
           (1000110
		   ,'ListadoTipo'
           ,'Listado Tipo Viaje'
           ,CAST(1 AS BIT)
           ,CAST(1 AS BIT)
           ,CAST(0 AS BIT)
           ,null
           ,GETDATE()
           ,null
           ,null
           ,null)
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
           (1000111
		   ,'ListadoTipo'
           ,'Listado Zonas Economicas'
           ,CAST(1 AS BIT)
           ,CAST(1 AS BIT)
           ,CAST(0 AS BIT)
           ,null
           ,GETDATE()
           ,null
           ,null
           ,null)
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
           (1000112
		   ,'ListadoTipo'
           ,'Listado Tipo Representacion'
           ,CAST(1 AS BIT)
           ,CAST(1 AS BIT)
           ,CAST(0 AS BIT)
           ,null
           ,GETDATE()
           ,null
           ,null
           ,null)
SET IDENTITY_INSERT tblListadoCMM OFF