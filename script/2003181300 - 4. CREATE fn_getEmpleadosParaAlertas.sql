SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION fn_getEmpleadosANotificarAlertas
(
	@alertaDefinicionId INT,
	@referenciaProcesoId INT
)
RETURNS 
@tblEmpleadosParaAlertas TABLE 
(
	empleadoId INT,
	figuraId INT,
	tipoNotificacionId INT,
	medioCorreoElectronico BIT,
	medioPlataforma BIT
)
AS
BEGIN

	DECLARE @solicitudId INT
	DECLARE @empleadoSolicitanteId INT
	DECLARE @usuarioCreadorSolicitudId INT
	DECLARE @empleadoCreadoSolicitudId INT
	DECLARE @areaAdscripcionId INT
	DECLARE @empleadoAutorizacionInmediatoId INT
	
	DECLARE @ALERTA_CONFIGURACION_FIGURA_JEFE_INMEDIATO_ID INT = 1000043
	DECLARE @ALERTA_CONFIGURACION_FIGURA_SOLICITANTE_ID INT = 1000044
	
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID INT = 1

	DECLARE @notificarAJefeInmediato BIT 
	DECLARE @notificarASolicitante BIT

	
	--Si la definicion alerta es para Autorizar una Solicitud de Viaticos, 
	IF(@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID)
	BEGIN 
		SELECT @solicitudId = tblSolicitudViatico.SolicitudViaticoId,
			   @empleadoSolicitanteId = tblSolicitudViatico.empleadoId,
			   @usuarioCreadorSolicitudId = tblSolicitudViatico.CreadoPorId,
			   @areaAdscripcionId = tblSolicitudViatico.AreaAdscripcionId,
			   @empleadoCreadoSolicitudId = tblUsuario.empleadoId,
			   @empleadoAutorizacionInmediatoId = dbo.getAutorizacionUsuarioInmediatoId(tblSolicitudViatico.AreaAdscripcionId)
		FROM tblSolicitudViatico
		INNER JOIN tblUsuario ON tblSolicitudViatico.CreadoPorId = tblUsuario.UsuarioId
		WHERE SolicitudViaticoId = @referenciaProcesoId
	END


	-- Obtenemos los empleados configurados para recibir alerta, asi como el tipo de notificacion y el medio por el que se les notificara
	INSERT INTO @tblEmpleadosParaAlertas
	SELECT tblAlertaConfiguracion.empleadoId, tblAlertaConfiguracion.FiguraId, tblAlertaConfiguracion.TipoNotificacionId, tblAlertaConfiguracion.EnCorreoElectronico, tblAlertaConfiguracion.EnPlataforma 
	FROM tblAlertaDefinicion
	INNER JOIN tblAlertaEtapaAccion ON tblAlertaDefinicion.AlertaEtapaAccionId = tblAlertaEtapaAccion.AlertaEtapaAccionId
	INNER JOIN tblAlertaConfiguracion ON tblAlertaEtapaAccion.AlertaEtapaAccionId = tblAlertaConfiguracion.AlertaEtapaAccionId 
	WHERE tblAlertaDefinicion.AlertaDefinicionId = @alertaDefinicionId AND
	      tblAlertaConfiguracion.EstatusId = 1000000 AND 
		  tblAlertaDefinicion.Borrado = 0

	-- Verificamos si dentro de los usuarios a los que se les tiene que notificar se encuentra la figura Jefe Inmediato o Solicitante para obtener los 
	-- Id de empleados que corresponden
	SELECT @notificarAJefeInmediato =  (CASE WHEN COUNT(figuraId) > 0 THEN 1 ELSE 0 END)
	FROM @tblEmpleadosParaAlertas
	WHERE figuraId = @ALERTA_CONFIGURACION_FIGURA_JEFE_INMEDIATO_ID
	
	SELECT @notificarASolicitante =  (CASE WHEN COUNT(figuraId) > 0 THEN 1 ELSE 0 END)
	FROM @tblEmpleadosParaAlertas
	WHERE figuraId =  @ALERTA_CONFIGURACION_FIGURA_SOLICITANTE_ID

	-- Si hay que notificar al jefe inmediato, actualizamos el id del empleado en la tabla temporal
	IF(@notificarAJefeInmediato = 1)
	BEGIN

		UPDATE @tblEmpleadosParaAlertas
		SET EmpleadoId = @empleadoAutorizacionInmediatoId
		WHERE FiguraId = @ALERTA_CONFIGURACION_FIGURA_JEFE_INMEDIATO_ID		

	END

	-- Si hay que notificar al solicitante, actualizamos el id del empleado en la tabla temporal
	IF(@notificarASolicitante = 1)
	BEGIN
		
		UPDATE @tblEmpleadosParaAlertas
		SET EmpleadoId = @empleadoSolicitanteId
		WHERE FiguraId = @ALERTA_CONFIGURACION_FIGURA_SOLICITANTE_ID

			
		-- Si el Id del Empleado Solicitante es Diferente al empleado que creo la Solicitud, tambien agregarlo en la lista de empleados a notificar
		IF(@empleadoSolicitanteId <> @empleadoCreadoSolicitudId)
		BEGIN

			INSERT INTO @tblEmpleadosParaAlertas
			SELECT @empleadoCreadoSolicitudId, NULL, TipoNotificacionId, medioCorreoElectronico, medioPlataforma
			FROM @tblEmpleadosParaAlertas
			WHERE EmpleadoId = @empleadoSolicitanteId
		END
	END	
	
	RETURN 
END
GO