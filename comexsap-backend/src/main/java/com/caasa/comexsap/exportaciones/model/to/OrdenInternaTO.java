package com.caasa.comexsap.exportaciones.model.to;

public class OrdenInternaTO {

	private Integer id;
	private String codigo;
	private Integer idDespacho;
	private Integer idPais;
	private String codigoCliente;
	private String codigoDestinatario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoDestinatario() {
		return codigoDestinatario;
	}
	public void setCodigoDestinatario(String codigoDestinatario) {
		this.codigoDestinatario = codigoDestinatario;
	}
	
}
