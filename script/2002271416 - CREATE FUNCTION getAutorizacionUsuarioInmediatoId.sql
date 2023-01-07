SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[getAutorizacionUsuarioInmediatoId](@areaAdscripcionId INT)
RETURNS INT
AS
	BEGIN
		DECLARE @permiteAutorizacion BIT = 0
		DECLARE @nodoId INT = ( SELECT NodoPadreId FROM tblOrganigrama WHERE NodoId = @areaAdscripcionId )
		DECLARE @responsableId INT

		WHILE(@permiteAutorizacion = 0 AND @nodoId IS NOT NULL)
		BEGIN
				SELECT @permiteAutorizacion = PermiteAutorizacion,
					   @responsableId = CASE WHEN PermiteAutorizacion = 1 THEN ResponsableId ELSE NULL END,
					   @nodoId = NodoPadreId
				FROM tblOrganigrama
				WHERE NodoId = @nodoId
		END

		RETURN @responsableId
	END
GO