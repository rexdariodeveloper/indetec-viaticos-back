SET IDENTITY_INSERT tblListadoCMM ON

-- Gasto Representacion

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000183, -- ControlId
	'GastoRepresentacion', -- Nombre
	'Viáticos', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), (
	1000184, -- ControlId
	'GastoRepresentacion', -- Nombre
	'Representación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF