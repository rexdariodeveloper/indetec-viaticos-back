SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==================================================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Modified:		Javier Elías
-- Modified date: 07/07/2020
-- Description:	Funcion para obtener un listado de los empleados configurados
--              Para recibir notificaciones en cada Etapa/accion de las alertas
-- ==================================================================
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
				
	-- Buscar los datos de la solicitud
	SELECT @empleadoSolicitanteId = tblSolicitudViatico.EmpleadoId,
			@usuarioCreadorSolicitudId = tblSolicitudViatico.CreadoPorId,
			@areaAdscripcionId = tblSolicitudViatico.AreaAdscripcionId,
			@empleadoCreadoSolicitudId = tblUsuario.empleadoId,
			@empleadoAutorizacionInmediatoId = CASE WHEN NodoPadreId IS NULL THEN tblSolicitudViatico.EmpleadoId ELSE dbo.getAutorizacionUsuarioInmediatoId(tblSolicitudViatico.AreaAdscripcionId) END
	FROM tblSolicitudViatico
		INNER JOIN tblUsuario ON tblSolicitudViatico.CreadoPorId = tblUsuario.UsuarioId
		INNER JOIN tblOrganigrama ON AreaAdscripcionId = NodoId
	WHERE SolicitudViaticoId = @referenciaProcesoId
	
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
