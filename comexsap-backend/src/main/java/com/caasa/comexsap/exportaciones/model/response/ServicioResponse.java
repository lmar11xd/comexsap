package com.caasa.comexsap.exportaciones.model.response;

import com.caasa.comexsap.exportaciones.model.to.ServicioTO;

public class ServicioResponse extends Response {
	private ServicioTO servicio;

	public ServicioTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioTO servicio) {
		this.servicio = servicio;
	}
	
}
