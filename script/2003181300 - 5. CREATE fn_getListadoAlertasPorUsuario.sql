SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION fn_getListadoAlertasPorUsuario
(	
	@usuarioId INT
)
RETURNS TABLE 
AS
RETURN 
(
	-- Add the SELECT statement with parameter references here
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
	INNER JOIN tblUsuario Usuario ON Usuario.UsuarioId = @usuarioId
	INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId
	INNER JOIN tblEmpleado ON usuarioCreadoPor.EmpleadoId = tblEmpleado.EmpleadoId
	WHERE tblAlertaAutorizacion.EmpleadoId = Usuario.EmpleadoId


	UNION ALL

	SELECT tblAlerta.AlertaId AS AlertaId,  
		   tblEmpleado.Nombre + ' ' + tblEmpleado.PrimerApellido + ISNULL( ' ' + tblEmpleado.SegundoApellido, '') + 'Noti' AS AlertaIniciadaPor,
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
	INNER JOIN tblUsuario Usuario ON Usuario.UsuarioId = @usuarioId
	INNER JOIN tblUsuario ON tblAlerta.CreadoPorId = tblUsuario.UsuarioId
	INNER JOIN tblUsuario usuarioCreadoPor ON tblAlerta.CreadoPorId = usuarioCreadoPor.UsuarioId
	INNER JOIN tblEmpleado ON usuarioCreadoPor.EmpleadoId = tblEmpleado.EmpleadoId
	WHERE tblAlertaNotificacion.EmpleadoId = Usuario.EmpleadoId AND
		  tblAlertaNotificacion.Vista = 0

)
GO
