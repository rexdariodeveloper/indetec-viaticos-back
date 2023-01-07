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
    22, -- NodoMenuId - int
    'Reporte Transparencia', -- Etiqueta - nvarchar
    'Ficha Reporte Transparencia (INAI)', -- Descripcion - nvarchar
    1000005, -- TipoNodoId - int
    12, -- NodoPadreId - int
    1000007, -- SistemaAccesoId - int
    'viaticos/reporte_transparencia', -- Url - nvarchar
    'fi flaticon-document', -- Icono - nvarchar
    1, -- AdmitePermiso - bit
    4, -- Orden - tinyint
    1000000, -- EstatusId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblMenuPrincipal OFF