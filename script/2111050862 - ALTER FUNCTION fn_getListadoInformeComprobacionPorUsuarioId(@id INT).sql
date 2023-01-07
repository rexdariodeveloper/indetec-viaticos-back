/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  UserDefinedFunction [dbo].[fn_getListadoInformeComprobacionPorUsuarioId]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getListadoInformeComprobacionPorUsuarioId]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
DROP FUNCTION fn_getListadoInformeComprobacionPorUsuarioId
END
GO
execute dbo.sp_executesql @statement = N'
-- ==============================================================
-- Author:		Rene Carrillo
-- Create date: 11/06/2020
-- Modified: Javier Elías
-- Modified date: 08/07/2020
-- Description:	Función para obtener el listado de Informe y Comprobación
-- ==============================================================
CREATE FUNCTION [dbo].[fn_getListadoInformeComprobacionPorUsuarioId] (@usuarioId INT)
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
		   CASE WHEN solicitud.EstatusId IN(1000161, 1000107) THEN -1 * (ROW_NUMBER() OVER(ORDER BY FechaInicioEvento DESC)) ELSE (ROW_NUMBER() OVER(ORDER BY FechaInicioEvento ASC)) END AS orden
	FROM tblSolicitudViatico AS solicitud		 
		 INNER JOIN tblListadoCMM ON solicitud.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = solicitud.SolicitudViaticoId AND sva.EstatusId=1000000
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
	WHERE solicitud.EstatusId IN(1000161, 1000099, 1000107, 1000108)
		 AND (solicitud.CreadoPorId = @usuarioId
               OR usuario.UsuarioId IS NOT NULL
               OR arbol.Id IS NOT NULL
			   OR permisoAsignacion.UsuarioId IS NOT NULL) 
)'
GO