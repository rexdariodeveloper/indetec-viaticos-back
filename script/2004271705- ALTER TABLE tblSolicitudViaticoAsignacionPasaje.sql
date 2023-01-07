
EXEC sp_rename 'tblConceptoViatico.ConceptoViatico', 'ConceptoViaticoId', 'COLUMN';
GO

ALTER TABLE tblSolicitudViaticoAsignacionPasaje
ADD ConceptoViaticoId INT NULL
GO

ALTER TABLE tblSolicitudViaticoAsignacionPasaje  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViaticoAsignacionPasaje_tblConceptoViatico] FOREIGN KEY([ConceptoViaticoId])
REFERENCES [dbo].[tblConceptoViatico] ([ConceptoViaticoId])
GO