SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER FUNCTION [dbo].[fn_getListadoSolicitudesPorUsuarioId] (@usuarioId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT SolicitudViaticoId AS id,
			NumeroSolicitud,
			dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS solicitante,
			noOficio,
			TipoViaje,
			duracionComision,
			dbo.getFechaConFormato(FechaSalida, 1) AS fechaSalida,
			dbo.getNombreCompletoCiudad(SolicitudViaticoId, 0) AS origen,
			dbo.getFechaConFormato(FechaRegreso, 1) AS fechaRegreso,
			dbo.getNombreCompletoCiudad(SolicitudViaticoId, 1) AS destino,
			estatus.Valor AS Estatus,
			solicitud.FechaCreacion AS FechaCreacion
	FROM tblSolicitudViatico AS solicitud
			INNER JOIN tblListadoCMM AS estatus ON solicitud.EstatusId = estatus.ControlId
	WHERE solicitud.CreadoPorId = @usuarioId
			AND solicitud.EstatusId != 1000101
)