SET IDENTITY_INSERT tblListadoCMM ON
INSERT INTO tblListadoCMM
(
    ControlId, -- column value is auto-generated
    Nombre,
    Valor,
    Sistema,
    Activo,
    ControlSencillo,
    FechaCreacion
)
VALUES
(
    '1000022', -- ControlId - int
    'EstatusRegistro', -- Nombre - nvarchar
    'Autorizado', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000023', -- ControlId - int
    'EstatusRegistro', -- Nombre - nvarchar
    'Rechazado', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000024', -- ControlId - int
    'EstatusRegistro', -- Nombre - nvarchar
    'En Revisión', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000025', -- ControlId - int
    'EstatusRegistro', -- Nombre - nvarchar
    'En Espera de Autorización', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
SET IDENTITY_INSERT tblListadoCMM OFF