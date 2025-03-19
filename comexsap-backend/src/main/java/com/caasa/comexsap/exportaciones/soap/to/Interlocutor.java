package com.caasa.comexsap.exportaciones.soap.to;

import javax.xml.bind.annotation.XmlElement;

public class Interlocutor {
    private String codInte;
    private String tipoInt;
	public String getCodInte() {
		return codInte;
	}
	public void setCodInte(String codInte) {
		this.codInte = codInte;
	}
	public String getTipoInt() {
		return tipoInt;
	}
	public void setTipoInt(String tipoInt) {
		this.tipoInt = tipoInt;
	}
    
    
    
}
