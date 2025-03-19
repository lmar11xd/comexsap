package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ControlGastosDetalleTO {
	private int id;
	private int idControlGasto;
	private int idConcepto;
	private int idProveedor;
	private String facturas;
	private BigDecimal monto;
	private String moneda;
	private BigDecimal montoTotal;
	private int estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdControlGasto() {
		return idControlGasto;
	}
	public void setIdControlGasto(int idControlGasto) {
		this.idControlGasto = idControlGasto;
	}
	public int getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getFacturas() {
		return facturas;
	}
	public void setFacturas(String facturas) {
		this.facturas = facturas;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
