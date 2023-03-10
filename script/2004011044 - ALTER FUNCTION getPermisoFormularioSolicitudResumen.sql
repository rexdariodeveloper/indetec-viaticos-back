SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

DROP FUNCTION IF EXISTS [dbo].[getPermisoFormularioSolicitudResumen]
GO

CREATE FUNCTION [dbo].[getPermisoFormularioSolicitudResumen](@solicitudId INT, @empleadoId INT)
RETURNS @tbl TABLE
(
	AlertaId INT,
	Permiso INT
)
AS
     BEGIN

		DECLARE @EN_PROCESO INT = 1000120
		
		INSERT INTO @tbl
		SELECT TOP 1 alerta.AlertaId, CASE WHEN autorizacion.EmpleadoId IS NOT NULL AND alerta.EstatusId = @EN_PROCESO THEN 1 ELSE CASE WHEN notificacion.EmpleadoId IS NOT NULL THEN 0 ELSE NULL END END
		FROM tblAlerta AS alerta
			 LEFT JOIN tblAlertaAutorizacion AS autorizacion ON alerta.AlertaId = autorizacion.AlertaId AND autorizacion.EmpleadoId = @empleadoId
			 LEFT JOIN tblAlertaNotificacion AS notificacion ON alerta.AlertaId = notificacion.AlertaId AND notificacion.EmpleadoId = @empleadoId
		WHERE ReferenciaProcesoId = @solicitudId
			  --AND alerta.EstatusId = @EN_PROCESO
		ORDER BY alerta.FechaCreacion DESC 

		-- Retornará como permiso:
		-- 1 en caso de tener permiso de autorización
		-- 0 en caso de tener permiso de notificiación
		-- NULL en caso de no tener ningún permiso
		RETURN
	END
