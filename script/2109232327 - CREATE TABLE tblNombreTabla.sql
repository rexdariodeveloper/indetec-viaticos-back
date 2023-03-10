SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblNombreTabla](
	[NombreTabla] [varchar](50) NOT NULL,
	[CampoBorrado] [varchar](100) NOT NULL,
	[IDEstatusBorrado] [int] NOT NULL
) ON [PRIMARY]
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblCiudad', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblConceptoViatico', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblEmpleado', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblEstado', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblGrupoJerarquico', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblGrupoJerarquicoPuesto', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblListadoCargo', N'Activo', 0)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblListadoCMM', N'Activo', 0)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblListadoPuesto', N'Activo', 0)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblMoneda', N'Activo', 0)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblOrganigrama', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblPais', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblRol', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblRolMenu', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViatico', N'EstatusId', 1000101)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoAsignacion', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoAsignacionPasaje', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoAsignacionViatico', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacion', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacionCargo', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacionDetalle', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacionDetalleImpuesto', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacionDetalleValidacion', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoComprobacionPasaje', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoInforme', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblSolicitudViaticoRevision', N'EstatusId', 1000002)
GO
INSERT [dbo].[tblNombreTabla] ([NombreTabla], [CampoBorrado], [IDEstatusBorrado]) VALUES (N'tblUsuario', N'EstatusId', 1000002)
GO
