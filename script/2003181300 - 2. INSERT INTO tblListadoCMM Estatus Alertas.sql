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
) 
VALUES (
	1000120, -- ControlId
	'EstatusAlerta', -- Nombre
	'En Proceso', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),  
(
	1000121, -- ControlId
	'EstatusAlerta', -- Nombre
	'Rechazada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000122, -- ControlId
	'EstatusAlerta', -- Nombre
	'En Revisión', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000123, -- ControlId
	'EstatusAlerta', -- Nombre
	'Finalizada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000124, -- ControlId
	'EstatusAlerta', -- Nombre
	'Cancelada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblAlertaEtapaAccion OFF