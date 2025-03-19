package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;

public class ExportacionIntercompanyPosicionTO {
	private int id;
	private int idPedido;
	private int idProducto;
	private String codigoSAP;
	private String codigoPedido;
	private String descripcionProducto;
	private String descripcionComercialProducto;
	private int cantidad;
	private BigDecimal cantidadVenta;
	private int idUnidadMedida;
	private String codigoSapUnidadMedida;
	private String descripcionUnidadMedida;
	private int idUnidadMedidaVenta;
	private String codigoSAPUnidadMedidaVenta;
	private String descripcionUnidadMedidaVenta;
	private int idCentro;
	private String codigoSapCentro;
	private String descripcionCentro;
	private int idAlmacen;
	private String codigoSapAlmacen;
	private String descripcionAlmacen;
	private BigDecimal pesoTonelada;
	private int cantidadTonelada;
	private Date fechaDisponibilidad;
	private BigDecimal precioUnitario;
	private BigDecimal precioUnitarioSap;
	private BigDecimal importe;
	private int estado;
    private String mensajeSap;
    private String bloqueo;
    private int cantidadSaldo;
    private String codigoMoneda;
    private String codigoPartidaArancelaria;
    private String entrega;
    private String folio;
    private int idExportacion;
    private int idMoneda;
    private int idPartidaArancelaria;
    private String nombreMoneda;
    private String nombrePartidaArancelaria;
    private String pedidoSap;
    private String referencia;
    private String unidadMedidaVenta;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getDescripcionComercialProducto() {
		return descripcionComercialProducto;
	}
	public void setDescripcionComercialProducto(String descripcionComercialProducto) {
		this.descripcionComercialProducto = descripcionComercialProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	public String getCodigoSapUnidadMedida() {
		return codigoSapUnidadMedida;
	}
	public void setCodigoSapUnidadMedida(String codigoSapUnidadMedida) {
		this.codigoSapUnidadMedida = codigoSapUnidadMedida;
	}
	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}
	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}
	public int getIdUnidadMedidaVenta() {
		return idUnidadMedidaVenta;
	}
	public void setIdUnidadMedidaVenta(int idUnidadMedidaVenta) {
		this.idUnidadMedidaVenta = idUnidadMedidaVenta;
	}
	
	public String getCodigoSAPUnidadMedidaVenta() {
		return codigoSAPUnidadMedidaVenta;
	}
	public void setCodigoSAPUnidadMedidaVenta(String codigoSAPUnidadMedidaVenta) {
		this.codigoSAPUnidadMedidaVenta = codigoSAPUnidadMedidaVenta;
	}
	public String getDescripcionUnidadMedidaVenta() {
		return descripcionUnidadMedidaVenta;
	}
	public void setDescripcionUnidadMedidaVenta(String descripcionUnidadMedidaVenta) {
		this.descripcionUnidadMedidaVenta = descripcionUnidadMedidaVenta;
	}
	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	public String getCodigoSapCentro() {
		return codigoSapCentro;
	}
	public void setCodigoSapCentro(String codigoSapCentro) {
		this.codigoSapCentro = codigoSapCentro;
	}
	public String getDescripcionCentro() {
		return descripcionCentro;
	}
	public void setDescripcionCentro(String descripcionCentro) {
		this.descripcionCentro = descripcionCentro;
	}
	public int getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public String getCodigoSapAlmacen() {
		return codigoSapAlmacen;
	}
	public void setCodigoSapAlmacen(String codigoSapAlmacen) {
		this.codigoSapAlmacen = codigoSapAlmacen;
	}
	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}
	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}
	public BigDecimal getPesoTonelada() {
		return pesoTonelada;
	}
	public void setPesoTonelada(BigDecimal pesoTonelada) {
		this.pesoTonelada = pesoTonelada;
	}
	public int getCantidadTonelada() {
		return cantidadTonelada;
	}
	public void setCantidadTonelada(int cantidadTonelada) {
		this.cantidadTonelada = cantidadTonelada;
	}
	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}
	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public BigDecimal getPrecioUnitarioSap() {
		return precioUnitarioSap;
	}
	public void setPrecioUnitarioSap(BigDecimal precioUnitarioSap) {
		this.precioUnitarioSap = precioUnitarioSap;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getMensajeSap() {
		return mensajeSap;
	}
	public void setMensajeSap(String mensajeSap) {
		this.mensajeSap = mensajeSap;
	}
	public String getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
	public int getCantidadSaldo() {
		return cantidadSaldo;
	}
	public void setCantidadSaldo(int cantidadSaldo) {
		this.cantidadSaldo = cantidadSaldo;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public String getCodigoPartidaArancelaria() {
		return codigoPartidaArancelaria;
	}
	public void setCodigoPartidaArancelaria(String codigoPartidaArancelaria) {
		this.codigoPartidaArancelaria = codigoPartidaArancelaria;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public int getIdPartidaArancelaria() {
		return idPartidaArancelaria;
	}
	public void setIdPartidaArancelaria(int idPartidaArancelaria) {
		this.idPartidaArancelaria = idPartidaArancelaria;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public String getNombrePartidaArancelaria() {
		return nombrePartidaArancelaria;
	}
	public void setNombrePartidaArancelaria(String nombrePartidaArancelaria) {
		this.nombrePartidaArancelaria = nombrePartidaArancelaria;
	}
	public String getPedidoSap() {
		return pedidoSap;
	}
	public void setPedidoSap(String pedidoSap) {
		this.pedidoSap = pedidoSap;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getUnidadMedidaVenta() {
		return unidadMedidaVenta;
	}
	public void setUnidadMedidaVenta(String unidadMedidaVenta) {
		this.unidadMedidaVenta = unidadMedidaVenta;
	}
}
