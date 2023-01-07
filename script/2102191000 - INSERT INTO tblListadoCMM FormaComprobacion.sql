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
    '1000208', -- ControlId - int
    'FormaComprobacion', -- Nombre - nvarchar
    'Factura completa', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
    '1000209', -- ControlId - int
    'FormaComprobacion', -- Nombre - nvarchar
    'Por detalles', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
SET IDENTITY_INSERT tblListadoCMM OFF