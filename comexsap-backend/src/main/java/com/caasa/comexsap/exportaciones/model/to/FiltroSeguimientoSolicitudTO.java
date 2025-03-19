package com.caasa.comexsap.exportaciones.model.to;

public class FiltroSeguimientoSolicitudTO {
	
	private String[] empresas;
	private String[] gerencias;
	private String[] areas;
	private String[] solicitantes;
	private String[] responsables;
	private String[] procesos;
	private String[] subprocesos;
	private String[] materias;
	private String[] estados;
	private String idSolicitud;
	private String texto;
	
	public String[] getEmpresas() {
		return empresas;
	}
	public void setEmpresas(String[] empresas) {
		this.empresas = empresas;
	}
	public String[] getGerencias() {
		return gerencias;
	}
	public void setGerencias(String[] gerencias) {
		this.gerencias = gerencias;
	}
	public String[] getAreas() {
		return areas;
	}
	public void setAreas(String[] areas) {
		this.areas = areas;
	}
	public String[] getSolicitantes() {
		return solicitantes;
	}
	public void setSolicitantes(String[] solicitantes) {
		this.solicitantes = solicitantes;
	}
	public String[] getResponsables() {
		return responsables;
	}
	public void setResponsables(String[] responsables) {
		this.responsables = responsables;
	}
	public String[] getProcesos() {
		return procesos;
	}
	public void setProcesos(String[] procesos) {
		this.procesos = procesos;
	}
	public String[] getSubprocesos() {
		return subprocesos;
	}
	public void setSubprocesos(String[] subprocesos) {
		this.subprocesos = subprocesos;
	}
	public String[] getMaterias() {
		return materias;
	}
	public void setMaterias(String[] materias) {
		this.materias = materias;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String[] getEstados() {
		return estados;
	}
	public void setEstados(String[] estados) {
		this.estados = estados;
	}		
    
}
