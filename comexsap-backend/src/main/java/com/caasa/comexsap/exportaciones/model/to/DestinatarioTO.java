package com.caasa.comexsap.exportaciones.model.to;

public class DestinatarioTO {

	private int idDestinatario;
	private String codigoClienteSap;
	private String codigoDestinatario;
	private String descripcion;
	
	public String getCodigoClienteSap() {
		return codigoClienteSap;
	}
	public void setCodigoClienteSap(String codigoClienteSap) {
		this.codigoClienteSap = codigoClienteSap;
	}
	public String getCodigoDestinatario() {
		return codigoDestinatario;
	}
	public void setCodigoDestinatario(String codigoDestinatario) {
		this.codigoDestinatario = codigoDestinatario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	
	
}
