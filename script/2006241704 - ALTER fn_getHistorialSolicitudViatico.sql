SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =====================================================
-- Author:		Javier Elías
-- Create date: 15/05/2020
-- Modified date: 25/06/2020
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
	WHERE ReferenciaProcesoId = @solicitudViaticoId
)