SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER FUNCTION [dbo].[fn_getResumenSolicitudViaticoPorId] (@id INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT SolicitudViaticoId AS id,
		   NumeroSolicitud,
		   solicitud.EmpleadoId,
		   dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS solicitante,
		   Puesto,
		   Cargo,
		   solicitud.AreaAdscripcionId,
		   AreaAdscripcion,
		   ProgramaGobierno,
		   Proyecto,
		   Ramo,
		   Dependencia,
		   NoOficio,
		   TipoViaje,
		   TipoRepresentacion,
		   DuracionComision,
		   dbo.getFechaConFormato(FechaSalida, 1) AS FechaSalida,
		   dbo.getNombreCompletoCiudad(SolicitudViaticoId, 0) AS origen,
		   dbo.getFechaConFormato(FechaRegreso, 1) AS FechaRegreso,
		   dbo.getNombreCompletoCiudad(SolicitudViaticoId, 1) AS destino,
		   GastoRepresentacion,
		   Sugerencias,
		   dbo.getFechaConFormato(FechaInicioEvento, 1) AS FechaInicioEvento,
		   dbo.getFechaConFormato(FechaFinEvento, 1) AS FechaFinEvento,
		   DuracionEvento,
		   Motivo,
		   DescripcionComision,
		   Organizador,
		   LinkEvento,
		   estatus.ControlId AS EstatusId,
		   estatus.Valor AS Estatus
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblListadoCMM AS estatus ON solicitud.EstatusId = estatus.ControlId
	WHERE SolicitudViaticoId = @id
)