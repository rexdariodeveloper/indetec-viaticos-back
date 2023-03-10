SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Description:	SP para ocultar alertas del usuario
-- =============================================
ALTER PROCEDURE [dbo].[sp_OcultarAlertas] 
	@accionId int,
	@creadoPorId INT,
	@alertasId NVARCHAR(MAX),
	@valorSalida NVARCHAR(MAX) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	EXEC sp_ProcesadorDeAlertas @accionId = @accionId,
							    @creadoPorId = @creadoPorId,
							    @alertasId = @alertasId,
							    @valorSalida = @valorSalida OUTPUT
END
