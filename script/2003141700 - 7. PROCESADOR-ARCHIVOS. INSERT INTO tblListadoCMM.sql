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

-- TipoCalculoNombreArchivo

(
    1000045, -- ControlId - int
    'TipoCalculoNombreArchivo', -- Nombre - nvarchar
    'Sencillo', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),

-- RutaTemporalArchivo

(
    1000046, -- ControlId - int
    'RutaTemporalArchivo', -- Nombre - nvarchar
    'C:/PIXVS Archivos/INDETEC/tmp', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    1, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),

-- RutaRaizArchivo

(
    1000047, -- ControlId - int
    'RutaRaizArchivo', -- Nombre - nvarchar
    'C:/PIXVS Archivos/INDETEC/raiz', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    1, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),

-- IdAutonumericoArchivo

(
    1000048, -- ControlId - int
    'IdAutonumericoArchivo', -- Nombre - nvarchar
    '1', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    1, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),

-- IdAutonumericoArchivo

(
    1000049, -- ControlId - int
    'CantidadCerosAutonumericoArchivo', -- Nombre - nvarchar
    '12', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    1, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)

SET IDENTITY_INSERT tblListadoCMM OFF