UPDATE tblAlertaEtapaAccion SET PermiteAutorizacion = 1 WHERE AlertaEtapaAccionId = 11
GO

SET IDENTITY_INSERT tblAlertaDefinicion ON
INSERT INTO tblAlertaDefinicion
(
    AlertaDefinicionId, -- column value is auto-generated
    NombreCorto,
    Descripcion,
    AlertaEtapaAccionId,
    NodoMenuId,
    RutaAccion,
    TablaReferencia,
    CampoId,
    CampoCodigo,
    CampoEstadoRegistro,
    NuevoEstado,
    CambiarEstatusATramite,
    EtapaAccionAlAutorizarId,
    EtapaAccionAlRechazarId,
    EtapaAccionAlRevisionId,
    Borrado,
    Timestamp
)
VALUES
(
    9, -- AlertaDefinicionId - int
    'Revisi�n - Finalizada', -- NombreCorto - nvarchar
    'Revisi�n - Etapa Finalizar - Notificaci�n', -- Descripcion - nvarchar
    10, -- AlertaEtapaAccionId - int
    20, -- NodoMenuId - int
    NULL, -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1000108, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    NULL, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    NULL, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
),
(
    10, -- AlertaDefinicionId - int
    'Revisi�n - Revisi�n Validaci�n', -- NombreCorto - nvarchar
    'Revisi�n - Etapa Revisi�n Validaci�n - Autorizaci�n', -- Descripcion - nvarchar
    11, -- AlertaEtapaAccionId - int
    20, -- NodoMenuId - int
    'app/viaticos/revisiones/revision/{id}', -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1000096, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    12, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    13, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
),
(
    11, -- AlertaDefinicionId - int
    'Revisi�n - Validaci�n Autorizaci�n', -- NombreCorto - nvarchar
    'Revisi�n - Etapa Validaci�n Autorizaci�n - Notificaci�n', -- Descripcion - nvarchar
    12, -- AlertaEtapaAccionId - int
    20, -- NodoMenuId - int
    NULL, -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1000097, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    NULL, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    NULL, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
),
(
    12, -- AlertaDefinicionId - int
    'Revisi�n - Validaci�n Revisi�n', -- NombreCorto - nvarchar
    'Revisi�n - Etapa Validaci�n Revisi�n - Notificaci�n', -- Descripcion - nvarchar
    13, -- AlertaEtapaAccionId - int
    20, -- NodoMenuId - int
    NULL, -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1000107, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    NULL, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    NULL, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblAlertaDefinicion OFF
GO