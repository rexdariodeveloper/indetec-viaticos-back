DROP TABLE IF EXISTS [tblPasajeUtilizado]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblPasajeUtilizado](
	[PasajeUtilizadoId] [int] IDENTITY(1,1) NOT NULL,
	[AsignacionPasajeId] [BIGINT] NOT NULL,
	[FechaCreacion] [DATETIME] NOT NULL,
	[CreadoPorId] [INT] NOT NULL,
	[Timestamp] [TIMESTAMP] NOT NULL,
 CONSTRAINT [PK_PasajeUtilizado] PRIMARY KEY CLUSTERED 
(
	[PasajeUtilizadoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblPasajeUtilizado] ADD  CONSTRAINT [DF_PasajeUtilizado_FechaCreacion]  DEFAULT (GETDATE()) FOR [FechaCreacion]
GO

ALTER TABLE [dbo].[tblPasajeUtilizado]  WITH CHECK ADD  CONSTRAINT [FK_tblPasajeUtilizado_tblSolicitudViaticoAsignacionPasaje] FOREIGN KEY([AsignacionPasajeId])
REFERENCES [dbo].[tblSolicitudViaticoAsignacionPasaje] ([AsignacionPasajeId])
GO

ALTER TABLE [dbo].[tblPasajeUtilizado] CHECK CONSTRAINT [FK_tblPasajeUtilizado_tblSolicitudViaticoAsignacionPasaje]
GO

ALTER TABLE [dbo].[tblPasajeUtilizado]  WITH CHECK ADD  CONSTRAINT [FK_tblPasajeUtilizado_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblPasajeUtilizado] CHECK CONSTRAINT [FK_tblPasajeUtilizado_tblUsuario]
GO


