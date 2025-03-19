package com.caasa.comexsap.exportaciones.model.to;

import com.caasa.comexsap.exportaciones.model.domain.ComexstCliente;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPuerto;

public class PuertoTO extends ComexstPuerto {

	private String codigoPais;
	private String descripcionPais;
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getDescripcionPais() {
		return descripcionPais;
	}
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
	
	
}
