SET IDENTITY_INSERT tblMenuPrincipal ON
INSERT INTO tblMenuPrincipal
(
    NodoMenuId,
    Etiqueta,
    Descripcion,
    TipoNodoId,
    NodoPadreId,
    SistemaAccesoId,
    Url,
    Icono,
    AdmitePermiso,
    Orden,
    EstatusId,
    Timestamp
)
VALUES
(
    20, -- NodoMenuId - int
    'Cambio Temporal Alertas', -- Etiqueta - nvarchar
    'Ficha de Cambio Temporal de Alertas', -- Descripcion - nvarchar
    1000005, -- TipoNodoId - int
    16, -- NodoPadreId - int
    1000007, -- SistemaAccesoId - int
    'alertas/cambio_alertas', -- Url - nvarchar
    'fi flaticon-repeat', -- Icono - nvarchar
    1, -- AdmitePermiso - bit
    3, -- Orden - tinyint
    1000000, -- EstatusId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblMenuPrincipal OFF
GO

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
    2, -- AutonumericoId - tinyint
    'CMA_AN_SiguenteNumAlertaCambioEmpleado', -- Nombre - nvarchar
    'ACE', -- Prefijo - nvarchar
    1, -- Siguiente - bigint
    8, -- Ceros - int
    20, -- NodoMenuId - int
    NULL, -- Ejercicio - int
    1, -- Activo - bit
    GETDATE(), -- FechaUltimaModificacion - datetime
    NULL, -- ModificadoPorId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblAutonumerico ON
GO