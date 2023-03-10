SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Description:	SP para autorizar una Alerta
-- =============================================
ALTER PROCEDURE [dbo].[sp_AutorizarAlerta] 
	@accionId int,
	@creadoPorId INT,
	@alertaId BIGINT,
	@valorSalida NVARCHAR(MAX) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	EXEC sp_ProcesadorDeAlertas @accionId = @accionId,
							    @creadoPorId = @creadoPorId,
							    @alertaId = @alertaId,
							    @valorSalida = @valorSalida OUTPUT
END
