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
    24, -- NodoMenuId - int
    'Pasajes no Utilizados', -- Etiqueta - nvarchar
    'Ficha Pasajes no Utilizados', -- Descripcion - nvarchar
    1000005, -- TipoNodoId - int
    12, -- NodoPadreId - int
    1000007, -- SistemaAccesoId - int
    'viaticos/pasajes_no_utilizados', -- Url - nvarchar
    'fi flaticon-price-tag', -- Icono - nvarchar
    1, -- AdmitePermiso - bit
    5, -- Orden - tinyint
    1000000, -- EstatusId - int
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblMenuPrincipal OFF