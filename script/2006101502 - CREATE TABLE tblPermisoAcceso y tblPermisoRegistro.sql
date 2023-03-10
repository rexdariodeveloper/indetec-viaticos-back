SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPermisoAcceso](
	[PermisoAccesoId] [bigint] IDENTITY(1,1) NOT NULL,
	[TipoPermisoId] [int] NOT NULL,
	[UsuarioId] [int] NOT NULL,
	[Borrado] [bit] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblPermisoAcceso] PRIMARY KEY CLUSTERED 
(
	[PermisoAccesoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPermisoRegistro]    Script Date: 08/06/2020 01:06:49 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPermisoRegistro](
	[PermisoRegistroId] [bigint] IDENTITY(1,1) NOT NULL,
	[PermisoAccesoId] [bigint] NOT NULL,
	[RegistroId] [bigint] NOT NULL,
	[Borrado] [bit] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblPermisoRegistro] PRIMARY KEY CLUSTERED 
(
	[PermisoRegistroId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblPermisoAcceso] ADD  CONSTRAINT [DF_tblPermisoAcceso_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblPermisoRegistro] ADD  CONSTRAINT [DF_tblPermisoRegistro_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[tblPermisoAcceso]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoAcceso_tblListadoCMM] FOREIGN KEY([TipoPermisoId])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblPermisoAcceso] CHECK CONSTRAINT [FK_tblPermisoAcceso_tblListadoCMM]
GO
ALTER TABLE [dbo].[tblPermisoAcceso]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoAcceso_tblUsuario] FOREIGN KEY([UsuarioId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblPermisoAcceso] CHECK CONSTRAINT [FK_tblPermisoAcceso_tblUsuario]
GO
ALTER TABLE [dbo].[tblPermisoAcceso]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoAcceso_tblUsuario1] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblPermisoAcceso] CHECK CONSTRAINT [FK_tblPermisoAcceso_tblUsuario1]
GO
ALTER TABLE [dbo].[tblPermisoAcceso]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoAcceso_tblUsuario2] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblPermisoAcceso] CHECK CONSTRAINT [FK_tblPermisoAcceso_tblUsuario2]
GO
ALTER TABLE [dbo].[tblPermisoRegistro]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoRegistro_tblPermisoAcceso] FOREIGN KEY([PermisoAccesoId])
REFERENCES [dbo].[tblPermisoAcceso] ([PermisoAccesoId])
GO
ALTER TABLE [dbo].[tblPermisoRegistro] CHECK CONSTRAINT [FK_tblPermisoRegistro_tblPermisoAcceso]
GO
ALTER TABLE [dbo].[tblPermisoRegistro]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoRegistro_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblPermisoRegistro] CHECK CONSTRAINT [FK_tblPermisoRegistro_tblUsuario]
GO
ALTER TABLE [dbo].[tblPermisoRegistro]  WITH CHECK ADD  CONSTRAINT [FK_tblPermisoRegistro_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblPermisoRegistro] CHECK CONSTRAINT [FK_tblPermisoRegistro_tblUsuario1]
GO
