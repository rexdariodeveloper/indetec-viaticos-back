SET IDENTITY_INSERT tblListadoCMOA ON​
INSERT INTO [dbo].[tblListadoCMOA]
           ([ControlOrigenArchivoId]
		   ,[Descripcion]
           ,[Vigente]
           ,[FechaCreacion]
           ,[CreadoPorId])
     VALUES
           (1
		   ,'Fotografias de los empleados'
           ,Cast(1 as bit)
           ,GETDATE()
           ,1)
SET IDENTITY_INSERT tblListadoCMOA OFF