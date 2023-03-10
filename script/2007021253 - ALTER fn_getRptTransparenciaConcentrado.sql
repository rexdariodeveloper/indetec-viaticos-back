SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Javier Elías
-- Create date: 04/06/2020
-- Create date: 26/06/2020
-- Description:	Función para obtener el concentrado
--						del Reporte de Transparencia
-- =============================================
ALTER FUNCTION [dbo].[fn_getRptTransparenciaConcentrado] ( @solicitudIds NVARCHAR(1000) )
RETURNS TABLE 
AS
RETURN 
(
	SELECT NumeroSolicitud AS CodigoSolicitud,
			dbo.getFechaConFormato(solicitud.FechaCreacion, 0) AS FechaSolicitud,
			dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS NombreSolicitud,
			Puesto,
			Cargo,
			AreaAdscripcion,
			ProgramaGobiernoId+' - '+ProgramaGobierno AS Programa,
			ProyectoId+' - '+Proyecto AS Proyecto,
			RamoId+' - '+Ramo AS FuenteFinanciamiento,
			DependenciaId+' - '+Dependencia AS DependenciaAdministrativa,
			NoOficio AS NumeroAcuerdoOficio,
			tipoViaje.Valor AS TipoViaje,
			TipoRepresentacion,
			dbo.getFechaConFormato(solicitud.FechaSalida, 1) AS FechaYHoraSalidaViaje,
			dbo.getFechaConFormato(solicitud.FechaRegreso, 1) AS FechaYHoraRegresoViaje,
			DuracionComision AS DiasComision,
			paisOrigen.Nombre AS PaisOrigen,
			estadoOrigen.Nombre AS EstadoOrigen,
			ciudadOrigen.Nombre AS CiudadOrigen,
			paisDestino.Nombre AS PaisDestino,
			estadoDestino.Nombre AS EstadoDestino,
			ciudadDestino.Nombre AS CiudadDestino,
			GastoRepresentacion,
			Sugerencias,
			dbo.getFechaConFormato(FechaInicioEvento, 1) AS FechaYHoraInicioEvento,
			dbo.getFechaConFormato(FechaFinEvento, 1) AS FechaYHoraFinEvento,
			DuracionEvento AS DiasEvento,
			Motivo,
			DescripcionComision,
			Organizador,
			LinkEvento,
			ISNULL(ViaticosConPernocta, 0.00) AS ViaticosConPernocta,
			ISNULL(ViaticosSinPernocta, 0.00) AS ViaticosSinPernocta,
			ISNULL(ViaticosConPernocta, 0.00) + ISNULL(ViaticosSinPernocta, 0.00) AS TotalViaticos,
			ISNULL(TotalTransferir, 0.00) AS TotalTransferir,
			ISNULL(PasajeAereo, 0.00) AS PasajeAereo,
			ISNULL(PasajeMaritimo, 0.00) AS PasajeMaritimo,
			ISNULL(PasajeTerrestre, 0.00) AS PasajeTerrestre,		
			ISNULL(PasajeAereo, 0.00) + ISNULL(PasajeMaritimo, 0.00) + ISNULL(PasajeTerrestre, 0.00) AS TotalPasaje,
			ISNULL(TotalTransferir2, 0.00) AS TotalTransferir2,
			ISNULL(ViaticosConPernocta, 0.00) + ISNULL(ViaticosSinPernocta, 0.00) + ISNULL(PasajeAereo, 0.00) + ISNULL(PasajeMaritimo, 0.00) + ISNULL(PasajeTerrestre, 0.00) AS TotalComision,
			CASE WHEN ComisionNoRealizada = 0 THEN 'Si' ELSE 'No' END AS ComisionRealizada,
			MotivoNoRealizada,
			ObjetivoEstrategico,
			ObjetivoEspecifico,
			ActividadesRealizadas,
			ResultadosObtenidos,
			Contribuciones,
			VinculosAnotas,
			ListadoDocumentos,
			Conclusiones,
			ISNULL(TotalTransferir, 0.00) + ISNULL(TotalTransferir2, 0.00) AS TotalTransferidoSolicitante,
			ISNULL(GastosSinComprobanteSolicitante, 0.00) AS GastosSinComprobanteSolicitante,
			ISNULL(GastosConComprobanteSolicitante, 0.00) AS GastosConComprobanteSolicitante,
			ISNULL(GastosSinComprobanteSolicitante, 0.00) + ISNULL(GastosConComprobanteSolicitante, 0.00) AS TotalComisionSolicitante,
			ISNULL(ReintegroSolicitante, 0.00) AS ReintegroSolicitante,
			(ISNULL(TotalTransferir, 0.00) + ISNULL(TotalTransferir2, 0.00)) - (ISNULL(GastosSinComprobanteSolicitante, 0.00) + ISNULL(GastosConComprobanteSolicitante, 0.00)) - ISNULL(ReintegroSolicitante, 0.00) AS BalanceSolicitante,
			ISNULL(GastosSinComprobanteRm, 0.00) AS GastosSinComprobanteRm,
			ISNULL(GastosConComprobanteRm, 0.00) AS GastosConComprobanteRm,
			ISNULL(GastosSinComprobanteRm, 0.00) + ISNULL(GastosConComprobanteRm, 0.00) AS TotalComisionRm,
			estatus.Valor AS EstatusSolicitud
	FROM tblSolicitudViatico AS solicitud
		INNER JOIN tblListadoCMM AS estatus ON solicitud.EstatusId = estatus.ControlId
		INNER JOIN tblListadoCMM AS tipoViaje ON solicitud.TipoViajeId = tipoViaje.ControlId
		INNER JOIN tblPais AS paisOrigen ON PaisOrigenId = paisOrigen.PaisId
		INNER JOIN tblEstado AS estadoOrigen ON EstadoOrigenId = estadoOrigen.EstadoId
		INNER JOIN tblCiudad AS ciudadOrigen ON CiudadOrigenId = ciudadOrigen.CiudadId
		INNER JOIN tblPais AS paisDestino ON PaisDestinoId = paisDestino.PaisId
		INNER JOIN tblEstado AS estadoDestino ON EstadoDestinoId = estadoDestino.EstadoId
		INNER JOIN tblCiudad AS ciudadDestino ON CiudadDestinoId = ciudadDestino.CiudadId
		INNER JOIN tblSolicitudViaticoAsignacion AS asignacion ON asignacion.SolicitudViaticoId = solicitud.SolicitudViaticoId
		LEFT JOIN
		(
			SELECT AsignacionId,
					SUM(MontoConPernocta) AS ViaticosConPernocta,
					SUM(MontoSinPernocta) AS ViaticosSinPernocta,
					SUM(MontoPorTransferir) AS TotalTransferir
			FROM tblSolicitudViaticoAsignacionViatico
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		) montoViaticos ON asignacion.AsignacionId = montoViaticos.AsignacionId
		LEFT JOIN
		(
			SELECT AsignacionId,
					SUM(CASE WHEN ConceptoViaticoId = 1 THEN Costo ELSE 0 END) AS PasajeAereo,
					SUM(CASE WHEN ConceptoViaticoId = 2 THEN Costo ELSE 0 END) AS PasajeMaritimo,
					SUM(CASE WHEN ConceptoViaticoId = 3 THEN Costo ELSE 0 END) AS PasajeTerrestre,
					SUM(CASE WHEN AsignadoAFuncionario = 1 THEN Costo ELSE 0 END) AS TotalTransferir2
			FROM tblSolicitudViaticoAsignacionPasaje
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId
		) montoPasajes ON asignacion.AsignacionId = montoPasajes.AsignacionId
		INNER JOIN tblSolicitudViaticoComprobacion AS comprobacion ON solicitud.SolicitudViaticoId = comprobacion.SolicitudViaticoId AND comprobacion.EstatusId = 1000000
		LEFT JOIN tblSolicitudViaticoInforme AS informe ON comprobacion.SolicitudViaticoComprobacionId = informe.SolicitudViaticoComprobacionId
		LEFT JOIN
		(
			SELECT SolicitudViaticoComprobacionId,
					SUM(CASE WHEN EsComprobadoPorRM = 0 AND CategoriaId != 1000060 AND TipoComprobanteId != 1000050 THEN ImportePesos ELSE 0 END) AS GastosSinComprobanteSolicitante,
					SUM(CASE WHEN EsComprobadoPorRM = 0 AND CategoriaId != 1000060 AND TipoComprobanteId = 1000050 THEN ImportePesos ELSE 0 END) AS GastosConComprobanteSolicitante,
					SUM(CASE WHEN EsComprobadoPorRM = 0 AND CategoriaId = 1000060 THEN ImportePesos ELSE 0 END) AS ReintegroSolicitante,
					SUM(CASE WHEN EsComprobadoPorRM = 1 AND TipoComprobanteId != 1000050 THEN ImportePesos ELSE 0 END) AS GastosSinComprobanteRm,
					SUM(CASE WHEN EsComprobadoPorRM = 1 AND TipoComprobanteId = 1000050 THEN ImportePesos ELSE 0 END) AS GastosConComprobanteRm
			FROM tblSolicitudViaticoComprobacionDetalle
			WHERE EstatusId = 1000000
			GROUP BY SolicitudViaticoComprobacionId
		) montoComprobacion ON comprobacion.SolicitudViaticoComprobacionId = montoComprobacion.SolicitudViaticoComprobacionId
		INNER JOIN tblSolicitudViaticoRevision AS revision ON solicitud.SolicitudViaticoId = revision.SolicitudViaticoId AND revision.EstatusId= 1000000
	WHERE @solicitudIds LIKE '%,' + CAST(solicitud.SolicitudViaticoId AS NVARCHAR(100)) + ',%' 
)