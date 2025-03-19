package com.caasa.comexsap.exportaciones.model.to;

public class FiltroServicioRequestTO {
	private String[] transportes;
	private String[] despachos;
	private String buscar;
	public String[] getTransportes() {
		return transportes;
	}
	public void setTransportes(String[] transportes) {
		this.transportes = transportes;
	}
	public String[] getDespachos() {
		return despachos;
	}
	public void setDespachos(String[] despachos) {
		this.despachos = despachos;
	}
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	
}
