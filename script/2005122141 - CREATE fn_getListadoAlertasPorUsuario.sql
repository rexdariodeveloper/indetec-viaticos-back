SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Description:	Funcion para obtener el listado de Alertas por usuario
-- =============================================
CREATE FUNCTION fn_getListadoAlertasPorUsuario
(
	-- Add the parameters for the function here
	@usuarioId INT
)
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
	AlertaAutorizacionId BIGINT,
	AlertaNotificacionId BIGINT
)
AS
BEGIN

	INSERT INTO @tblAlertas
	SELECT tblAlerta.AlertaId AS AlertaId,  
		   tblEmpleado.Nombre + ' ' + tblEmpleado.PrimerApellido + ISNULL( ' ' + tblEmpleado.SegundoApellido, '') AS AlertaIniciadaPor,
		   dbo.getFechaConFormato(tblAlerta.FechaCreacion,1) AS Fecha,
		   tblAlertaDefinicion.NombreCorto AS TipoMovimiento,
		   tblAlerta.TextoRepresentativo AS Tramite,
		   estatus.Valor AS Estatus,
		   REPLACE(tblAlertaDefinicion.RutaAccion, '{id}', tblAlerta.ReferenciaProcesoId)  AS RutaAccion,
		   1000041 AS tipoAlertaId, --Tipo Autorizacion
		   tblAlertaAutorizacion.AlertaAutorizacionId AS AlertaAutorizacionId,
		   NULL AS AlertaNotificacionId
	FROM tblAlerta
	INNER JOIN tblAlertaAutorizacion ON tblAlerta.AlertaId = tblAlertaAutorizacion.AlertaId
	INNER JOIN tblAlertaDefinicion ON tblAlerta.AlertaDefinicionId = tblAlertaDefinicion.AlertaDefinicionId
	INNER JOIN tblListadoCMM estatus ON tblAlerta.EstatusId = estatus.ControlId
	INNER JOIN tblUsuario Usuario ON Usuario.UsuarioId IN ( SELECT usuarioId FROM fn_getUsuariosTempANotificarAlertas(@usuarioId) )
	INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId
	INNER JOIN tblEmpleado ON usuarioCreadoPor.EmpleadoId = tblEmpleado.EmpleadoId
	WHERE tblAlertaAutorizacion.EmpleadoId = Usuario.EmpleadoId AND 
	      tblAlerta.EstatusId = 1000120 --En proceso
	ORDER BY tblAlerta.FechaCreacion ASC
	
	INSERT INTO  @tblAlertas 
	SELECT tblAlerta.AlertaId AS AlertaId,  
		   tblEmpleado.Nombre + ' ' + tblEmpleado.PrimerApellido + ISNULL( ' ' + tblEmpleado.SegundoApellido, '') AS AlertaIniciadaPor,
		   dbo.getFechaConFormato(tblAlerta.FechaCreacion,1) AS Fecha,
		   tblAlertaDefinicion.NombreCorto AS TipoMovimiento,
		   tblAlerta.TextoRepresentativo AS Tramite,
		   estatus.Valor AS Estatus,
		   REPLACE(tblAlertaDefinicion.RutaAccion, '{id}', tblAlerta.ReferenciaProcesoId)  AS RutaAccion,
		   1000042 AS tipoAlertaId, --Tipo Notificacion
		   NULL AS AlertaAutorizacionId,
		   tblAlertaNotificacion.AlertaNotificacionId AS AlertaNotificacionId
	FROM tblAlerta
	INNER JOIN tblAlertaNotificacion ON tblAlerta.AlertaId = tblAlertaNotificacion.AlertaId
	INNER JOIN tblAlertaDefinicion ON tblAlerta.AlertaDefinicionId = tblAlertaDefinicion.AlertaDefinicionId
	INNER JOIN tblListadoCMM estatus ON tblAlerta.EstatusId = estatus.ControlId
	INNER JOIN tblUsuario Usuario ON Usuario.UsuarioId IN ( SELECT usuarioId FROM fn_getUsuariosTempANotificarAlertas(@usuarioId) )
	INNER JOIN tblUsuario ON tblAlerta.CreadoPorId = tblUsuario.UsuarioId
	INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId
	INNER JOIN tblEmpleado ON usuarioCreadoPor.EmpleadoId = tblEmpleado.EmpleadoId
	WHERE tblAlertaNotificacion.EmpleadoId = Usuario.EmpleadoId AND
		  tblAlertaNotificacion.Vista = 0
	ORDER BY tblAlerta.FechaCreacion DESC
	
	RETURN 
END
GO