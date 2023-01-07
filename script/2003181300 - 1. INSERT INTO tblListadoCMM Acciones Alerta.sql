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
--	'Solicitud Vi�ticos. Validaci�n', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000028, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Vi�ticos. Asignaci�n de Recursos', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000029, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Vi�ticos. Informe y Comprobaci�n', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--),(
--	1000030, -- ControlId
--	'AlertaEtapa', -- Nombre
--	'Solicitud Vi�ticos. Revisi�n', -- Valor
--	1, -- Sistema
--	1, -- Activo
--	0, -- ControlSencillo
--	1 -- CreadoPorId
--)


SET IDENTITY_INSERT tblListadoCMM OFF