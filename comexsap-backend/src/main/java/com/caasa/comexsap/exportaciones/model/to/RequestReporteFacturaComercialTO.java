package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestReporteFacturaComercialTO {

	private int idExportacion;
	private String direccionShipper;
	private String consignatario;
	private String direccionConsignatario;
	private String factura;
	private String textoFecha;
	private String emitidoEn;
	private String cliente;
	private String puertoEmbarque;
	private String navio;
	private String puertoDescarga;
	private String booking;
	private String condicionFlete;
	private String formaPago;
	private String paisOrigen;
	private String referencia;
	private String descripcionTotal;
	private String nombreUsuario;
	private String unidadMedida;
	private float flete;
	private float seguro;
	private float importeTotal;
	private List<RequestPosicionFacturaTO> posiciones;
	private List<FacturaComercialTotalesTO> totales;
	private String incoterm;
	private String incotermComercial;
	private boolean pesoPackingList;
	private int tipoDespacho;
	private String codigoPersonal;
	
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
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
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getTextoFecha() {
		return textoFecha;
	}
	public void setTextoFecha(String textoFecha) {
		this.textoFecha = textoFecha;
	}
	public String getEmitidoEn() {
		return emitidoEn;
	}
	public void setEmitidoEn(String emitidoEn) {
		this.emitidoEn = emitidoEn;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getPuertoEmbarque() {
		return puertoEmbarque;
	}
	public void setPuertoEmbarque(String puertoEmbarque) {
		this.puertoEmbarque = puertoEmbarque;
	}
	public String getNavio() {
		return navio;
	}
	public void setNavio(String navio) {
		this.navio = navio;
	}
	public String getPuertoDescarga() {
		return puertoDescarga;
	}
	public void setPuertoDescarga(String puertoDescarga) {
		this.puertoDescarga = puertoDescarga;
	}
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public String getCondicionFlete() {
		return condicionFlete;
	}
	public void setCondicionFlete(String condicionFlete) {
		this.condicionFlete = condicionFlete;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDescripcionTotal() {
		return descripcionTotal;
	}
	public void setDescripcionTotal(String descripcionTotal) {
		this.descripcionTotal = descripcionTotal;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public float getFlete() {
		return flete;
	}
	public void setFlete(float flete) {
		this.flete = flete;
	}
	public float getSeguro() {
		return seguro;
	}
	public void setSeguro(float seguro) {
		this.seguro = seguro;
	}
	public float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public List<RequestPosicionFacturaTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<RequestPosicionFacturaTO> posiciones) {
		this.posiciones = posiciones;
	}
	public List<FacturaComercialTotalesTO> getTotales() {
		return totales;
	}
	public void setTotales(List<FacturaComercialTotalesTO> totales) {
		this.totales = totales;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public String getIncotermComercial() {
		return incotermComercial;
	}
	public void setIncotermComercial(String incotermComercial) {
		this.incotermComercial = incotermComercial;
	}
	public boolean isPesoPackingList() {
		return pesoPackingList;
	}
	public void setPesoPackingList(boolean pesoPackingList) {
		this.pesoPackingList = pesoPackingList;
	}
	public int getTipoDespacho() {
		return tipoDespacho;
	}
	public void setTipoDespacho(int tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
	public String getCodigoPersonal() {
		return codigoPersonal;
	}
	public void setCodigoPersonal(String codigoPersonal) {
		this.codigoPersonal = codigoPersonal;
	}
}
