package com.caasa.comexsap.exportaciones.model.to;

import com.caasa.comexsap.exportaciones.model.domain.ConfigtParametro;

public class ParametroTO extends ConfigtParametro{
	private int id;
	private String codigo;
	private int idDominio;
	private String nombreDominio;
	private String codigoDominio;
	private int estado;
	
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
	public int getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}
	public String getNombreDominio() {
		return nombreDominio;
	}
	public void setNombreDominio(String nombreDominio) {
		this.nombreDominio = nombreDominio;
	}
	public String getCodigoDominio() {
		return codigoDominio;
	}
	public void setCodigoDominio(String codigoDominio) {
		this.codigoDominio = codigoDominio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}