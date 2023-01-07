-- =============================================
-- Author: Rene Carrillo
-- Create date: 28/05/20
-- Description:	Agregar un campo "SolicitudViaticoComprobacionId" 
-- =============================================
ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] ADD SolicitudViaticoComprobacionId BIGINT NULL
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion]  WITH CHECK ADD  CONSTRAINT [FK_SolicitudViaticoCompDetalleValidacion_tblSolicitudViaticoComprobacion] FOREIGN KEY([SolicitudViaticoComprobacionId])
REFERENCES [dbo].[tblSolicitudViaticoComprobacion] ([SolicitudViaticoComprobacionId])
GO

ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalleValidacion] ALTER COLUMN [SolicitudViaticoComprobacionDetalleId] BIGINT NULL
GO