UPDATE tblListadoCMM SET Valor = 'Revisada' WHERE ControlId = 1000108
GO

SET IDENTITY_INSERT tblListadoCMM ON
INSERT INTO tblListadoCMM
(
    ControlId,
    Nombre,
    Valor,
    Sistema,
    Activo,
    ControlSencillo,
    ModuloId,
    FechaCreacion,
    CreadoPorId
)
SELECT CASE ControlId WHEN 1001164 THEN 1000012 WHEN 1001165 THEN 1000013 WHEN 1001166 THEN 1000098 WHEN 1001167 THEN 1000099 END,
       Nombre,
       Valor,
       Sistema,
       Activo,
       ControlSencillo,
       ModuloId,
       FechaCreacion,
       CreadoPorId
FROM tblListadoCMM
WHERE ControlId IN(1001164, 1001165, 1001166, 1001167)

SET IDENTITY_INSERT tblListadoCMM OFF
GO

DECLARE @ident INT = ( SELECT MAX(ControlId ) FROM tblListadoCMM WHERE ControlId < 100000 )
DBCC CHECKIDENT( tblListadoCMM, RESEED, @ident )
GO

UPDATE tblSolicitudViatico SET EstatusId = CASE EstatusId WHEN 1001166 THEN 1000098 WHEN 1001167 THEN 1000099 END WHERE EstatusId IN(1001164, 1001165, 1001166, 1001167)
GO

UPDATE tblPermisoAcceso SET TipoPermisoId = CASE TipoPermisoId WHEN 1001164 THEN 1000012 WHEN 1001165 THEN 1000013 END WHERE TipoPermisoId IN(1001164, 1001165, 1001166, 1001167)
GO

UPDATE tblAlertaDefinicion SET NuevoEstado = 1000099 WHERE AlertaDefinicionId = 8
GO

DELETE FROM tblListadoCMM WHERE ControlId IN(1001164, 1001165, 1001166, 1001167)
GO