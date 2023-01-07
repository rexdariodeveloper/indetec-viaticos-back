SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoCambioAlertas] ()
RETURNS TABLE 
AS
RETURN 
(
	SELECT AlertaCambioUsuarioTmpId AS id,
		   Folio,
		   EmpleadoOrigenId AS origenId,
		   dbo.getNombreCompletoEmpleado(EmpleadoOrigenId) AS empleadoOrigen,
		   EmpleadoDestinoId AS destinoId,
		   dbo.getNombreCompletoEmpleado(EmpleadoDestinoId) AS empleadoDestino,
		   dbo.getFechaConFormato(FechaInicio, 0)+'- '+dbo.getFechaConFormato(FechaFin, 0) AS periodo,
		   CASE WHEN Borrado = 0 THEN 'Activo' ELSE 'Cancelado' END AS estatus
	FROM tblAlertaCambioUsuarioTmp
)