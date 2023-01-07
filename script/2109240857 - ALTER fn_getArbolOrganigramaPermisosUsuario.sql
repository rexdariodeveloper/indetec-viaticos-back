/****** Object:  UserDefinedFunction [dbo].[fn_getArbolOrganigramaPermisosUsuario]    Script Date: 12/10/2021 10:42:50 a. m. ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER ON
GO
-- ====================================================
-- Author:		Javier Elías
-- Create date: 11/06/2020
-- Modified date: 11/11/2020
-- Description:	Función para obtener los Permisos de un Usuario
--						en el árbol del Organigrama
-- ====================================================
ALTER FUNCTION [dbo].[fn_getArbolOrganigramaPermisosUsuario] ( @usuarioId INT )
RETURNS TABLE
AS
RETURN
(
	WITH cte
	AS
	(
		SELECT tbl.NodoId AS Id, tbl.NodoPadreId, tbl.Clave, tbl.Descripcion,
			CAST(RIGHT('00000' + Ltrim(Rtrim(ROW_NUMBER() OVER(ORDER BY tbl.Orden))),5) AS NVARCHAR(255)) AS Ordenamiento
		FROM tblOrganigrama AS tbl
		WHERE NodoPadreId IS NULL AND tbl.EstatusId <> 1000002

		UNION ALL

		SELECT tbl.NodoId AS Id, tbl.NodoPadreId, tbl.Clave, tbl.Descripcion,
			CAST(cte.Ordenamiento + '.' + RIGHT('00000' + Ltrim(Rtrim(ROW_NUMBER() OVER(ORDER BY tbl.Orden))),5) AS NVARCHAR(255)) AS Ordenamiento
		FROM tblOrganigrama AS tbl
			INNER JOIN cte ON tbl.NodoPadreId = cte.Id
		WHERE tbl.EstatusId <> 1000002
	)

	SELECT Id,
       NodoPadreId,
	   Clave + ' - ' + Descripcion AS Nombre,
	   Ordenamiento,
	   CAST(CASE WHEN permios.RegistroId IS NOT NULL THEN 1 ELSE 0 END AS BIT) AS TienePermiso
	FROM cte AS arbol
		 LEFT JOIN
		 (
			 SELECT nodo.RegistroId
			 FROM tblPermisoRegistro AS nodo
				  INNER JOIN tblPermisoAcceso AS acceso ON nodo.PermisoAccesoId = acceso.PermisoAccesoId
														  AND acceso.Borrado = 0
														  AND acceso.UsuarioId = @usuarioId
														  AND acceso.TipoPermisoId = 1000013
				   AND nodo.Borrado = 0
		 ) AS permios ON arbol.Id = permios.RegistroId
)