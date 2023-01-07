package com.pixvs.viaticos.model.viaticos;

import com.pixvs.viaticos.model.viaticos.projection.Archivo.InfoArchivoProjection;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblConfiguracionEnte")
public class ConfiguracionEnte {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ConfiguracionEnteId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NombreEnte", nullable = false)
    private String nombreEnte;

    @Column(name = "CorreoElectronico")
    private String correoElectronico;

    @Column(name = "PaginaWEB")
    private String paginaWeb;

    @Column(name = "Domicilio")
    private String domicilio;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Colonia")
    private String colonia;

    @Column(name = "PaisId", nullable = false)
    private Integer paisId;

    @Column(name = "EstadoId", nullable = false)
    private Integer estadoId;

    @Column(name = "CiudadId", nullable = false)
    private Integer ciudadId;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "MonedaPredeterminadaId", nullable = false)
    private Integer monedaPredeterminadaId;

    @Column(name = "PorcentajeSinComprobante", nullable = false)
    private Integer porcentajeSinComprobante;

    @Column(name = "MontoAnualSinComprobante", nullable = false)
    private Integer montoAnualSinComprobante;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "CreadoPorId", nullable = false, updatable = false)
    private Integer creadoPorId;

    @Column(name = "FechaUltimaModificacion", nullable = false)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId")
    private Integer modificadoPorId;

    @Column(name = "Timestamp", nullable = false, insertable = false, updatable = false)
    private String timestamp;

    @Column(name = "Fotografia")
    private String fotografia;

    @Transient
    private Boolean fotografiaEliminada;

    @Transient
    private String nombreArchivoTemporal;

    @Transient
    private InfoArchivoProjection archivoFotografia;

    @Column(name = "NombreUsuario")
    private String nombreUsuario;

    @Column(name = "Email")
    private String email;

    @Column(name = "Contrasenia")
    private String contrasenia;

    @Column(name = "Host")
    private String host;

    @Column(name = "Puerto")
    private Integer puerto;

    @Column(name = "NormativaViaticos", nullable = false)
    private String normativaViaticos;

    @Column(name = "AreaResponsableTransparencia", nullable = false)
    private String areaResponsableTransparencia;

    @Column(name = "Protocolo", nullable = false)
    private Integer protocolo;

    @Transient
    private String nombreNormativaViaticosArchivoTemp;

    @Transient
    private InfoArchivoProjection archivoNormativaViaticos;

    @Column(name = "DirectorioPublico")
    private String directorioPublico;

    @Column(name = "URLPublica", nullable = false)
    private String urlPublica;

    @Column(name = "DirectorioRemoto")
    private boolean directorioRemoto;

    @Column(name = "DirectorioFTP")
    private String directorioFTP;

    @Column(name = "ProtocoloFTP")
    private Integer protocoloFTP;

    @Column(name = "ServidorFTP", nullable = true)
    private String servidorFTP;

    @Column(name = "UsuarioFTP", nullable = true)
    private String usuarioFTP;

    @Column(name = "ContraseniaFTP", nullable = true)
    private String contraseniaFTP;

    @Column(name = "PuertoFTP", nullable = true)
    private Integer puertoFTP;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEnte() {
        return nombreEnte;
    }

    public void setNombreEnte(String nombreEnte) {
        this.nombreEnte = nombreEnte;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCreadoPorId() {
        return creadoPorId;
    }

    public void setCreadoPorId(Integer creadoPorId) {
        this.creadoPorId = creadoPorId;
    }

    public Timestamp getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Timestamp fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Integer getModificadoPorId() {
        return modificadoPorId;
    }

    public void setModificadoPorId(Integer modificadoPorId) {
        this.modificadoPorId = modificadoPorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getMonedaPredeterminadaId() {
        return monedaPredeterminadaId;
    }

    public void setMonedaPredeterminadaId(Integer monedaPredeterminadaId) {
        this.monedaPredeterminadaId = monedaPredeterminadaId;
    }

    public Integer getPorcentajeSinComprobante() {
        return porcentajeSinComprobante;
    }

    public void setPorcentajeSinComprobante(Integer porcentajeSinComprobante) {
        this.porcentajeSinComprobante = porcentajeSinComprobante;
    }

    public Integer getMontoAnualSinComprobante() {
        return montoAnualSinComprobante;
    }

    public void setMontoAnualSinComprobante(Integer montoAnualSinComprobante) {
        this.montoAnualSinComprobante = montoAnualSinComprobante;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Boolean getFotografiaEliminada() {
        return fotografiaEliminada;
    }

    public void setFotografiaEliminada(Boolean fotografiaEliminada) {
        this.fotografiaEliminada = fotografiaEliminada;
    }

    public String getNombreArchivoTemporal() {
        return nombreArchivoTemporal;
    }

    public void setNombreArchivoTemporal(String nombreArchivoTemporal) {
        this.nombreArchivoTemporal = nombreArchivoTemporal;
    }

    public InfoArchivoProjection getArchivoFotografia() {
        return archivoFotografia;
    }

    public void setArchivoFotografia(InfoArchivoProjection archivoFotografia) {
        this.archivoFotografia = archivoFotografia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getNormativaViaticos() {
        return normativaViaticos;
    }

    public void setNormativaViaticos(String normativaViaticos) {
        this.normativaViaticos = normativaViaticos;
    }

    public String getNombreNormativaViaticosArchivoTemp() {
        return nombreNormativaViaticosArchivoTemp;
    }

    public void setNombreNormativaViaticosArchivoTemp(String nombreNormativaViaticosArchivoTemp) {
        this.nombreNormativaViaticosArchivoTemp = nombreNormativaViaticosArchivoTemp;
    }

    public InfoArchivoProjection getArchivoNormativaViaticos() {
        return archivoNormativaViaticos;
    }

    public void setArchivoNormativaViaticos(InfoArchivoProjection archivoNormativaViaticos) {
        this.archivoNormativaViaticos = archivoNormativaViaticos;
    }

    public String getAreaResponsableTransparencia() {
        return areaResponsableTransparencia;
    }

    public void setAreaResponsableTransparencia(String areaResponsableTransparencia) {
        this.areaResponsableTransparencia = areaResponsableTransparencia;
    }

    public Integer getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Integer protocolo) {
        this.protocolo = protocolo;
    }

    public String getDirectorioPublico() {
        return directorioPublico;
    }

    public void setDirectorioPublico(String directorioPublico) {
        this.directorioPublico = directorioPublico;
    }

    public String getUrlPublica() {
        return urlPublica;
    }

    public void setUrlPublica(String urlPublica) {
        this.urlPublica = urlPublica;
    }

    public boolean isDirectorioRemoto() {
        return directorioRemoto;
    }

    public void setDirectorioRemoto(boolean directorioRemoto) {
        this.directorioRemoto = directorioRemoto;
    }

    public String getDirectorioFTP() {
        return directorioFTP;
    }

    public void setDirectorioFTP(String directorioFTP) {
        this.directorioFTP = directorioFTP;
    }

    public Integer getProtocoloFTP() {
        return protocoloFTP;
    }

    public void setProtocoloFTP(Integer protocoloFTP) {
        this.protocoloFTP = protocoloFTP;
    }

    public String getServidorFTP() {
        return servidorFTP;
    }

    public void setServidorFTP(String servidorFTP) {
        this.servidorFTP = servidorFTP;
    }

    public String getUsuarioFTP() {
        return usuarioFTP;
    }

    public void setUsuarioFTP(String usuarioFTP) {
        this.usuarioFTP = usuarioFTP;
    }

    public String getContraseniaFTP() {
        return contraseniaFTP;
    }

    public void setContraseniaFTP(String contraseniaFTP) {
        this.contraseniaFTP = contraseniaFTP;
    }

    public Integer getPuertoFTP() {
        return puertoFTP;
    }

    public void setPuertoFTP(Integer puertoFTP) {
        this.puertoFTP = puertoFTP;
    }
}
