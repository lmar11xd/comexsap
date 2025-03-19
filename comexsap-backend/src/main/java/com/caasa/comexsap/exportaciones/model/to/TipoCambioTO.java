package com.caasa.comexsap.exportaciones.model.to;

import java.util.Date;

public class TipoCambioTO {
	private String tipoCotizacion;
	private String monedaProcedencia;
	private String monedaDestino;
	private Date fecha;
	private float valor;
	
	public String getTipoCotizacion() {
		return tipoCotizacion;
	}
	public void setTipoCotizacion(String tipoCotizacion) {
		this.tipoCotizacion = tipoCotizacion;
	}
	public String getMonedaProcedencia() {
		return monedaProcedencia;
	}
	public void setMonedaProcedencia(String monedaProcedencia) {
		this.monedaProcedencia = monedaProcedencia;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
