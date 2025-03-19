package com.caasa.comexsap.exportaciones.model.to;

public class ClienteTO {
	
	private int idCliente;
	private String codigoClienteSap;
	private String descripcion;
	private String ruc;
	private String codigoPais;
	
	public String getCodigoClienteSap() {
		return codigoClienteSap;
	}
	public void setCodigoClienteSap(String codigoClienteSap) {
		this.codigoClienteSap = codigoClienteSap;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
