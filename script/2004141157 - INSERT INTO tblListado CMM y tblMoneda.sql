INSERT INTO tblListadoCMM(Nombre,Valor,Sistema,Activo,ControlSencillo,FechaCreacion,CreadoPorId)
VALUES
---TipoRepresentacion
('TipoRepresentacion','Técnico',0,1,0,GETDATE(),1),
('TipoRepresentacion','Alto Nivel',0,1,0,GETDATE(),1),
---ZonaEconomica
('ZonaEconomica','Nacional más económica',0,1,0,GETDATE(),1),
('ZonaEconomica','Nacional menos económica',0,1,0,GETDATE(),1),
('ZonaEconomica','Internacional',0,1,0,GETDATE(),1);

INSERT INTO tblMoneda(Abreviacion,Nombre,Simbolo,Predeterminada,Activo,FechaCreacion,CreadoPorId)
VALUES
('EUR','Euro','€',0,1,GETDATE(),1);
