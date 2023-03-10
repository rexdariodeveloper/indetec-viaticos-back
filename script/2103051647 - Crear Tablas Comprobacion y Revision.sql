

drop table tblSolicitudViaticoRevision
drop table tblSolicitudViaticoComprobacionCargo
drop table tblSolicitudViaticoComprobacionPasaje
drop table tblSolicitudViaticoComprobacionDetalleValidacion
drop table tblSolicitudViaticoComprobacionDetalleImpuesto
drop table tblSolicitudViaticoComprobacionDetalle
drop table tblSolicitudViaticoInforme
drop table tblSolicitudViaticoComprobacion

GO

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
	[FechaSolicitanteFinalizoComprobacion] [datetime] NULL,
	[FechaRMFinalizoComprobacion] [datetime] NULL,
	[ComisionNoRealizada] [bit] NULL,
	[MotivoNoRealizada] [nvarchar](500) NULL,
	[PolizaComprobacionId] [nvarchar](6) NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacion_1] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionCargo]    Script Date: 05/03/2021 04:46:21 p. m. ******/
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
/****** Object:  Table [dbo].[TblSolicitudViaticoComprobacionDetalle]    Script Date: 05/03/2021 04:46:21 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle](
	[SolicitudViaticoComprobacionDetalleId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionId] [bigint] NOT NULL,
	[CategoriaId] [int] NOT NULL,
	[ConceptoViaticoId] [int] NULL,
	[TipoComprobanteId] [int] NULL,
	[RFC] [nvarchar](50) NULL,
	[RazonSocial] [nvarchar](100) NULL,
	[ProveedorId] [int] NULL,
	[ProveedorPaisId] [nvarchar](2) NULL,
	[FechaComprobante] [datetime] NULL,
	[Folio] [nvarchar](50) NULL,
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
	[TotalFactura] [money] NULL,
	[SubTotalFactura] [money] NULL,
	[DescuentoFactura] [money] NULL,
	[NumeroPartida] [int] NULL,
	[ConceptoDescripcion] [nvarchar](1000) NULL,
	[ClaveProdServ] [nvarchar](8) NULL,
	[ConceptoImporte] [money] NULL,
	[ConceptoDescuento] [money] NULL,
	[SubTotalComprobacion] [money] NULL,
	[DescuentoComprobacion] [money] NULL,
	[CuentaPagoGastoId] [int] NULL,
	[FormaComprobacionId] [int] NULL,
	[UUID] [nvarchar](36) NULL,
 CONSTRAINT [PK_TblSolicitudViaticoComprobacionDetalle2] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionDetalleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto]    Script Date: 05/03/2021 04:46:21 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto](
	[SolicitudViaticoCompDetImpuestoId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionDetalleId] [bigint] NOT NULL,
	[TipoImpuestoId] [int] NULL,
	[ImpuestoId] [int] NOT NULL,
	[ImpuestoImporte] [money] NOT NULL,
	[TasaOCuota] [decimal](18, 6) NOT NULL,
	[ImpuestoComprobado] [money] NOT NULL,
	[EstatusId] [int] NOT NULL,
	[FechaRegistro] [datetime] NOT NULL,
	[RegistradoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacionDetalleImpuesto2] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoCompDetImpuestoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]    Script Date: 05/03/2021 04:46:21 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion](
	[SolicitudViaticoCompDetalleValidacionId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoComprobacionDetalleId] [bigint] NULL,
	[TextoValidacion] [nvarchar](max) NOT NULL,
	[EstatusId] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
	[SolicitudViaticoComprobacionId] [bigint] NULL,
 CONSTRAINT [PK_SolicitudViaticoCompDetalleValidacion] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoCompDetalleValidacionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoComprobacionPasaje]    Script Date: 05/03/2021 04:46:21 p. m. ******/
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
	[BoletoUtilizadoIda] [bit] NULL,
	[BoletoUtilizadoRegreso] [bit] NULL,
 CONSTRAINT [PK_tblSolicitudViaticoComprobacionPasaje] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoComprobacionPasajeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSolicitudViaticoInforme]    Script Date: 05/03/2021 04:46:21 p. m. ******/
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
/****** Object:  Table [dbo].[tblSolicitudViaticoRevision]    Script Date: 05/03/2021 04:46:21 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSolicitudViaticoRevision](
	[SolicitudViaticoRevisionId] [bigint] IDENTITY(1,1) NOT NULL,
	[SolicitudViaticoId] [int] NOT NULL,
	[EstatusId] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblSolicitudViaticoRevision] PRIMARY KEY CLUSTERED 
(
	[SolicitudViaticoRevisionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionCargo] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacionCargo_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[TblSolicitudViaticoComprobacionDetalle] ADD  CONSTRAINT [DF_TblSolicitudViaticoComprobacionDetalle2_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleImpuesto] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacionDetalleImpuesto2_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] ADD  CONSTRAINT [DF_SolicitudViaticoCompDetalleValidacion_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] ADD  CONSTRAINT [DF_tblSolicitudViaticoComprobacionPasaje_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD  CONSTRAINT [DF_tblSolicitudViaticoInforme_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoRevision] ADD  CONSTRAINT [DF_tblSolicitudViaticoRevision_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
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
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] CHECK CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacion]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacionDetalle] FOREIGN KEY([SolicitudViaticoComprobacionDetalleId])
REFERENCES [dbo].[TblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
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
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblListadoCMM] FOREIGN KEY([EstatusId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] CHECK CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionDetalleId])
REFERENCES [dbo].[TblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
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
