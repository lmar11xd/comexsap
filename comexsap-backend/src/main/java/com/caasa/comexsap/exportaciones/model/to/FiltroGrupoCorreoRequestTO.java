package com.caasa.comexsap.exportaciones.model.to;

public class FiltroGrupoCorreoRequestTO {
	private String[] grupos;
	private String buscar;
	public String[] getGrupos() {
		return grupos;
	}
	public void setGrupos(String[] grupos) {
		this.grupos = grupos;
	}
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	
}
