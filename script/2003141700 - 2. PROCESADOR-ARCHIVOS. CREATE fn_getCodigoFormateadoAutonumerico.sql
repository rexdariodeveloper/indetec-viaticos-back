SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author: Alonso Soto
-- Created: 15/05/2019
-- Description:	Funcion que Retorna una Cadena rellenando con 0's a la izquierda segun el numero de ceros que maneje el Control Maestro de Autonumerico de Archivos
-- Example: SELECT dbo.fn_getCodigoFormateadoAutonumerico('10886','CM_AN_SiguienteNumeroPuesto')
-- =============================================
CREATE FUNCTION [dbo].[fn_getCodigoFormateadoAutonumerico](@codigo NVARCHAR(MAX))
RETURNS NVARCHAR(MAX)
AS
BEGIN

	DECLARE @codigoFormateado NVARCHAR(MAX)
	SET @codigoFormateado = ''

	DECLARE @contador INT
	SET @contador = 0

	DECLARE @cantidadCeros INT
	SET @cantidadCeros = (SELECT CAST(Valor AS INT) FROM tblListadoCMM WHERE Nombre = 'CantidadCerosAutonumericoArchivo')

	DECLARE @cantidadCerosRestantes INT
	SET @cantidadCerosRestantes = @cantidadCeros - LEN(@codigo)

	WHILE @contador < @cantidadCerosRestantes
	BEGIN
		SET @codigoFormateado = @codigoFormateado + '0'
		SET @contador = @contador + 1
	END

	SET @codigoFormateado = @codigoFormateado + @codigo

	-- Return the result of the function
	RETURN @codigoFormateado

END

