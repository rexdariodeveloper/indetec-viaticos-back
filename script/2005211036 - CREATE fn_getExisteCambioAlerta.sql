SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[fn_getExisteCambioAlerta](@id INT, @empleadoOrigenId INT, @fechaInicio DATETIME, @fechaFin DATETIME)
RETURNS INT
AS
	BEGIN
		DECLARE @alertaId INT =
		(
			SELECT TOP 1 AlertaCambioUsuarioTmpId
			FROM tblAlertaCambioUsuarioTmp
			WHERE AlertaCambioUsuarioTmpId != ISNULL(@id, -1)
				  AND Borrado = 0
				  AND EmpleadoOrigenId = @empleadoOrigenId
				  AND (@fechaInicio BETWEEN FechaInicio AND FechaFin
					   OR @fechaFin BETWEEN FechaInicio AND FechaFin
					   OR FechaInicio BETWEEN @fechaInicio AND @fechaFin
					   OR FechaFin BETWEEN @fechaInicio AND @fechaFin)
		)

		RETURN @alertaId
	END
