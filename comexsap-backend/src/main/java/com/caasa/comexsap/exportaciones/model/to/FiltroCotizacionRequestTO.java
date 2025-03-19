package com.caasa.comexsap.exportaciones.model.to;

public class FiltroCotizacionRequestTO {
	
	private String[] pedidos;
	private String[] clientes;
	private int[] pagos;
	private int[] incoterms;
	private int[] documentos;
	private String fechaCotizacionInicio;
	private String fechaCotizacionFin;

	public String[] getPedidos() {
		return pedidos;
	}
	public void setPedidos(String[] pedidos) {
		this.pedidos = pedidos;
	}
	public String[] getClientes() {
		return clientes;
	}
	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}
	public int[] getPagos() {
		return pagos;
	}
	public void setPagos(int[] pagos) {
		this.pagos = pagos;
	}
	public int[] getIncoterms() {
		return incoterms;
	}
	public void setIncoterms(int[] incoterms) {
		this.incoterms = incoterms;
	}
	public int[] getDocumentos() {
		return documentos;
	}
	public void setDocumentos(int[] documentos) {
		this.documentos = documentos;
	}
	public String getFechaCotizacionInicio() {
		return fechaCotizacionInicio;
	}
	public void setFechaCotizacionInicio(String fechaCotizacionInicio) {
		this.fechaCotizacionInicio = fechaCotizacionInicio;
	}
	public String getFechaCotizacionFin() {
		return fechaCotizacionFin;
	}
	public void setFechaCotizacionFin(String fechaCotizacionFin) {
		this.fechaCotizacionFin = fechaCotizacionFin;
	}
	
}
