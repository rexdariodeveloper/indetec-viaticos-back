SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
DROP FUNCTION IF EXISTS [dbo].[fn_getUsuariosTempANotificarAlertas]
GO

CREATE FUNCTION [dbo].[fn_getUsuariosTempANotificarAlertas] ( @usuarioId INT )
RETURNS @tbl TABLE ( usuarioId INT )
AS
BEGIN
	
	INSERT INTO @tbl
	SELECT origen.UsuarioId
	FROM tblAlertaCambioUsuarioTmp
			INNER JOIN tblUsuario AS origen ON EmpleadoOrigenId = origen.EmpleadoId
			INNER JOIN tblUsuario AS destino ON EmpleadoDestinoId = destino.EmpleadoId AND destino.UsuarioId = @usuarioId
	WHERE Borrado = 0
		AND GETDATE() BETWEEN FechaInicio AND FechaFin

	DECLARE @usuarioTemp INT = (
		SELECT UsuarioId
		FROM tblAlertaCambioUsuarioTmp
			 INNER JOIN tblUsuario ON EmpleadoOrigenId = EmpleadoId AND UsuarioId = @usuarioId
		WHERE Borrado = 0
			AND GETDATE() BETWEEN FechaInicio AND FechaFin
	)

	IF (@usuarioTemp IS NULL)
	BEGIN
		INSERT INTO @tbl
		SELECT @usuarioId
	END

	RETURN
END
