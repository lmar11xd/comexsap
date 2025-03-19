package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ControlGastosTO {
	
	private int id;
	private BigDecimal peso;
	private BigDecimal importe;
	private BigDecimal importeTonelada;
	private BigDecimal tipoCambio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getImporteTonelada() {
		return importeTonelada;
	}
	public void setImporteTonelada(BigDecimal importeTonelada) {
		this.importeTonelada = importeTonelada;
	}
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
}
