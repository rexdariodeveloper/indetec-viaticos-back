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
	1000100, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Activa', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),  
(
	1000101, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Borrada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000102, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Cancelada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000103, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Autorizada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000104, -- ControlId
	'EstatusSolicitud', -- Nombre
	'En revisión', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), 
(
	1000105, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Rechazada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
),
(
	1000106, -- ControlId
	'EstatusSolicitud', -- Nombre
	'En Proceso Autorización', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblAlertaEtapaAccion OFF