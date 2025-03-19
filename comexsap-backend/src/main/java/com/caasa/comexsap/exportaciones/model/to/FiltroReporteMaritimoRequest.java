package com.caasa.comexsap.exportaciones.model.to;

public class FiltroReporteMaritimoRequest {
	private String[] codigos;
	private String[] codigosPedidoFirme;
	private String[] clientes;
	private String fechaInicio;
	private String fechaFin;
	private String[] responsables;
	private String[] bookings;
	private String[] naves;
	public String[] getCodigos() {
		return codigos;
	}
	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
	}
	public String[] getCodigosPedidoFirme() {
		return codigosPedidoFirme;
	}
	public void setCodigosPedidoFirme(String[] codigosPedidoFirme) {
		this.codigosPedidoFirme = codigosPedidoFirme;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
	public String[] getResponsables() {
		return responsables;
	}
	public void setResponsables(String[] responsables) {
		this.responsables = responsables;
	}
	public String[] getBookings() {
		return bookings;
	}
	public void setBookings(String[] bookings) {
		this.bookings = bookings;
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
	public String[] getNaves() {
		return naves;
	}
	public void setNaves(String[] naves) {
		this.naves = naves;
	}
	
}
