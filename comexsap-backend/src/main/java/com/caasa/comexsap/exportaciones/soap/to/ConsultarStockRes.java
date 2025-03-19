package com.caasa.comexsap.exportaciones.soap.to;
import java.util.List;

public class ConsultarStockRes {

	private List<Datos> datos;
	private List<RespuestaStock> mensajes;
	public List<Datos> getDatos() {
		return datos;
	}
	public void setDatos(List<Datos> datos) {
		this.datos = datos;
	}
	public List<RespuestaStock> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<RespuestaStock> mensajes) {
		this.mensajes = mensajes;
	}
	
	
}
