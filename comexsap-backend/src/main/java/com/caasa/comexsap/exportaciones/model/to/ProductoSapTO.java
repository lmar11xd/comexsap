package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ProductoSapTO {
	private String codigo;
	private String codigoLinea;
	private String codigoFamilia;
	private String descripcion;
	private String descripcionLinea;
	private String descripcionFamilia;
	private String unidadBase;
	private BigDecimal pesoNeto;
	private BigDecimal pesoBruto;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public String getCodigoFamilia() {
		return codigoFamilia;
	}
	public void setCodigoFamilia(String codigoFamilia) {
		this.codigoFamilia = codigoFamilia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionLinea() {
		return descripcionLinea;
	}
	public void setDescripcionLinea(String descripcionLinea) {
		this.descripcionLinea = descripcionLinea;
	}
	public String getDescripcionFamilia() {
		return descripcionFamilia;
	}
	public void setDescripcionFamilia(String descripcionFamilia) {
		this.descripcionFamilia = descripcionFamilia;
	}
	public String getUnidadBase() {
		return unidadBase;
	}
	public void setUnidadBase(String unidadBase) {
		this.unidadBase = unidadBase;
	}
	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
}
