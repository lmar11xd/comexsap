package com.caasa.comexsap.exportaciones.soap.to;

import javax.xml.bind.annotation.XmlElement;

public class RespuestaStock {
	private String tipo;
	private String codigo;
	private String mensaje;
	private String logNo;
	private String logMsgNo;
	private String mensaje1;
	private String mensaje2;
	private String mensaje3;
	private String mensaje4;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getLogNo() {
		return logNo;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public String getLogMsgNo() {
		return logMsgNo;
	}

	public void setLogMsgNo(String logMsgNo) {
		this.logMsgNo = logMsgNo;
	}

	public String getMensaje1() {
		return mensaje1;
	}

	public void setMensaje1(String mensaje1) {
		this.mensaje1 = mensaje1;
	}

	public String getMensaje2() {
		return mensaje2;
	}

	public void setMensaje2(String mensaje2) {
		this.mensaje2 = mensaje2;
	}

	public String getMensaje3() {
		return mensaje3;
	}

	public void setMensaje3(String mensaje3) {
		this.mensaje3 = mensaje3;
	}

	public String getMensaje4() {
		return mensaje4;
	}

	public void setMensaje4(String mensaje4) {
		this.mensaje4 = mensaje4;
	}

}
