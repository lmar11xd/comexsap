package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.soap.to.Salida;

public class PedidoExportacionResponse {
	private String respuestaStatus;
	private String respuestaMensaje;
	private List<Salida> salida;
	public String getRespuestaStatus() {
		return respuestaStatus;
	}
	public void setRespuestaStatus(String respuestaStatus) {
		this.respuestaStatus = respuestaStatus;
	}
	public String getRespuestaMensaje() {
		return respuestaMensaje;
	}
	public void setRespuestaMensaje(String respuestaMensaje) {
		this.respuestaMensaje = respuestaMensaje;
	}
	public List<Salida> getSalida() {
		return salida;
	}
	public void setSalida(List<Salida> salida) {
		this.salida = salida;
	}
	
	
}
