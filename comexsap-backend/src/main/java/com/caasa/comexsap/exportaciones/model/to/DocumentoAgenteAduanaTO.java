package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentoAgenteAduanaTO {
	private int id;
	private String agenteAduana;
	private String mesDespacho;
	private String sede;
	private String cliente;
	private BigDecimal pesoReal;
	private String empresaTransporte;
	private String destino;
	private String factura;
	private String serie;
	private String mesFactura;
	private String incoterm;
	private Date fechaLevante;
	private Date fechaTransito;
	private String comentario;
	private String dam;
	private String ordenAgenteAduana;
	private Date fechaDam40;
	private String canalControl;
	private Date inicioEmbarque;
	private Date fechaRegularizacionDAM41;
	private int tiempoRegularizacion;
	private Date fechaEntregaDUA;
	private String estadoOrden;
	private Date fechaEntregaDrawback;
	private String dataAnterior;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAgenteAduana() {
		return agenteAduana;
	}
	public void setAgenteAduana(String agenteAduana) {
		this.agenteAduana = agenteAduana;
	}
	public String getMesDespacho() {
		return mesDespacho;
	}
	public void setMesDespacho(String mesDespacho) {
		this.mesDespacho = mesDespacho;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getPesoReal() {
		return pesoReal;
	}
	public void setPesoReal(BigDecimal pesoReal) {
		this.pesoReal = pesoReal;
	}
	public String getEmpresaTransporte() {
		return empresaTransporte;
	}
	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getMesFactura() {
		return mesFactura;
	}
	public void setMesFactura(String mesFactura) {
		this.mesFactura = mesFactura;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public Date getFechaLevante() {
		return fechaLevante;
	}
	public void setFechaLevante(Date fechaLevante) {
		this.fechaLevante = fechaLevante;
	}
	public Date getFechaTransito() {
		return fechaTransito;
	}
	public void setFechaTransito(Date fechaTransito) {
		this.fechaTransito = fechaTransito;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getDam() {
		return dam;
	}
	public void setDam(String dam) {
		this.dam = dam;
	}
	public String getOrdenAgenteAduana() {
		return ordenAgenteAduana;
	}
	public void setOrdenAgenteAduana(String ordenAgenteAduana) {
		this.ordenAgenteAduana = ordenAgenteAduana;
	}
	public Date getFechaDam40() {
		return fechaDam40;
	}
	public void setFechaDam40(Date fechaDam40) {
		this.fechaDam40 = fechaDam40;
	}
	public String getCanalControl() {
		return canalControl;
	}
	public void setCanalControl(String canalControl) {
		this.canalControl = canalControl;
	}
	public Date getInicioEmbarque() {
		return inicioEmbarque;
	}
	public void setInicioEmbarque(Date inicioEmbarque) {
		this.inicioEmbarque = inicioEmbarque;
	}
	public Date getFechaRegularizacionDAM41() {
		return fechaRegularizacionDAM41;
	}
	public void setFechaRegularizacionDAM41(Date fechaRegularizacionDAM41) {
		this.fechaRegularizacionDAM41 = fechaRegularizacionDAM41;
	}
	public int getTiempoRegularizacion() {
		return tiempoRegularizacion;
	}
	public void setTiempoRegularizacion(int tiempoRegularizacion) {
		this.tiempoRegularizacion = tiempoRegularizacion;
	}
	public Date getFechaEntregaDUA() {
		return fechaEntregaDUA;
	}
	public void setFechaEntregaDUA(Date fechaEntregaDUA) {
		this.fechaEntregaDUA = fechaEntregaDUA;
	}
	public String getEstadoOrden() {
		return estadoOrden;
	}
	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}
	public Date getFechaEntregaDrawback() {
		return fechaEntregaDrawback;
	}
	public void setFechaEntregaDrawback(Date fechaEntregaDrawback) {
		this.fechaEntregaDrawback = fechaEntregaDrawback;
	}
	public String getDataAnterior() {
		return dataAnterior;
	}
	public void setDataAnterior(String dataAnterior) {
		this.dataAnterior = dataAnterior;
	}
	
}
