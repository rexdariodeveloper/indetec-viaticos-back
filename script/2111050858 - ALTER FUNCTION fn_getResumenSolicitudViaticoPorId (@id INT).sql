/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  UserDefinedFunction [dbo].[fn_getResumenSolicitudViaticoPorId]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getResumenSolicitudViaticoPorId]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
DROP FUNCTION fn_getResumenSolicitudViaticoPorId
END
GO
execute dbo.sp_executesql @statement = N'
-- ========================================================
-- Author:		Rene Carrillo
-- Create date: 20/05/2020
-- Modified: Javier Elías
-- Modified by: Alex Gaytan
-- Modified date: 21/01/2022
-- Description:	Función para obtener el listado de Asignación Viáticos
-- ========================================================
CREATE FUNCTION [dbo].[fn_getResumenSolicitudViaticoPorId] (@id INT)
RETURNS TABLE
AS
RETURN
(
	SELECT solicitud.SolicitudViaticoId AS id,
		   NumeroSolicitud,
		   solicitud.EmpleadoId,
		   dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) AS solicitante,
		   Puesto,
		   Cargo,
		   solicitud.AreaAdscripcionId,
		   AreaAdscripcion,
		   ProgramaGobierno,
		   Proyecto,
		   Ramo,
		   Dependencia,
		   NoOficio,
		   TipoViaje,
		   TipoRepresentacion,
		   DuracionComision,
		   dbo.getFechaConFormato(FechaSalida, 1) AS FechaSalida,
		   dbo.getNombreCompletoCiudad(solicitud.SolicitudViaticoId, 0) AS origen,
		   dbo.getFechaConFormato(FechaRegreso, 1) AS FechaRegreso,
		   dbo.getNombreCompletoCiudad(solicitud.SolicitudViaticoId, 1) AS destino,
		   GastoRepresentacion,
		   Sugerencias,
		   dbo.getFechaConFormato(FechaInicioEvento, 1) AS FechaInicioEvento,
		   dbo.getFechaConFormato(FechaFinEvento, 1) AS FechaFinEvento,
		   DuracionEvento,
		   Motivo,
		   DescripcionComision,
		   Organizador,
		   LinkEvento,
		   estatus.ControlId AS EstatusId,
		   estatus.Valor AS Estatus,
		   asignacion.NumeroPolizaComprometido,
		   asignacion.NumeroPolizaGastoPorComprobarId,
		   comprobacion.NumeroPolizaGastoComprobado
	FROM tblSolicitudViatico AS solicitud
		 INNER JOIN tblListadoCMM AS estatus ON solicitud.EstatusId = estatus.ControlId
		 LEFT JOIN dbo.tblSolicitudViaticoAsignacion AS asignacion ON asignacion.SolicitudViaticoId=solicitud.SolicitudViaticoId
		 AND asignacion.EstatusId<>1000002
		 LEFT JOIN tblSolicitudViaticoComprobacion AS comprobacion ON comprobacion.SolicitudViaticoId=solicitud.SolicitudViaticoId
	WHERE solicitud.SolicitudViaticoId = @id
)'
GO