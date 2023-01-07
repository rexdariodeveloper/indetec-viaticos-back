ALTER TABLE tblConfiguracionEnte
ADD NormativaViaticos UNIQUEIDENTIFIER
GO

ALTER TABLE [dbo].[tblConfiguracionEnte]  WITH CHECK ADD  CONSTRAINT [FK_tblConfiguracionEnte_tblArchivo] FOREIGN KEY([NormativaViaticos])
REFERENCES [dbo].[tblArchivo] ([ArchivoId])
GO

ALTER TABLE [dbo].[tblConfiguracionEnte] CHECK CONSTRAINT [FK_tblConfiguracionEnte_tblArchivo]
GO

ALTER TABLE tblConfiguracionEnte
ADD AreaResponsableTransparencia NVARCHAR(2000)
GO

UPDATE tblConfiguracionEnte SET /*NormativaViaticos = '00000000-0000-0000-0000-000000000000',*/ AreaResponsableTransparencia = ''
GO

--ALTER TABLE tblConfiguracionEnte
--ALTER COLUMN NormativaViaticos UNIQUEIDENTIFIER NOT NULL
--GO

ALTER TABLE tblConfiguracionEnte
ALTER COLUMN AreaResponsableTransparencia NVARCHAR(2000) NOT NULL
GO

SET IDENTITY_INSERT tblListadoCMOA ON
INSERT INTO tblListadoCMOA
(
    ControlOrigenArchivoId, -- column value is auto-generated
    Descripcion,
    Vigente,
    FechaCreacion,
    CreadoPorId,
    Timestamp
)
VALUES
(
    3, -- ControlOrigenArchivoId - tinyint
    'Archivo de Normativa Viáticos', -- Descripcion - nvarchar
    1, -- Vigente - bit
    GETDATE(), -- FechaCreacion - datetime
    1, -- CreadoPorId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblListadoCMOA OFF
GO

INSERT INTO tblArchivoEstructuraCarpeta
(
    -- EstructuraId, -- column value is auto-generated
    NombreCarpeta,
    DescripcionCorta,
    EstructuraReferenciaId,
    OrigenArchivoId,
    NombreCalculado,
    TipoCalculo,
    Vigente,
    FechaCreacion,
    CreadoPorId,
    Timestamp
)
VALUES
(
    -- EstructuraId - int
    'NormativaViaticos', -- NombreCarpeta - nvarchar
    'Normativas Viáticos del ente', -- DescripcionCorta - nvarchar
    5, -- EstructuraReferenciaId - int
    3, -- OrigenArchivoId - tinyint
    0, -- NombreCalculado - bit
    NULL, -- TipoCalculo - int
    1, -- Vigente - bit
    GETDATE(), -- FechaCreacion - datetime
    1, -- CreadoPorId - int
    NULL -- Timestamp - timestamp
)
GO