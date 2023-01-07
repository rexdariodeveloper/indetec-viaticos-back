USE ViaticosDatos
GO
INSERT INTO tblListadoEsquema(NodoMenuId, ListadoId, Etiqueta, NombreTabla,TablaControl,NombreControl,PermiteAgregarRegistros, PermiteEliminarRegistros, CampoOrden)
VALUES
(1,1000114,'Listado Claves Productos Hospedaje','tblListadoCMM',0,'ClavesProductosHospedaje',1,1,'Valor')
GO
-----------
INSERT INTO tblListadoEsquemaFormato(EsquemaId,NombreCampoTabla, Etiqueta,TipoDato, Tamanio, Orden, Editable, Excluyente, Requerido, Visible)
VALUES
-- Listado Claves Productos Hospedaje
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'ControlId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'Nombre','Nombre','NVARCHAR',100,2,1,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'Valor','Valor','NVARCHAR',null,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'Sistema','Sistema','BIT',null,4,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'ControlSencillo','ControlSencillo','BIT',null,5,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000114 ),'ModuloId','ModuloId','INT',null,6,0,0,0,0)