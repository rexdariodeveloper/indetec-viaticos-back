SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
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
    
	SELECT dbo.getNombreCompletoEmpleado(CreadoPorId) AS empleado,
			dbo.getFechaConFormato(ISNULL(FechaFinalizacion, FechaCreacion), 1) AS fecha,
			--CASE WHEN EstatusId = 1000124 THEN REPLACE(SUBSTRING(NombreCorto, CHARINDEX(' - ', NombreCorto)+3, LEN(NombreCorto)), 'Autorización', 'Cancelada') ELSE SUBSTRING(NombreCorto, CHARINDEX(' - ', NombreCorto)+3, LEN(NombreCorto)) END AS proceso,
			CASE WHEN EstatusId = 1000124 THEN REPLACE(NombreCorto, 'Autorización', 'Cancelada') ELSE NombreCorto END AS proceso,
			CASE WHEN CHARINDEX('Motivo', TextoRepresentativo) > 0 THEN SUBSTRING(TextoRepresentativo, CHARINDEX('Motivo', TextoRepresentativo)+8, LEN(TextoRepresentativo)) ELSE NULL END AS motivo
	FROM tblAlerta AS alerta
			INNER JOIN tblAlertaDefinicion AS definicion ON alerta.AlertaDefinicionId = definicion.AlertaDefinicionId
	WHERE ReferenciaProcesoId = @solicitudViaticoId
)