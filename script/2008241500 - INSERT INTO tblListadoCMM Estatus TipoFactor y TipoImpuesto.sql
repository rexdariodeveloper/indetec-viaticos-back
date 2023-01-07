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
    '1000191', -- ControlId - int
    'TipoFactor', -- Nombre - nvarchar
    'TASA', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000192', -- ControlId - int
    'TipoFactor', -- Nombre - nvarchar
    'COUTA', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000193', -- ControlId - int
    'TipoImpuesto', -- Nombre - nvarchar
    '001 - ISR', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
,
(
    '1000194', -- ControlId - int
    'TipoImpuesto', -- Nombre - nvarchar
    '002 - IVA', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
,
(
    '1000195', -- ControlId - int
    'TipoImpuesto', -- Nombre - nvarchar
    '003 - IEPS', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
,
(
    '1000196', -- ControlId - int
    'TipoImpuesto', -- Nombre - nvarchar
    '004 - ISH', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
SET IDENTITY_INSERT tblListadoCMM OFF