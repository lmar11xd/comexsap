package com.caasa.comexsap.exportaciones.model.to;

public class DetalleStockTO {
	private String unidad;
	private String material;
	private String posicion;
	private String cantidadSolicitada;
	private String centro;
	private String almacen;
	private String multisociedad;

	
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(String cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public String getMultisociedad() {
		return multisociedad;
	}
	public void setMultisociedad(String multisociedad) {
		this.multisociedad = multisociedad;
	}
	
}
