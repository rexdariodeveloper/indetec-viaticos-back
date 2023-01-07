/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  UserDefinedFunction [dbo].[fn_getOficioComision]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getOficioComision]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
DROP FUNCTION fn_getOficioComision
END
GO
execute dbo.sp_executesql @statement = N'
-- ===================================================
-- Author:		Javier Elías
-- Create date: 26/11/2020
-- Description:	Función para obtener el Oficio de Comisión
-- ===================================================
CREATE FUNCTION [dbo].[fn_getOficioComision] ( @solicitudId INT, @separador NVARCHAR(4) )
RETURNS TABLE 
AS
RETURN 
(
	SELECT REPLACE(REPLACE(rutaImagen.Valor + ''/'' + RutaFisica, ''/'', @separador), ''\'', @separador) AS Imagen, 
		   UPPER(NombreEnte) AS NombreEnte, 
		   NoOficio, 
		   ciudadEnte.Nombre + '', '' + estadOente.Nombre + '' a '' + dbo.getFechaConFormato(GETDATE(), 0) AS LugarFecha, 
		   dbo.getNombreCompletoEmpleado(solicitante.EmpleadoId) AS Solicitante, 
		   puestoSolicitante.Nombre AS SolicitantePuesto,
		   tipoViaje.Valor AS TipoViaje,
		   representacion.Valor AS TipoRepresentacion,
		   DescripcionComision,
		   Organizador,
		   ISNULL(LinkEvento, ''N/A'') AS Link,
		   paisDestino.Nombre AS Pais,
		   estadoDestino.Nombre AS Estado,
		   ciudadDestino.Nombre AS Ciudad,
		   CONVERT(VARCHAR, FechaSalida, 103) AS FehaSalida,
		   LEFT(CONVERT(VARCHAR, FechaSalida, 8), 5) AS HoraSalida,
		   CONVERT(VARCHAR, FechaRegreso, 103) AS FechaRegreso,
		   LEFT(CONVERT(VARCHAR, FechaRegreso, 8), 5) AS HoraRegreso,
		   zona.Valor AS Zona,
		   CAST(MontoSinPernocta AS NVARCHAR(300)) AS MontoSinPernocta,
		   CAST(MontoConPernocta AS NVARCHAR(300)) AS MontoConPernocta,
		   DATEDIFF(DAY, CAST(FechaSalida AS DATE), CAST(FechaRegreso AS DATE)) + 1 AS DiasSinPernocta,
		   DATEDIFF(DAY, CAST(FechaSalida AS DATE), CAST(FechaRegreso AS DATE)) AS DiasConPernocta,
		   CAST(MontoTransferir AS NVARCHAR(300)) AS MontoTransferir,
		   MontoLetra,
		   moneda.Abreviacion AS Moneda,
		   Motivo,
		   area.Clave AS ClaveUA,
		   area.Descripcion AS NombreUA,
		   ProgramaGobiernoId AS ClavePrograma,
		   ProgramaGobierno AS Programa,
		   ProyectoId AS ClaveProyecto,
		   Proyecto,
		   AutorizadoPor,
		   AutorizadoPuesto
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblConfiguracionEnte AS ente ON ConfiguracionEnteId IS NOT NULL
		 LEFT JOIN tblArchivo AS imagen ON Fotografia = imagen.ArchivoId AND Vigente = 1
		 INNER JOIN tblListadoCMM AS rutaImagen ON rutaImagen.ControlId = 1000047
		 INNER JOIN tblCiudad AS ciudadEnte ON ente.CiudadId = ciudadEnte.CiudadId
		 INNER JOIN tblEstado AS estadOente ON ente.EstadoId = estadOente.EstadoId
		 INNER JOIN tblEmpleado AS solicitante ON solicitud.EmpleadoId = solicitante.EmpleadoId
		 INNER JOIN tblListadoPuesto AS puestoSolicitante ON solicitante.PuestoId = puestoSolicitante.PuestoId
		 INNER JOIN tblListadoCMM AS tipoViaje ON TipoViajeId = tipoViaje.ControlId
		 INNER JOIN tblListadoCMM AS representacion ON TipoRepresentacionId = representacion.ControlId	 
		 INNER JOIN tblPais AS paisDestino ON PaisDestinoId = paisDestino.PaisId
		 INNER JOIN tblEstado AS estadoDestino ON EstadoDestinoId = estadoDestino.EstadoId
		 INNER JOIN tblCiudad AS ciudadDestino ON CiudadDestinoId = ciudadDestino.CiudadId
		 INNER JOIN tblListadoCMM AS zona ON ciudadDestino.ZonaEconomicaId = zona.ControlId
		 INNER JOIN tblOrganigrama AS area ON solicitud.AreaAdscripcionId = NodoId	 
		 CROSS APPLY
		 (
				SELECT SUM(MontoSinPernocta) AS MontoSinPernocta,
					   SUM(MontoConPernocta) AS MontoConPernocta, 
					   SUM(MontoPorTransferir) AS MontoTransferir,
					   dbo.fn_getCantidadConLetra(SUM(MontoPorTransferir)) AS MontoLetra
				FROM tblSolicitudViaticoAsignacion AS asignacion
					 INNER JOIN tblSolicitudViaticoAsignacionViatico AS viaticos ON asignacion.AsignacionId = viaticos.AsignacionId AND viaticos.EstatusId = 1000000
				WHERE asignacion.SolicitudViaticoId = @solicitudId
					  AND asignacion.EstatusId = 1000000
		 ) AS viaticos
		 INNER JOIN tblMoneda AS moneda ON MonedaPredeterminadaId = MonedaId
		 CROSS APPLY 
		 (
				SELECT TOP 1 dbo.getNombreCompletoEmpleado(autorizadoPor.EmpleadoId) AS AutorizadoPor, 
							 puesto.Nombre AS AutorizadoPuesto
				FROM tblAlerta AS alerta
					 INNER JOIN tblUsuario AS usuarioAutorizado ON alerta.CreadoPorId = usuarioAutorizado.UsuarioId
					 INNER JOIN tblEmpleado AS autorizadoPor ON usuarioAutorizado.EmpleadoId = autorizadoPor.EmpleadoId
					 INNER JOIN tblListadoPuesto AS puesto ON autorizadoPor.PuestoId = puesto.PuestoId
				WHERE ReferenciaProcesoId = @solicitudId
					  AND AlertaDefinicionId = 3
					  AND alerta.EstatusId = 1000123
				ORDER BY FechaFinalizacion DESC
		 ) AS autorizado
	WHERE SolicitudViaticoId = @solicitudId
)'
GO