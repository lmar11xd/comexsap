package com.caasa.comexsap.exportaciones.model.to;

public class FiltroPackingListRequestTO {
	
	private String[] codigoPacking;
	private String[] codigoExportacion;
	private String[] clientes;
	private int[] pagos;
	private String fechaExpMaritimoInicio;
	private String fechaExpMaritimoFin;
    private int id;
	private int idDespacho;
	
	public String[] getCodigoPacking() {
		return codigoPacking;
	}
	public void setCodigoPacking(String[] codigoPacking) {
		this.codigoPacking = codigoPacking;
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
	public String[] getCodigoExportacion() {
		return codigoExportacion;
	}
	public void setCodigoExportacion(String[] codigoExportacion) {
		this.codigoExportacion = codigoExportacion;
	}
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
}
