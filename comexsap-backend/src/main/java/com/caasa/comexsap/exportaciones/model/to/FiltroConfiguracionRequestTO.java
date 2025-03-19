package com.caasa.comexsap.exportaciones.model.to;

public class FiltroConfiguracionRequestTO {
	private String[] dominios;
	private String buscar;
	public String[] getDominios() {
		return dominios;
	}
	public void setDominios(String[] dominios) {
		this.dominios = dominios;
	}
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	
}
