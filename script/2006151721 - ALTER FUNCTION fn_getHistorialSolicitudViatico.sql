SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =====================================================
-- Author:		Javier Elías
-- Create date: 15/05/2020
-- Modified date: 15/06/2020
-- Description:	Función para obtener el historial de una Solicitud
-- =====================================================
ALTER FUNCTION [dbo].[fn_getHistorialSolicitudViatico] ( @solicitudViaticoId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT dbo.getNombreCompletoEmpleado(CreadoPorId) AS empleado,
			dbo.getFechaConFormato(FechaCreacion, 1) AS fecha,
			'Solicitud Creada' AS proceso,
			NULL AS motivo
	FROM tblSolicitudViatico
	WHERE SolicitudViaticoId = @solicitudViaticoId
    
	UNION ALL
    
	SELECT dbo.getNombreCompletoEmpleado(alerta.CreadoPorId) AS empleado,
		   dbo.getFechaConFormato(alerta.FechaCreacion, 1) AS fecha,
		   CASE WHEN alerta.EstatusId = 1000124 THEN REPLACE(NombreCorto, 'Autorización', 'Cancelada') ELSE NombreCorto END AS proceso,
		   CASE WHEN CHARINDEX('Motivo', TextoRepresentativo) > 0 THEN SUBSTRING(TextoRepresentativo, CHARINDEX('Motivo', TextoRepresentativo)+8, LEN(TextoRepresentativo)) ELSE NULL END AS motivo
	FROM tblAlerta AS alerta
		 INNER JOIN tblAlertaDefinicion AS definicion ON alerta.AlertaDefinicionId = definicion.AlertaDefinicionId
		 LEFT JOIN tblSolicitudViatico AS solicitud ON CASE WHEN alerta.AlertaDefinicionId IN (1, 2, 3, 4, 5, 7) THEN ReferenciaProcesoId ELSE-1 END = solicitud.SolicitudViaticoId
		 LEFT JOIN tblSolicitudViaticoAsignacion AS asignacion ON CASE WHEN alerta.AlertaDefinicionId IN (6) THEN ReferenciaProcesoId ELSE-1 END = asignacion.AsignacionId
		 LEFT JOIN tblSolicitudViaticoComprobacion AS comprobacion ON CASE WHEN definicion.AlertaDefinicionId IN (8) THEN ReferenciaProcesoId ELSE -1 END = comprobacion.SolicitudViaticoComprobacionId
	WHERE @solicitudViaticoId = CASE WHEN definicion.AlertaDefinicionId IN (1, 2, 3, 4, 5, 7) THEN solicitud.SolicitudViaticoId 
													 ELSE CASE WHEN definicion.AlertaDefinicionId IN (6) THEN asignacion.SolicitudViaticoId 
													 ELSE CASE WHEN definicion.AlertaDefinicionId IN (8) THEN comprobacion.SolicitudViaticoId
													 ELSE -1 END END END
)