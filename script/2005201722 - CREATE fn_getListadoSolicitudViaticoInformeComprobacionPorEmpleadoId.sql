SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoSolicitudViaticoInformeComprobacionPorEmpleadoId] (@empleadoId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoEmpleado(emp.EmpleadoId) AS Empleado,
		   CONCAT(c.Nombre, ', ', e.Nombre, ', ', p.Nombre) AS ciudadDestino,
		   dbo.getFechaConFormato(sv.FechaInicioEvento, 1) AS fecha,
		   ISNULL(svav.MontoAsignadoTotal, 0) + ISNULL(svap.MontoAsignadoTotal, 0) AS montoAsignado,
		   ISNULL(comprobacion.MontoComprobadoTotal, 0) AS montoTransferido,
		   Valor AS estatus,
		   FechaInicioEvento
	FROM tblSolicitudViatico sv
		 INNER JOIN tblEmpleado emp ON emp.empleadoId = sv.empleadoId
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
		 INNER JOIN tblCiudad c ON c.CiudadId = sv.CiudadDestinoId
		 INNER JOIN tblEstado e ON e.EstadoId = sv.EstadoDestinoId
		 INNER JOIN tblPais p ON p.PaisId = sv.PaisDestinoId
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
		 ) comprobacion ON sv.SolicitudViaticoId = comprobacion.SolicitudViaticoId
	WHERE sv.EstatusId IN(1000161, 1000106)
		 AND sv.empleadoId = @empleadoId
)