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
    8, -- AlertaDefinicionId - int
    'Informe y Comprobación - Finalizar', -- NombreCorto - nvarchar
    'Informe y Comprobación - Etapa Finalizar - Notificación', -- Descripcion - nvarchar
    8, -- AlertaEtapaAccionId - int
    15, -- NodoMenuId - int
    NULL, -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1001167, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    NULL, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    NULL, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblAlertaDefinicion OFF
GO