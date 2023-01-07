SET IDENTITY_INSERT tblListadoCMM ON

-- Nodo Impuesto

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000197, -- ControlId
	'NodoImpuesto', -- Nombre
	'Traslado', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), (
	1000198, -- ControlId
	'NodoImpuesto', -- Nombre
	'Retención', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF