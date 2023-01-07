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
	1000157, -- ControlId
	'AlertaAccion', -- Nombre
	'Autorizar Alerta', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)
,(
	1000158, -- ControlId
	'AlertaAccion', -- Nombre
	'Revision Alerta', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000159, -- ControlId
	'AlertaAccion', -- Nombre
	'Rechazar Alerta', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000160, -- ControlId
	'AlertaAccion', -- Nombre
	'Cancelar Alerta', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF