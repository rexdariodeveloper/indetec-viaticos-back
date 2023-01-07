SET IDENTITY_INSERT tblAutonumerico ON
INSERT INTO tblAutonumerico
(
    AutonumericoId,
    Nombre,
    Prefijo,
    Siguiente,
    Ceros,
    NodoMenuId,
    Ejercicio,
    Activo,
    FechaUltimaModificacion,
    ModificadoPorId,
    Timestamp
)
VALUES
(
    1, -- AutonumericoId - tinyint
    'CMA_AN_SiguenteNumSolicitudViatico', -- Nombre - nvarchar
    'SV', -- Prefijo - nvarchar
    1, -- Siguiente - bigint
    6, -- Ceros - int
    13, -- NodoMenuId - int
    2020, -- Ejercicio - int
    1, -- Activo - bit
    GETDATE(), -- FechaUltimaModificacion - datetime
    NULL, -- ModificadoPorId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblAutonumerico ON
GO

ALTER TABLE tblSolicitudViatico ADD NumeroSolicitud NVARCHAR(12) NULL
GO

DECLARE @tbl TABLE (id INT, orden INT)

INSERT INTO @tbl
SELECT SolicitudViaticoId, ROW_NUMBER() OVER (ORDER BY FechaCreacion) FROM tblSolicitudViatico 

DECLARE @noRegistros INT = ( SELECT COUNT(id) FROM @tbl )
DECLARE @contador INT = 1
DECLARE @numeroSolicitud VARCHAR(12)

WHILE( @contador <= @noRegistros )
BEGIN
	EXEC sp_AutonumericoSigIncr 'CMA_AN_SiguenteNumSolicitudViatico', @numeroSolicitud OUTPUT

	UPDATE tblSolicitudViatico SET NumeroSolicitud = @numeroSolicitud WHERE SolicitudViaticoId = ( SELECT id FROM @tbl WHERE orden = @contador )

	SET @contador = @contador + 1
END

ALTER TABLE tblSolicitudViatico ALTER COLUMN NumeroSolicitud NVARCHAR(12) NOT NULL
GO