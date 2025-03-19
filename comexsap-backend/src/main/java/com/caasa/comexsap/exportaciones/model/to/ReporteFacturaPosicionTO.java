package com.caasa.comexsap.exportaciones.model.to;

public class ReporteFacturaPosicionTO {

	private int item;
	private String descripcion;
	private String regimen;
	private String cantidad;
	private String valorFOB;
	private String partida;
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getValorFOB() {
		return valorFOB;
	}
	public void setValorFOB(String valorFOB) {
		this.valorFOB = valorFOB;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	
}
