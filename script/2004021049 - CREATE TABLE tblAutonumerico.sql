SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

DROP TABLE IF EXISTS [tblAutonumerico]
GO

CREATE TABLE [dbo].[tblAutonumerico](
	[AutonumericoId] [tinyint] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](50) NOT NULL,
	[Prefijo] [nvarchar](4) NULL,
	[Siguiente] [bigint] NOT NULL,
	[Ceros] [int] NOT NULL,
	[NodoMenuId] [int] NULL,
	[Ejercicio] [int] NULL,
	[Activo] [bit] NOT NULL,
	[FechaUltimaModificacion] [datetime] NULL,
	[ModificadoPorId] [int] NULL,
	[Timestamp] [timestamp] NOT NULL,
CONSTRAINT [PK_Autonumericos] PRIMARY KEY NONCLUSTERED 
(
	[AutonumericoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Nombre_Ejercicio] UNIQUE NONCLUSTERED 
(
	[Nombre] ASC,
	[Ejercicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblAutonumerico] ADD  CONSTRAINT [DF_Siguiente]  DEFAULT (1) FOR [Siguiente]
GO

ALTER TABLE [dbo].[tblAutonumerico] ADD  CONSTRAINT [DF_Activo]  DEFAULT (1) FOR [Activo]
GO

ALTER TABLE [dbo].[tblAutonumerico] ADD  CONSTRAINT [DF_Autonumericos_Ceros]  DEFAULT (0) FOR [Ceros]
GO

ALTER TABLE [dbo].[tblAutonumerico] ADD  CONSTRAINT [DF_Autonumericos_FechaUltimaModificacion]  DEFAULT (GETDATE()) FOR [FechaUltimaModificacion]
GO

ALTER TABLE [dbo].[tblAutonumerico]  WITH NOCHECK ADD  CONSTRAINT [FK_Autonumericos_NodoMenu] FOREIGN KEY([NodoMenuId])
REFERENCES [dbo].[tblMenuPrincipal] ([NodoMenuId])
GO

ALTER TABLE [dbo].[tblAutonumerico] CHECK CONSTRAINT [FK_Autonumericos_NodoMenu]
GO

ALTER TABLE [dbo].[tblAutonumerico]  WITH NOCHECK ADD  CONSTRAINT [FK_Autonumericos_ModificadoPorId] FOREIGN KEY([ModificadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblAutonumerico] CHECK CONSTRAINT [FK_Autonumericos_ModificadoPorId]
GO