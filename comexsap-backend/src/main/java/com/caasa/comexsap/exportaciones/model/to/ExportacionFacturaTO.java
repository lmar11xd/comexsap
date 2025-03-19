package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ExportacionFacturaTO {

	private int id;
	private int idExportacion;
	private int idPedido;
	private String codigoPedido;
	private String factura;
	private String etiquetaTotal;
	private String etiquetaFlete;
	private String etiquetaImporteTotal;
	private String etiquetaUnidadMedida;
	private BigDecimal montoFlete;
	private BigDecimal montoImporteTotal;
	private BigDecimal montoTotal;
	private String usuarioCreacion;
	private Short estado;
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
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getEtiquetaTotal() {
		return etiquetaTotal;
	}
	public void setEtiquetaTotal(String etiquetaTotal) {
		this.etiquetaTotal = etiquetaTotal;
	}
	public String getEtiquetaFlete() {
		return etiquetaFlete;
	}
	public void setEtiquetaFlete(String etiquetaFlete) {
		this.etiquetaFlete = etiquetaFlete;
	}
	public String getEtiquetaImporteTotal() {
		return etiquetaImporteTotal;
	}
	public void setEtiquetaImporteTotal(String etiquetaImporteTotal) {
		this.etiquetaImporteTotal = etiquetaImporteTotal;
	}
	public String getEtiquetaUnidadMedida() {
		return etiquetaUnidadMedida;
	}
	public void setEtiquetaUnidadMedida(String etiquetaUnidadMedida) {
		this.etiquetaUnidadMedida = etiquetaUnidadMedida;
	}
	public BigDecimal getMontoFlete() {
		return montoFlete;
	}
	public void setMontoFlete(BigDecimal montoFlete) {
		this.montoFlete = montoFlete;
	}
	public BigDecimal getMontoImporteTotal() {
		return montoImporteTotal;
	}
	public void setMontoImporteTotal(BigDecimal montoImporteTotal) {
		this.montoImporteTotal = montoImporteTotal;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Short getEstado() {
		return estado;
	}
	public void setEstado(Short estado) {
		this.estado = estado;
	}
}
