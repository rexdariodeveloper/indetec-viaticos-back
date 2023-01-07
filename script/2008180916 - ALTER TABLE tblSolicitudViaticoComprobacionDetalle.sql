ALTER TABLE tblSolicitudViaticoComprobacionDetalle
ADD ProveedorId INT NULL ,
    ConceptoFactura NVARCHAR(1000) NULL,
	ClaveProdServ NVARCHAR(8) NULL,
	NumeroPartida INT NULL,
	ImportePartida MONEY NULL

ALTER TABLE tblSolicitudViaticoComprobacionDetalle
DROP COLUMN ImporteFactura