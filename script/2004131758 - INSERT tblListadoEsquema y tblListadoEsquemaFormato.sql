INSERT INTO tblListadoEsquema(NodoMenuId, ListadoId, Etiqueta, NombreTabla,TablaControl,NombreControl,PermiteAgregarRegistros, PermiteEliminarRegistros)
VALUES
(1,1000110,'Listado Tipo de Viaje','tblListadoCMM',0,'TipoViaje',1,1),
(1,1000111,'Listado Zonas Económicas','tblListadoCMM',0,'ZonaEconomica',1,1),
(1,1000112,'Listado Tipo Representación','tblListadoCMM',0,'TipoRepresentacion',1,1)
GO
-----------
INSERT INTO tblListadoEsquemaFormato(EsquemaId,NombreCampoTabla, Etiqueta,TipoDato, Tamanio, Orden, Editable, Excluyente, Requerido, Visible)
VALUES
-- Listado tipo de viaje
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110),'ControlId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110 ),'Nombre','Nombre','NVARCHAR',100,2,1,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110 ),'Valor','Valor','NVARCHAR',null,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110 ),'Sistema','Sistema','BIT',null,4,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110 ),'ControlSencillo','ControlSencillo','BIT',null,6,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000110 ),'ModuloId','ModuloId','INT',null,7,0,0,0,0),
-- Listado tipo de representación
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111),'ControlId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111 ),'Nombre','Nombre','NVARCHAR',100,2,1,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111 ),'Valor','Valor','NVARCHAR',null,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111 ),'Sistema','Sistema','BIT',null,4,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111 ),'ControlSencillo','ControlSencillo','BIT',null,6,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000111 ),'ModuloId','ModuloId','INT',null,7,0,0,0,0),
-- Listado zonas economicas
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112),'ControlId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112 ),'Nombre','Nombre','NVARCHAR',100,2,1,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112 ),'Valor','Valor','NVARCHAR',null,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112 ),'Sistema','Sistema','BIT',null,4,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112 ),'ControlSencillo','ControlSencillo','BIT',null,6,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000112 ),'ModuloId','ModuloId','INT',null,7,0,0,0,0)