package com.caasa.comexsap.exportaciones.model.to;

import java.util.Date;

public class PrecioMaterialTO {

	private String mandante;
	private String codigoMaterial;
	private String codigoPrecio;
	private Float importe;
	private String codigoMoneda;
	private Float peso;
	private String unidadMedida;
	private Date fechaInicio;
	private Date fechaFin;

	
	public String getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getCodigoPrecio() {
		return codigoPrecio;
	}
	public void setCodigoPrecio(String codigoPrecio) {
		this.codigoPrecio = codigoPrecio;
	}
	
	public String getMandante() {
		return mandante;
	}
	public void setMandante(String mandante) {
		this.mandante = mandante;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
