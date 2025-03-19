package com.caasa.comexsap.exportaciones.model.response;

public class ArchivoResponse {
	private int status;
	private String message;
	private Object data;
	
	public ArchivoResponse() {
		super();
		this.status = 200;
		this.message = "OK";
		this.data = null;
	}
	
	public ArchivoResponse(String message) {
		super();
		this.status = 200;
		this.message = message;
		this.data = null;
	}
	
	public ArchivoResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.data = null;
	}
	
	public ArchivoResponse(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}	
}
