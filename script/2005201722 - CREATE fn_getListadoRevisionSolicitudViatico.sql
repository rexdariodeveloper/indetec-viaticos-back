SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getListadoRevisionSolicitudViatico] ()
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   CONCAT(c.Nombre, ', ', e.Nombre, ', ', p.Nombre) AS ciudadDestino,
		   sv.FechaCreacion AS fechaSolicitud,
		   sv.fechaSalida AS fechaViaje,
		   (ISNULL(svap.CostoTotal, 0) + ISNULL(svav.MontoPorTransferirTotal, 0)) AS montoAsignado,
		   ISNULL(svav.MontoPorTransferirTotal, 0) AS montoTransferido,
		   Valor AS estatus
	FROM tblSolicitudViatico sv
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
		 INNER JOIN tblCiudad c ON c.CiudadId = sv.CiudadDestinoId
		 INNER JOIN tblEstado e ON e.EstadoId = sv.EstadoDestinoId
		 INNER JOIN tblPais p ON p.PaisId = sv.PaisDestinoId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(MontoPorTransferir) AS MontoPorTransferirTotal,
				   EstatusId
			FROM tblSolicitudViaticoAsignacionViatico
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId,
					 EstatusId
		 ) svav ON svav.AsignacionId = sva.AsignacionId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(Costo) AS CostoTotal,
				   EstatusId
			FROM tblSolicitudViaticoAsignacionPasaje
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId,
					 EstatusId
		 ) svap ON svap.AsignacionId = sva.AsignacionId
		 INNER JOIN tblSolicitudViaticoComprobacion svc ON svc.SolicitudViaticoId = sv.SolicitudViaticoId
	WHERE sv.EstatusId IN(1000161, 1000104, 1000107, 1000108)
		 AND svc.SolicitanteFinalizoComprobacion = 1
		 AND svc.RMFinalizoComprobacion = 1
)