package com.caasa.comexsap.exportaciones.model.to;

public class FiltroExportacionMaritimoRequestTO {
	
	private String[] codigos;
	private String[] codigosPedidoFirme;
	private String[] clientes;
	private int[] pagos;
	private String fechaExpMaritimoInicio;
	private String fechaExpMaritimoFin;
	private int idDespacho;
    private int id;
    private int intercompany;
    
	public String[] getCodigos() {
		return codigos;
	}
	public void setCodigos(String[] codigos) {
		this.codigos = codigos;
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
	public String getFechaExpMaritimoInicio() {
		return fechaExpMaritimoInicio;
	}
	public void setFechaExpMaritimoInicio(String fechaExpMaritimoInicio) {
		this.fechaExpMaritimoInicio = fechaExpMaritimoInicio;
	}
	public String getFechaExpMaritimoFin() {
		return fechaExpMaritimoFin;
	}
	public void setFechaExpMaritimoFin(String fechaExpMaritimoFin) {
		this.fechaExpMaritimoFin = fechaExpMaritimoFin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getCodigosPedidoFirme() {
		return codigosPedidoFirme;
	}
	public void setCodigosPedidoFirme(String[] codigosPedidoFirme) {
		this.codigosPedidoFirme = codigosPedidoFirme;
	}
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public int getIntercompany() {
		return intercompany;
	}
	public void setIntercompany(int intercompany) {
		this.intercompany = intercompany;
	}	
}
