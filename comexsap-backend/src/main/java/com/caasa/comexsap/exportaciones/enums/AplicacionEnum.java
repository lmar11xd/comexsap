package com.caasa.comexsap.exportaciones.enums;

public enum AplicacionEnum {	

	APLICACION("COMEXSAP");
	
	private String value;

	private AplicacionEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
