SET IDENTITY_INSERT tblAlertaEtapaAccion ON
INSERT INTO tblAlertaEtapaAccion(
	AlertaEtapaAccionId,
	EtapaId,
	AccionId,
	PermiteAutorizacion,
	PermiteNotificacion
) VALUES
-- Solicitud Viáticos. Nuevo
(
	1,
	1000026,
	1000031,
	0,
	0
),(
	2,
	1000026,
	1000032,
	0,
	0
),
-- Solicitud Viáticos. Validación
(
	3,
	1000027,
	1000033,
	0,
	0
),(
	4,
	1000027,
	1000034,
	0,
	0
),(
	5,
	1000027,
	1000036,
	0,
	0
),
-- Solicitud Viáticos. Asignación de Recursos
(
	6,
	1000028,
	1000035,
	0,
	0
),(
	7,
	1000028,
	1000036,
	0,
	0
),
-- Solicitud Viáticos. Informe y Comprobación
(
	8,
	1000029,
	1000037,
	0,
	0
),(
	9,
	1000029,
	1000031,
	0,
	0
),
-- Solicitud Viáticos. Revisión
(
	10,
	1000030,
	1000037,
	0,
	0
),(
	11,
	1000030,
	1000038,
	0,
	0
),(
	12,
	1000030,
	1000039,
	0,
	0
),(
	13,
	1000030,
	1000040,
	0,
	0
)
SET IDENTITY_INSERT tblAlertaEtapaAccion OFF