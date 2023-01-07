UPDATE tblAlertaDefinicion SET NuevoEstado = NULL, CambiarEstatusATramite = 0 WHERE AlertaDefinicionId = 8
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
    13, -- AlertaDefinicionId - int
    'Informe y Comprobación - Enviar', -- NombreCorto - nvarchar
    'Informe y Comprobación - Etapa Enviar - Notificación', -- Descripcion - nvarchar
    9, -- AlertaEtapaAccionId - int
    15, -- NodoMenuId - int
    NULL, -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    'NumeroSolicitud', -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    1000099, -- NuevoEstado - int
    1, -- CambiarEstatusATramite - bit
    NULL, -- EtapaAccionAlAutorizarId - int
    NULL, -- EtapaAccionAlRechazarId - int
    NULL, -- EtapaAccionAlRevisionId - int
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
)
SET IDENTITY_INSERT tblAlertaDefinicion OFF
GO