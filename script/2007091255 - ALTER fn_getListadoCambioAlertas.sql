SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ========================================================
-- Author:		Javier Elías
-- Create date: 
-- Modified date: 09/07/2020
-- Description:	Función para obtener el Listado de Cambio de Alertas
-- ========================================================
ALTER FUNCTION [dbo].[fn_getListadoCambioAlertas] ()
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
		   CASE WHEN Borrado = 1 THEN 'Cancelado' ELSE CASE WHEN  GETDATE() < FechaInicio THEN 'Próximo' ELSE CASE WHEN GETDATE() > FechaFin THEN 'Vencido' ELSE 'Vigente' END END END AS estatus
	FROM tblAlertaCambioUsuarioTmp
)