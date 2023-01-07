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
	1000150, -- ControlId
	'AlertaAccion', -- Nombre
	'Iniciar Alerta', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)
--,(
--	1000027, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Viáticos. Validación', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000028, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Viáticos. Asignación de Recursos', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000029, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Viáticos. Informe y Comprobación', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000030, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Viáticos. Revisión', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--)


SET IDENTITY_INSERT tblListadoCMM OFF