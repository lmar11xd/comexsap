package com.caasa.comexsap.exportaciones.model.to;

public class FiltroOrdenInterna {

	private int idDespacho;
	private int idPuertoDestino;
	private String codigoCliente;
	private String codigoDestinatario;
	
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public int getIdPuertoDestino() {
		return idPuertoDestino;
	}
	public void setIdPuertoDestino(int idPuertoDestino) {
		this.idPuertoDestino = idPuertoDestino;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoDestinatario() {
		return codigoDestinatario;
	}
	public void setCodigoDestinatario(String codigoDestinatario) {
		this.codigoDestinatario = codigoDestinatario;
	}
	
}
