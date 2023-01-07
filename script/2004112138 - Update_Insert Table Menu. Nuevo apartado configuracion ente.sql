UPDATE menu
SET menu.Orden = t.Orden
FROM tblMenuPrincipal menu
JOIN(
    SELECT Orden=(Orden+1), NodoMenuId from tblMenuPrincipal where NodoPadreId=1
) t ON t.NodoMenuId = menu.NodoMenuId
INSERT INTO [dbo].[tblMenuPrincipal]
           ([Etiqueta]
           ,[Descripcion]
           ,[TipoNodoId]
           ,[NodoPadreId]
           ,[SistemaAccesoId]
           ,[Url]
           ,[Icono]
           ,[AdmitePermiso]
           ,[Orden]
           ,[EstatusId])
     VALUES
           ('Configuración Ente'
           ,'Configuración del ente'
           ,1000005
           ,1
           ,1000007
           ,'catalogos/configuracion_ente'
           ,'fi flaticon-id-card-1'
           ,CAST(1 AS BIT)
           ,1
           ,1000000)