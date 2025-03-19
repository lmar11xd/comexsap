package com.caasa.comexsap.exportaciones.model.to;

public class ExportacionEtiquetaTO {
	private int id;
	private int idEtiqueta;
	private String nombreEtiqueta;
	private String nombreInglesEtiqueta;
	private String contenido;
	private Short posicion;
	private int estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEtiqueta() {
		return idEtiqueta;
	}
	public void setIdEtiqueta(int idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}
	public String getNombreEtiqueta() {
		return nombreEtiqueta;
	}
	public void setNombreEtiqueta(String nombreEtiqueta) {
		this.nombreEtiqueta = nombreEtiqueta;
	}

	public String getNombreInglesEtiqueta() {
		return nombreInglesEtiqueta;
	}

	public void setNombreInglesEtiqueta(String nombreInglesEtiqueta) {
		this.nombreInglesEtiqueta = nombreInglesEtiqueta;
	}

	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Short getPosicion() {
		return posicion;
	}
	public void setPosicion(Short posicion) {
		this.posicion = posicion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
