SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_CancelarAlertas] 
	@accionId INT,
	@alertaDefinicionId INT,
	@referenciaProcesoId INT,
	@valorSalida NVARCHAR(500) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	EXEC sp_ProcesadorDeAlertas @accionId = @accionId,
							    @alertaDefinicionId = @alertaDefinicionId,
							    @referenciaProcesoId = @referenciaProcesoId,
							    @valorSalida = @valorSalida
END
