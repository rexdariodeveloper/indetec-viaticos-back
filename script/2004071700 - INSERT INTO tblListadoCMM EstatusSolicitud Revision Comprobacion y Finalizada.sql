SET IDENTITY_INSERT tblListadoCMM ON

-- Estatus Solicitud

INSERT INTO tblListadoCMM(
	ControlId,
	Nombre,
	Valor,
	Sistema,
	Activo,
	ControlSencillo,
	CreadoPorId
) VALUES (
	1000107, -- ControlId
	'EstatusSolicitud', -- Nombre
	'En Revision Comprobación', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
), (
	1000108, -- ControlId
	'EstatusSolicitud', -- Nombre
	'Finalizada', -- Valor
	1, -- Sistema
	1, -- Activo
	0, -- ControlSencillo
	1 -- CreadoPorId
)

SET IDENTITY_INSERT tblListadoCMM OFF