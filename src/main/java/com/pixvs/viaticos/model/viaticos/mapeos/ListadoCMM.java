package com.pixvs.viaticos.model.viaticos.mapeos;

public class ListadoCMM {

    public static final class EstatusRegistro {

        public static final int ACTIVO = 1000000;
        public static final int INACTIVO = 1000001;
        public static final int BORRADO = 1000002;
        public static final int AUTORIZADO = 1000022;
        public static final int RECHAZADO = 1000023;
        public static final int REVISION = 1000024;
        public static final int EN_ESPERA_AUTORIZACION = 1000025;

        public static final int LISTADO_PUESTO = 1000010;
        public static final int LISTADO_CARGO = 1000011;
    }

    public static final class TipoNotificacion {

        public static final int AUTORIZACION = 1000041;
        public static final int NOTIFICACION = 1000042;

    }

    public static final int RutaTemporalArchivo = 1000046;
    public static final int RutaRaizArchivo = 1000047;

    public static final class EstatusSolicitudViatico {
        public static final int ACTIVA = 1000100;
        public static final int BORRADA = 1000101;
        public static final int CANCELADA = 1000102;
        public static final int AUTORIZADA = 1000103;
        public static final int EN_REVISION = 1000104;
        public static final int RECHAZADA = 1000105;
        public static final int PROCESO_AUTORIZACION = 1000106;
        public static final int EN_REVISION_COMPROBACION = 1000107;
        public static final int REVISADA = 1000108;
        public static final int RECURSOS_ASIGNADOS = 1000161;
        public static final int ENVIADA_FINANZAS = 1000098;
        public static final int COMPROBACION_FINALIZADA = 1000099;
        public static final int EN_PROCESO_AUTORIZACION_REVISION = 1000096;
        public static final int AUTORIZACION_REVISION_APROBADA = 1000097;
    }

    public static  final class CategoriaViatico {
        public static final int VIATICO = 1000058;
        public static final int PASAJE = 1000059;
        public static final int REINTEGRO = 1000060;
        public static final int CARGO = 1000061;
    }

    public static  final class TipoArchivo {
        public static final int DOCUMENTOTEXTO = 1000151;
        public static final int HOJACALCULO = 1000152;
        public static final int PDF = 1000153;
        public static final int XML = 1000154;
        public static final int IMAGEN = 1000155;
        public static final int OTRO = 1000156;
    }

    public static final class AccionesAlerta {
        public static final int INICIAR = 1000150;
        public static final int AUTORIZAR = 1000157;
        public static final int REVISION = 1000158;
        public static final int RECHAZAR = 1000159;
        public static final int CANCELAR = 1000160;
        public static final int OCULTAR = 1000162;
    }

    public static final class ProtocoloSeguridadEmail {
        public static final int SSL = 1000189;
        public static final int TLS = 1000190;
    }

    public static final class ProtocoloServidorFTP {
        public static final int FTP = 1000014;
        public static final int FTPS = 1000015;
        public static final int SFTP = 1000016;
    }

    public static final class TipoComprobante{
        public static final int FACTURA_NACIONAL = 1000050;
        public static final int COMPROBANTE_EXTRANJERO = 1000051;
        public static final int SIN_COMPROBANTE = 1000052;
    }

    public static final class TipoPermisoAcceso {

        public static final int CREAR_SOLICITUDES_TERCEROS = 1000012;
        public static final int VISUALIZAR_SOLICITUDES_TERCEROS = 1000013;
    }

    public static final class FormaComprobacion {
        public static  final int FACTURA_COMPLETA = 1000208;
        public static  final int POR_DETALLES = 1000209;
    }
}
