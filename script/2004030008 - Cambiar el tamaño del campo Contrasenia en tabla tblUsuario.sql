
-- ============================================================
-- Cambiar el tama�o del campo Contrasenia en tabla tblUsuario
-- ============================================================
ALTER TABLE tblUsuario
ALTER COLUMN Contrasenia NVARCHAR(500)
GO

-- ============================================================
-- Cambiar contrase�a a usuario Admin 
-- ============================================================
UPDATE tblUsuario
SET Contrasenia = '/Czf1iQOIV8POZW6unSrZQ=='
WHERE Usuario = 'admin'
GO