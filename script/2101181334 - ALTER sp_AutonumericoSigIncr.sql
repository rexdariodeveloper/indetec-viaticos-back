SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_AutonumericoSigIncr] @nombre VARCHAR(500), @ejercicio INT, @valorSalida VARCHAR(500) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	SET @ejercicio = CASE WHEN @ejercicio = -1 OR @ejercicio = 0 THEN NULL ELSE @ejercicio END	

	IF ( @ejercicio IS NOT NULL )
	BEGIN
			DECLARE @existe BIT = 0
			
			SELECT @existe = CASE WHEN AutonumericoId IS NOT NULL THEN 1 ELSE 0 END FROM tblAutonumerico WHERE Nombre = @nombre AND Ejercicio = @ejercicio AND Activo = 1

			IF ( @existe = 0  )
			BEGIN
				DECLARE @prefijo NVARCHAR(10)
				DECLARE @ceros INT
				DECLARE @nodoMenuId INT

				SELECT @prefijo = Prefijo, @ceros = Ceros, @nodoMenuId = NodoMenuId FROM tblAutonumerico WHERE Nombre = @nombre AND Activo = 1

				INSERT INTO tblAutonumerico ( Nombre, Prefijo, Siguiente, Ceros, NodoMenuId, Ejercicio, Activo, FechaUltimaModificacion )
				VALUES ( @nombre, ISNULL(@prefijo, ''), 1, ISNULL(@ceros, 6), @nodoMenuId, @ejercicio, 1, GETDATE() )
			END
	END 

	SELECT @valorSalida = Prefijo+ISNULL(CAST(Ejercicio AS VARCHAR(4)), '')+RIGHT('00000000000000000000'+ISNULL(CAST(Siguiente AS VARCHAR(20)), ''), Ceros)
	FROM tblAutonumerico
	WHERE Nombre = @nombre
			AND ISNULL(Ejercicio, 0) = ISNULL(@ejercicio, 0)
			AND Activo = 1

	UPDATE tblAutonumerico SET Siguiente = Siguiente + 1 WHERE Nombre = @nombre AND ISNULL(Ejercicio, 0) = ISNULL(@ejercicio, 0)
END