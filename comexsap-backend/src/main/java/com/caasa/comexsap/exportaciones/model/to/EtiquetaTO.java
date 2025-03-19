package com.caasa.comexsap.exportaciones.model.to;

public class EtiquetaTO {

	private int id;
	private String nombre;
	private String nombreIngles;
	
	public EtiquetaTO() {}
	
	public EtiquetaTO(int id, String nombre, String nombreIngles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreIngles = nombreIngles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreIngles() {
		return nombreIngles;
	}
	public void setNombreIngles(String nombreIngles) {
		this.nombreIngles = nombreIngles;
	}
}
