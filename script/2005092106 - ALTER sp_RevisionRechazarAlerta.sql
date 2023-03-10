SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Alonso Soto
-- Create date: <Create Date,,>
-- Description:	SP para Rechazar/Revision una alerta
-- =============================================
ALTER PROCEDURE [dbo].[sp_RevisionRechazarAlerta] 
	@accionId int,
	@creadoPorId INT,
	@alertaId BIGINT,
	@motivo NVARCHAR(2000),
	@valorSalida NVARCHAR(MAX) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	EXEC sp_ProcesadorDeAlertas @accionId = @accionId,
							    @creadoPorId = @creadoPorId,
							    @alertaId = @alertaId,
								@motivo = @motivo,
							    @valorSalida = @valorSalida OUTPUT
END
