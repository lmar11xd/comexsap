package com.caasa.comexsap.exportaciones.enums;

public enum CorreoEnum {
	CORREO_SSL("CORREO_SSL"),
	CORREO_PUERTO("CORREO_PUERTO"),
	CORREO_SERVIDOR("CORREO_SERVIDOR"),
	CORREO_NOTIFICADOR("CORREO_NOTIFICADOR"),
	CORREO_CONTRASENIA("CORREO_CONTRASENIA");
	
	private String value;

	private CorreoEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
