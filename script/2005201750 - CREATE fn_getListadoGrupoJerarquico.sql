SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoGrupoJerarquico] ()
RETURNS TABLE 
AS
RETURN 
(
	SELECT tblGrupoJerarquico.GrupoJerarquicoId AS id,
		   tblGrupoJerarquico.Nombre,
		   Descripcion,
		   tblGrupoJerarquico.EstatusId,
		   tblListadoCMM.Valor AS estatus,
		   SUM(CASE WHEN tblGrupoJerarquicoPuesto.EstatusId = 1000000 THEN 1 ELSE 0 END) AS noPuestos
	FROM tblGrupoJerarquico
		 LEFT JOIN tblGrupoJerarquicoPuesto ON tblGrupoJerarquico.GrupoJerarquicoId = tblGrupoJerarquicoPuesto.GrupoJerarquicoId
		 INNER JOIN tblListadoCMM ON tblGrupoJerarquico.EstatusId = tblListadoCMM.ControlId
	WHERE tblGrupoJerarquico.EstatusId != 1000002
	GROUP BY tblGrupoJerarquico.GrupoJerarquicoId,
			 tblGrupoJerarquico.Nombre,
			 Descripcion,
			 tblGrupoJerarquico.EstatusId,
			 tblListadoCMM.Valor
)