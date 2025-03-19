package com.caasa.comexsap.exportaciones.model.to;

public class ReportePackingListTO {
	private String orden;
	private String paquete;
	private String colada;
	private float peso;
	private String piezas;
	private String dimensiones;
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getColada() {
		return colada;
	}
	public void setColada(String colada) {
		this.colada = colada;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getPiezas() {
		return piezas;
	}
	public void setPiezas(String piezas) {
		this.piezas = piezas;
	}
	public String getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	
}
