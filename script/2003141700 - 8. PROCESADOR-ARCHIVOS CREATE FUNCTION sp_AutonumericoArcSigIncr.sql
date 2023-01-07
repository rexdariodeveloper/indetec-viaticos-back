SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE PROCEDURE [dbo].[sp_AutonumericoArcSigIncr] @valor VARCHAR(500)  OUTPUT
AS
	UPDATE tblListadoCMM
	SET Valor = CAST((CAST(Valor AS bigint) + 1) AS nvarchar(max))
	WHERE Nombre = 'IdAutonumericoArchivo'

	SELECT @valor = [dbo].[fn_getCodigoFormateadoAutonumerico] ([dbo].[fn_ConvertToBase] (CAST(Valor AS bigint),32))
	FROM tblListadoCMM
	WHERE Nombre = 'IdAutonumericoArchivo'
