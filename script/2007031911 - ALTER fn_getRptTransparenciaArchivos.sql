SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Javier Elías
-- Create date: 02/07/2020
-- Modified date: 03/07/2020
-- Description:	Función para obtener los Archivos
--						del Reporte de Transparencia
-- =============================================
ALTER FUNCTION [dbo].[fn_getRptTransparenciaArchivos] ( @solicitudViaticoId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT solicitud.NumeroSolicitud AS NumeroSolicitud,
		   NombreOriginal,
		   URLPublica + 'comprobantes/' + CAST(YEAR(solicitud.FechaCreacion) AS NVARCHAR(4)) + '/' +  dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) + '/' + solicitud.NumeroSolicitud + '/' + NombreFisico + SUBSTRING(NombreOriginal, CHARINDEX('.', NombreOriginal), LEN(NombreOriginal)+1) AS Hipervinculo,
		   Exportado,		   
		   ROW_NUMBER() OVER (ORDER BY detalle.SolicitudViaticoComprobacionDetalleId, archivo.NombreFisico) AS Orden
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblSolicitudViaticoComprobacion AS comprobacion ON solicitud.SolicitudViaticoId = comprobacion.SolicitudViaticoId AND comprobacion.EstatusId = 1000000
		 INNER JOIN tblSolicitudViaticoComprobacionDetalle AS detalle ON comprobacion.SolicitudViaticoComprobacionId = detalle.SolicitudViaticoComprobacionId AND detalle.EstatusId = 1000000
		 INNER JOIN tblArchivo AS archivo ON SolicitudViaticoComprobacionDetalleId = ReferenciaId AND Vigente = 1
		 INNER JOIN tblConfiguracionEnte ON ConfiguracionEnteId != -1
	WHERE solicitud.SolicitudViaticoId = @solicitudViaticoId
)