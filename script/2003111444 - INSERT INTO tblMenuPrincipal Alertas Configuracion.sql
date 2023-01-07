UPDATE tblMenuPrincipal SET Orden = (Orden+1) WHERE TipoNodoId = 1000004

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
	16, -- NodoMenuId
	'ALERTAS', -- Etiqueta
	'Menú Alertas', -- Descripcion
	1000004, -- TipoNodoId
	NULL, -- NodoPadreId
	1000007, -- SistemaAccesoId
	NULL, -- Url
	NULL, -- Icono
	0, -- AdmitePermiso
	1, -- Orden
	1000000 -- EstatusId
),(
	17, -- NodoMenuId
	'Configuración', -- Etiqueta
	'Ficha de configuración de alertas', -- Descripcion
	1000005, -- TipoNodoId
	14, -- NodoPadreId
	1000007, -- SistemaAccesoId
	'alertas/configuracion', -- Url
	'fa fa-cog', -- Icono
	1, -- AdmitePermiso
	1, -- Orden
	1000000 -- EstatusId
)
SET IDENTITY_INSERT tblMenuPrincipal OFF