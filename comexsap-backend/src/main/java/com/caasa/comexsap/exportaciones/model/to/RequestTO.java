package com.caasa.comexsap.exportaciones.model.to;

import java.io.Serializable;

public class RequestTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private T formulario;
	
	public void set(T formulario) { this.formulario = formulario; }
    public T get() { return formulario; }
    	
	public T getFormulario() {
		return formulario;
	}
	public void setFormulario(T formulario) {
		this.formulario = formulario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
