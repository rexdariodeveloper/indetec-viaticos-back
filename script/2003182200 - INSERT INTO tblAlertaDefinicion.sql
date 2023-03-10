/*
Run this script on:

(local).ViaticosX    -  This database will be modified

to synchronize it with:

(local).ViaticosNueva

You are recommended to back up your database before running this script

Script created by SQL Data Compare version 14.1.7.14336 from Red Gate Software Ltd at 18/03/2020 02:53:14 a. m.

*/
		
SET NUMERIC_ROUNDABORT OFF
GO
SET ANSI_PADDING, ANSI_WARNINGS, CONCAT_NULL_YIELDS_NULL, ARITHABORT, QUOTED_IDENTIFIER, ANSI_NULLS, NOCOUNT ON
GO
SET DATEFORMAT YMD
GO
SET XACT_ABORT ON
GO
SET TRANSACTION ISOLATION LEVEL Serializable
GO
BEGIN TRANSACTION

PRINT(N'Drop constraints from [dbo].[tblAlertaDefinicion]')
ALTER TABLE [dbo].[tblAlertaDefinicion] NOCHECK CONSTRAINT [FK_tblAlertaDefinicion_tblAlertaEtapaAccion]
ALTER TABLE [dbo].[tblAlertaDefinicion] NOCHECK CONSTRAINT [FK_tblAlertaDefinicion_tblMenuPrincipal]

PRINT(N'Drop constraint FK_tblAlerta_tblAlertaDefinicion from [dbo].[tblAlerta]')
ALTER TABLE [dbo].[tblAlerta] NOCHECK CONSTRAINT [FK_tblAlerta_tblAlertaDefinicion]

PRINT(N'Add 2 rows to [dbo].[tblAlertaDefinicion]')
SET IDENTITY_INSERT [dbo].[tblAlertaDefinicion] ON
INSERT INTO [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [EstadoAutorizado], [EstadoEnProceso], [EstadoRechazado], [EstadoEnRevision], [CambiarEstatusATramite], [Borrado]) VALUES (1, N'Solicitud Viáticos - Autorización', N'Solicitud Viaticos - Etapa Nueva - Accion Enviar - Autorizacion', 1, 13, N'app/viaticos/solicitudes/resumen/{id}/0', N'tblSolicitudViatico', N'SolicitudViaticoId', NULL, N'EstatusId', 1000103, 1000106, 1000105, 1000104, 1, 0)
SET IDENTITY_INSERT [dbo].[tblAlertaDefinicion] OFF

PRINT(N'Add constraints to [dbo].[tblAlertaDefinicion]')
ALTER TABLE [dbo].[tblAlertaDefinicion] WITH CHECK CHECK CONSTRAINT [FK_tblAlertaDefinicion_tblAlertaEtapaAccion]
ALTER TABLE [dbo].[tblAlertaDefinicion] WITH CHECK CHECK CONSTRAINT [FK_tblAlertaDefinicion_tblMenuPrincipal]
ALTER TABLE [dbo].[tblAlerta] WITH CHECK CHECK CONSTRAINT [FK_tblAlerta_tblAlertaDefinicion]
COMMIT TRANSACTION
GO
