SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ===========================================================
-- Author:		Javier Elías
-- Create date: 11/06/2020
-- Modified date: 15/06/2020
-- Description:	Función para obtener el listado Informe y Comprobación
-- ===========================================================
ALTER FUNCTION [dbo].[fn_getListadoInformeComprobacionPorUsuarioId] (@usuarioId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT solicitud.SolicitudViaticoId AS id,
		   solicitud.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS Empleado,
		   dbo.getNombreCompletoCiudad(solicitud.SolicitudViaticoId, 1) AS ciudadDestino,
		   dbo.getFechaConFormato(solicitud.FechaInicioEvento, 1) AS fecha,
		   ISNULL(svav.MontoAsignadoTotal, 0) + ISNULL(svap.MontoAsignadoTotal, 0) AS montoAsignado,
		   ISNULL(comprobacion.MontoComprobadoTotal, 0) AS montoTransferido,
		   Valor AS estatus,
		   FechaInicioEvento
	FROM tblSolicitudViatico AS solicitud		 
		 INNER JOIN tblListadoCMM ON solicitud.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = solicitud.SolicitudViaticoId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(MontoConPernocta + MontoSinPernocta) AS MontoAsignadoTotal
			FROM tblSolicitudViaticoAsignacionViatico
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		 ) svav ON svav.AsignacionId = sva.AsignacionId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(Costo) AS MontoAsignadoTotal
			FROM tblSolicitudViaticoAsignacionPasaje
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		 ) svap ON svap.AsignacionId = sva.AsignacionId
		 LEFT JOIN
		 (
			SELECT comprobacion.SolicitudViaticoId AS SolicitudViaticoId,
				   SUM(ImportePesos) AS MontoComprobadoTotal
			FROM tblSolicitudViaticoComprobacion AS comprobacion
				 INNER JOIN tblSolicitudViaticoComprobacionDetalle AS detalle ON comprobacion.SolicitudViaticoComprobacionId = detalle.SolicitudViaticoComprobacionId
			WHERE comprobacion.EstatusId = 1000000
				  AND detalle.EstatusId = 1000000
			GROUP BY comprobacion.SolicitudViaticoId
		 ) comprobacion ON solicitud.SolicitudViaticoId = comprobacion.SolicitudViaticoId
		 LEFT JOIN tblUsuario AS usuario ON solicitud.EmpleadoId = usuario.EmpleadoId AND usuario.UsuarioId = @usuarioId
		 LEFT JOIN fn_getArbolOrganigramaPermisosUsuario(@usuarioId) AS arbol ON solicitud.AreaAdscripcionId = arbol.Id AND arbol.TienePermiso = 1
		 LEFT JOIN 
		 (
			SELECT UsuarioId
			FROM tblUsuario AS usuario
				 INNER JOIN tblRol AS rol ON usuario.RolId = rol.RolId AND rol.EstatusId = 1000000
				 INNER JOIN tblRolMenu AS rolMenu ON rol.RolId = rolMenu.RolId AND rolMenu.NodoMenuId = 14 AND rolMenu.EstatusId = 1000000
			WHERE UsuarioId = @usuarioId
		 ) AS permisoAsignacion ON permisoAsignacion.UsuarioId IS NOT NULL 
	WHERE solicitud.EstatusId IN(1000161, 1001167)
		 AND (solicitud.CreadoPorId = @usuarioId
               OR usuario.UsuarioId IS NOT NULL
               OR arbol.Id IS NOT NULL
			   OR permisoAsignacion.UsuarioId IS NOT NULL)
)