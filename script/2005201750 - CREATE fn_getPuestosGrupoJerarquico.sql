SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getPuestosGrupoJerarquico] (@id INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT PuestoId AS id,
		   Nombre
	FROM
	(
		SELECT PuestoId,
			   Nombre
		FROM tblListadoPuesto
			 INNER JOIN tblGrupoJerarquicoPuesto ON PuestoId = ListadoPuestoId
		WHERE Activo = 1
			  AND EstatusId = 1000000
			  AND GrupoJerarquicoId = @id

		UNION ALL
    
		SELECT PuestoId,
			   Nombre
		FROM tblListadoPuesto
		WHERE Activo = 1
			  AND PuestoId NOT IN ( SELECT PuestoId FROM tblListadoPuesto INNER JOIN tblGrupoJerarquicoPuesto ON PuestoId = ListadoPuestoId WHERE Activo = 1 AND EstatusId = 1000000 )
	) AS puestos
)