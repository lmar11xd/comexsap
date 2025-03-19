package com.caasa.comexsap.exportaciones.model.to;

import java.io.Serializable;
import java.util.List;

import com.caasa.comexsap.exportaciones.enums.EstadoRespuestaEnum;

public class ResponseTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String status;
	private String summary;
	private String severity;
	private String message;
	private List<String> errorTrace;

	private T response;

	public ResponseTO() {
		super();
		this.status = EstadoRespuestaEnum.OK.getValue();
		this.severity = "success";
	}

	public ResponseTO(String summary) {
		super();
		this.status = EstadoRespuestaEnum.OK.getValue();
		this.severity = "success";
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.severity = "success";
		
		if (status.equals(EstadoRespuestaEnum.INTERNAL_SERVER_ERROR.getValue())) {
			this.severity = "error";
		}
		if (status.equals(EstadoRespuestaEnum.NOT_FOUND.getValue())) {
			this.severity = "warn";
		}

		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(List<String> errorTrace) {
		this.errorTrace = errorTrace;
	}

	public void set(T response) {
		this.response = response;
	}

	public T get() {
		return response;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
}