package com.caasa.comexsap.exportaciones.model.to;

public class FiltroReporteTerrestreRequest {
	private String mes;
	private String[] sedes;	
	private String[] clientes;
	private String fechaSalidaInicio;
	private String fechaSalidaFin;
	private String[] facturas;
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String[] getSedes() {
		return sedes;
	}
	public void setSedes(String[] sedes) {
		this.sedes = sedes;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
	public String getFechaSalidaInicio() {
		return fechaSalidaInicio;
	}
	public void setFechaSalidaInicio(String fechaSalidaInicio) {
		this.fechaSalidaInicio = fechaSalidaInicio;
	}
	public String getFechaSalidaFin() {
		return fechaSalidaFin;
	}
	public void setFechaSalidaFin(String fechaSalidaFin) {
		this.fechaSalidaFin = fechaSalidaFin;
	}
	public String[] getFacturas() {
		return facturas;
	}
	public void setFacturas(String[] facturas) {
		this.facturas = facturas;
	}
}
