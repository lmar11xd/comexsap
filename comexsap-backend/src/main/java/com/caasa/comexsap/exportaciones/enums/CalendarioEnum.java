package com.caasa.comexsap.exportaciones.enums;

public enum CalendarioEnum {

	UNO("1"),
	DOS("2"),
	TRES("3"),
	CUATRO("4"),
	CINCO("5"),
	SEIS("6"),
	SIETE("7"),
	OCHO("8"),
	NUEVE("9"),
	DIEZ("10"),
	ONCE("11"),
	DOCE("12"),
	ENERO("ENERO"),
	FEBRERO("FEBRERO"),
	MARZO("MARZO"),
	ABRIL("ABRIL"),
	MAYO("MAYO"),
	JUNIO("JUNIO"),
	JULIO("JULIO"),
	AGOSTO("AGOSTO"),
	SETIEMBRE("SETIEMBRE"),
	OCTUBRE("OCTUBRE"),
	NOVIEMBRE("NOVIEMBRE"),
	DICIEMBRE("DICIEMBRE");
	
	private String value;

	private CalendarioEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
