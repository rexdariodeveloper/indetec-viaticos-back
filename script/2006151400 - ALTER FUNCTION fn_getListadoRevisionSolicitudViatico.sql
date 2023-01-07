USE [Viaticos]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_getListadoRevisionSolicitudViatico]    Script Date: 6/15/2020 1:56:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author: Rene Carrillo
-- Create date: --/--/--
-- Modified date: 15/06/2020
-- Description:	Agregar un permiso de comprobacion
-- =============================================
ALTER FUNCTION [dbo].[fn_getListadoRevisionSolicitudViatico] (@usuarioId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT sv.SolicitudViaticoId AS id,
		   sv.NumeroSolicitud AS NumeroSolicitud,
		   dbo.getNombreCompletoCiudad(sv.SolicitudViaticoId, 1) AS ciudadDestino,
		   sv.FechaCreacion AS fechaSolicitud,
		   sv.fechaSalida AS fechaViaje,
		   (ISNULL(svap.CostoTotal, 0) + ISNULL(svav.MontoPorTransferirTotal, 0)) AS montoAsignado,
		   ISNULL(svav.MontoPorTransferirTotal, 0) AS montoTransferido,
		   Valor AS estatus
	FROM tblSolicitudViatico sv
		 INNER JOIN tblListadoCMM ON sv.EstatusId = ControlId
		 INNER JOIN tblSolicitudViaticoAsignacion sva ON sva.SolicitudViaticoId = sv.SolicitudViaticoId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(MontoPorTransferir) AS MontoPorTransferirTotal,
				   EstatusId
			FROM tblSolicitudViaticoAsignacionViatico
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId,
					 EstatusId
		 ) svav ON svav.AsignacionId = sva.AsignacionId
		 LEFT JOIN
		 (
			SELECT AsignacionId,
				   SUM(Costo) AS CostoTotal,
				   EstatusId
			FROM tblSolicitudViaticoAsignacionPasaje
			WHERE EstatusId = 1000000
			GROUP BY AsignacionId,
					 EstatusId
		 ) svap ON svap.AsignacionId = sva.AsignacionId
		 INNER JOIN tblSolicitudViaticoComprobacion svc ON svc.SolicitudViaticoId = sv.SolicitudViaticoId
		 LEFT JOIN 
		 (
			SELECT UsuarioId
			FROM tblUsuario AS usuario
				 INNER JOIN tblRol AS rol ON usuario.RolId = rol.RolId AND rol.EstatusId = 1000000
				 INNER JOIN tblRolMenu AS rolMenu ON rol.RolId = rolMenu.RolId AND rolMenu.NodoMenuId = 15 AND rolMenu.EstatusId = 1000000
			WHERE UsuarioId = @usuarioId
		 ) AS permisoComprobacion ON permisoComprobacion.UsuarioId IS NOT NULL
	WHERE sv.EstatusId IN(1000161, 1000104, 1000107, 1000108)
		 AND svc.SolicitanteFinalizoComprobacion = 1
		 AND svc.RMFinalizoComprobacion = 1
		 AND (permisoComprobacion.UsuarioId IS NOT NULL OR sv.CreadoPorId = @usuarioId)
)