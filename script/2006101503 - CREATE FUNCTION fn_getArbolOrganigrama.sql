SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==================================================
-- Author:		Javier Elías
-- Create date: 09/06/2020
-- Description:	Función para obtener el Árbol del Organigrama
-- ==================================================
CREATE FUNCTION [dbo].[fn_getArbolOrganigrama] ( @usuarioId INT )
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
		WHERE NodoPadreId IS NULL

		UNION ALL
	
		SELECT tbl.NodoId AS Id, tbl.NodoPadreId, tbl.Clave, tbl.Descripcion,
			CAST(cte.Ordenamiento + '.' + RIGHT('00000' + Ltrim(Rtrim(ROW_NUMBER() OVER(ORDER BY tbl.Orden))),5) AS NVARCHAR(255)) AS Ordenamiento
		FROM tblOrganigrama AS tbl
			INNER JOIN cte ON tbl.NodoPadreId = cte.Id
		WHERE tbl.EstatusId <> 1000002
	)

	SELECT 0 AS Id,
           NULL AS NodoPadreId,
           'Organigrama' AS Nombre,
           '00000' AS Ordenamiento,
           CAST(0 AS BIT) AS TienePermiso

	UNION ALL

	SELECT Id,
       ISNULL(NodoPadreId, 0) AS NodoPadreId,
	   Clave + ' - ' + Descripcion AS Nombre,
	   Ordenamiento,
	   CAST(CASE WHEN nodo.PermisoRegistroId IS NOT NULL AND acceso.PermisoAccesoId IS NOT NULL THEN 1 ELSE 0 END AS BIT) AS TienePermiso
	FROM cte AS arbol
		 LEFT JOIN tblPermisoRegistro AS nodo ON arbol.Id = nodo.RegistroId AND nodo.Borrado = 0
		 LEFT JOIN tblPermisoAcceso AS acceso ON nodo.PermisoAccesoId = acceso.PermisoAccesoId AND acceso.Borrado = 0 AND acceso.UsuarioId = @usuarioId AND acceso.TipoPermisoId = 1001165
)