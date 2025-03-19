package com.caasa.comexsap.exportaciones.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "carpeta")
public class CarpetaProperties {

	private String rutaCarpetaRaiz;
	
	public String getRutaCarpetaRaiz() {
		return rutaCarpetaRaiz;
	}
	public void setRutaCarpetaRaiz(String rutaCarpetaRaiz) {
		this.rutaCarpetaRaiz = rutaCarpetaRaiz;
	}
}
