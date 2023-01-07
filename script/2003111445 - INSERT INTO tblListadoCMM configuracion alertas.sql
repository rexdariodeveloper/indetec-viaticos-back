SET IDENTITY_INSERT tblListadoCMM ON

-- AlertaEtapa

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000026, -- ControlId
	'AlertaEtapa', -- Nombre
	'Solicitud Viáticos. Nuevo', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000027, -- ControlId
	'AlertaEtapa', -- Nombre
	'Solicitud Viáticos. Validación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000028, -- ControlId
	'AlertaEtapa', -- Nombre
	'Solicitud Viáticos. Asignación de Recursos', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000029, -- ControlId
	'AlertaEtapa', -- Nombre
	'Solicitud Viáticos. Informe y Comprobación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000030, -- ControlId
	'AlertaEtapa', -- Nombre
	'Solicitud Viáticos. Revisión', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

-- AlertaEtapaAccion

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000031, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Enviar', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000032, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Cancelar', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000033, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Autorizar', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000034, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Rechazar', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000035, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Asignar Viáticos', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000036, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Revisión', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000037, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Finalizar', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000038, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Revisión Validación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000039, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Validación Autorización', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000040, -- ControlId
	'AlertaEtapaAccion', -- Nombre
	'Validación Revisión', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

-- TipoNotificacion

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000041, -- ControlId
	'TipoNotificacion', -- Nombre
	'Autorización', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000042, -- ControlId
	'TipoNotificacion', -- Nombre
	'Notificación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

-- AlertaConfiguracionFigura

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000043, -- ControlId
	'AlertaConfiguracionFigura', -- Nombre
	'Jefe inmediato', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),(
	1000044, -- ControlId
	'AlertaConfiguracionFigura', -- Nombre
	'Solicitante', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF