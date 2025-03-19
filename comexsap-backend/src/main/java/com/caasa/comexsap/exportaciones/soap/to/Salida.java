package com.caasa.comexsap.exportaciones.soap.to;

import javax.xml.bind.annotation.XmlElement;

public class Salida {

	private String numPedido;
	private String tipoMsj;
	private String id;
	private String descMsj;
	private String num;

	public String getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	public String getTipoMsj() {
		return tipoMsj;
	}

	public void setTipoMsj(String tipoMsj) {
		this.tipoMsj = tipoMsj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescMsj() {
		return descMsj;
	}

	public void setDescMsj(String descMsj) {
		this.descMsj = descMsj;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
