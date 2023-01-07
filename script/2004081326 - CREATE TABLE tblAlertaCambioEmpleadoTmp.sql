SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblAlertaCambioUsuarioTmp](
	[AlertaCambioUsuarioTmpId] [int] IDENTITY(1,1) NOT NULL,
	[Folio] [nvarchar](15) NOT NULL,
	[EmpleadoOrigenId] [int] NOT NULL,
	[EmpleadoDestinoId] [int] NOT NULL,
	[FechaInicio] [datetime] NOT NULL,
	[FechaFin] [datetime] NOT NULL,
	[Borrado] [bit] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[CreadoPorId] [int] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
 CONSTRAINT [PK_tblAlertaCambioUsuarioTmp] PRIMARY KEY CLUSTERED 
(
	[AlertaCambioUsuarioTmpId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp] ADD  CONSTRAINT [DF_tblAlertaCambioUsuarioTmp_FechaCreacion]  DEFAULT (getdate()) FOR [FechaCreacion]
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp]  WITH CHECK ADD  CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblEmpleado] FOREIGN KEY([EmpleadoOrigenId])
REFERENCES [dbo].[tblEmpleado] ([EmpleadoId])
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp] CHECK CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblEmpleado]
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp]  WITH CHECK ADD  CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblEmpleado1] FOREIGN KEY([EmpleadoDestinoId])
REFERENCES [dbo].[tblEmpleado] ([EmpleadoId])
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp] CHECK CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblEmpleado1]
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp]  WITH CHECK ADD  CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblUsuario] FOREIGN KEY([CreadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp] CHECK CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblUsuario]
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp]  WITH CHECK ADD  CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblUsuario1] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblAlertaCambioUsuarioTmp] CHECK CONSTRAINT [FK_tblAlertaCambioUsuarioTmp_tblUsuario1]
GO


