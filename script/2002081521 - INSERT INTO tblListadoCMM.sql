SET IDENTITY_INSERT tblListadoCMM ON
INSERT INTO tblListadoCMM
(
	ControlId,
    Nombre,
    Valor,
    Sistema,
    Activo,
    ControlSencillo,
    FechaCreacion
)
VALUES
(
	1000010,
    'ListadoTipo', -- Nombre - nvarchar
    'Listado Puestos', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
),
(
	1000011,
    'ListadoTipo', -- Nombre - nvarchar
    'Listado Cargos', -- Valor - nvarchar
    1, -- Sistema - bit
    1, -- Activo - bit
    0, -- ControlSencillo - bit
    GETDATE() -- FechaCreacion - datetime
)
SET IDENTITY_INSERT tblListadoCMM OFF