package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;

public class ExportacionPedidoTO {

	private int idExportacion;
	private int idPosicion;
	private String descripcionComercial;
	private int idAlmacen;
	private int cantidad;
	private BigDecimal importe;
	private String pedidoSap;
	private String folio;
	private int idPartidaArancelaria;
	private Date fechaDisponibilidad;
	private int unidadMedidaVenta;
	private int unidadMedida;
	private int unidadMedidaConversion;
	private BigDecimal cantidadVenta;
	private BigDecimal cantidadConversion;
	private BigDecimal pesoTonelada;
	private BigDecimal precioUnitarioConversion;
	private BigDecimal precioUnitario;
	private int itemSap;
	private int idMoneda;
	private String mensajeSap;
	private String entrega;
	private String referencia;
	private String componenteTexto;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;
	private Short estado;
	private String pedidoPCP;
	public int getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public int getIdPosicion() {
		return idPosicion;
	}
	public void setIdPosicion(int idPosicion) {
		this.idPosicion = idPosicion;
	}
	public String getDescripcionComercial() {
		return descripcionComercial;
	}
	public void setDescripcionComercial(String descripcionComercial) {
		this.descripcionComercial = descripcionComercial;
	}
	public int getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getPedidoSap() {
		return pedidoSap;
	}
	public void setPedidoSap(String pedidoSap) {
		this.pedidoSap = pedidoSap;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getIdPartidaArancelaria() {
		return idPartidaArancelaria;
	}
	public void setIdPartidaArancelaria(int idPartidaArancelaria) {
		this.idPartidaArancelaria = idPartidaArancelaria;
	}
	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}
	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}
	public int getUnidadMedidaVenta() {
		return unidadMedidaVenta;
	}
	public void setUnidadMedidaVenta(int unidadMedidaVenta) {
		this.unidadMedidaVenta = unidadMedidaVenta;
	}
	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}
	public BigDecimal getCantidadConversion() {
		return cantidadConversion;
	}
	public void setCantidadConversion(BigDecimal cantidadConversion) {
		this.cantidadConversion = cantidadConversion;
	}
	public BigDecimal getPrecioUnitarioConversion() {
		return precioUnitarioConversion;
	}
	public void setPrecioUnitarioConversion(BigDecimal precioUnitarioConversion) {
		this.precioUnitarioConversion = precioUnitarioConversion;
	}
	public int getItemSap() {
		return itemSap;
	}
	public void setItemSap(int itemSap) {
		this.itemSap = itemSap;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public String getMensajeSap() {
		return mensajeSap;
	}
	public void setMensajeSap(String mensajeSap) {
		this.mensajeSap = mensajeSap;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getComponenteTexto() {
		return componenteTexto;
	}
	public void setComponenteTexto(String componenteTexto) {
		this.componenteTexto = componenteTexto;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Short getEstado() {
		return estado;
	}
	public void setEstado(Short estado) {
		this.estado = estado;
	}
	public String getPedidoPCP() {
		return pedidoPCP;
	}
	public void setPedidoPCP(String pedidoPCP) {
		this.pedidoPCP = pedidoPCP;
	}
	public BigDecimal getPesoTonelada() {
		return pesoTonelada;
	}
	public void setPesoTonelada(BigDecimal pesoTonelada) {
		this.pesoTonelada = pesoTonelada;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getUnidadMedidaConversion() {
		return unidadMedidaConversion;
	}
	public void setUnidadMedidaConversion(int unidadMedidaConversion) {
		this.unidadMedidaConversion = unidadMedidaConversion;
	}

}
