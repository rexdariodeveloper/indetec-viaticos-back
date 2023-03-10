
-- Borramos todas las alertas
DELETE FROM tblAlertaNotificacion
DELETE FROM tblAlertaAutorizacion
DELETE FROM tblAlerta
DELETE FROM tblAlertaDefinicion

--Insertamos los nuevos registros en tblAlertaDefinicion
SET IDENTITY_INSERT [dbo].[tblAlertaDefinicion] ON 
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (1, N'Solicitud Viáticos - Autorización', N'Solicitud Viaticos - Etapa Nueva - Accion Enviar - Autorizacion', 1, 13, N'app/viaticos/solicitudes/resumen/{id}/1', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000106, 1, 3, 4, 5, 0)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (2, N'Solicitud Viáticos - Notificación', N'Solicitud Viaticos - Etapa Nueva - Accion Cancelar - Notificación', 2, 13, N'app/viaticos/solicitudes/resumen/{id}/1', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', NULL, 0, NULL, NULL, NULL, 1)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (3, N'Solicitud Viáticos - Autorizada', N'Solicitud Viaticos - Etapa Validacion - Accion Autorizar', 3, 13, N'app/viaticos/solicitudes/resumen/{id}/1', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000103, 1, NULL, NULL, NULL, 0)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (4, N'Solicitud Viáticos - Rechazada', N'Solicitud Viaticos - Etapa Validacion - Accion Rechazar', 4, 13, N'app/viaticos/solicitudes/resumen/{id}/1', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000105, 1, NULL, NULL, NULL, 0)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (5, N'Solicitud Viáticos - En Revisión', N'Solicitud Viaticos - Etapa Validacion - Accion Revisión', 5, 13, N'app/viaticos/solicitudes/editar/{id}', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000104, 1, NULL, NULL, NULL, 0)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (6, N'Asignación Viaticos - Recursos Asignados', N'Asignación Viaticos - Recursos Asignados', 6, 14, NULL, N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000161, 1, NULL, NULL, NULL, 0)
GO
INSERT [dbo].[tblAlertaDefinicion] ([AlertaDefinicionId], [NombreCorto], [Descripcion], [AlertaEtapaAccionId], [NodoMenuId], [RutaAccion], [TablaReferencia], [CampoId], [CampoCodigo], [CampoEstadoRegistro], [NuevoEstado], [CambiarEstatusATramite], [EtapaAccionAlAutorizarId], [EtapaAccionAlRechazarId], [EtapaAccionAlRevisionId], [Borrado]) VALUES (7, N'Asignación Viaticos - En revisión', N'Asignación Viaticos - En revisión', 7, 14, N'app/viaticos/solicitudes/editar/{id}', N'tblSolicitudViatico', N'SolicitudViaticoId', N'NumeroSolicitud', N'EstatusId', 1000104, 1, NULL, NULL, NULL, 0)
GO
SET IDENTITY_INSERT [dbo].[tblAlertaDefinicion] OFF
GO

