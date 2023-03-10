
-- Drop Tables
DROP TABLE tblSolicitudViaticoComprobacionCargo
DROP TABLE tblSolicitudViaticoComprobacionPasaje
DROP TABLE tblSolicitudViaticoInforme
DROP TABLE tblSolicitudViaticoComprobacion
GO
--

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacion](
	[SolicitudViaticoComprobacionId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoId] [int] NOT NULL,
	[SolicitanteFinalizoComprobacion] [bit] NULL,
	[RMFinalizoComprobacion] [bit] NULL,
	[EstatusId] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacion_1] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionCargo]    Script Date: 23/03/2020 07:09:45 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionCargo](
	[SolicitudViaticoComprobacionCargoId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionPasajeId] [bigint] NOT NULL,
	[FechaCargoSalida] [datetime] NOT NULL,
	[FechaCargoRegreso] [datetime] NULL,
	[MontoCargoSalida] [money] NOT NULL,
	[MontoCargoRegreso] [money] NULL,
	[SolicitudCambio] [nvarchar](max) NULL,
	[EstatusId] [int] NOT NULL,
	[FechaRegistro] [datetime] NOT NULL,
	[RegistradoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacionCargo] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionCargoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionDetalle]    Script Date: 23/03/2020 07:09:45 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle](
	[SolicitudViaticoComprobacionDetalleId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionId] [bigint] NOT NULL,
	[CategoriaId] [int] NOT NULL,
	[ConceptoViaticoId] [int] NULL,
	[TipoPasajeId] [int] NULL,
	[TipoComprobanteId] [int] NULL,
	[FechaComprobante] [datetime] NULL,
	[Folio] [nvarchar](50) NULL,
	[RFC] [nvarchar](50) NULL,
	[RazonSocial] [nvarchar](100) NULL,
	[FormaPagoId] [int] NULL,
	[MonedaId] [int] NULL,
	[TipoCambio] [money] NULL,
	[Importe] [money] NULL,
	[ImportePesos] [money] NOT NULL,
	[EsComprobadoPorRM] [bit] NOT NULL,
	[AsignacionViaticoId] [bigint] NULL,
	[AsignacionPasajeId] [bigint] NULL,
	[Comentarios] [nvarchar](max) NULL,
	[EstatusId] [int] NOT NULL,
	[FechaRegistro] [datetime] NOT NULL,
	[RegistradoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacion] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionDetalleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionPasaje]    Script Date: 23/03/2020 07:09:45 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje](
	[SolicitudViaticoComprobacionPasajeId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionDetalleId] [bigint] NOT NULL,
	[FechaCompra] [date] NOT NULL,
	[NombreLinea] [nvarchar](50) NOT NULL,
	[ViajeRedondo] [bit] NOT NULL,
	[FechaSalida] [datetime] NULL,
	[FechaRegreso] [datetime] NULL,
	[NumeroBoletoIda] [nvarchar](50) NULL,
	[NumeroBoletoRegreso] [nvarchar](50) NULL,
	[CodigoReservacion] [nvarchar](50) NULL,
	[Comentarios] [nvarchar](max) NULL,
	[EstatusId] [int] NOT NULL,
	[FechaRegistro] [datetime] NOT NULL,
	[RegistradoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacionPasaje] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionPasajeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoInforme]    Script Date: 23/03/2020 07:09:45 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoInforme](
	[SolicitudViaticoInformeId] [int] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionId] [bigint] NOT NULL,
	[ObjetivoEstrategico] [nvarchar](max) NULL,
	[ObjetivoEspecifico] [nvarchar](max) NULL,
	[ActividadesRealizadas] [nvarchar](max) NULL,
	[ResultadosObtenidos] [nvarchar](max) NULL,
	[Contribuciones] [nvarchar](max) NULL,
	[VinculosANotas] [nvarchar](max) NULL,
	[ListadoDocumentos] [nvarchar](max) NULL,
	[Conclusiones] [nvarchar](max) NULL,
	[EstatusId] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoInforme] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoInformeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacionCargo_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacionPasaje_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD  CONSTRAINT [DF_tblSolicitudViaticoInforme_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM4] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM4]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViatico] FOREIGN KEY([SolicitudViaticoId])
REFERENCES [dbo].[tblSolicitudViatico] ([SolicitudViaticoId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViatico]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario2] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario2]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario3] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario3]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblSolicitudViaticoComprobacionPasaje] FOREIGN KEY([SolicitudViaticoComprobacionPasajeId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacionPasaje] ([SolicitudViaticoComprobacionPasajeId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblSolicitudViaticoComprobacionPasaje]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblUsuario] FOREIGN KEY([RegistradoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblUsuario]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionCargo_tblUsuario1]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblConceptoViatico] FOREIGN KEY([ConceptoViaticoId])
REFERENCES [dbo].[tblConceptoViatico] ([ConceptoViatico])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblConceptoViatico]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM] FOREIGN KEY([CategoriaId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM1] FOREIGN KEY([TipoComprobanteId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM1]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM2] FOREIGN KEY([FormaPagoId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM2]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM3] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM3]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionPasaje] FOREIGN KEY([AsignacionPasajeId])
REFERENCES [dbo].[tblSolicitudViaticoAsignacionPasaje] ([AsignacionPasajeId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionPasaje]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionViatico] FOREIGN KEY([AsignacionViaticoId])
REFERENCES [dbo].[tblSolicitudViaticoAsignacionViatico] ([AsignacionViaticoId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionViatico]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario] FOREIGN KEY([RegistradoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario1]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalle_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalle_tblSolicitudViaticoComprobacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionDetalleId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblSolicitudViaticoComprobacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblUsuario] FOREIGN KEY([RegistradoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblUsuario]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblUsuario1]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoInforme_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] CHECK CONSTRAINT [FK_tblSolicitudViaticoInforme_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoInforme_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] CHECK CONSTRAINT [FK_tblSolicitudViaticoInforme_tblSolicitudViaticoComprobacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] CHECK CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] CHECK CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario1]
GO
