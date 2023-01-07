UPDATE alerta SET alerta.ReferenciaProcesoId = 
										CASE WHEN definicion.AlertaDefinicionId IN (1, 2, 3, 4, 5, 7) THEN solicitud.SolicitudViaticoId 
										ELSE CASE WHEN definicion.AlertaDefinicionId IN (6) THEN asignacion.SolicitudViaticoId 
										ELSE CASE WHEN definicion.AlertaDefinicionId IN (8) THEN comprobacion.SolicitudViaticoId
										ELSE -1 END END END
FROM tblAlerta AS alerta
		INNER JOIN tblAlertaDefinicion AS definicion ON alerta.AlertaDefinicionId = definicion.AlertaDefinicionId
		LEFT JOIN tblSolicitudViatico AS solicitud ON CASE WHEN alerta.AlertaDefinicionId IN (1, 2, 3, 4, 5, 7) THEN ReferenciaProcesoId ELSE-1 END = solicitud.SolicitudViaticoId
		LEFT JOIN tblSolicitudViaticoAsignacion AS asignacion ON CASE WHEN alerta.AlertaDefinicionId IN (6) THEN ReferenciaProcesoId ELSE-1 END = asignacion.AsignacionId
		LEFT JOIN tblSolicitudViaticoComprobacion AS comprobacion ON CASE WHEN definicion.AlertaDefinicionId IN (8) THEN ReferenciaProcesoId ELSE -1 END = comprobacion.SolicitudViaticoComprobacionId
GO