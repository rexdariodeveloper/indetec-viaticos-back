SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Javier Elías
-- Create date: 25/11/2020
-- Description:	Función para obtener el Asunto y Cuerpo
--						necesarios para enviar un correo
-- =============================================
CREATE FUNCTION [dbo].[fn_getDatosEnviarCorreo] ( @alertaId INT )
RETURNS @tblDatos TABLE(Correos NVARCHAR(4000), Asunto NVARCHAR(4000), Cuerpo NVARCHAR(4000))
AS
BEGIN

	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID INT = 1
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_CANCELACION_ID INT = 2
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZADA_ID INT = 3
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_RECHAZADA_ID INT = 4
	DECLARE @ALERTA_DEFINICION_SOLICITUD_VIATICOS_REVISION_ID INT = 5
	DECLARE @ALERTA_DEFINICION_ASIGNACION_VIATICOS_ID INT = 6
	DECLARE @ALERTA_DEFINICION_ASIGNACION_VIATICOS_REVISION_ID INT = 7
	DECLARE @ALERTA_DEFINICION_INFORME_COMPROBACION_FINALIZAR_ID INT = 8
	DECLARE @ALERTA_DEFINICION_INFORME_COMPROBACION_REVISION_ID INT = 13
	DECLARE @ALERTA_DEFINICION_REVISION_FINALIZADA_ID INT = 9
	DECLARE @ALERTA_DEFINICION_REVISION_REVISION_VALIDACION_ID INT = 10
	DECLARE @ALERTA_DEFINICION_REVISION_VALIDACION_AUTORIZACION_ID INT = 11
	DECLARE @ALERTA_DEFINICION_REVISION_VALIDACION_REVISION_ID INT = 12

	DECLARE @ENVIADA_A_FINANZAS INT = 1000098
	DECLARE @RECURSOS_ASIGNADOS INT = 1000161

	DECLARE @formatoFecha INT = 103
	DECLARE @correos NVARCHAR(4000)
	DECLARE @asunto NVARCHAR(2000)
	DECLARE @cuerpo NVARCHAR(4000)

	DECLARE @numeroSolicitud NVARCHAR(100)
	DECLARE @oficio NVARCHAR(200)
	DECLARE @fechaSolicitud NVARCHAR(200)
	DECLARE @empleadoId INT
	DECLARE @empleado NVARCHAR(200)
	DECLARE @puesto NVARCHAR(200)
	DECLARE @area NVARCHAR(200)
	DECLARE @cuentaContable NVARCHAR(100)
	DECLARE @origen NVARCHAR(200)
	DECLARE @destino NVARCHAR(200)
	DECLARE @fechaSalida NVARCHAR(200)
	DECLARE @fechaRegreso NVARCHAR(200)
	DECLARE @fechaInicioEvento NVARCHAR(200)
	DECLARE @fechaFinEvento NVARCHAR(200)
	DECLARE @motivo NVARCHAR (4000)
	DECLARE @atendidaPor NVARCHAR(400)
	DECLARE @motivoRechazo NVARCHAR (4000)
	DECLARE @creadoPorId INT

	DECLARE @solicitudId INT
	DECLARE @alertaDefinicionId INT
	DECLARE @estatusSolicitud INT

	SELECT @solicitudId = ReferenciaProcesoId, 
		   @alertaDefinicionId = AlertaDefinicionId, 
		   @estatusSolicitud = solicitud.EstatusId,
		   @empleadoId = solicitud.EmpleadoId,
		   @creadoPorId = creado.EmpleadoId
	FROM tblAlerta AS alerta
		 INNER JOIN tblSolicitudViatico AS solicitud ON alerta.ReferenciaProcesoId = solicitud.SolicitudViaticoId
		 INNER JOIN tblUsuario AS creado ON solicitud.CreadoPorId = creado.UsuarioId
	WHERE AlertaId = @alertaId

	SELECT @numeroSolicitud = NumeroSolicitud, 
		   @oficio = '<strong>' + NoOficio + '</strong>', 
		   @fechaSolicitud = '<strong>' + CONVERT(VARCHAR, solicitud.FechaCreacion, @formatoFecha) + '</strong>', 
		   @empleado = '<strong>' + dbo.getNombreCompletoEmpleado(solicitud.EmpleadoId) + '</strong>', 
		   @puesto = '<strong>' + puesto.Nombre + '</strong>', 
		   @area = '<strong>' + area.Descripcion + '</strong>', 
		   @cuentaContable = '<strong>' + Cuenta + '</strong>',
		   @destino = '<strong>' + dbo.getNombreCompletoCiudad(SolicitudViaticoId, 1) + '</strong>',
		   @motivo = '<strong>' + Motivo + '</strong>',
		   @fechaSalida = '<strong>' + CONVERT(VARCHAR, FechaSalida, @formatoFecha) + '</strong>',
		   @fechaRegreso = '<strong>' + CONVERT(VARCHAR, FechaRegreso, @formatoFecha) + '</strong>',
		   @fechaInicioEvento = '<strong>' + CONVERT(VARCHAR, FechaInicioEvento, @formatoFecha) + '</strong>',
		   @fechaFinEvento = '<strong>' + CONVERT(VARCHAR, FechaFinEvento, @formatoFecha) + '</strong>',
		   @atendidaPor = '<strong>' + dbo.getNombreCompletoEmpleado(alerta.CreadoPorId) + '</strong>',
		   @motivoRechazo = '<strong>' + CASE WHEN CHARINDEX('Motivo', TextoRepresentativo) > 0 THEN SUBSTRING(TextoRepresentativo, CHARINDEX('Motivo', TextoRepresentativo)+8, LEN(TextoRepresentativo)) ELSE '' END + '</strong>'
	FROM tblSolicitudViatico AS solicitud
			INNER JOIN tblEmpleado AS empleado ON solicitud.EmpleadoId = empleado.EmpleadoId
			INNER JOIN tblListadoPuesto AS puesto ON empleado.PuestoId = puesto.PuestoId
			INNER JOIN tblOrganigrama AS area ON empleado.AreaAdscripcionId = NodoId
			INNER JOIN tblAlerta AS alerta ON SolicitudViaticoId = ReferenciaProcesoId AND AlertaId = @alertaId
	WHERE SolicitudViaticoId = @solicitudId

	SET @asunto = 'Comisión de trabajo ' + @numeroSolicitud

	IF (@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZACION_ID)
	BEGIN
			SET @cuerpo = 'Con fecha ' + @fechaSolicitud + ', se ha recibido una solicitud de viáticos de ' + @empleado + ' con destino a la ciudad de ' + @destino + '.<br>' 
									+ 'Ingrese a la plataforma para autorizar/cancelar la solicitud.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_CANCELACION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue cancelada por el usuario: ' + @atendidaPor
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_AUTORIZADA_ID)
	BEGIN
			SET @cuerpo = 'Sírvase otorgar viáticos a ' + @empleado + ' para traslados en la Ciudad de ' + @destino + ', ya que asistirá y participará en ' + @motivo + ' que se llevará a cabo del ' + @fechaInicioEvento + ', al ' + @fechaFinEvento + ' del año en curso.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_RECHAZADA_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue rechazada por el usuario: ' + @atendidaPor + '<br>'
						+ 'Causa: ' + @motivoRechazo
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_SOLICITUD_VIATICOS_REVISION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue enviada a revisión por el usuario: ' + @atendidaPor + '<br>'
						+ 'Causa: ' + @motivoRechazo
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_ASIGNACION_VIATICOS_ID)
	BEGIN
			SET @asunto = 'Depósito de Viáticos: ' + @numeroSolicitud

			SET @cuerpo = '<h3 style="text-align:center;">NOTIFICACIÓN DE COMISIÓN</h3>'
						+ @empleado + '<br>'
						+ @puesto + '<br>'
						+ @area + '<br><br>'
						+ '<strong>P R E S E N T E</strong><br><br>'
						+ 'Me permito informarle que por acuerdo de la Dirección General, ha sido designado para atender la comisión que se detalla a continuación:<br><br>'
						+ '•	Comisión: <strong>' + @numeroSolicitud + '</strong><br>'
						+ '•	No. Acuerdo / Oficio del solicitante: ' + @oficio + '<br>'
						+ '•	Fecha de la comisión:  Del ' + @fechaSalida + ', al ' + @fechaRegreso + '<br>'
						+ '•	Destino de la comisión: ' + @destino + '<br>'
						+ '•	Comisión: ' + @motivo + '<br><br>'
						+ 'Sírvase ponerse en contacto con su superior inmediato, a fin de recabar mayor información.'

			IF ( @estatusSolicitud = @ENVIADA_A_FINANZAS )
			BEGIN
					DECLARE @tbl TABLE (Id INT, Partida NVARCHAR(4000), Concepto NVARCHAR(4000), Monto DECIMAL(28, 2))

					INSERT INTO @tbl
					SELECT *
					FROM 
					(
						SELECT SolicitudViaticoId, 
							   ObjetoGastoId AS Partida, 
							   Concepto, 
							   Costo AS Monto
						FROM tblSolicitudViaticoAsignacion AS asignacion
							 INNER JOIN tblSolicitudViaticoAsignacionPasaje AS pasaje ON asignacion.AsignacionId = pasaje.AsignacionId AND pasaje.EstatusId = 1000000 AND AsignadoAFuncionario = 1
							 INNER JOIN tblConceptoViatico AS concepto ON pasaje.ConceptoViaticoId = concepto.ConceptoViaticoId
						WHERE SolicitudViaticoId = @solicitudId

						UNION ALL

						SELECT SolicitudViaticoId, 
							   ObjetoGastoId AS Partida, 
							   Concepto, 
							   MontoPorTransferir AS Monto
						FROM tblSolicitudViaticoAsignacion AS asignacion
							 INNER JOIN tblSolicitudViaticoAsignacionViatico AS viaticos ON asignacion.AsignacionId = viaticos.AsignacionId AND viaticos.EstatusId = 1000000 AND MontoPorTransferir > 0
							 INNER JOIN tblConceptoViatico AS concepto ON viaticos.ConceptoViaticoId = concepto.ConceptoViaticoId
						WHERE SolicitudViaticoId = @solicitudId
					) AS todo

					DECLARE @total NVARCHAR(4000) = '<strong>' + '$ ' + CONVERT(VARCHAR, CAST((SELECT SUM(Monto) FROM @tbl) AS MONEY), 1) + '</strong>'

					DECLARE @tablaHTML NVARCHAR(4000)

					SET @tablaHTML = CAST (
						(
							SELECT td = Partida + '</td><td>' + Concepto + '</td><td style="text-align:right;">' + '$ ' + CONVERT(VARCHAR, CAST(Monto AS MONEY), 1)
							FROM (
									SELECT Partida, Concepto, Monto FROM @tbl
							) AS d
							FOR XML PATH('tr'), TYPE
						) AS NVARCHAR(4000)
					)
		
					SET @tablaHTML = '<table cellpadding="2" cellspacing="2" border="1">' 
						  + '<tr><th>Partida</th><th>Concepto</th><th>Monto</th></tr>' 
						  + REPLACE(REPLACE(@tablaHTML, '&lt;', '<'), '&gt;', '>') 
						  + '<tr><td></td><td style="text-align:right;"><strong>Total</strong></td><td style="text-align:right;">' + @total + '</td></tr>'
						  + '</table>' 
		
					DECLARE @cuerpo2 NVARCHAR(4000)
					DECLARE @correos2 NVARCHAR(4000)

					SET @cuerpo2 = 'Sírvase otorgar viáticos a ' + @empleado + ' para cumplir con la comisión de trabajo encomendada en la Ciudad de ' + @destino + ', ya que asistirá y participará en ' + @motivo + ' que se llevará a cabo del ' + @fechaInicioEvento + ', al ' + @fechaFinEvento + ' del año en curso.<br><br>'
										+ 'La cuenta contable del funcionario es: ' + @cuentaContable + '<br>'
										+ 'Monto asignado: ' + @total + '<br><br>'
										+ 'Detalle de asignación:<br><br>'
										+ @tablaHTML

					SET @correos2 = STUFF(
					(
						SELECT ','+email
						FROM
						(
							SELECT ISNULL(EmailInstitucional, EmailPersonal) AS email
							FROM fn_getEmpleadosANotificarAlertas(@alertaDefinicionId, @solicitudId) AS notificacion
									INNER JOIN tblEmpleado AS empleado ON notificacion.empleadoId = empleado.EmpleadoId
							WHERE notificacion.medioCorreoElectronico = 1
									AND 1 = CASE WHEN notificacion.empleadoId NOT IN (@empleadoId, @creadoPorId) THEN 1 ELSE 0 END
						) AS correos
						ORDER BY email FOR XML PATH('')
					), 1, 1, '')

					INSERT INTO @tblDatos
					SELECT @correos2, @asunto, @cuerpo2
			END
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_ASIGNACION_VIATICOS_REVISION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue enviada a revisión por el usuario: ' + @atendidaPor + '<br>'
						+ 'Causa: ' + @motivoRechazo
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_INFORME_COMPROBACION_FINALIZAR_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue finalizada en la comprobación por el usuario: ' + @atendidaPor + ', está pendiente de enviar a revisión.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_INFORME_COMPROBACION_REVISION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue finalizada en la comprobación por el usuario: ' + @atendidaPor + ', y fue enviada a revisión.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_REVISION_FINALIZADA_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue revisada en la comprobación por el usuario: ' + @atendidaPor + ', y se encuentra en proceso de publicación.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_REVISION_REVISION_VALIDACION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue revisada en la comprobación por el usuario: ' + @atendidaPor + ', y se encuentra en proceso de que se autorice un gasto.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_REVISION_VALIDACION_AUTORIZACION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue revisada en la comprobación por el usuario: ' + @atendidaPor + ', y se autoriza el gasto extraordinario.'
	END

	ELSE IF (@alertaDefinicionId = @ALERTA_DEFINICION_REVISION_VALIDACION_REVISION_ID)
	BEGIN
			SET @cuerpo = 'La solicitud fue revisada en la comprobación por el usuario: ' + @atendidaPor + ', y se solicita la revisión del gasto.'
	END

	SELECT @correos = STUFF(
	(
		SELECT ','+email
		FROM
		(
			SELECT ISNULL(EmailInstitucional, EmailPersonal) AS email
			FROM fn_getEmpleadosANotificarAlertas(@alertaDefinicionId, @solicitudId) AS notificacion
					INNER JOIN tblEmpleado AS empleado ON notificacion.empleadoId = empleado.EmpleadoId
			WHERE notificacion.medioCorreoElectronico = 1
					AND 1 = CASE WHEN @estatusSolicitud IN (@RECURSOS_ASIGNADOS, @ENVIADA_A_FINANZAS) THEN CASE WHEN notificacion.empleadoId IN (@empleadoId, @creadoPorId) THEN 1 ELSE 0 END ELSE 1 END
		) AS correos
		ORDER BY email FOR XML PATH('')
	), 1, 1, '')

	INSERT INTO @tblDatos
	SELECT @correos, @asunto, @cuerpo

RETURN
END