package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestReporteFacturaTO {
	private String booking;
	private String agenteAduana;
	private String shipper;
	private String direccionShipper;
	private String consignatario;
	private String direccionConsignatario;
	private String notificante;
	private String direccionNotificante;
	private String puertoOrigen;
	private String puertoDestino;
	private String fechaCarguio;
	private String tipoTransporte;
	private String motonave;
	private String regimen;
	private String etiquetaValor;
	private float valorFOBTotal;
	private float fleteTotal; 
	private float seguroTotal; 
	private float valorCFRTotal;
	private List<ReporteFacturaPosicionTO> posiciones;
	
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public String getAgenteAduana() {
		return agenteAduana;
	}
	public void setAgenteAduana(String agenteAduana) {
		this.agenteAduana = agenteAduana;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getDireccionShipper() {
		return direccionShipper;
	}
	public void setDireccionShipper(String direccionShipper) {
		this.direccionShipper = direccionShipper;
	}
	public String getConsignatario() {
		return consignatario;
	}
	public void setConsignatario(String consignatario) {
		this.consignatario = consignatario;
	}
	public String getDireccionConsignatario() {
		return direccionConsignatario;
	}
	public void setDireccionConsignatario(String direccionConsignatario) {
		this.direccionConsignatario = direccionConsignatario;
	}
	public String getNotificante() {
		return notificante;
	}
	public void setNotificante(String notificante) {
		this.notificante = notificante;
	}
	public String getDireccionNotificante() {
		return direccionNotificante;
	}
	public void setDireccionNotificante(String direccionNotificante) {
		this.direccionNotificante = direccionNotificante;
	}
	public String getPuertoOrigen() {
		return puertoOrigen;
	}
	public void setPuertoOrigen(String puertoOrigen) {
		this.puertoOrigen = puertoOrigen;
	}
	public String getPuertoDestino() {
		return puertoDestino;
	}
	public void setPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}
	public String getFechaCarguio() {
		return fechaCarguio;
	}
	public void setFechaCarguio(String fechaCarguio) {
		this.fechaCarguio = fechaCarguio;
	}
	public String getTipoTransporte() {
		return tipoTransporte;
	}
	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	public String getMotonave() {
		return motonave;
	}
	public void setMotonave(String motonave) {
		this.motonave = motonave;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public float getValorFOBTotal() {
		return valorFOBTotal;
	}
	public void setValorFOBTotal(float valorFOBTotal) {
		this.valorFOBTotal = valorFOBTotal;
	}
	public float getFleteTotal() {
		return fleteTotal;
	}
	public void setFleteTotal(float fleteTotal) {
		this.fleteTotal = fleteTotal;
	}
	public float getSeguroTotal() {
		return seguroTotal;
	}
	public void setSeguroTotal(float seguroTotal) {
		this.seguroTotal = seguroTotal;
	}
	public float getValorCFRTotal() {
		return valorCFRTotal;
	}
	public void setValorCFRTotal(float valorCFRTotal) {
		this.valorCFRTotal = valorCFRTotal;
	}
	public List<ReporteFacturaPosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<ReporteFacturaPosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	public String getEtiquetaValor() {
		return etiquetaValor;
	}
	public void setEtiquetaValor(String etiquetaValor) {
		this.etiquetaValor = etiquetaValor;
	}
	
}
