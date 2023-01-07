SET IDENTITY_INSERT tblMenuPrincipal ON
INSERT INTO tblMenuPrincipal(
	NodoMenuId,
	Etiqueta,
	Descripcion,
	TipoNodoId,
	NodoPadreId,
	SistemaAccesoId,
	Url,
	Icono,
	AdmitePermiso,
	Orden,
	EstatusId
) VALUES (
	18, -- NodoMenuId
	'Notificaciones', -- Etiqueta
	'Ficha Alertas', -- Descripcion
	1000005, -- TipoNodoId
	16, -- NodoPadreId
	1000007, -- SistemaAccesoId
	'alertas/notificaciones', -- Url
	'fi flaticon-alarm', -- Icono
	1, -- AdmitePermiso
	2, -- Orden
	1000000 -- EstatusId
)
SET IDENTITY_INSERT tblMenuPrincipal OFF