SET IDENTITY_INSERT [dbo].[tblListadoCMM] ON 
GO

-- =============================================
-- Tipo Pasaje
-- =============================================

INSERT [dbo].[tblListadoCMM] ([ControlId], [Nombre], [Valor], [Sistema], [Activo], [ControlSencillo], [ModuloId], [FechaCreacion]) 
VALUES (1000022, 'TipoPasaje', 'Aéreo', 1, 1, 0, NULL, GETDATE())
GO
INSERT [dbo].[tblListadoCMM] ([ControlId], [Nombre], [Valor], [Sistema], [Activo], [ControlSencillo], [ModuloId], [FechaCreacion]) 
VALUES (1000023, 'TipoPasaje', 'Marítimo', 1, 1, 0, NULL, GETDATE())
GO
INSERT [dbo].[tblListadoCMM] ([ControlId], [Nombre], [Valor], [Sistema], [Activo], [ControlSencillo], [ModuloId], [FechaCreacion]) 
VALUES (1000024, 'TipoPasaje', 'Terrestre', 1, 1, 0, NULL, GETDATE())
GO

SET IDENTITY_INSERT [dbo].[tblListadoCMM] OFF
GO