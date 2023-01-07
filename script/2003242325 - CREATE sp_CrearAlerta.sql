SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_IniciarAlerta] 
	@accionId int,
	@alertaDefinicionId int = null,
	@referenciaProcesoId int = null,
	@codigoTramite nvarchar(50) = null,
	@textoRepresentativo nvarchar(255) = null,
	@creadoPorId INT = NULL,
	@valorSalida NVARCHAR(500) OUTPUT 
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	EXEC sp_ProcesadorDeAlertas @accionId = @accionId
							   ,@alertaDefinicionId = @alertaDefinicionId
							   ,@referenciaProcesoId = @referenciaProcesoId
							   ,@codigoTramite = @codigoTramite
							   ,@textoRepresentativo = @textoRepresentativo
							   ,@creadoPorId = @creadoPorId
							   ,@valorSalida = @valorSalida
END
