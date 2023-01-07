SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getRptTransparenciaPartidas] ( @solicitudViaticoId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT solicitud.NumeroSolicitud AS NumeroSolicitud,
		   ISNULL(concepto.Codigo, '') AS Clave,
		   ISNULL(concepto.Concepto, '') AS Denominacion,
		   ISNULL(ImportePesos, 0.00) AS Importe
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblSolicitudViaticoComprobacion AS comprobacion ON solicitud.SolicitudViaticoId = comprobacion.SolicitudViaticoId
		 INNER JOIN tblSolicitudViaticoComprobacionDetalle AS detalle ON comprobacion.SolicitudViaticoComprobacionId = detalle.SolicitudViaticoComprobacionId AND detalle.EstatusId = 1000000
		 LEFT JOIN tblConceptoViatico AS concepto ON detalle.ConceptoViaticoId = concepto.ConceptoViaticoId
	WHERE solicitud.SolicitudViaticoId = @solicitudViaticoId
		  AND comprobacion.EstatusId = 1000000
)