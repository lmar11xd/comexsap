package com.caasa.comexsap.exportaciones.model.to;

public class FiltroExportacionTerrestreRequestTO {
	private String[] codigos;
	private String folio;
	private String fechaInicio;
	private String fechaFin;
	private String[] clientes;
	private int[] pagos;
	
	public String[] getCodigos() {
		return codigos;
	}
	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int[] getPagos() {
		return pagos;
	}
	public void setPagos(int[] pagos) {
		this.pagos = pagos;
	}
}
