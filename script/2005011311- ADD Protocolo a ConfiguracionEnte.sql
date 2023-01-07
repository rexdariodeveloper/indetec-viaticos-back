SET IDENTITY_INSERT tblListadoCMM ON
INSERT INTO tblListadoCMM
(
    ControlId, -- column value is auto-generated
    Nombre,
    Valor,
    Sistema,
    Activo,
    ControlSencillo,
    ModuloId,
    FechaCreacion,
    CreadoPorId,
    FechaUltimaModificacion,
    ModificadoPorId,
    Timestamp
)
VALUES
(
    1000189, -- ControlId - int
    'ProtocoloSeguridadEmail', -- Nombre - nvarchar
    'SSL', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    NULL, -- ModuloId - int
    GETDATE(), -- FechaCreacion - datetime
    1, -- CreadoPorId - int
    NULL, -- FechaUltimaModificacion - datetime
    NULL, -- ModificadoPorId - int
    NULL -- Timestamp - timestamp
),
(
    1000190, -- ControlId - int
    'ProtocoloSeguridadEmail', -- Nombre - nvarchar
    'TLS', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    NULL, -- ModuloId - int
    GETDATE(), -- FechaCreacion - datetime
    1, -- CreadoPorId - int
    NULL, -- FechaUltimaModificacion - datetime
    NULL, -- ModificadoPorId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblListadoCMM OFF
GO

DECLARE @ident INT = ( SELECT MAX(ControlId) FROM tblListadoCMM WHERE ControlId < 100000 )
DBCC CHECKIDENT( tblListadoCMM, RESEED, @ident )
GO

ALTER TABLE tblConfiguracionEnte ADD Protocolo INT
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblListadoCMM] FOREIGN KEY([Protocolo])
REFERENCES [dbo].[tblListadoCMM] ([ControlId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblListadoCMM]
GO

UPDATE tblConfiguracionEnte SET Protocolo = 1000189
GO

ALTER TABLE tblConfiguracionEnte ALTER COLUMN Protocolo INT NOT NULL
GO