package com.caasa.comexsap.exportaciones.enums;

public enum PosicionEtiquetaEnum {

	ARRIBA(1),
	ABAJO(2);
	
	private int value;

	private PosicionEtiquetaEnum(int value) {
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
