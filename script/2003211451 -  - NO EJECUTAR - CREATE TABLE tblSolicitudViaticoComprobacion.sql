SET NUMERIC_ROUNDABORT OFF
GO
SET ANSI_PADDING, ANSI_WARNINGS, CONCAT_NULL_YIELDS_NULL, ARITHABORT, QUOTED_IDENTIFIER, ANSI_NULLS ON
GO
SET XACT_ABORT ON
GO
SET TRANSACTION ISOLATION LEVEL Serializable
GO
BEGIN TRANSACTION
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping foreign keys from [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblConceptoViatico]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM1]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM2]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM3]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionPasaje]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionViatico]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario1]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping foreign keys from [dbo].[tblSolicitudViaticoComprobacionPasaje]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] DROP CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblSolicitudViaticoComprobacion]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping constraints from [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [PK_tblSolicitudViaticoComprobacion]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping constraints from [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] DROP CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaRegistro]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Rebuilding [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
CREATE TABLE [dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle]
(
[SolicitudViaticoComprobacionDetalleId] [bigint] NOT NULL IDENTITY(1, 1),
[SolicitudViaticoComprobacionId] [bigint] NOT NULL,
[CategoriaId] [int] NOT NULL,
[ConceptoViaticoId] [int] NULL,
[TipoPasajeId] [int] NULL, 
[TipoComprobanteId] [int] NULL,
[FechaComprobante] [datetime] NULL,
[Folio] [nvarchar] (50) COLLATE Modern_Spanish_CI_AS NULL,
[RFC] [nvarchar] (50) COLLATE Modern_Spanish_CI_AS NULL,
[RazonSocial] [nvarchar] (100) COLLATE Modern_Spanish_CI_AS NULL,
[FormaPagoId] [int] NULL,
[MonedaId] [int] NULL,
[TipoCambio] [money] NULL,
[Importe] [money] NULL,
[ImportePesos] [money] NOT NULL,
[EsComprobadoPorRM] [bit] NOT NULL,
[AsignacionViaticoId] [bigint] NULL,
[AsignacionPasajeId] [bigint] NULL,
[Comentarios] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[EstatusId] [int] NOT NULL,
[FechaRegistro] [datetime] NOT NULL CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaRegistro] DEFAULT (getdate()),
[RegistradoPorId] [int] NOT NULL,
[FechaUltimaModificacion] [datetime] NULL,
[ModificadoPorId] [int] NULL,
[Timestamp] [timestamp] NOT NULL
)
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
SET IDENTITY_INSERT [dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle] ON
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
INSERT INTO [dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle]([SolicitudViaticoComprobacionDetalleId], [CategoriaId], [ConceptoViaticoId], [TipoComprobanteId], [FechaComprobante], [Folio], [RFC], [RazonSocial], [FormaPagoId], [MonedaId], [TipoCambio], [Importe], [ImportePesos], [EsComprobadoPorRM], [AsignacionViaticoId], [AsignacionPasajeId], [Comentarios], [EstatusId], [FechaRegistro], [RegistradoPorId], [FechaUltimaModificacion], [ModificadoPorId]) SELECT [SolicitudViaticoComprobacionDetalleId], [CategoriaId], [ConceptoViaticoId], [TipoComprobanteId], [FechaComprobante], [Folio], [RFC], [RazonSocial], [FormaPagoId], [MonedaId], [TipoCambio], [Importe], [ImportePesos], [EsComprobadoPorRM], [AsignacionViaticoId], [AsignacionPasajeId], [Comentarios], [EstatusId], [FechaRegistro], [RegistradoPorId], [FechaUltimaModificacion], [ModificadoPorId] FROM [dbo].[tblSolicitudViaticoComprobacionDetalle]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
SET IDENTITY_INSERT [dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle] OFF
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
DECLARE @idVal BIGINT
SELECT @idVal = IDENT_CURRENT(N'[dbo].[tblSolicitudViaticoComprobacionDetalle]')
IF @idVal IS NOT NULL
    DBCC CHECKIDENT(N'[dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle]', RESEED, @idVal)
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
DROP TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
EXEC sp_rename N'[dbo].[RG_Recovery_1_tblSolicitudViaticoComprobacionDetalle]', N'tblSolicitudViaticoComprobacionDetalle', N'OBJECT'
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Creating primary key [PK_tblSolicitudViaticoComprobacion] on [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [PK_tblSolicitudViaticoComprobacion] PRIMARY KEY CLUSTERED  ([SolicitudViaticoComprobacionDetalleId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Creating [dbo].[tblSolicitudViaticoComprobacion]'
GO
CREATE TABLE [dbo].[tblSolicitudViaticoComprobacion]
(
[SolicitudViaticoComprobacionId] [bigint] NOT NULL IDENTITY(1, 1),
[SolicitudViaticoId] [int] NOT NULL,
[SolicitanteFinalizoComprobacion] [bit] NULL,
[RMFinalizoComprobacion] [bit] NULL,
[EstatusId] [int] NOT NULL,
[FechaCreacion] [datetime] NOT NULL CONSTRAINT [DF_tblSolicitudViaticoComprobacion_FechaCreacion] DEFAULT (getdate()),
[CreadoPorId] [int] NOT NULL,
[FechaUltimaModificacion] [datetime] NULL,
[ModificadoPorId] [int] NULL,
[Timestamp] [timestamp] NOT NULL
)
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Creating primary key [PK_tblSolicitudViaticoComprobacion_1] on [dbo].[tblSolicitudViaticoComprobacion]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD CONSTRAINT [PK_tblSolicitudViaticoComprobacion_1] PRIMARY KEY CLUSTERED  ([SolicitudViaticoComprobacionId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Adding foreign keys to [dbo].[tblSolicitudViaticoComprobacionDetalle]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblConceptoViatico] FOREIGN KEY ([ConceptoViaticoId]) REFERENCES [dbo].[tblConceptoViatico] ([ConceptoViatico])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM] FOREIGN KEY ([CategoriaId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM1] FOREIGN KEY ([TipoComprobanteId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM2] FOREIGN KEY ([FormaPagoId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM3] FOREIGN KEY ([EstatusId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionPasaje] FOREIGN KEY ([AsignacionPasajeId]) REFERENCES [dbo].[tblSolicitudViaticoAsignacionPasaje] ([AsignacionPasajeId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViaticoAsignacionViatico] FOREIGN KEY ([AsignacionViaticoId]) REFERENCES [dbo].[tblSolicitudViaticoAsignacionViatico] ([AsignacionViaticoId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario] FOREIGN KEY ([RegistradoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario1] FOREIGN KEY ([ModificadoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacionDetalle_tblSolicitudViaticoComprobacion] FOREIGN KEY ([SolicitudViaticoComprobacionId]) REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Adding foreign keys to [dbo].[tblSolicitudViaticoComprobacion]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblSolicitudViatico] FOREIGN KEY ([SolicitudViaticoId]) REFERENCES [dbo].[tblSolicitudViatico] ([SolicitudViaticoId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblListadoCMM4] FOREIGN KEY ([EstatusId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario2] FOREIGN KEY ([CreadoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacion] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacion_tblUsuario3] FOREIGN KEY ([ModificadoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Adding foreign keys to [dbo].[tblSolicitudViaticoComprobacionPasaje]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionPasaje] ADD CONSTRAINT [FK_tblSolicitudViaticoComprobacionPasaje_tblSolicitudViaticoComprobacion] FOREIGN KEY ([SolicitudViaticoComprobacionDetalleId]) REFERENCES [dbo].[tblSolicitudViaticoComprobacionDetalle] ([SolicitudViaticoComprobacionDetalleId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
COMMIT TRANSACTION
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
-- This statement writes to the SQL Server Log so SQL Monitor can show this deployment.
IF HAS_PERMS_BY_NAME(N'sys.xp_logevent', N'OBJECT', N'EXECUTE') = 1
BEGIN
    DECLARE @databaseName AS nvarchar(2048), @eventMessage AS nvarchar(2048)
    SET @databaseName = REPLACE(REPLACE(DB_NAME(), N'\', N'\\'), N'"', N'\"')
    SET @eventMessage = N'Redgate SQL Compare: { "deployment": { "description": "Redgate SQL Compare deployed to ' + @databaseName + N'", "database": "' + @databaseName + N'" }}'
    EXECUTE sys.xp_logevent 55000, @eventMessage
END
GO
DECLARE @Success AS BIT
SET @Success = 1
SET NOEXEC OFF
IF (@Success = 1) PRINT 'The database update succeeded'
ELSE BEGIN
	IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION
	PRINT 'The database update failed'
END
GO
