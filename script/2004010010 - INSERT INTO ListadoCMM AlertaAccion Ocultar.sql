SET IDENTITY_INSERT tblListadoCMM ON

-- Acciones Alerta

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000162, -- ControlId
	'AlertaAccion', -- Nombre
	'Ocultar Alertas', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF