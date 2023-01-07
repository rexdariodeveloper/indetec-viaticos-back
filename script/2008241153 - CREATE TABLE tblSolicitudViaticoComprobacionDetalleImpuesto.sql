SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto](
	[SolicitudViaticoCompDetImpuestoId] [bigint] NOT NULL,
	[SolicitudViaticoComprobacionDetalleId] [bigint] NOT NULL,
	[ImporteImpuesto] [money] NOT NULL,
	[TasaOCuota] [decimal](18, 6) NOT NULL,
	[TipoFactorId] [int] NOT NULL,
	[ImpuestoId] [int] NOT NULL,
	[Importe] [money] NOT NULL,
	[EstatusId] [int] NOT NULL,
	[FechaRegistro] [datetime] NOT NULL,
	[RegistradoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoCompDetImpuesto] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoCompDetImpuestoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] ADD  CONSTRAINT [DF_tblSolicitudViaticoCompDetImpuesto_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM] FOREIGN KEY([TipoFactorId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM1] FOREIGN KEY([ImpuestoId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM1]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM2] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblListadoCMM2]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblSolicitudViaticoComprobacionDetalle] FOREIGN KEY([SolicitudViaticoComprobacionDetalleId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblSolicitudViaticoComprobacionDetalle]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblUsuario] FOREIGN KEY([RegistradoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblUsuario]
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalleImpuesto_tblUsuario1]
GO


