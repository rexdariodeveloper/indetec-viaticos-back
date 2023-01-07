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
           (1000113
		   ,'ListadoTipo'
           ,'Listado Tipo Empleado'
           ,CAST(1 AS BIT)
           ,CAST(1 AS BIT)
           ,CAST(0 AS BIT)
           ,null
           ,GETDATE()
           ,null
           ,null
           ,null)

SET IDENTITY_INSERT tblListadoCMM OFF