INSERT INTO tblListadoEsquema(NodoMenuId, ListadoId, Etiqueta, NombreTabla,TablaControl,NombreControl,PermiteAgregarRegistros, PermiteEliminarRegistros)
VALUES
(1,1000109,'Listado Moneda','tblMoneda',1,null,1,1)
GO
-----------
INSERT INTO tblListadoEsquemaFormato(EsquemaId,NombreCampoTabla, Etiqueta,TipoDato, Tamanio, Orden, Editable, Excluyente, Requerido, Visible)
VALUES
-- Listado tipo de viaje
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000109),'MonedaId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000109 ),'Abreviacion','Abreviacion','NVARCHAR',100,2,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000109 ),'Nombre','Nombre','NVARCHAR',50,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000109 ),'Simbolo','Simbolo','NVARCHAR',1,4,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000109 ),'Predeterminada','Predeterminada','BIT',null,5,1,1,1,0)