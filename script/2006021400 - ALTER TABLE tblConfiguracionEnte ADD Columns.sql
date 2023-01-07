-- =============================================
-- Author: Rene Carrillo
-- Create date: 02/06/20
-- Description:	Agregar 4 campos tblConfiguracionEnte 
-- =============================================

ALTER TABLE [dbo].[tblConfiguracionEnte] ADD 
	ServidorFTP [NVARCHAR](500) NULL,
	UsuarioFTP [NVARCHAR](50) NULL,
	ContraseniaFTP [NVARCHAR](MAX) NULL,
	PuertoFTP INT NULL
GO