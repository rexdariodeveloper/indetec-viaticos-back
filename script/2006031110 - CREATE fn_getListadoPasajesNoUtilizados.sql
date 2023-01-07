SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ==========================================================
-- Author:		Javier Elías
-- Create date: 03/06/2020
-- Description:	Función para obtener el listado de Pasajes no Utilizados
-- ==========================================================
CREATE FUNCTION [dbo].[fn_getListadoPasajesNoUtilizados]()
RETURNS TABLE 
AS
RETURN 
(
	SELECT pasaje.AsignacionPasajeId AS id,
			Concepto+' '+CASE WHEN ViajeRedondo = 1 THEN 'Redondo' ELSE 'Sencillo' END AS TipoViaje,
			dbo.getFechaConFormato(FechaCompra, 0) AS FechaCompra,
			NombreLinea,
			CodigoReservacion,
			dbo.getFechaConFormato(FechaSalida, 0)+ISNULL('/ '+dbo.getFechaConFormato(FechaRegreso, 0), '') AS FechaSalidaRegreso,
			Costo,
			CASE WHEN FechaSalida > GETDATE() THEN -1 ELSE 1 END * (ROW_NUMBER() OVER (ORDER BY FechaSalida DESC)) AS Orden
	FROM tblSolicitudViaticoAsignacionPasaje AS pasaje
			INNER JOIN tblConceptoViatico AS tipo ON pasaje.ConceptoViaticoId = tipo.ConceptoViaticoId
			LEFT JOIN tblPasajeUtilizado AS utilizado ON pasaje.AsignacionPasajeId = utilizado.AsignacionPasajeId
			LEFT JOIN 
			(
				SELECT DISTINCT
						detalle.AsignacionPasajeId
				FROM tblSolicitudViaticoComprobacionDetalle AS detalle
						INNER JOIN tblSolicitudViaticoComprobacionPasaje AS comprobacionPasaje ON detalle.SolicitudViaticoComprobacionDetalleId = comprobacionPasaje.SolicitudViaticoComprobacionDetalleId
				WHERE detalle.EstatusId = 1000000
						AND comprobacionPasaje.EstatusId = 1000000
			) AS comprobacion ON pasaje.AsignacionPasajeId = comprobacion.AsignacionPasajeId
	WHERE pasaje.EstatusId = 1000000
			AND AsignadoAFuncionario = 0
			AND utilizado.AsignacionPasajeId IS NULL 
			AND comprobacion.AsignacionPasajeId IS NULL
)