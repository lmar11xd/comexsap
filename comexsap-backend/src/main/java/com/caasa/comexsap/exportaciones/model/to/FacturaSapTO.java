package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.util.Date;

public class FacturaSapTO {
	private String codigoSap;
	private String pedidoSap;
	private String folio;
	private Date fecha;
	private String clase;
	private BigDecimal importe;
	private BigDecimal flete;
	private BigDecimal seguro;
	private BigDecimal importePosiciones;
	private BigDecimal fletePosiciones;
	private BigDecimal seguroPosiciones;
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getPedidoSap() {
		return pedidoSap;
	}
	public void setPedidoSap(String pedidoSap) {
		this.pedidoSap = pedidoSap;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getFlete() {
		return flete;
	}
	public void setFlete(BigDecimal flete) {
		this.flete = flete;
	}
	public BigDecimal getSeguro() {
		return seguro;
	}
	public void setSeguro(BigDecimal seguro) {
		this.seguro = seguro;
	}
	public BigDecimal getImportePosiciones() {
		return importePosiciones;
	}
	public void setImportePosiciones(BigDecimal importePosiciones) {
		this.importePosiciones = importePosiciones;
	}
	public BigDecimal getFletePosiciones() {
		return fletePosiciones;
	}
	public void setFletePosiciones(BigDecimal fletePosiciones) {
		this.fletePosiciones = fletePosiciones;
	}
	public BigDecimal getSeguroPosiciones() {
		return seguroPosiciones;
	}
	public void setSeguroPosiciones(BigDecimal seguroPosiciones) {
		this.seguroPosiciones = seguroPosiciones;
	}
}
