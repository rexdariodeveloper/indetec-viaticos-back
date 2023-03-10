SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =========================================================
-- Author:		Javier Elías
-- Create date: 24/06/2020
-- Modified date: 09/07/2020
-- Description:	Funcion para obtener los permisos de alertas de usuario
-- =========================================================
ALTER FUNCTION [dbo].[fn_getPermisoAutorizacionAlerta]( @menuId INT, @solicitudId INT, @usuarioId INT )
RETURNS 
@tbl TABLE 
(
	AlertaId BIGINT, 
	Permiso BIT
)
AS
BEGIN

		DECLARE @EN_PROCESO INT = 1000120
		DECLARE @RECHAZADA INT = 1000121
		DECLARE @EN_REVISION INT = 1000122
		DECLARE @FINALIZADA INT = 1000123
		DECLARE @CANCELADA INT = 1000124

		DECLARE @permiso INT = NULL

		DECLARE @empleados TABLE ( empleadoId INT )
		INSERT INTO @empleados
		SELECT EmpleadoId
		FROM tblUsuario
			 INNER JOIN fn_getUsuariosTempANotificarAlertas(@usuarioId) AS usuarios ON tblUsuario.UsuarioId = usuarios.usuarioId
		
		INSERT INTO @tbl
		SELECT DISTINCT alerta.AlertaId, 1
		FROM tblAlerta AS alerta
				INNER JOIN tblAlertaDefinicion AS definicion ON alerta.AlertaDefinicionId = definicion.AlertaDefinicionId AND definicion.RutaAccionNodoMenuId = @menuId
				INNER JOIN tblAlertaEtapaAccion AS etapa ON definicion.AlertaEtapaAccionId = etapa.AlertaEtapaAccionId AND etapa.PermiteAutorizacion = 1
				INNER JOIN tblAlertaAutorizacion AS autorizacion ON alerta.AlertaId = autorizacion.AlertaId AND autorizacion.EmpleadoId IN ( SELECT empleadoId FROM @empleados )
		WHERE alerta.ReferenciaProcesoId = @solicitudId
				AND alerta.EstatusId = @EN_PROCESO

		SET @permiso = ( SELECT COUNT(*) FROM @tbl )

		IF ( @permiso IS NULL OR @permiso = 0 )
		BEGIN
				INSERT INTO @tbl
				SELECT NULL, CASE WHEN COUNT(*) = 0 THEN NULL ELSE 0 END
				FROM tblAlerta AS alerta
						INNER JOIN tblAlertaDefinicion AS definicion ON alerta.AlertaDefinicionId = definicion.AlertaDefinicionId AND definicion.RutaAccionNodoMenuId = @menuId
						INNER JOIN tblAlertaEtapaAccion AS etapa ON definicion.AlertaEtapaAccionId = etapa.AlertaEtapaAccionId AND etapa.PermiteNotificacion = 1
						INNER JOIN tblAlertaNotificacion AS notificacion ON alerta.AlertaId = notificacion.AlertaId AND notificacion.EmpleadoId IN ( SELECT empleadoId FROM @empleados )
				WHERE alerta.ReferenciaProcesoId = @solicitudId
						AND alerta.EstatusId != @CANCELADA
		END

		RETURN
END