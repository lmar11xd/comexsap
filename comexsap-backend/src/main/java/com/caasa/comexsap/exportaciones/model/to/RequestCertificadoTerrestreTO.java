package com.caasa.comexsap.exportaciones.model.to;

public class RequestCertificadoTerrestreTO {
	private String textoAduana;
	private String textoCliente;
	private String textoDestinatario;
	private String factura;
	private float montoTotal;
	private String formaPago;
	private String textoFecha;
	public String getTextoAduana() {
		return textoAduana;
	}
	public void setTextoAduana(String textoAduana) {
		this.textoAduana = textoAduana;
	}
	public String getTextoCliente() {
		return textoCliente;
	}
	public void setTextoCliente(String textoCliente) {
		this.textoCliente = textoCliente;
	}
	public String getTextoDestinatario() {
		return textoDestinatario;
	}
	public void setTextoDestinatario(String textoDestinatario) {
		this.textoDestinatario = textoDestinatario;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getTextoFecha() {
		return textoFecha;
	}
	public void setTextoFecha(String textoFecha) {
		this.textoFecha = textoFecha;
	}
	
}
