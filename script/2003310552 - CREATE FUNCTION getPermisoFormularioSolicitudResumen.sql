SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[getPermisoFormularioSolicitudResumen](@solicitudId INT, @empleadoId INT)
RETURNS INT
AS
	BEGIN
		DECLARE @EN_PROCESO INT = 1000120
		
		DECLARE @permiso INT

		SELECT TOP 1 @permiso = CASE WHEN autorizacion.EmpleadoId IS NOT NULL THEN 1 ELSE CASE WHEN notificacion.EmpleadoId IS NOT NULL THEN 0 ELSE NULL END END
		FROM tblAlerta AS alerta
			 LEFT JOIN tblAlertaAutorizacion AS autorizacion ON alerta.AlertaId = autorizacion.AlertaId AND autorizacion.EmpleadoId = @empleadoId
			 LEFT JOIN tblAlertaNotificacion AS notificacion ON alerta.AlertaId = notificacion.AlertaId AND notificacion.EmpleadoId = @empleadoId
		WHERE ReferenciaProcesoId = @solicitudId
			  AND alerta.EstatusId = @EN_PROCESO
		ORDER BY alerta.FechaCreacion DESC 

		-- Retornará:
		-- 1 en caso de tener permiso de autorización
		-- 0 en caso de tener permiso de notificiación
		-- NULL en caso de no tener ningún permiso
		RETURN @permiso
	END
