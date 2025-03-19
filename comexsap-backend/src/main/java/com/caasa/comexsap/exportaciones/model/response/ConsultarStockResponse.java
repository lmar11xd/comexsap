package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.soap.to.Datos;
import com.caasa.comexsap.exportaciones.soap.to.RespuestaStock;

public class ConsultarStockResponse {
	
	List<Datos> datos;
	List<RespuestaStock> mensajes;
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
