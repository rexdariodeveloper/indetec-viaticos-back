SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================
-- Author:		Javier Elías
-- Create date: 31/03/2020
-- Modified date: 12/06/2020
-- Description:	Función para obtener el Permiso 
--						para el Resumen de una Solicitud
-- ==========================================
ALTER FUNCTION [dbo].[getPermisoFormularioSolicitudResumen](@solicitudId INT, @usuarioId INT)
RETURNS @tbl TABLE
(
	AlertaId INT,
	Permiso INT
)
AS
     BEGIN

		DECLARE @EN_PROCESO INT = 1000120

		DECLARE @empleados TABLE ( empleadoId INT )

		INSERT INTO @empleados
		SELECT EmpleadoId
		FROM tblUsuario
			 INNER JOIN fn_getUsuariosTempANotificarAlertas(@usuarioId) AS usuarios ON tblUsuario.UsuarioId = usuarios.usuarioId
		
		INSERT INTO @tbl
		SELECT alerta.AlertaId, CASE WHEN autorizacion.EmpleadoId IS NOT NULL AND alerta.EstatusId = @EN_PROCESO THEN 1 ELSE CASE WHEN notificacion.EmpleadoId IS NOT NULL THEN 0 ELSE NULL END END
		FROM tblAlerta AS alerta
			 LEFT JOIN tblAlertaAutorizacion AS autorizacion ON alerta.AlertaId = autorizacion.AlertaId AND autorizacion.EmpleadoId IN ( SELECT empleadoId FROM @empleados )
			 LEFT JOIN tblAlertaNotificacion AS notificacion ON alerta.AlertaId = notificacion.AlertaId AND notificacion.EmpleadoId IN ( SELECT empleadoId FROM @empleados )
		WHERE ReferenciaProcesoId = @solicitudId
			  --AND alerta.EstatusId = @EN_PROCESO
		ORDER BY alerta.FechaCreacion DESC 

		-- Retornará como permiso:
		-- 1 en caso de tener permiso de autorización
		-- 0 en caso de tener permiso de notificiación
		-- NULL en caso de no tener ningún permiso
		RETURN
	END
