package com.caasa.comexsap.exportaciones.enums;

public enum DominioEnum {
	
	ESTADOS_ESTANDAR("D001"),
	ESTADOS_SOLICITUD("D011"),
	RESPONSABLES("D010");
		
	private String value;

	private DominioEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
