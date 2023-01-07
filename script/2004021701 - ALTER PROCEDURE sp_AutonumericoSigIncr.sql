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

	 SELECT @valorSalida = Prefijo+ISNULL(CAST(Ejercicio AS VARCHAR(4)), '')+RIGHT('00000000000000000000'+ISNULL(CAST(Siguiente AS VARCHAR(20)), ''), Ceros)
     FROM tblAutonumerico
     WHERE Nombre = @nombre
		AND ISNULL(Ejercicio, 0) = ISNULL(@ejercicio, 0)

     UPDATE tblAutonumerico SET Siguiente = Siguiente + 1 WHERE Nombre = @nombre AND ISNULL(Ejercicio, 0) = ISNULL(@ejercicio, 0)
END