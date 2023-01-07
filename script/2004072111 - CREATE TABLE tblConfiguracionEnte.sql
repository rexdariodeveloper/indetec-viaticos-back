SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblConfiguracionEnte](
	[ConfiguracionEnteId] [tinyint] IDENTITY(1,1) NOT NULL,
	[NombreEnte] [nvarchar](500) NOT NULL,
	[CorreoElectronico] [nvarchar](50) NULL,
	[PaginaWEB] [nvarchar](500) NULL,
	[Domicilio] [nvarchar](100) NULL,
	[Numero] [nvarchar](10) NULL,
	[Colonia] [nvarchar](50) NULL,
	[PaisId] [int] NULL,
	[EstadoId] [int] NULL,
	[CiudadId] [int] NULL,
	[Telefono] [nvarchar](50) NULL,
	[MonedaPredeterminadaId] [int] NULL,
	[PorcentajeSinComprobante] [int] NULL,
	[MontoAnualSinComprobante] [int] NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblConfiguracionEnte] PRIMARY KEY CLUSTERED 
(
	[ConfiguracionEnteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] ADD  CONSTRAINT [DF_tblConfiguracionEnte_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblCiudad] FOREIGN KEY([CiudadId])
REFERENCES [dbo].[tblCiudad] ([CiudadId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblCiudad]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblEstado] FOREIGN KEY([EstadoId])
REFERENCES [dbo].[tblEstado] ([EstadoId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblEstado]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblMoneda] FOREIGN KEY([MonedaPredeterminadaId])
REFERENCES [dbo].[tblMoneda] ([MonedaId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblMoneda]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblPais] FOREIGN KEY([PaisId])
REFERENCES [dbo].[tblPais] ([PaisId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblPais]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblUsuario]
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblUsuario1]
GO


