SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblSolicitudViaticoRevision](
	[SolicitudViaticoRevisionId] [BIGINT] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoId] [INT] NOT NULL,
	[EstatusId] [INT] NOT NULL,
	[FechaCreacion] [DATETIME] NOT NULL,
	[CreadoPorId] [INT] NOT NULL,
	[FechaUltimaModificacion] [DATETIME] NULL,
	[ModificadoPorId] [INT] NULL,
	[Timestamp] [TIMESTAMP] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoRevision] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoRevisionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision] ADD  CONSTRAINT [DF_tblSolicitudViaticoRevision_FechaCreacion]  DEFAULT (GETDATE()) FOR [FechaCreacion]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoRevision_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision] CHECK CONSTRAINT [FK_tblSolicitudViaticoRevision_tblListadoCMM]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoRevision_tblSolicitudViatico] FOREIGN KEY([SolicitudViaticoId])
REFERENCES [dbo].[tblSolicitudViatico] ([SolicitudViaticoId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision] CHECK CONSTRAINT [FK_tblSolicitudViaticoRevision_tblSolicitudViatico]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoRevision_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision] CHECK CONSTRAINT [FK_tblSolicitudViaticoRevision_tblUsuario]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoRevision_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoRevision] CHECK CONSTRAINT [FK_tblSolicitudViaticoRevision_tblUsuario1]
GO


