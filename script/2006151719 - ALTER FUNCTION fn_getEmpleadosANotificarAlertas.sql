SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Modified:		Javier Elías
-- Create date: 15/06/2020
-- Description:	Funcion para obtener un listado de los empleados configurados
--              Para recibir notificaciones en cada Etapa/accion de las alertas
-- =============================================
ALTER FUNCTION [dbo].[fn_getEmpleadosANotificarAlertas]
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

	--DECLARE @solicitudId INT
	DECLARE @empleadoSolicitanteId INT
	DECLARE @usuarioCreadorSolicitudId INT
	DECLARE @empleadoCreadoSolicitudId INT
	DECLARE @areaAdscripcionId INT
	DECLARE @empleadoAutorizacionInmediatoId INT
	DECLARE @notificarAJefeInmediato BIT 
	DECLARE @notificarASolicitante BIT
	
	DECLARE @ALERTA_CONFIGURACION_FIGURA_JEFE_INMEDIATO_ID INT = 1000043
	DECLARE @ALERTA_CONFIGURACION_FIGURA_SOLICITANTE_ID INT = 1000044
	
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID INT = 1
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_CANCELACION_ID INT = 2
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZADA_ID INT = 3
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_RECHAZADA_ID INT = 4
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_REVISION_ID INT = 5
	DECLARE @ALERTA_DEFINICION_ASIGNACION_VIATICOS_ID INT = 6
	DECLARE @ALERTA_DEFINICION_ASIGNACION_VIATICOS_REVISION_ID INT = 7
	DECLARE @ALERTA_DEFINICION_INFORME_COMPROBACION_FINALIZAR_ID INT = 8
				
	--Si la definicion alerta es para Iniciar Autorizacion de una Solicitud o Autorizarla/Rechazarla/Revision, 
	IF(@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID OR 
	   @alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZADA_ID OR
	   @alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_RECHAZADA_ID OR
	   @alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_REVISION_ID OR 
	   @alertaDefinicionId = @ALERTA_DEFINICION_ASIGNACION_VIATICOS_REVISION_ID)
	BEGIN 
		-- Buscar los datos de la solicitud
		SELECT @empleadoSolicitanteId = tblSolicitudViatico.empleadoId,
			   @usuarioCreadorSolicitudId = tblSolicitudViatico.CreadoPorId,
			   @areaAdscripcionId = tblSolicitudViatico.AreaAdscripcionId,
			   @empleadoCreadoSolicitudId = tblUsuario.empleadoId,
			   @empleadoAutorizacionInmediatoId = dbo.getAutorizacionUsuarioInmediatoId(tblSolicitudViatico.AreaAdscripcionId)
		FROM tblSolicitudViatico
		INNER JOIN tblUsuario ON tblSolicitudViatico.CreadoPorId = tblUsuario.UsuarioId
		WHERE SolicitudViaticoId = @referenciaProcesoId
	END

	--Si la definicion alerta es para Asignar viaticos 
	ELSE IF(@alertaDefinicionId = @ALERTA_DEFINICION_ASIGNACION_VIATICOS_ID)
	BEGIN 
		-- Buscar los datos de la solicitud
		SELECT @empleadoSolicitanteId = tblSolicitudViatico.empleadoId,
			   @usuarioCreadorSolicitudId = tblSolicitudViatico.CreadoPorId,
			   @areaAdscripcionId = tblSolicitudViatico.AreaAdscripcionId,
			   @empleadoCreadoSolicitudId = tblUsuario.empleadoId,
			   @empleadoAutorizacionInmediatoId = dbo.getAutorizacionUsuarioInmediatoId(tblSolicitudViatico.AreaAdscripcionId)
		FROM tblSolicitudViaticoAsignacion
		INNER JOIN tblSolicitudViatico ON tblSolicitudViatico.SolicitudViaticoId = tblSolicitudViaticoAsignacion.SolicitudViaticoId
		INNER JOIN tblUsuario ON tblSolicitudViatico.CreadoPorId = tblUsuario.UsuarioId
		WHERE AsignacionId = @referenciaProcesoId
	END

	--Si la definicion alerta es para Finalizar Comprobación
	ELSE IF(@alertaDefinicionId = @ALERTA_DEFINICION_INFORME_COMPROBACION_FINALIZAR_ID)
	BEGIN 
		-- Buscar los datos de la solicitud
		SELECT @empleadoSolicitanteId = tblSolicitudViatico.empleadoId,
			   @usuarioCreadorSolicitudId = tblSolicitudViatico.CreadoPorId,
			   @areaAdscripcionId = tblSolicitudViatico.AreaAdscripcionId,
			   @empleadoCreadoSolicitudId = tblUsuario.empleadoId,
			   @empleadoAutorizacionInmediatoId = dbo.getAutorizacionUsuarioInmediatoId(tblSolicitudViatico.AreaAdscripcionId)
		FROM tblSolicitudViaticoComprobacion
		INNER JOIN tblSolicitudViatico ON tblSolicitudViatico.SolicitudViaticoId = tblSolicitudViaticoComprobacion.SolicitudViaticoId
		INNER JOIN tblUsuario ON tblSolicitudViatico.CreadoPorId = tblUsuario.UsuarioId
		WHERE SolicitudViaticoComprobacionId = @referenciaProcesoId
	END
	
	-- Obtenemos los empleados configurados para recibir alerta, asi como el tipo de notificacion y el medio por el que se les notificara
	INSERT INTO @tblEmpleadosParaAlertas
	SELECT tblAlertaConfiguracion.empleadoId, 
		   tblAlertaConfiguracion.FiguraId, 
		   tblAlertaConfiguracion.TipoNotificacionId, 
		   tblAlertaConfiguracion.EnCorreoElectronico, 
		   tblAlertaConfiguracion.EnPlataforma 
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

	-- Si hay que notificar al jefe inmediato, actualizamos el id de la figura Jefe Inmediato por el id del empleado que le corresponde en la tabla temporal
	IF(@notificarAJefeInmediato = 1)
	BEGIN

		UPDATE @tblEmpleadosParaAlertas
		SET EmpleadoId = @empleadoAutorizacionInmediatoId
		WHERE FiguraId = @ALERTA_CONFIGURACION_FIGURA_JEFE_INMEDIATO_ID		

	END

	-- Si hay que notificar al solicitante, actualizamos el id de la figura Solicitante por el id del empleado que le corresponde en la tabla temporal
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
