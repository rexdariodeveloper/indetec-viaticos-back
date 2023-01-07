package com.pixvs.viaticos.model.viaticos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tblSolicitudViaticoComprobacionDetalle")
public class SolicitudViaticoComprobacionDetalle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SolicitudViaticoComprobacionDetalleId", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "SolicitudViaticoComprobacionId", nullable = false)
    private Integer solicitudViaticoComprobacionId;

    @OneToOne
    @JoinColumn(name="SolicitudViaticoComprobacionId", referencedColumnName = "SolicitudViaticoComprobacionId", insertable = false, updatable = false)
    private SolicitudViaticoComprobacion solicitudViaticoComprobacion;

    @Column(name = "CategoriaId", nullable = true)
    private Integer categoriaId;

    @OneToOne
    @JoinColumn(name="CategoriaId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM categoria;

    @Column(name = "ConceptoViaticoId", nullable = false)
    private Integer conceptoViaticoId;

    @OneToOne
    @JoinColumn(name="ConceptoViaticoId", referencedColumnName = "ConceptoViaticoId", insertable = false, updatable = false)
    private ConceptoViatico conceptoViatico;

    @Column(name = "TipoComprobanteId", nullable = true)
    private Integer tipoComprobanteId;

    @OneToOne
    @JoinColumn(name="TipoComprobanteId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM tipoComprobante;

    @Column(name = "RFC", nullable = true)
    private String rfc;

    @Column(name = "RazonSocial", nullable = true)
    private String razonSocial;

    @Column(name = "ProveedorId", nullable = true)
    private BigDecimal proveedorId;

    @Column(name = "ProveedorPaisId", nullable = true)
    private String proveedorPaisId;

    @Column(name = "FechaComprobante", nullable = true)
    private Timestamp fechaComprobante;

    @Column(name = "Folio", nullable = true)
    private String folio;

    @Column(name = "FormaPagoId", nullable = true)
    private Integer formaPagoId;

    @OneToOne
    @JoinColumn(name="FormaPagoId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM formaPago;

    @Column(name = "MonedaId", nullable = true)
    private Integer monedaId;

    @Column(name = "TipoCambio", nullable = true)
    private BigDecimal tipoCambio;

    @Column(name = "Importe", nullable = true)
    private BigDecimal importe;

    @Column(name = "ImportePesos", nullable = false)
    private BigDecimal importePesos;

    @Column(name = "EsComprobadoPorRM", nullable = false)
    private Boolean esComprobadoPorRM;

    @Column(name = "AsignacionViaticoId", nullable = true)
    private Integer asignacionViaticoId;

    @Column(name = "AsignacionPasajeId", nullable = true)
    private Integer asignacionPasajeId;

    @Column(name = "Comentarios", nullable = true)
    private String comentarios;

    @Column(name = "EstatusId", nullable = false)
    private Integer estatusId;

    @OneToOne
    @JoinColumn(name="EstatusId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM estatus;

    @Column(name = "FechaRegistro", nullable = false)
    private Timestamp fechaRegistro;

    @Column(name = "RegistradoPorId", nullable = false)
    private Integer registradoPorId;

    @Column(name = "FechaUltimaModificacion", nullable = true)
    private Timestamp fechaUltimaModificacion;

    @Column(name = "ModificadoPorId", nullable = true)
    private Integer modificadoPorId;

    @Column(name = "Timestamp", insertable = false,updatable = false, nullable = false)
    private String timestamp;

    @Column(name = "TotalFactura", nullable = true)
    private BigDecimal totalFactura;

    @Column(name = "SubTotalFactura", nullable = true)
    private BigDecimal subTotalFactura;

    @Column(name = "DescuentoFactura", nullable = true)
    private BigDecimal descuentoFactura;

    @Column(name = "NumeroPartida", nullable = true)
    private Integer numeroPartida;

    @Column(name = "ConceptoDescripcion", nullable = true)
    private String conceptoDescripcion;

    @Column(name = "ClaveProdServ", nullable = true)
    private String claveProdServ;

    @Column(name = "ConceptoImporte", nullable = true)
    private BigDecimal conceptoImporte;

    @Column(name = "ConceptoDescuento", nullable = true)
    private BigDecimal conceptoDescuento;

    @Column(name = "SubTotalComprobacion", nullable = true)
    private BigDecimal subTotalComprobacion;

    @Column(name = "DescuentoComprobacion", nullable = true)
    private BigDecimal descuentoComprobacion;

    @Column(name = "CuentaPagoGastoId", nullable = true)
    private Integer cuentaPagoGastoId;

    @Column(name = "FormaComprobacionId", nullable = true)
    private Integer formaComprobacionId;

    @OneToOne
    @JoinColumn(name="FormaComprobacionId", referencedColumnName = "ControlId", insertable = false, updatable = false)
    private ListadoCMM formaComprobacion;

    @Column(name = "UUID", nullable = true)
    private String uuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSolicitudViaticoComprobacionId() {
        return solicitudViaticoComprobacionId;
    }

    public void setSolicitudViaticoComprobacionId(Integer solicitudViaticoComprobacionId) {
        this.solicitudViaticoComprobacionId = solicitudViaticoComprobacionId;
    }

    public SolicitudViaticoComprobacion getSolicitudViaticoComprobacion() {
        return solicitudViaticoComprobacion;
    }

    public void setSolicitudViaticoComprobacion(SolicitudViaticoComprobacion solicitudViaticoComprobacion) {
        this.solicitudViaticoComprobacion = solicitudViaticoComprobacion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public ListadoCMM getCategoria() {
        return categoria;
    }

    public void setCategoria(ListadoCMM categoria) {
        this.categoria = categoria;
    }

    public Integer getConceptoViaticoId() {
        return conceptoViaticoId;
    }

    public void setConceptoViaticoId(Integer conceptoViaticoId) {
        this.conceptoViaticoId = conceptoViaticoId;
    }

    public ConceptoViatico getConceptoViatico() {
        return conceptoViatico;
    }

    public void setConceptoViatico(ConceptoViatico conceptoViatico) {
        this.conceptoViatico = conceptoViatico;
    }

    public Integer getTipoComprobanteId() {
        return tipoComprobanteId;
    }

    public void setTipoComprobanteId(Integer tipoComprobanteId) {
        this.tipoComprobanteId = tipoComprobanteId;
    }

    public ListadoCMM getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(ListadoCMM tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public BigDecimal getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(BigDecimal proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedorPaisId() {
        return proveedorPaisId;
    }

    public void setProveedorPaisId(String proveedorPaisId) {
        this.proveedorPaisId = proveedorPaisId;
    }

    public Timestamp getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Timestamp fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(Integer formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public ListadoCMM getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(ListadoCMM formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(Integer monedaId) {
        this.monedaId = monedaId;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getImportePesos() {
        return importePesos;
    }

    public void setImportePesos(BigDecimal importePesos) {
        this.importePesos = importePesos;
    }

    public Boolean getEsComprobadoPorRM() {
        return esComprobadoPorRM;
    }

    public void setEsComprobadoPorRM(Boolean esComprobadoPorRM) {
        this.esComprobadoPorRM = esComprobadoPorRM;
    }

    public Integer getAsignacionViaticoId() {
        return asignacionViaticoId;
    }

    public void setAsignacionViaticoId(Integer asignacionViaticoId) {
        this.asignacionViaticoId = asignacionViaticoId;
    }

    public Integer getAsignacionPasajeId() {
        return asignacionPasajeId;
    }

    public void setAsignacionPasajeId(Integer asignacionPasajeId) {
        this.asignacionPasajeId = asignacionPasajeId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public ListadoCMM getEstatus() {
        return estatus;
    }

    public void setEstatus(ListadoCMM estatus) {
        this.estatus = estatus;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getRegistradoPorId() {
        return registradoPorId;
    }

    public void setRegistradoPorId(Integer registradoPorId) {
        this.registradoPorId = registradoPorId;
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

    public BigDecimal getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(BigDecimal totalFactura) {
        this.totalFactura = totalFactura;
    }

    public BigDecimal getSubTotalFactura() {
        return subTotalFactura;
    }

    public void setSubTotalFactura(BigDecimal subTotalFactura) {
        this.subTotalFactura = subTotalFactura;
    }

    public BigDecimal getDescuentoFactura() {
        return descuentoFactura;
    }

    public void setDescuentoFactura(BigDecimal descuentoFactura) {
        this.descuentoFactura = descuentoFactura;
    }

    public Integer getNumeroPartida() {
        return numeroPartida;
    }

    public void setNumeroPartida(Integer numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    public String getConceptoDescripcion() {
        return conceptoDescripcion;
    }

    public void setConceptoDescripcion(String conceptoDescripcion) {
        this.conceptoDescripcion = conceptoDescripcion;
    }

    public String getClaveProdServ() {
        return claveProdServ;
    }

    public void setClaveProdServ(String claveProdServ) {
        this.claveProdServ = claveProdServ;
    }

    public BigDecimal getConceptoImporte() {
        return conceptoImporte;
    }

    public void setConceptoImporte(BigDecimal conceptoImporte) {
        this.conceptoImporte = conceptoImporte;
    }

    public BigDecimal getConceptoDescuento() {
        return conceptoDescuento;
    }

    public void setConceptoDescuento(BigDecimal conceptoDescuento) {
        this.conceptoDescuento = conceptoDescuento;
    }

    public BigDecimal getSubTotalComprobacion() {
        return subTotalComprobacion;
    }

    public void setSubTotalComprobacion(BigDecimal subTotalComprobacion) {
        this.subTotalComprobacion = subTotalComprobacion;
    }

    public BigDecimal getDescuentoComprobacion() {
        return descuentoComprobacion;
    }

    public void setDescuentoComprobacion(BigDecimal descuentoComprobacion) {
        this.descuentoComprobacion = descuentoComprobacion;
    }

    public Integer getCuentaPagoGastoId() {
        return cuentaPagoGastoId;
    }

    public void setCuentaPagoGastoId(Integer cuentaPagoGastoId) {
        this.cuentaPagoGastoId = cuentaPagoGastoId;
    }

    public Integer getFormaComprobacionId() {
        return formaComprobacionId;
    }

    public void setFormaComprobacionId(Integer formaComprobacionId) {
        this.formaComprobacionId = formaComprobacionId;
    }

    public ListadoCMM getFormaComprobacion() {
        return formaComprobacion;
    }

    public void setFormaComprobacion(ListadoCMM formaComprobacion) {
        this.formaComprobacion = formaComprobacion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
