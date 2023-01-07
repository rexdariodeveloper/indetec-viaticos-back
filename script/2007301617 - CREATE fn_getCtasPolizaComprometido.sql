/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2017 (14.0.1000)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2008
    Target Database Engine Edition : Microsoft SQL Server Express Edition
    Target Database Engine Type : Standalone SQL Server
*/
/****** Object:  UserDefinedFunction [dbo].[fn_getCtasPolizaComprometido]    Script Date: 21/01/2022 08:22:21 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[fn_getCtasPolizaComprometido]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
BEGIN
	DROP FUNCTION fn_getCtasPolizaComprometido
END
GO
execute dbo.sp_executesql @statement = N'-- =============================================================
-- Author:		Javier Elías
-- Create date: 12/06/2020
-- Modified by: Alex Gaytan
-- Modified date: 21/01/2022 
-- Description:	Función para obtener las Ctas para la Póliza Comprometido
-- =============================================================
CREATE FUNCTION [dbo].[fn_getCtasPolizaComprometido] (@asignacionId INT)
RETURNS TABLE 
AS
RETURN 
(
	-- Quien Comprueba es 1 para Solicitante y 2 para RM
	-- Tipo 1 para Viaticos y 2 para Pasajes

	-- Gasto de Viaticos Solicitante
	SELECT ObjetoGastoId,
		   viatico.ConceptoViaticoId,
		   Codigo+'' - ''+Concepto AS ConceptoViaticoNombre,
		   MontoPorTransferir AS ComprometerMonto,
		   1 AS QuienComprueba,
		   1 AS Tipo
	FROM tblSolicitudViaticoAsignacionViatico AS viatico
		 INNER JOIN tblConceptoViatico AS concepto ON viatico.ConceptoViaticoId = concepto.ConceptoViaticoId
		 AND viatico.EstatusId=1000000
	WHERE AsignacionId = @asignacionId
		  AND MontoPorTransferir != 0

	UNION ALL
	-- Gasto de Viaticos RM
	SELECT ObjetoGastoId,
		   viatico.ConceptoViaticoId,
		   Codigo+'' - ''+Concepto AS ConceptoViaticoNombre,
		   MontoConPernocta + MontoSinPernocta - MontoPorTransferir AS ComprometerMonto,
		   2 AS QuienComprueba,
		   1 AS Tipo
	FROM tblSolicitudViaticoAsignacionViatico AS viatico
		 INNER JOIN tblConceptoViatico AS concepto ON viatico.ConceptoViaticoId = concepto.ConceptoViaticoId
		 AND viatico.EstatusId=1000000
	WHERE AsignacionId = @asignacionId
		  AND MontoConPernocta + MontoSinPernocta - MontoPorTransferir != 0

	UNION ALL
	-- Gasto de Pasajes
	SELECT ObjetoGastoId,
		   pasajes.ConceptoViaticoId AS ConceptoId,
		   Codigo+'' - ''+Concepto AS ConceptoViaticoNombre,
		   SUM(Costo) AS ComprometerMonto,
		   CASE WHEN AsignadoAFuncionario = 1 THEN 1 ELSE 2 END AS QuienComprueba,
		   2 AS Tipo
	FROM tblSolicitudViaticoAsignacionPasaje AS pasajes
		 INNER JOIN tblConceptoViatico AS concepto ON pasajes.ConceptoViaticoId = concepto.ConceptoViaticoId
	WHERE AsignacionId = @asignacionId
		  AND pasajes.EstatusId = 1000000
	GROUP BY ObjetoGastoId,
			 pasajes.ConceptoViaticoId,
			 Codigo,
			 Concepto,
			 AsignadoAFuncionario
)' 
GO