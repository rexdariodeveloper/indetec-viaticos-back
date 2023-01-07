SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[getNombreCompletoCiudad](@solicitudId INT, @tipoDestino BIT)
RETURNS VARCHAR(200)
AS
BEGIN	
	DECLARE @ciudad NVARCHAR(200) = (	
		SELECT ciudad.Nombre+', '+estado.Nombre+', '+pais.Nombre
		FROM tblSolicitudViatico AS solicitud
			 INNER JOIN tblPais AS pais ON CASE WHEN @tipoDestino = 0 THEN PaisOrigenId ELSE PaisDestinoId END = PaisId
			 INNER JOIN tblEstado AS estado ON CASE WHEN @tipoDestino = 0 THEN EstadoOrigenId ELSE EstadoDestinoId END = EstadoId
			 INNER JOIN tblCiudad AS ciudad ON CASE WHEN @tipoDestino = 0 THEN CiudadOrigenId ELSE CiudadDestinoId END = CiudadId
		WHERE solicitud.SolicitudViaticoId = @solicitudId
	)

	RETURN @ciudad
END