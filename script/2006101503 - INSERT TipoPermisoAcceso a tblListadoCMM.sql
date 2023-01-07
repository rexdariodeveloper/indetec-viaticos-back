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
    1001164, -- ControlId - int
    'TipoPermisoAcceso', -- Nombre - nvarchar
    'Permitir Crear Solicitudes de Terceros', -- Valor - nvarchar
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
    1001165, -- ControlId - int
    'TipoPermisoAcceso', -- Nombre - nvarchar
    'Permitir Visualizar Solicitudes de Terceros', -- Valor - nvarchar
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