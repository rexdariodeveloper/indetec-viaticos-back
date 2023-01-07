SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==================================================
-- Author:		Javier Elías
-- Create date: 21/05/2020
-- Modified date: 11/06/2020
-- Description:	Función para obtener el Listado de Solicitudes
-- ==================================================
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
			LEFT JOIN tblUsuario AS usuario ON solicitud.EmpleadoId = usuario.EmpleadoId AND usuario.UsuarioId = @usuarioId
			LEFT JOIN fn_getArbolOrganigramaPermisosUsuario(@usuarioId) AS arbol ON solicitud.AreaAdscripcionId = arbol.Id AND arbol.TienePermiso = 1
	WHERE solicitud.EstatusId != 1000101
			AND (solicitud.CreadoPorId = @usuarioId
               OR usuario.UsuarioId IS NOT NULL
               OR arbol.Id IS NOT NULL)
)