SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[sp_ProcesadorDeAlertas] 
	@accionId int,
	@alertaDefinicionId int = null,
	@referenciaProcesoId int = null,
	@codigoTramite nvarchar(50) = null,
	@textoRepresentativo nvarchar(255) = null,
	@creadoPorId INT = NULL,
	@alertaId BIGINT = NULL,
	@motivo NVARCHAR(2000) = NULL,
	@valorSalida NVARCHAR(500) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DECLARE @ACCION_INICIAR_ALERTA_ID INT = 1000150
	DECLARE @ACCION_AUTORIZAR_ALERTA_ID INT = 1000157
	DECLARE @ACCION_REVISION_ALERTA_ID INT = 1000158
	DECLARE @ACCION_RECHAZAR_ALERTA_ID INT = 1000159
	DECLARE @ACCION_CANCELAR_ALERTA_ID INT = 1000160
			
	DECLARE @ESTATUS_ALERTA_EN_PROCESO_ID  INT = 1000120
	DECLARE @ESTATUS_ALERTA_RECHAZADA_ID   INT = 1000121
	DECLARE @ESTATUS_ALERTA_EN_REVISION_ID INT = 1000122
	DECLARE @ESTATUS_ALERTA_FINALIZADA_ID  INT = 1000123
	DECLARE @ESTATUS_ALERTA_CANCELADA_ID   INT = 1000124

	DECLARE @TIPO_NOTIFICACION_AUTORIZACION_ID INT = 1000041
	DECLARE @TIPO_NOTIFICACION_NOTIFICACION_ID INT = 1000042
	
	BEGIN TRY
		BEGIN TRANSACTION

			DECLARE @alertaTempId BIGINT
			DECLARE @estatusActualAlertaId INT
			DECLARE @empleadoId INT

			DECLARE @actualizarEstatus BIT
			DECLARE @nombreTabla NVARCHAR(100)
			DECLARE @nombreCampoId NVARCHAR(50)
			DECLARE @nombreCampoEstatus NVARCHAR(50)
			DECLARE @valorEstatus INT 
			DECLARE @updateQuery NVARCHAR(MAX)
			
			-- Accion Iniciar Alerta
			IF (@accionId = @ACCION_INICIAR_ALERTA_ID)
			BEGIN 
				-- Validamos que:
				-- 1.- No se haya iniciado ya una alerta para el mismo registro
				DECLARE @contadorAlertas INT = 
				(SELECT COUNT(*) 
				 FROM tblAlerta 
				 WHERE ReferenciaProcesoId = @referenciaProcesoId AND 
					   AlertaDefinicionId = @alertaDefinicionId AND
					   EstatusId != @ESTATUS_ALERTA_EN_REVISION_ID
				)

				--Si ya se habia iniciado la alerta, lanzar error
				IF (@contadorAlertas > 0)
					THROW 51000, 'No es posible Iniciar la Alerta. Ya existe un proceso de Alerta Iniciado.', 1;

				-- 2.- Que se haya configurado a alguien para notificar

				DECLARE @tblEmpleadosANotificar TABLE (EmpleadoId INT,
													   figuraId INT,
													   TipoNotificacionId INT, 
													   MedioCorreoElectronico BIT,
													   medioPlataforma BIT
													  )
				INSERT INTO @tblEmpleadosANotificar
				SELECT empleadoId,
					   figuraId,
					   tipoNotificacionId,
					   medioCorreoElectronico,
					   medioPlataforma 
				FROM fn_getEmpleadosANotificarAlertas(@alertaDefinicionId, @referenciaProcesoId)

				DECLARE @contadorEmpleadosANotificar INT = (SELECT COUNT(*) FROM @tblEmpleadosANotificar)

				--Si no hay ningun empleado configurado para notificar, lanzar error
				IF(@contadorEmpleadosANotificar = 0)
					THROW 51001, 'No es posible Iniciar la Alerta. No existen Empleados configurados a los que notificar.', 1;

				--Si hay alguno configurado, validar
				ELSE
				BEGIN

					--2.1 Que las figuras que aparecen en la configuracion, si se haya podido ligar a un Empleado, es decir, si en la configuracion
					--de la alerta aparece una figura Jefe Inmediato, verificamos que se haya podido encontrar el Empleado que figura como 
					--Jefe Inmediato para la autorizacion o notiicacion.
					DECLARE @contadorFigurasSinEmpleado INT = (SELECT COUNT(*) FROM @tblEmpleadosANotificar WHERE empleadoId IS NULL AND figuraId IS NOT NULL) 
					IF(@contadorFigurasSinEmpleado > 0)
						THROW 51002, 'No es posible Iniciar la Alerta. No se encontró Jefe Inmediato al que Notificar, favor de revisar la Configuración de alertas.', 1;

					--2.2 Que el empleado configurado sea para el tipo correcto de notificacion de alerta, es decir, que si es una alerta de Tipo Autorizacion
					--exista algun empleado configurado para Autorizar o si el tipo de alerta es de tipo Notificacion haya algun empleado configurado para 
					--recibir la notificacion.

					DECLARE @permiteAutorizacion BIT, @permiteNotificacion BIT 
					SELECT @permiteAutorizacion = PermiteAutorizacion, @permiteNotificacion = PermiteNotificacion 
					FROM  tblAlertaDefinicion 
					INNER JOIN tblAlertaEtapaAccion ON tblAlertaDefinicion.AlertaEtapaAccionId = tblAlertaEtapaAccion.AlertaEtapaAccionId
					WHERE AlertaDefinicionId = @alertaDefinicionId

					--Si permiteAutorizacion = true, quiere decir, que el tipo de Alerta es Tipo Autorizacion, por lo que en la tabla temporal @tblEmpleadosNotificacion
					--debe existir algun empleado configurado para Autorizar la alerta
					IF(@permiteAutorizacion = 1)
					BEGIN
						DECLARE @empleadosParaAutorizar INT = (SELECT COUNT(*) FROM @tblEmpleadosANotificar WHERE TipoNotificacionId = @TIPO_NOTIFICACION_AUTORIZACION_ID)
						IF(@empleadosParaAutorizar = 0)
							THROW 51003, 'No es posible Iniciar la Alerta. No existen Empleados configurados para Autorizar.', 1;
					END

					--Si @permiteAutorizacion = 0 AND @permiteNotificacion = 1, quiere decir, que el tip de Alerta es de Tipo Notificacion, por lo que en la tabla 
					-- temporal @tblEmpleadosNotificacion debe existir algun empleado configurado para Recibir la alerta
					ELSE IF(@permiteAutorizacion = 0 AND @permiteNotificacion = 1)
					BEGIN
						DECLARE @empleadosParaNotificar INT = (SELECT COUNT(*) FROM @tblEmpleadosANotificar WHERE TipoNotificacionId = @TIPO_NOTIFICACION_NOTIFICACION_ID)
						IF(@empleadosParaNotificar = 0)
							THROW 51004, 'No es posible Iniciar la Alerta. No existen Empleados configurados para Notificar.', 1;
					END

					--2.3 Que los empleados configurados para recibir las notificaciones esten ACTIVOS
					DECLARE @contadorEmpleadosNoActivos INT = (
						SELECT COUNT(*) 
						FROM @tblEmpleadosANotificar tblEmpleadosANotificar
						INNER JOIN tblEmpleado ON tblEmpleadosANotificar.EmpleadoId = tblEmpleado.EmpleadoId
						WHERE tblEmpleado.EstatusId <> 1000000
					)

					IF(@contadorEmpleadosNoActivos > 0)
						THROW 51005, 'No es posible Iniciar la Alerta. Existen Empleados configurados para recibir Alertas que no estan ACTIVOS.', 1;
						
					--Si pasó todas las validaciones, generamos la Alerta y notificamos a todos los empleados configurados
					INSERT INTO tblAlerta (AlertaDefinicionId, ReferenciaProcesoId, CodigoTramite, TextoRepresentativo, EstatusId, CreadoPorId, FechaCreacion)
					VALUES(@alertaDefinicionId, @referenciaProcesoId, @codigoTramite, @textoRepresentativo, @ESTATUS_ALERTA_EN_PROCESO_ID, @creadoPorId, GETDATE())

					--Recuperamos el Id de la Alerta que se genero
					SET @alertaId = (SELECT SCOPE_IDENTITY())
					SELECT @alertaId

					--Creamos los registros en Alertas Autorizaciones con los empleados que esten configurados para Autorizar
					INSERT INTO tblAlertaAutorizacion (AlertaId, EmpleadoId, EstatusId, MotivoRechazo, FechaCreacion, FechaAtendido, Vista)
					SELECT DISTINCT @alertaId, EmpleadoId, @ESTATUS_ALERTA_EN_PROCESO_ID, NULL, GETDATE(), NULL, 0
					FROM @tblEmpleadosANotificar 
					WHERE TipoNotificacionId = @TIPO_NOTIFICACION_AUTORIZACION_ID AND
						  medioPlataforma = 1

					--Creamos los registros en Alertas Notificaciones con los empleados que esten configurados para ser Notificados
					INSERT INTO tblAlertaNotificacion (AlertaId, EmpleadoId, FechaCreacion, FechaAtendido, Vista)
					SELECT DISTINCT @alertaId, EmpleadoId, GETDATE(), NULL, 0
					FROM @tblEmpleadosANotificar 
					WHERE TipoNotificacionId = @TIPO_NOTIFICACION_NOTIFICACION_ID AND
						  medioPlataforma = 1

					--Si la configuracion de la alerta dice que debemos actualizar el estatus del tramite, actualizamos el estatus
					SELECT @actualizarEstatus = CambiarEstatusATramite, 
						   @nombreTabla = TablaReferencia, 
						   @nombreCampoId = CampoId,
						   @nombreCampoEstatus = CampoEstadoRegistro,
						   @valorEstatus = EstadoEnProceso
					FROM tblAlertaDefinicion 
					WHERE AlertaDefinicionId = @alertaDefinicionId

					--Si debemos actualizar el estatus del tramite
					IF(@actualizarEstatus = 1)
					BEGIN
						SET @updateQuery = ' UPDATE ' + @nombreTabla + 
										   ' SET ' + @nombreCampoEstatus + ' = ' + CAST(@valorEstatus AS NVARCHAR(100)) + 
										   ' WHERE ' + @nombreCampoId + ' = ' + CAST(@referenciaProcesoId AS NVARCHAR(100))
						EXEC(@updateQuery)
 					END
				END
			END 
			
			-- Accion Autorizar
			ELSE IF (@accionId = @ACCION_AUTORIZAR_ALERTA_ID)
			BEGIN

				SELECT @alertaTempId = AlertaId, @estatusActualAlertaId = EstatusId FROM tblAlerta WHERE AlertaId = @alertaId
				SELECT @empleadoId = EmpleadoId FROM tblUsuario WHERE UsuarioId = @creadoPorId

				--Verificamos que exista la Alerta a Autorizar
				IF(@alertaTempId IS NULL)
					THROW 51006, 'No es posible Autorizar el trámite. Alerta no encontrada.', 1;

				--Verificamos que la alerta que se pretende autorizar no haya cambiado ya su Estatus o que no se encuentre Vigente
				IF(@estatusActualAlertaId != @ESTATUS_ALERTA_EN_PROCESO_ID) 
					THROW 51007, 'No es posible Autorizar el trámite. La alerta ya fue atendida por otro Usuario.', 1;

				--Si pasó todas las validaciones, se cambia el estatus de la alerta tanto del detalle como de la cabecera
				UPDATE tblAlertaAutorizacion
				SET EstatusId = @ESTATUS_ALERTA_FINALIZADA_ID,
					FechaAtendido = GETDATE()
				WHERE AlertaId = @alertaId AND
					  EmpleadoId = @empleadoId

				UPDATE tblAlerta
				SET EstatusId = @ESTATUS_ALERTA_FINALIZADA_ID,
					FechaFinalizacion = GETDATE()
				WHERE AlertaId = @alertaId

				--Si la configuracion de la alerta dice que debemos actualizar el estatus del tramite, actualizamos el estatus
				SELECT @alertaDefinicionId = AlertaDefinicionId,
					   @referenciaProcesoId = ReferenciaProcesoId 
				FROM tblAlerta 
				WHERE AlertaId = @alertaId

				SELECT  @actualizarEstatus = CambiarEstatusATramite, 
						@nombreTabla = TablaReferencia, 
						@nombreCampoId = CampoId,
						@nombreCampoEstatus = CampoEstadoRegistro,
						@valorEstatus = EstadoAutorizado
				FROM tblAlertaDefinicion 
				WHERE AlertaDefinicionId = @alertaDefinicionId

				--Si debemos actualizar el estatus del tramite
				IF(@actualizarEstatus = 1)
				BEGIN
					SET @updateQuery = ' UPDATE ' + @nombreTabla + 
										' SET ' + @nombreCampoEstatus + ' = ' + CAST(@valorEstatus AS NVARCHAR(100)) + 
										' WHERE ' + @nombreCampoId + ' = ' + CAST(@referenciaProcesoId AS NVARCHAR(100))
					EXEC(@updateQuery)
					SELECT @updateQuery
 				END
			END

			-- Accion Revision Alerta
			ELSE IF (@accionId = @ACCION_REVISION_ALERTA_ID)
			BEGIN

				SELECT @alertaTempId = AlertaId, @estatusActualAlertaId = EstatusId FROM tblAlerta WHERE AlertaId = @alertaId
				SELECT @empleadoId = EmpleadoId FROM tblUsuario WHERE UsuarioId = @creadoPorId

				--Verificamos que exista la Alerta a Autorizar
				IF(@alertaTempId IS NULL)
					THROW 51006, 'No es posible enviar a Revisión el trámite. Alerta no encontrada.', 1;

				--Verificamos que la alerta que se pretende autorizar no haya cambiado ya su Estatus o que no se encuentre Vigente
				IF(@estatusActualAlertaId != @ESTATUS_ALERTA_EN_PROCESO_ID) 
					THROW 51007, 'No es posible enviar a Revisión el trámite. La alerta ya fue atendida por otro Usuario.', 1;

				--Si pasó todas las validaciones, se cambia el estatus de la alerta tanto del detalle como de la cabecera
				UPDATE tblAlertaAutorizacion
				SET EstatusId = @ESTATUS_ALERTA_EN_REVISION_ID,
					MotivoRechazo = @motivo,
					FechaAtendido = GETDATE()
				WHERE AlertaId = @alertaId AND
					  EmpleadoId = @empleadoId

				UPDATE tblAlerta
				SET EstatusId = @ESTATUS_ALERTA_EN_REVISION_ID,
					FechaFinalizacion = GETDATE()
				WHERE AlertaId = @alertaId

				--Si la configuracion de la alerta dice que debemos actualizar el estatus del tramite, actualizamos el estatus
				SELECT @alertaDefinicionId = AlertaDefinicionId,
					   @referenciaProcesoId = ReferenciaProcesoId 
				FROM tblAlerta 
				WHERE AlertaId = @alertaId

				SELECT  @actualizarEstatus = CambiarEstatusATramite, 
						@nombreTabla = TablaReferencia, 
						@nombreCampoId = CampoId,
						@nombreCampoEstatus = CampoEstadoRegistro,
						@valorEstatus = EstadoEnRevision
				FROM tblAlertaDefinicion 
				WHERE AlertaDefinicionId = @alertaDefinicionId

				--Si debemos actualizar el estatus del tramite
				IF(@actualizarEstatus = 1)
				BEGIN
					SET @updateQuery = ' UPDATE ' + @nombreTabla + 
										' SET ' + @nombreCampoEstatus + ' = ' + CAST(@valorEstatus AS NVARCHAR(100)) + 
										' WHERE ' + @nombreCampoId + ' = ' + CAST(@referenciaProcesoId AS NVARCHAR(100))
					EXEC(@updateQuery)
 				END
			END

			-- Accion Rechazar Alerta
			ELSE IF (@accionId = @ACCION_RECHAZAR_ALERTA_ID )
			BEGIN

				SELECT @alertaTempId = AlertaId, @estatusActualAlertaId = EstatusId FROM tblAlerta WHERE AlertaId = @alertaId
				SELECT @empleadoId = EmpleadoId FROM tblUsuario WHERE UsuarioId = @creadoPorId

				--Verificamos que exista la Alerta a Autorizar
				IF(@alertaTempId IS NULL)
					THROW 51006, 'No es posible Rechazar el trámite. Alerta no encontrada.', 1;

				--Verificamos que la alerta que se pretende autorizar no haya cambiado ya su Estatus o que no se encuentre Vigente
				IF(@estatusActualAlertaId != @ESTATUS_ALERTA_EN_PROCESO_ID) 
					THROW 51007, 'No es posible Rechar el trámite. La alerta ya fue atendida por otro Usuario.', 1;

				--Si pasó todas las validaciones, se cambia el estatus de la alerta tanto del detalle como de la cabecera
				UPDATE tblAlertaAutorizacion
				SET EstatusId = @ESTATUS_ALERTA_RECHAZADA_ID,
					MotivoRechazo = @motivo,
					FechaAtendido = GETDATE()
				WHERE AlertaId = @alertaId AND
					  EmpleadoId = @empleadoId

				UPDATE tblAlerta
				SET EstatusId = @ESTATUS_ALERTA_RECHAZADA_ID,
					FechaFinalizacion = GETDATE()
				WHERE AlertaId = @alertaId

				--Si la configuracion de la alerta dice que debemos actualizar el estatus del tramite, actualizamos el estatus
				SELECT @alertaDefinicionId = AlertaDefinicionId,
					   @referenciaProcesoId = ReferenciaProcesoId 
				FROM tblAlerta 
				WHERE AlertaId = @alertaId

				SELECT  @actualizarEstatus = CambiarEstatusATramite, 
						@nombreTabla = TablaReferencia, 
						@nombreCampoId = CampoId,
						@nombreCampoEstatus = CampoEstadoRegistro,
						@valorEstatus = EstadoRechazado
				FROM tblAlertaDefinicion 
				WHERE AlertaDefinicionId = @alertaDefinicionId

				--Si debemos actualizar el estatus del tramite
				IF(@actualizarEstatus = 1)
				BEGIN
					SET @updateQuery = ' UPDATE ' + @nombreTabla + 
										' SET ' + @nombreCampoEstatus + ' = ' + CAST(@valorEstatus AS NVARCHAR(100)) + 
										' WHERE ' + @nombreCampoId + ' = ' + CAST(@referenciaProcesoId AS NVARCHAR(100))
					EXEC(@updateQuery)
 				END
			END

		COMMIT 
	END TRY
	BEGIN CATCH
		ROLLBACK;
		THROW;
	END CATCH
END
