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
    '1000049', -- ControlId - int
    'TipoComprobante', -- Nombre - nvarchar
    'Factura Nacional', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000050', -- ControlId - int
    'TipoComprobante', -- Nombre - nvarchar
    'Comprobante Extranjero', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000051', -- ControlId - int
    'TipoComprobante', -- Nombre - nvarchar
    'Sin Comprobante', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
SET IDENTITY_INSERT tblListadoCMM OFF