SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Javier Elías
-- Create date: 22/05/20
-- Description:	Función para comprobar si ya existe una
--						solicitud elaborada para un empleado
-- =============================================
CREATE FUNCTION [dbo].[fn_getExisteSolicitud](@id INT, @empleadoId INT, @fechaSalida DATETIME, @fechaRegreso DATETIME)
RETURNS NVARCHAR(12)
AS
	BEGIN
		DECLARE @ACTIVA INT = 1000100
		DECLARE @BORRADA INT = 1000101
		DECLARE @CANCELADA INT = 1000102
		DECLARE @AUTORIZADA INT = 1000103
		DECLARE @EN_REVISION INT = 1000104
		DECLARE @RECHAZADA INT = 1000105
		DECLARE @EN_PROCESO_AUTORIZACION INT = 1000106
		DECLARE @EN_REVISION_COMPROBACION INT = 1000107
		DECLARE @FINALIZADA INT = 1000108
		DECLARE @RECURSOS_ASIGNADOS INT = 1000161

		DECLARE @solicitud NVARCHAR(12) =
		(
			SELECT TOP 1 NumeroSolicitud
			FROM tblSolicitudViatico 
			WHERE SolicitudViaticoId != ISNULL(@id, -1)
				  AND EstatusId NOT IN (@BORRADA, @CANCELADA, @RECHAZADA) 
				  AND EmpleadoId = @empleadoId
				  AND (@fechaSalida BETWEEN FechaSalida AND FechaRegreso
					   OR @fechaRegreso BETWEEN FechaSalida AND FechaRegreso
					   OR FechaSalida BETWEEN @fechaSalida AND @fechaRegreso
					   OR FechaRegreso BETWEEN @fechaSalida AND @fechaRegreso)
		)

		RETURN @solicitud
	END
