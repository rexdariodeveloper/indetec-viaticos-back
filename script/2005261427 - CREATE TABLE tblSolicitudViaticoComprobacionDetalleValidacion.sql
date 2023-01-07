SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion](
	[SolicitudViaticoCompDetalleValidacionId] [BIGINT] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionDetalleId] [BIGINT] NOT NULL,
	[TextoValidacion] [NVARCHAR](MAX) NOT NULL,
	[EstatusId] [INT] NOT NULL,
	[FechaCreacion] [DATETIME] NOT NULL,
	[CreadoPorId] [INT] NOT NULL,
	[FechaUltimaModificacion] [DATETIME] NULL,
	[ModificadoPorId] [INT] NULL,
	[Timestamp] [TIMESTAMP] NOT NULL,
 CONSTRAINT [PK_SolicitudViaticoCompDetalleValidacion] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoCompDetalleValidacionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] ADD  CONSTRAINT [DF_SolicitudViaticoCompDetalleValidacion_FechaCreacion]  DEFAULT (GETDATE()) FOR [FechaCreacion]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblListadoCMM]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacionDetalle] FOREIGN KEY([SolicitudViaticoComprobacionDetalleId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacionDetalle]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblUsuario]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblUsuario1]
GO


