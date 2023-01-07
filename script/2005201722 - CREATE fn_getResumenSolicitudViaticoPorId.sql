SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getResumenSolicitudViaticoPorId] (@id INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT SolicitudViaticoId AS id,
		   NumeroSolicitud,
		   empleado.EmpleadoId AS empleadoId,
		   dbo.getNombreCompletoEmpleado(empleado.EmpleadoId) AS solicitante,
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
		   ciudadOrigen.Nombre+', '+estadoOrigen.Nombre+', '+paisOrigen.Nombre AS origen,
		   dbo.getFechaConFormato(FechaRegreso, 1) AS FechaRegreso,
		   ciudadDestino.Nombre+', '+estadoDestino.Nombre+', '+paisDestino.Nombre AS destino,
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
		 INNER JOIN tblEmpleado AS empleado ON solicitud.EmpleadoId = empleado.EmpleadoId
		 INNER JOIN tblPais AS paisOrigen ON PaisOrigenId = paisOrigen.PaisId
		 INNER JOIN tblEstado AS estadoOrigen ON EstadoOrigenId = estadoOrigen.EstadoId
		 INNER JOIN tblCiudad AS ciudadOrigen ON CiudadOrigenId = ciudadOrigen.CiudadId
		 INNER JOIN tblPais AS paisDestino ON PaisDestinoId = paisDestino.PaisId
		 INNER JOIN tblEstado AS estadoDestino ON EstadoDestinoId = estadoDestino.EstadoId
		 INNER JOIN tblCiudad AS ciudadDestino ON CiudadDestinoId = ciudadDestino.CiudadId
	WHERE SolicitudViaticoId = @id
)