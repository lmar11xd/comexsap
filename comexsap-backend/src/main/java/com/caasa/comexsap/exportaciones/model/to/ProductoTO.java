package com.caasa.comexsap.exportaciones.model.to;

public class ProductoTO{

	private int idProducto;
	private String codigoMaterial;
	private String descripcionMaterial;
	private String unidadBase;
	private String unidadVenta;
	private double pesoNeto;
	private double pesoBruto;
	private String descripcionJerarquia;
	private String descripcionJerarquia2;
	private double pesoKilos;
	private double unidades;
	private int unidadesPaquete;
	
	public String getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getDescripcionMaterial() {
		return descripcionMaterial;
	}
	public void setDescripcionMaterial(String descripcionMaterial) {
		this.descripcionMaterial = descripcionMaterial;
	}
	public String getUnidadBase() {
		return unidadBase;
	}
	public void setUnidadBase(String unidadBase) {
		this.unidadBase = unidadBase;
	}
	public String getUnidadVenta() {
		return unidadVenta;
	}
	public void setUnidadVenta(String unidadVenta) {
		this.unidadVenta = unidadVenta;
	}
	public double getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public double getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getDescripcionJerarquia() {
		return descripcionJerarquia;
	}
	public void setDescripcionJerarquia(String descripcionJerarquia) {
		this.descripcionJerarquia = descripcionJerarquia;
	}
	public String getDescripcionJerarquia2() {
		return descripcionJerarquia2;
	}
	public void setDescripcionJerarquia2(String descripcionJerarquia2) {
		this.descripcionJerarquia2 = descripcionJerarquia2;
	}
	public double getPesoKilos() {
		return pesoKilos;
	}
	public void setPesoKilos(double pesoKilos) {
		this.pesoKilos = pesoKilos;
	}
	public double getUnidades() {
		return unidades;
	}
	public void setUnidades(double unidades) {
		this.unidades = unidades;
	}
	public int getUnidadesPaquete() {
		return unidadesPaquete;
	}
	public void setUnidadesPaquete(int unidadesPaquete) {
		this.unidadesPaquete = unidadesPaquete;
	}
}
