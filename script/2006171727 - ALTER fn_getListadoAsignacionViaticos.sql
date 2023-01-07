SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ====================================================
-- Author:		Javier Elías
-- Create date: 20/05/2020
-- Modified date: 17/06/2020
-- Description:	Función para obtener el listado de Asignaciones
-- ====================================================
ALTER FUNCTION [dbo].[fn_getListadoAsignacionViaticos]()
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoEmpleado(sv.EmpleadoId) AS nombre,
		   dbo.getFechaConFormato(sv.FechaInicioEvento, 1) AS fecha,
		   dbo.getNombreCompletoCiudad(sv.SolicitudViaticoId, 1) AS ciudadDestino,
		   ISNULL(svav.MontoAsignadoTotal, 0) + ISNULL(svap.MontoAsignadoTotal, 0) AS montoAsignado,
		   ISNULL(svav.MontoPorTransferirTotal, 0) + ISNULL(svap.MontoPorTransferirTotal, 0) AS montoTransferido,
		   Valor AS estatus,
		   sv.EmpleadoId,
		   CASE WHEN FechaInicioEvento > GETDATE() THEN -1 ELSE 1 END * (ROW_NUMBER() OVER (ORDER BY sv.FechaInicioEvento DESC)) AS orden
	FROM tblSolicitudViatico AS sv
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 LEFT JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
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
	WHERE sv.EstatusId IN(1000103, 1000161, 1000098)
)