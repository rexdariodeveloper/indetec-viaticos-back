INSERT INTO tblListadoEsquema(NodoMenuId, ListadoId, Etiqueta, NombreTabla,TablaControl,NombreControl,PermiteAgregarRegistros, PermiteEliminarRegistros, CampoOrden)
VALUES
(1,1000113,'Listado Tipo de Empleado','tblListadoCMM',0,'TipoEmpleado',1,1,'Valor')
GO
-----------
INSERT INTO tblListadoEsquemaFormato(EsquemaId,NombreCampoTabla, Etiqueta,TipoDato, Tamanio, Orden, Editable, Excluyente, Requerido, Visible)
VALUES
-- Listado tipo de empleado
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113),'ControlId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113 ),'Nombre','Nombre','NVARCHAR',100,2,1,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113 ),'Valor','Valor','NVARCHAR',null,3,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113 ),'Sistema','Sistema','BIT',null,4,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113 ),'ControlSencillo','ControlSencillo','BIT',null,5,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000113 ),'ModuloId','ModuloId','INT',null,6,0,0,0,0)