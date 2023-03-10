SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoAsignacionViaticos] ()
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoEmpleado(em.EmpleadoId) AS nombre,
		   dbo.getFechaConFormato(sv.FechaInicioEvento, 1) AS fecha,
		   CONCAT(cd.Nombre, ', ', edo.Nombre, ', ', p.Nombre) AS ciudadDestino,
		   ISNULL(svav.MontoAsignadoTotal, 0) + ISNULL(svap.MontoAsignadoTotal, 0) AS montoAsignado,
		   ISNULL(svav.MontoPorTransferirTotal, 0) + ISNULL(svap.MontoPorTransferirTotal, 0) AS montoTransferido,
		   Valor AS estatus,
		   em.EmpleadoId,
		   CASE WHEN FechaInicioEvento > GETDATE() THEN FechaInicioEvento ELSE DATEADD(YEAR, 100, FechaInicioEvento) END AS orden
	FROM tblSolicitudViatico sv
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 LEFT JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
		 INNER JOIN tblEmpleado em ON em.EmpleadoId = sv.EmpleadoId
		 INNER JOIN tblCiudad cd ON cd.CiudadId = sv.CiudadDestinoId
		 INNER JOIN tblEstado edo ON edo.EstadoId = sv.EstadoDestinoId
		 INNER JOIN tblPais p ON p.PaisId = sv.PaisDestinoId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(MontoConPernocta + MontoSinPernocta) AS MontoAsignadoTotal,
				   SUM(MontoPorTransferir) AS MontoPorTransferirTotal
			FROM tblSolicitudViaticoAsignacionViatico
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		 ) svav ON svav.AsignacionId = sva.AsignacionId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(Costo) AS MontoAsignadoTotal,
				   SUM(CASE WHEN AsignadoAFuncionario = 1 THEN Costo ELSE 0 END) AS MontoPorTransferirTotal
			FROM tblSolicitudViaticoAsignacionPasaje
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		 ) svap ON svap.AsignacionId = sva.AsignacionId
	WHERE sv.EstatusId IN(1000161, 1000103)
)