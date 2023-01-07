SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =================================================
-- Author: Rene Carrillo
-- Create date: --/--/--
-- Modified: Javier Elías
-- Modified date: 25/06/2020
-- Description:	Función para obtener el Listado de Revisión
-- =================================================
ALTER FUNCTION [dbo].[fn_getListadoRevisionSolicitudViatico] ()
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoCiudad(sv.SolicitudViaticoId, 1) AS ciudadDestino,
		   dbo.getFechaConFormato(sv.FechaCreacion, 1) AS fechaSolicitud,
		   dbo.getFechaConFormato(sv.fechaSalida, 1) AS fechaViaje,
		   (ISNULL(svap.CostoTotal, 0) + ISNULL(svav.MontoPorTransferirTotal, 0)) AS montoAsignado,
		   ISNULL(svav.MontoPorTransferirTotal, 0) AS montoTransferido,
		   Valor AS estatus
	FROM tblSolicitudViatico AS sv
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion AS sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
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
	WHERE sv.EstatusId IN(1000096, 1000108, 1000099, 1000097)
)