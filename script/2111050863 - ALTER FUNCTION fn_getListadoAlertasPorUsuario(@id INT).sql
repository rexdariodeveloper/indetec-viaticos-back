/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  UserDefinedFunction [dbo].[fn_getListadoAlertasPorUsuario]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getListadoAlertasPorUsuario]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
DROP FUNCTION fn_getListadoAlertasPorUsuario
END
GO
execute dbo.sp_executesql @statement = N'
-- =========================================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Modified: Javier Elías
-- Modified: Alejandro Gaytan 23/02/2021
-- Create date: 25/06/2020
-- Description:	Funcion para obtener el listado de Alertas por usuario
-- =========================================================
CREATE FUNCTION [dbo].[fn_getListadoAlertasPorUsuario] ( @usuarioId INT )
RETURNS 
@tblAlertas TABLE 
(
	-- Add the column definitions for the TABLE variable here
	AlertaId BIGINT, 
	AlertaIniciadaPor NVARCHAR(500),
	Fecha NVARCHAR(100),
	TipoMovimiento NVARCHAR(50),
	Tramite NVARCHAR(255),
	Estatus NVARCHAR(50),
	RutaAccion NVARCHAR(MAX),
	tipoAlertaId INT,
	EtapaAccionAlAutorizarId BIGINT,
	EtapaAccionAlRevisionId BIGINT,
	EtapaAccionAlRechazarId BIGINT
)
AS
BEGIN
	--set @usuarioId=0
	INSERT INTO @tblAlertas 
	SELECT tblAlerta.AlertaId AS AlertaId,  
		   dbo.getNombreCompletoEmpleado(usuarioCreadoPor.EmpleadoId) AS AlertaIniciadaPor,
		   dbo.getFechaConFormato(tblAlerta.FechaCreacion,1) AS Fecha,
		   tblAlertaDefinicion.NombreCorto AS TipoMovimiento,
		   tblAlerta.TextoRepresentativo AS Tramite,
		   estatus.Valor AS Estatus,
		   REPLACE(tblAlertaDefinicion.RutaAccion, ''{id}'', tblAlerta.ReferenciaProcesoId) AS RutaAccion,
		   1000041 AS tipoAlertaId, --Tipo Autorizacion
		   EtapaAccionAlAutorizarId,
		   EtapaAccionAlRevisionId,
		   EtapaAccionAlRechazarId
	FROM tblAlerta
		INNER JOIN tblAlertaDefinicion ON tblAlerta.AlertaDefinicionId = tblAlertaDefinicion.AlertaDefinicionId AND tblAlerta.EstatusId<>1000123
		INNER JOIN tblListadoCMM estatus ON tblAlerta.EstatusId = estatus.ControlId
		INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId 
		INNER JOIN 
		(
			SELECT DISTINCT AlertaId
			FROM fn_getUsuariosTempANotificarAlertas(@usuarioId) AS usuarioTemp
				 INNER JOIN tblUsuario AS usuario ON usuarioTemp.UsuarioId = usuario.UsuarioId
				 INNER JOIN tblAlertaAutorizacion AS detalle ON usuario.EmpleadoId = detalle.EmpleadoId
		) AS detalle ON tblAlerta.AlertaId = detalle.AlertaId
	WHERE tblAlerta.EstatusId = 1000120 --En proceso
	ORDER BY tblAlerta.FechaCreacion ASC
	
	INSERT INTO  @tblAlertas 
	SELECT tblAlerta.AlertaId AS AlertaId,  
		   dbo.getNombreCompletoEmpleado(usuarioCreadoPor.EmpleadoId) AS AlertaIniciadaPor,
		   dbo.getFechaConFormato(tblAlerta.FechaCreacion,1) AS Fecha,
		   tblAlertaDefinicion.NombreCorto AS TipoMovimiento,
		   tblAlerta.TextoRepresentativo AS Tramite,
		   estatus.Valor AS Estatus,
		   REPLACE(tblAlertaDefinicion.RutaAccion, ''{id}'', tblAlerta.ReferenciaProcesoId) AS RutaAccion,
		   1000042 AS tipoAlertaId, --Tipo Notificacion
		   NULL AS EtapaAccionAlAutorizarId,
		   NULL AS EtapaAccionAlRevisionId,
		   NULL AS EtapaAccionAlRechazarId
	FROM tblAlerta
		INNER JOIN tblAlertaDefinicion ON tblAlerta.AlertaDefinicionId = tblAlertaDefinicion.AlertaDefinicionId AND tblAlerta.EstatusId<>1000123
		INNER JOIN tblListadoCMM estatus ON tblAlerta.EstatusId = estatus.ControlId
		INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId
		INNER JOIN 
		(
			SELECT DISTINCT AlertaId
			FROM fn_getUsuariosTempANotificarAlertas(@usuarioId) AS usuarioTemp
				 INNER JOIN tblUsuario AS usuario ON usuarioTemp.UsuarioId = usuario.UsuarioId
				 INNER JOIN tblAlertaNotificacion AS detalle ON usuario.EmpleadoId = detalle.EmpleadoId AND detalle.Vista = 0
		) AS detalle ON tblAlerta.AlertaId = detalle.AlertaId
	WHERE tblAlerta.EstatusId not in(1000124)
	ORDER BY tblAlerta.FechaCreacion DESC
	
	RETURN 
END'
GO