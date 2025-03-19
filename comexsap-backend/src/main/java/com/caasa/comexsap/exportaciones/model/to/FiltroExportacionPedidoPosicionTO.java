package com.caasa.comexsap.exportaciones.model.to;

public class FiltroExportacionPedidoPosicionTO {
	
	private int idDespacho;
	private String[] codigos;
	private String[] clientes;

	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public String[] getCodigos() {
		return codigos;
	}
	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
}
