package com.caasa.comexsap.exportaciones.enums;

public enum EstadoRespuestaEnum {

	OK("200"),
	NO_CONTENT("204"),
	NOT_FOUND("404"),
	INTERNAL_SERVER_ERROR("500");
	
	private String value;

	private EstadoRespuestaEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
