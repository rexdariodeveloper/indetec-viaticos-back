


ALTER TABLE [dbo].[tblSolicitudViaticoComprobacionDetalle]
ADD ImporteTotalFactura MONEY NULL,
    ImporteDescuentoPartida MONEY NULL,
    ImporteDescuentoComprobado MONEY NULL


ALTER TABLE tblSolicitudViaticoComprobacionDetalleImpuesto
ADD TipoImpuestoId INT NULL