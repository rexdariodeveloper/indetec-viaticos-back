SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[getNombreCompletoEmpleado](@empleadoId INT)
RETURNS NVARCHAR(200)
AS
	BEGIN
		DECLARE @nombreCompleto NVARCHAR(200) = ( SELECT Nombre + ' ' + PrimerApellido + ISNULL(' ' + SegundoApellido, '') FROM tblEmpleado WHERE EmpleadoId = @empleadoId )

		RETURN @nombreCompleto
	END
