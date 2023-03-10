SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER FUNCTION [dbo].[fn_getRptTransparencia] ( @fechaInicio DATETIME, @fechaFin DATETIME )
RETURNS TABLE 
AS
RETURN 
(
	SELECT solicitud.SolicitudViaticoId AS Id,
		   NumeroSolicitud,
		   YEAR(comprobacion.FechaUltimaModificacion) AS Ejercicio,
		   dbo.getFechaConFormato(@fechaInicio, 0) AS FechaInicio,
		   dbo.getFechaConFormato(@fechaFin, 0) AS FechaFin,
		   dbo.getNombreCompletoEmpleado(empleado.EmpleadoId) AS Solicitante,
		   tipoEmpleado.Valor AS TipoIntegrante,
		   SUBSTRING(Puesto, 0, CHARINDEX(' - ', Puesto)) AS ClavePuesto,
		   SUBSTRING(Puesto, CHARINDEX(' - ', Puesto) + 3, LEN(Puesto)) AS Puesto,
		   Cargo,
		   AreaAdscripcion,
		   empleado.Nombre,
		   empleado.PrimerApellido AS PrimerApellido,
		   ISNULL(empleado.SegundoApellido, '') AS SegundoApellido,
		   ISNULL(GastoRepresentacion, '') AS TipoGasto,
		   DescripcionComision,
		   tipoViaje.Valor AS TipoViaje,
		   0 AS Acompanantes,
		   0.0 AS MontoAcompanantes,
		   ciudadOrigen.Nombre AS CiudadOrigen,
		   estadoOrigen.Nombre AS EstadoOrigen,
		   paisOrigen.Nombre AS PaisOrigen,
		   ciudadDestino.Nombre AS CiudadDestino,
		   estadoDestino.Nombre AS EstadoDestino,
		   paisDestino.Nombre AS PaisDestino,
		   Motivo,
		   dbo.getFechaConFormato(solicitud.FechaSalida, 0) AS FechaSalida,
		   dbo.getFechaConFormato(solicitud.FechaRegreso, 0) AS FechaRegreso,
		   ISNULL(monto.Monto, 0) AS TotalErogado,
		   0.0 AS NoErogado,
		   dbo.getFechaConFormato(solicitud.FechaSalida, 0) + '/ ' + dbo.getFechaConFormato(solicitud.FechaRegreso, 0) AS FechaSalidaRegreso,
		   dbo.getFechaConFormato(comprobacion.FechaUltimaModificacion, 1) AS FechaFinalizacion,
		   '' AS LinkInforme,
		   '' AS LinkNormativa,
		   AreaResponsableTransparencia,
		   dbo.getFechaConFormato(GETDATE(), 0) AS FechaValidacion,
		   dbo.getFechaConFormato(GETDATE(), 0) AS FechaActualizacion,
		   '' AS Notas
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblEmpleado AS empleado ON solicitud.EmpleadoId = empleado.EmpleadoId
		 INNER JOIN tblListadoCMM AS tipoEmpleado ON TipoEmpleadoId = tipoEmpleado.ControlId
		 INNER JOIN tblListadoCMM AS tipoViaje ON solicitud.TipoViajeId = tipoViaje.ControlId
		 INNER JOIN tblPais AS paisOrigen ON PaisOrigenId = paisOrigen.PaisId
		 INNER JOIN tblEstado AS estadoOrigen ON EstadoOrigenId = estadoOrigen.EstadoId
		 INNER JOIN tblCiudad AS ciudadOrigen ON CiudadOrigenId = ciudadOrigen.CiudadId
		 INNER JOIN tblPais AS paisDestino ON PaisDestinoId = paisDestino.PaisId
		 INNER JOIN tblEstado AS estadoDestino ON EstadoDestinoId = estadoDestino.EstadoId
		 INNER JOIN tblCiudad AS ciudadDestino ON CiudadDestinoId = ciudadDestino.CiudadId
		 INNER JOIN tblConfiguracionEnte ON ConfiguracionEnteId != -1
		 INNER JOIN tblSolicitudViaticoComprobacion AS comprobacion ON solicitud.SolicitudViaticoId = comprobacion.SolicitudViaticoId AND comprobacion.EstatusId = 1000000
		 LEFT JOIN
		 (
			SELECT SolicitudViaticoComprobacionId,
				   SUM(ImportePesos) AS Monto
			FROM tblSolicitudViaticoComprobacionDetalle
			WHERE EstatusId = 1000000
			GROUP BY SolicitudViaticoComprobacionId
		 ) monto ON comprobacion.SolicitudViaticoComprobacionId = monto.SolicitudViaticoComprobacionId
	WHERE comprobacion.FechaUltimaModificacion BETWEEN @fechaInicio AND DATEADD(MINUTE, 1439, @fechaFin)
)