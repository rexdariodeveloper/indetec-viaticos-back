ALTER TABLE tblSolicitudViatico
ADD 
	ProgramaGobierno		NVARCHAR(250) NULL,
    Proyecto						NVARCHAR(250) NULL,
    Ramo							NVARCHAR(250) NULL,
    Dependencia				NVARCHAR(250) NULL,
    TipoViaje						NVARCHAR(MAX) NULL,
    TipoRepresentacion	NVARCHAR(MAX) NULL,
	AutorizadoPorId			INT NULL,
	FechaAutorizacion		DATETIME NULL
GO

ALTER TABLE [dbo].[tblSolicitudViatico]  WITH CHECK ADD  CONSTRAINT [FK_tblSolicitudViatico_tblUsuario2] FOREIGN KEY([AutorizadoPorId])
REFERENCES [dbo].[tblUsuario] ([UsuarioId])
GO

ALTER TABLE [dbo].[tblSolicitudViatico] CHECK CONSTRAINT [FK_tblSolicitudViatico_tblUsuario2]
GO

UPDATE solicitud
  SET
      ProgramaGobierno = programa.Nombre,
      Proyecto = proyecto.Nombre,
      Ramo = ramo.Nombre,
      Dependencia = dependencia.Nombre,
      TipoViaje = tipoVIaje.Valor,
      TipoRepresentacion = tipoRepresentacion.Valor
FROM tblSolicitudViatico AS solicitud
     INNER JOIN tblListadoCMM AS tipoVIaje ON solicitud.TipoViajeId = tipoViaje.ControlId
     INNER JOIN tblListadoCMM AS tipoRepresentacion ON solicitud.TipoRepresentacionId = tipoRepresentacion.ControlId
     INNER JOIN SACG000000B.dbo.tblProgramaGobierno AS programa ON solicitud.ProgramaGobiernoId = programa.ProgramaGobiernoId
     INNER JOIN SACG000000B.dbo.tblProyecto AS proyecto ON solicitud.ProyectoId = proyecto.ProyectoId
     INNER JOIN SACG000000B.dbo.tblRamo AS ramo ON solicitud.RamoId = ramo.RamoId
     INNER JOIN SACG000000B.dbo.tblDependencia AS dependencia ON solicitud.DependenciaId = dependencia.DependenciaId
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN ProgramaGobierno NVARCHAR(250) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN Proyecto NVARCHAR(250) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN Ramo NVARCHAR(250) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN Dependencia NVARCHAR(250) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN TipoViaje NVARCHAR(MAX) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN TipoRepresentacion NVARCHAR(MAX) NOT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN AutorizadoPorId INT NULL
GO

ALTER TABLE tblSolicitudViatico ALTER COLUMN FechaAutorizacion DATETIME NULL
GO