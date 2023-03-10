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
PRINT N'Dropping foreign keys from [dbo].[tblSolicitudViaticoInforme]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [FK_tblSolicitudViaticoInforme_tblListadoCMM]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [FK_tblSolicitudViaticoInforme_tblSolicitudViatico]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario1]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping constraints from [dbo].[tblSolicitudViaticoInforme]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [PK_tblSolicitudViaticoInforme]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Dropping constraints from [dbo].[tblSolicitudViaticoInforme]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] DROP CONSTRAINT [DF_tblSolicitudViaticoInforme_FechaCreacion]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Rebuilding [dbo].[tblSolicitudViaticoInforme]'
GO
CREATE TABLE [dbo].[RG_Recovery_1_tblSolicitudViaticoInforme]
(
[SolicitudViaticoInformeId] [int] NOT NULL IDENTITY(1, 1),
[SolicitudViaticoComprobacionId] [bigint] NOT NULL,
[ObjetivoEstrategico] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[ObjetivoEspecifico] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[ActividadesRealizadas] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[ResultadosObtenidos] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[Contribuciones] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[VinculosANotas] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[ListadoDocumentos] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[Conclusiones] [nvarchar] (max) COLLATE Modern_Spanish_CI_AS NULL,
[EstatusId] [int] NOT NULL,
[FechaCreacion] [datetime] NOT NULL CONSTRAINT [DF_tblSolicitudViaticoInforme_FechaCreacion] DEFAULT (getdate()),
[CreadoPorId] [int] NOT NULL,
[FechaUltimaModificacion] [datetime] NULL,
[ModificadoPorId] [int] NULL,
[Timestamp] [timestamp] NOT NULL
)
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
SET IDENTITY_INSERT [dbo].[RG_Recovery_1_tblSolicitudViaticoInforme] ON
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
INSERT INTO [dbo].[RG_Recovery_1_tblSolicitudViaticoInforme]([SolicitudViaticoInformeId], [ObjetivoEstrategico], [ObjetivoEspecifico], [ActividadesRealizadas], [ResultadosObtenidos], [Contribuciones], [VinculosANotas], [ListadoDocumentos], [Conclusiones], [EstatusId], [FechaCreacion], [CreadoPorId], [FechaUltimaModificacion], [ModificadoPorId]) SELECT [SolicitudViaticoInformeId], [ObjetivoEstrategico], [ObjetivoEspecifico], [ActividadesRealizadas], [ResultadosObtenidos], [Contribuciones], [VinculosANotas], [ListadoDocumentos], [Conclusiones], [EstatusId], [FechaCreacion], [CreadoPorId], [FechaUltimaModificacion], [ModificadoPorId] FROM [dbo].[tblSolicitudViaticoInforme]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
SET IDENTITY_INSERT [dbo].[RG_Recovery_1_tblSolicitudViaticoInforme] OFF
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
DECLARE @idVal BIGINT
SELECT @idVal = IDENT_CURRENT(N'[dbo].[tblSolicitudViaticoInforme]')
IF @idVal IS NOT NULL
    DBCC CHECKIDENT(N'[dbo].[RG_Recovery_1_tblSolicitudViaticoInforme]', RESEED, @idVal)
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
DROP TABLE [dbo].[tblSolicitudViaticoInforme]
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
EXEC sp_rename N'[dbo].[RG_Recovery_1_tblSolicitudViaticoInforme]', N'tblSolicitudViaticoInforme', N'OBJECT'
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Creating primary key [PK_tblSolicitudViaticoInforme] on [dbo].[tblSolicitudViaticoInforme]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD CONSTRAINT [PK_tblSolicitudViaticoInforme] PRIMARY KEY CLUSTERED  ([SolicitudViaticoInformeId])
GO
IF @@ERROR <> 0 SET NOEXEC ON
GO
PRINT N'Adding foreign keys to [dbo].[tblSolicitudViaticoInforme]'
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD CONSTRAINT [FK_tblSolicitudViaticoInforme_tblListadoCMM] FOREIGN KEY ([EstatusId]) REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD CONSTRAINT [FK_tblSolicitudViaticoInforme_tblSolicitudViaticoComprobacion] FOREIGN KEY ([SolicitudViaticoComprobacionId]) REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario] FOREIGN KEY ([CreadoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO
ALTER TABLE [dbo].[tblSolicitudViaticoInforme] ADD CONSTRAINT [FK_tblSolicitudViaticoInforme_tblUsuario1] FOREIGN KEY ([ModificadoPorId]) REFERENCES [dbo].[tblUsuario] ([UsuarioId])
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
