package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;

public class ExportacionTerrestrePosicionTO {
	private int id;
	private int idExportacion;
	private int idPedido;
	private int item;
	private int idProducto;
	private String codigoSap;
	private String descripcionProducto;
	private String descripcionComercialProducto;
	private int cantidad;
	private BigDecimal cantidadVenta;
	private int factor;
	private int idUnidadMedida;
	private String codigoSapUnidadMedida;
	private String descripcionUnidadMedida;
	private int idUnidadMedidaVenta;
	private String codigoSapUnidadMedidaVenta;
	private String descripcionUnidadMedidaVenta;
	private int idCentro;
	private String codigoSapCentro;
	private String descripcionCentro;
	private int idAlmacen;
	private String codigoSapAlmacen;
	private String descripcionAlmacen;
	private BigDecimal pesoTonelada;
	private Date fechaDisponibilidad;
	private BigDecimal precioUnitario;
	private BigDecimal precioUnitarioSap;
	private BigDecimal importe;
	private int idPadre;
	private int estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
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
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
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
	public String getCodigoSapUnidadMedidaVenta() {
		return codigoSapUnidadMedidaVenta;
	}
	public void setCodigoSapUnidadMedidaVenta(String codigoSapUnidadMedidaVenta) {
		this.codigoSapUnidadMedidaVenta = codigoSapUnidadMedidaVenta;
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
	public int getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}	
}
