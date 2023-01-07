SET IDENTITY_INSERT tblAlertaDefinicion ON

INSERT INTO tblAlertaDefinicion
(
    AlertaDefinicionId,
    NombreCorto,
    Descripcion,
    AlertaEtapaAccionId,
    NodoMenuId,
    RutaAccion,
    TablaReferencia,
    CampoId,
    CampoCodigo,
    CampoEstadoRegistro,
    EstadoAutorizado,
    EstadoEnProceso,
    EstadoRechazado,
    EstadoEnRevision,
    CambiarEstatusATramite,
    Borrado,
    Timestamp
)
VALUES
(
    2, -- AlertaDefinicionId - int
    'Solicitud Viáticos - Notificación', -- NombreCorto - nvarchar
    'Solicitud Viaticos - Etapa Nueva - Accion Cancelar - Notificación', -- Descripcion - nvarchar
    2, -- AlertaEtapaAccionId - int
    13, -- NodoMenuId - int
    'app/viaticos/solicitudes/resumen/{id}/1', -- RutaAccion - nvarchar
    'tblSolicitudViatico', -- TablaReferencia - nvarchar
    'SolicitudViaticoId', -- CampoId - nvarchar
    NULL, -- CampoCodigo - nvarchar
    'EstatusId', -- CampoEstadoRegistro - nvarchar
    NULL, -- EstadoAutorizado - int
    NULL, -- EstadoEnProceso - int
    NULL, -- EstadoRechazado - int
    NULL, -- EstadoEnRevision - int
    0, -- CambiarEstatusATramite - bit
    0, -- Borrado - bit
    NULL -- Timestamp - timestamp
)

SET IDENTITY_INSERT tblAlertaDefinicion OFF