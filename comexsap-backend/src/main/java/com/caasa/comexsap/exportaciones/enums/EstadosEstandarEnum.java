package com.caasa.comexsap.exportaciones.enums;

public enum EstadosEstandarEnum {	

	INACTIVO("0"),
	ACTIVO("1"),
	BORRADO("2"),
	APERTURADO("3"),
	CERRADO("4"),
	//Valoes de indicadores
	CREADO("1"),
	PRESENTADO("2"),
	APROBADO("3"),
	RECHAZADO("4"),
	OBSERVADO("0");

	private String value;

	private EstadosEstandarEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
