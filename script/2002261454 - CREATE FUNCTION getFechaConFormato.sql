SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[getFechaConFormato](@date DATETIME, @mostrarHoras BIT)
RETURNS VARCHAR(30)
AS
	BEGIN
		DECLARE @fecha NVARCHAR(30)
		SELECT @fecha = FORMAT(@date, 'MMMM dd, yyyy ', 'es-es')+CASE WHEN @mostrarHoras = 1 THEN LEFT(RIGHT(CONVERT(VARCHAR, @date, 22), 11), 5) + RIGHT(RIGHT(CONVERT(VARCHAR, @date, 22), 11), 3) ELSE '' END
		SET @fecha = UPPER(SUBSTRING(@fecha, 1, 1)) + SUBSTRING(@fecha, 2, LEN(@fecha))
		RETURN REPLACE(@fecha, '  ', ' ')
	END
GO