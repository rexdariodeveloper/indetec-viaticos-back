DELETE 
FROM tblListadoCargo 
WHERE CargoId = (SELECT MAX(CargoId) FROM tblListadoCargo WHERE Nombre = 'auxiliar administrativo' AND Activo = 1)