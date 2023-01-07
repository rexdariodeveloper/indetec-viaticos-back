INSERT INTO tblListadoEsquema(NodoMenuId, ListadoId, Etiqueta, NombreTabla,TablaControl,PermiteAgregarRegistros, PermiteEliminarRegistros)
VALUES
(1,1000010,'Listado Puestos','tblListadoPuesto',1,1,1),
(1,1000011,'Listado Cargos','tblListadoCargo',1,1,1)
GO
INSERT INTO tblListadoEsquemaFormato(EsquemaId,NombreCampoTabla, Etiqueta,TipoDato, Tamanio, Orden, Editable, Excluyente, Requerido, Visible)
VALUES
-- Puesto
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000010),'PuestoId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000010 ),'Clave','Clave','NVARCHAR',20,2,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000010 ),'Nombre','Nombre','NVARCHAR',50,3,1,0,1,1),
-- Cargo
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000011 ),'CargoId','ID','INT',NULL,1,0,0,1,0),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000011 ),'Nombre','Nombre','NVARCHAR',50,2,1,0,1,1),
((SELECT EsquemaId FROM tblListadoEsquema WHERE ListadoId = 1000011 ),'Descripcion','Descripcion','NVARCHAR',100,3,1,0,1,1)