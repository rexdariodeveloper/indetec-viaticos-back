DELETE FROM tblMatrizViatico WHERE MatrizViaticoId IN (
	SELECT MatrizViaticoId
	FROM tblMatrizViatico AS matriz
		 INNER JOIN tblConceptoViatico AS concepto ON matriz.ConceptoViaticoId = concepto.ConceptoViaticoId
	WHERE concepto.CategoriaId != 1000058
)
GO