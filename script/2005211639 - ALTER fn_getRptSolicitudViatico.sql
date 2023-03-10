SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER FUNCTION [dbo].[fn_getRptSolicitudViatico] (	 @solicitudId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT ruta.Valor + '/' + RutaFisica AS Imagen,
		   dbo.getFechaConFormato(solicitud.FechaCreacion, 0) AS Fecha,
		   solicitud.AreaAdscripcion AS Departamento,
		   solicitud.NumeroSolicitud AS Folio,
		   dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS Solicitante,
		   ProgramaGobiernoId AS ProgramaId,
		   ProgramaGobierno AS Programa,
		   ProyectoId,
		   Proyecto,
		   RamoId AS FuenteFinanciamientoId,
		   Ramo AS FuenteFinanciamiento,
		   DependenciaId AS DependenciaId,
		   Dependencia AS Dependencia,
		   '' AS Observaciones,
		   dbo.getNombreCompletoCiudad(SolicitudViaticoId, 1) AS Destino,
		   solicitud.DescripcionComision AS DetalleActividad,
		   dbo.getFechaConFormato(solicitud.FechaSalida, 1) AS FechaSalida,
		   ISNULL(solicitud.Sugerencias, '') AS Transporte,
		   dbo.getFechaConFormato(solicitud.FechaRegreso, 1) AS FechaRegreso,
		   dbo.getNombreCompletoEmpleado(autorizadoPor.EmpleadoId) AS AutorizadoPor,
		   ISNULL(puestoAutorizado.Nombre, '') AS AutorizadoPuesto,
		   '' AS Enterado,
		   '' AS EnteradoPuesto
	FROM tblSolicitudViatico AS solicitud
		 CROSS APPLY 
		 (
				SELECT TOP 1 CreadoPorId
				FROM tblAlerta
				WHERE ReferenciaProcesoId = SolicitudViaticoId
					  AND AlertaDefinicionId = 3
					  AND EstatusId = 1000123
				ORDER BY FechaFinalizacion DESC
		 ) AS alerta
		 INNER JOIN tblEmpleado AS autorizadoPor ON alerta.CreadoPorId  = autorizadoPor.EmpleadoId
		 INNER JOIN tblListadoPuesto AS puestoAutorizado ON autorizadoPor.PuestoId = puestoAutorizado.PuestoId		 
		 INNER JOIN tblListadoCMM AS ruta ON ruta.Nombre = 'RutaRaizArchivo'
		 LEFT JOIN tblArchivo ON ArchivoId = ( SELECT Fotografia FROM tblConfiguracionEnte )
	WHERE SolicitudViaticoId = @solicitudId
)