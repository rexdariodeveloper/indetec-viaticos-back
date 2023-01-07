/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  UserDefinedFunction [dbo].[fn_getListadoRevisionSolicitudViatico]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getListadoRevisionSolicitudViatico]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
DROP FUNCTION fn_getListadoRevisionSolicitudViatico
END
GO
execute dbo.sp_executesql @statement = N'
-- =================================================
-- Author: Rene Carrillo
-- Create date: --/--/--
-- Modified: Javier Elías
-- Modified date: 25/06/2020
-- Description:	Función para obtener el Listado de Revisión
-- =================================================
CREATE FUNCTION [dbo].[fn_getListadoRevisionSolicitudViatico] ()
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
		 INNER JOIN tblSolicitudViaticoAsignacion AS sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId AND sva.EstatusId=1000000
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
)'
GO