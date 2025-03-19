package com.caasa.comexsap.exportaciones.model.to;

public class FiltroPedidoFirmeRequestTO {
	
	private String[] pedidos;
	private String[] clientes;
	private int[] pagos;
	private int[] incoterms;
	private int[] documentos;
	private String fechaPedidoFirmeInicio;
	private String fechaPedidoFirmeFin;
    private int id;
    
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
	public String getFechaPedidoFirmeInicio() {
		return fechaPedidoFirmeInicio;
	}
	public void setFechaPedidoFirmeInicio(String fechaPedidoFirmeInicio) {
		this.fechaPedidoFirmeInicio = fechaPedidoFirmeInicio;
	}
	public String getFechaPedidoFirmeFin() {
		return fechaPedidoFirmeFin;
	}
	public void setFechaPedidoFirmeFin(String fechaPedidoFirmeFin) {
		this.fechaPedidoFirmeFin = fechaPedidoFirmeFin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
