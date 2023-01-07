SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ================================================
-- Author:		Javier Elías
-- Create date: 12/06/2020
-- Description:	Función para obtener las Ctas de pasajes 
--						para la Póliza Comprometido
-- ================================================
CREATE FUNCTION [dbo].[fn_getCtasPasajePolizaComprometido] (@asignacionId INT)
RETURNS TABLE 
AS
RETURN 
(
	SELECT ObjetoGastoId,
		   Concepto,
		   SUM(Costo) AS Costo,
		   CASE WHEN AsignadoAFuncionario = 1 THEN 1 ELSE 2 END AS Comprueba
	FROM tblSolicitudViaticoAsignacionPasaje AS pasajes
		 INNER JOIN tblConceptoViatico AS concepto ON pasajes.ConceptoViaticoId = concepto.ConceptoViaticoId
	WHERE AsignacionId = @asignacionId
		  AND pasajes.EstatusId = 1000000
	GROUP BY ObjetoGastoId,
			 pasajes.ConceptoViaticoId,
			 Concepto,
			 AsignadoAFuncionario
)