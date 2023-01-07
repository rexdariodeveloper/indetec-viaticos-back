

-- Agregar campo CategoriaId a Tabla tblConceptoViatico
ALTER TABLE tblConceptoViatico
ADD CategoriaId int 
GO

-- Actualizar todos los Conceptos a Categoria Viatico
update tblConceptoViatico
set CategoriaId = 1000058