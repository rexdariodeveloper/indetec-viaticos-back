SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoSolicitudesPorUsuarioId] (@usuarioId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT SolicitudViaticoId AS id,
			NumeroSolicitud,
			dbo.getNombreCompletoEmpleado(empleado.EmpleadoId) AS solicitante,
			noOficio,
			TipoViaje,
			duracionComision,
			dbo.getFechaConFormato(FechaSalida, 1) AS fechaSalida,
			ciudadOrigen.Nombre+', '+estadoOrigen.Nombre+', '+paisOrigen.Nombre AS origen,
			dbo.getFechaConFormato(FechaRegreso, 1) AS fechaRegreso,
			+ciudadDestino.Nombre+', '+estadoDestino.Nombre+', '+paisDestino.Nombre AS destino,
			estatus.Valor AS Estatus,
			solicitud.FechaCreacion AS FechaCreacion
	FROM tblSolicitudViatico AS solicitud
			INNER JOIN tblListadoCMM AS estatus ON solicitud.EstatusId = estatus.ControlId
			INNER JOIN tblEmpleado AS empleado ON solicitud.EmpleadoId = empleado.EmpleadoId
			INNER JOIN tblPais AS paisOrigen ON PaisOrigenId = paisOrigen.PaisId
			INNER JOIN tblEstado AS estadoOrigen ON EstadoOrigenId = estadoOrigen.EstadoId
			INNER JOIN tblCiudad AS ciudadOrigen ON CiudadOrigenId = ciudadOrigen.CiudadId
			INNER JOIN tblPais AS paisDestino ON PaisDestinoId = paisDestino.PaisId
			INNER JOIN tblEstado AS estadoDestino ON EstadoDestinoId = estadoDestino.EstadoId
			INNER JOIN tblCiudad AS ciudadDestino ON CiudadDestinoId = ciudadDestino.CiudadId
	WHERE solicitud.CreadoPorId = @usuarioId
			AND solicitud.EstatusId != 1000101
)