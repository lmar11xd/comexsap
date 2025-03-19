package com.caasa.comexsap.exportaciones.model.to;

public class RequestPosicionFacturaTO {
	private int id;
	private int item;
	private String codigo;
	private float pesoTeorico;
	private float pesoNeto;
	private float pesoReal;
	private float cantidad;
	private String descripcion;
	private String descripcionMaterial;
	private String componenteTexto;
	private String descripcionPartida;
	private float precio;
	private float importe;
	private int paquetes;
	private int piezas;
	private boolean padre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public float getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(float pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionMaterial() {
		return descripcionMaterial;
	}
	public void setDescripcionMaterial(String descripcionMaterial) {
		this.descripcionMaterial = descripcionMaterial;
	}
	public String getComponenteTexto() {
		return componenteTexto;
	}
	public void setComponenteTexto(String componenteTexto) {
		this.componenteTexto = componenteTexto;
	}
	public String getDescripcionPartida() {
		return descripcionPartida;
	}
	public void setDescripcionPartida(String descripcionPartida) {
		this.descripcionPartida = descripcionPartida;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public int getPaquetes() {
		return paquetes;
	}
	public void setPaquetes(int paquetes) {
		this.paquetes = paquetes;
	}
	public int getPiezas() {
		return piezas;
	}
	public void setPiezas(int piezas) {
		this.piezas = piezas;
	}
	public float getPesoTeorico() {
		return pesoTeorico;
	}
	public void setPesoTeorico(float pesoTeorico) {
		this.pesoTeorico = pesoTeorico;
	}
	public float getPesoReal() {
		return pesoReal;
	}
	public void setPesoReal(float pesoReal) {
		this.pesoReal = pesoReal;
	}
	public boolean isPadre() {
		return padre;
	}
	public void setPadre(boolean padre) {
		this.padre = padre;
	}
}
