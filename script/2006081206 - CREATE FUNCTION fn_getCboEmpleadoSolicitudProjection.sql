SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================================
-- Author:		Javier Elías
-- Create date: 08/06/2020
-- Description:	Función para obtener el ComboEmpleadoSolicitudProjection
-- =============================================================
CREATE FUNCTION [dbo].[fn_getCboEmpleadoSolicitudProjection] ( @empleadoId INT )
RETURNS TABLE 
AS
RETURN 
(
	SELECT EmpleadoId AS Id,
		   dbo.getNombreCompletoEmpleado(EmpleadoId) AS Nombre,
		   puesto.PuestoId,
		   puesto.Clave+' - '+puesto.Nombre AS Puesto,
		   cargo.CargoId,
		   cargo.Nombre AS Cargo,
		   AreaAdscripcionId,
		   area.Clave+' - '+area.Descripcion AS AreaAdscripcion,
		   Fotografia
	FROM tblEmpleado AS empleado
		 INNER JOIN tblListadoPuesto AS puesto ON empleado.PuestoId = puesto.PuestoId
		 INNER JOIN tblListadoCargo AS cargo ON empleado.CargoId = cargo.CargoId
		 INNER JOIN tblOrganigrama AS area ON AreaAdscripcionId = NodoId
	WHERE empleado.EstatusId NOT IN(1000001, 1000002)
		 AND empleado.EmpleadoId = ISNULL(@empleadoId, empleado.EmpleadoId)
)