package com.caasa.comexsap.exportaciones.api.to;

public class ResultadoPrecioMaterialTO {

	private String mandate;
	private String nroMaterial;
	private String listaPrecio;
	private String importe;
	private String moneda;
	private String cantidad;
	private String unidadMedida;
	private String fechaInicio;
	private String fechaFin;
	public String getMandate() {
		return mandate;
	}
	public void setMandate(String mandate) {
		this.mandate = mandate;
	}
	public String getNroMaterial() {
		return nroMaterial;
	}
	public void setNroMaterial(String nroMaterial) {
		this.nroMaterial = nroMaterial;
	}
	public String getListaPrecio() {
		return listaPrecio;
	}
	public void setListaPrecio(String listaPrecio) {
		this.listaPrecio = listaPrecio;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
