SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[fn_getRptSolicitudViatico] (	 @solicitudId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT ruta.Valor + '/' + RutaFisica AS Imagen,
		   dbo.getFechaConFormato(solicitud.FechaCreacion, 0) AS Fecha,
		   solicitud.AreaAdscripcion AS Departamento,
		   solicitud.NumeroSolicitud AS Folio,
		   solicitante.Nombre+ISNULL(' '+solicitante.PrimerApellido, '')+ISNULL(' '+solicitante.SegundoApellido, '') AS Solicitante,
		   ProgramaGobiernoId AS ProgramaId,
		   ProgramaGobierno AS Programa,
		   ProyectoId,
		   Proyecto,
		   RamoId AS FuenteFinanciamientoId,
		   Ramo AS FuenteFinanciamiento,
		   DependenciaId AS DependenciaId,
		   Dependencia AS Dependencia,
		   '' AS Observaciones,
		   ciudad.Nombre + ', ' + estado.Nombre + ', ' + pais.Nombre AS Destino,
		   solicitud.DescripcionComision AS DetalleActividad,
		   dbo.getFechaConFormato(solicitud.FechaSalida, 1) AS FechaSalida,
		   ISNULL(solicitud.Sugerencias, '') AS Transporte,
		   dbo.getFechaConFormato(solicitud.FechaRegreso, 1) AS FechaRegreso,
		   ISNULL(autorizadoPor.Nombre+ISNULL(' '+autorizadoPor.PrimerApellido, '')+ISNULL(' '+autorizadoPor.SegundoApellido, ''), '') AS AutorizadoPor,
		   ISNULL(puestoAutorizado.Nombre, '') AS AutorizadoPuesto,
		   '' AS Enterado,
		   '' AS EnteradoPuesto
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblEmpleado AS solicitante ON solicitud.EmpleadoId = solicitante.EmpleadoId
		 INNER JOIN tblPais AS pais ON PaisDestinoId = pais.PaisId
		 INNER JOIN tblEstado AS estado ON EstadoDestinoId = estado.EstadoId
		 INNER JOIN tblCiudad AS ciudad ON CiudadDestinoId = ciudad.CiudadId		 
		 INNER JOIN tblAlerta AS alerta ON SolicitudViaticoId = alerta.ReferenciaProcesoId
		 INNER JOIN tblAlertaAutorizacion AS autorizacion ON alerta.AlertaId = autorizacion.AlertaId AND autorizacion.EstatusId = 1000123
		 INNER JOIN tblEmpleado AS autorizadoPor ON autorizacion.EmpleadoId  = autorizadoPor.EmpleadoId
		 INNER JOIN tblListadoPuesto AS puestoAutorizado ON autorizadoPor.PuestoId = puestoAutorizado.PuestoId		 
		 INNER JOIN tblListadoCMM AS ruta ON ruta.Nombre = 'RutaRaizArchivo'
		 LEFT JOIN tblArchivo ON ArchivoId = ( SELECT Fotografia FROM tblConfiguracionEnte )
	WHERE SolicitudViaticoId = @solicitudId
)