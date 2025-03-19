package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ReporteMaritimoTO {
	private int id;
	private int idExportacion;
	private String anio;
	private String mes;
	private String codigo;
	private String pedidoFirme;
	private String codigoSapCliente;
	private String nombreCliente;
	private String codigoPuertoDestino;
	private String puertoDestino;
	private BigDecimal pesoReal;
	private String codigoPaisDestino;
	private String paisDestino;
	private String usuarioCreacion;
	private String tipoPedido;
	private String fechaDisponibilidad;
	private String codigoIncotermComercial;
	private String codigoAgenteNaviera;
	private String nombreAgenteNaviera;
	private String codigoAgenteCarga;
	private String nombreAgenteCarga;
	private String booking;
	private String bl;
	private String etaOrigen;
	private String etaDestino;
	private String facturaElectronica;
	private String facturaSap;
	private String fechaFactura;
	private String fechaEnvioDocumento;
	private String codigoMoneda;
	private String nombreMoneda;
	private BigDecimal importeTotal;
	private BigDecimal importe;
	private BigDecimal flete;
	private String guiaDhl;
	private String fechaBlProgramado;
	private String fechaBlReal;
	private String fechaDam41;
	private String fechaDam;
	private String dam;
	private String puertoOrigen;
	private String fechaCarguio;
	private String fechaMaximaIngreso;
	private String despachoEnSemana;
	private String nave;
	private String armador;
	private BigDecimal fleteTm;
	private BigDecimal seguro;
	private String ordenInterna;
	private int numeroContenedor;
	private int idControlGasto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPedidoFirme() {
		return pedidoFirme;
	}
	public void setPedidoFirme(String pedidoFirme) {
		this.pedidoFirme = pedidoFirme;
	}
	public String getCodigoSapCliente() {
		return codigoSapCliente;
	}
	public void setCodigoSapCliente(String codigoSapCliente) {
		this.codigoSapCliente = codigoSapCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCodigoPuertoDestino() {
		return codigoPuertoDestino;
	}
	public void setCodigoPuertoDestino(String codigoPuertoDestino) {
		this.codigoPuertoDestino = codigoPuertoDestino;
	}
	public String getPuertoDestino() {
		return puertoDestino;
	}
	public void setPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}
	public BigDecimal getPesoReal() {
		return pesoReal;
	}
	public void setPesoReal(BigDecimal pesoReal) {
		this.pesoReal = pesoReal;
	}
	public String getCodigoPaisDestino() {
		return codigoPaisDestino;
	}
	public void setCodigoPaisDestino(String codigoPaisDestino) {
		this.codigoPaisDestino = codigoPaisDestino;
	}
	public String getPaisDestino() {
		return paisDestino;
	}
	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public String getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}
	public void setFechaDisponibilidad(String fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}
	public String getCodigoIncotermComercial() {
		return codigoIncotermComercial;
	}
	public void setCodigoIncotermComercial(String codigoIncotermComercial) {
		this.codigoIncotermComercial = codigoIncotermComercial;
	}
	public String getCodigoAgenteNaviera() {
		return codigoAgenteNaviera;
	}
	public void setCodigoAgenteNaviera(String codigoAgenteNaviera) {
		this.codigoAgenteNaviera = codigoAgenteNaviera;
	}
	public String getNombreAgenteNaviera() {
		return nombreAgenteNaviera;
	}
	public void setNombreAgenteNaviera(String nombreAgenteNaviera) {
		this.nombreAgenteNaviera = nombreAgenteNaviera;
	}
	public String getCodigoAgenteCarga() {
		return codigoAgenteCarga;
	}
	public void setCodigoAgenteCarga(String codigoAgenteCarga) {
		this.codigoAgenteCarga = codigoAgenteCarga;
	}
	public String getNombreAgenteCarga() {
		return nombreAgenteCarga;
	}
	public void setNombreAgenteCarga(String nombreAgenteCarga) {
		this.nombreAgenteCarga = nombreAgenteCarga;
	}
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getEtaOrigen() {
		return etaOrigen;
	}
	public void setEtaOrigen(String etaOrigen) {
		this.etaOrigen = etaOrigen;
	}
	public String getEtaDestino() {
		return etaDestino;
	}
	public void setEtaDestino(String etaDestino) {
		this.etaDestino = etaDestino;
	}
	public String getFacturaElectronica() {
		return facturaElectronica;
	}
	public void setFacturaElectronica(String facturaElectronica) {
		this.facturaElectronica = facturaElectronica;
	}
	public String getFacturaSap() {
		return facturaSap;
	}
	public void setFacturaSap(String facturaSap) {
		this.facturaSap = facturaSap;
	}
	public String getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getFechaEnvioDocumento() {
		return fechaEnvioDocumento;
	}
	public void setFechaEnvioDocumento(String fechaEnvioDocumento) {
		this.fechaEnvioDocumento = fechaEnvioDocumento;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	public BigDecimal getFlete() {
		return flete;
	}
	public void setFlete(BigDecimal flete) {
		this.flete = flete;
	}
	public String getGuiaDhl() {
		return guiaDhl;
	}
	public void setGuiaDhl(String guiaDhl) {
		this.guiaDhl = guiaDhl;
	}
	public String getFechaBlProgramado() {
		return fechaBlProgramado;
	}
	public void setFechaBlProgramado(String fechaBlProgramado) {
		this.fechaBlProgramado = fechaBlProgramado;
	}
	public String getFechaBlReal() {
		return fechaBlReal;
	}
	public void setFechaBlReal(String fechaBlReal) {
		this.fechaBlReal = fechaBlReal;
	}
	public String getFechaDam41() {
		return fechaDam41;
	}
	public void setFechaDam41(String fechaDam41) {
		this.fechaDam41 = fechaDam41;
	}
	public String getFechaDam() {
		return fechaDam;
	}
	public void setFechaDam(String fechaDam) {
		this.fechaDam = fechaDam;
	}
	public String getDam() {
		return dam;
	}
	public void setDam(String dam) {
		this.dam = dam;
	}
	public String getPuertoOrigen() {
		return puertoOrigen;
	}
	public void setPuertoOrigen(String puertoOrigen) {
		this.puertoOrigen = puertoOrigen;
	}
	public String getFechaCarguio() {
		return fechaCarguio;
	}
	public void setFechaCarguio(String fechaCarguio) {
		this.fechaCarguio = fechaCarguio;
	}
	public String getFechaMaximaIngreso() {
		return fechaMaximaIngreso;
	}
	public void setFechaMaximaIngreso(String fechaMaximaIngreso) {
		this.fechaMaximaIngreso = fechaMaximaIngreso;
	}
	public String getDespachoEnSemana() {
		return despachoEnSemana;
	}
	public void setDespachoEnSemana(String despachoEnSemana) {
		this.despachoEnSemana = despachoEnSemana;
	}
	public String getNave() {
		return nave;
	}
	public void setNave(String nave) {
		this.nave = nave;
	}
	public String getArmador() {
		return armador;
	}
	public void setArmador(String armador) {
		this.armador = armador;
	}
	public BigDecimal getFleteTm() {
		return fleteTm;
	}
	public void setFleteTm(BigDecimal fleteTm) {
		this.fleteTm = fleteTm;
	}
	public BigDecimal getSeguro() {
		return seguro;
	}
	public void setSeguro(BigDecimal seguro) {
		this.seguro = seguro;
	}
	public String getOrdenInterna() {
		return ordenInterna;
	}
	public void setOrdenInterna(String ordenInterna) {
		this.ordenInterna = ordenInterna;
	}
	public int getNumeroContenedor() {
		return numeroContenedor;
	}
	public void setNumeroContenedor(int numeroContenedor) {
		this.numeroContenedor = numeroContenedor;
	}
	public int getIdControlGasto() {
		return idControlGasto;
	}
	public void setIdControlGasto(int idControlGasto) {
		this.idControlGasto = idControlGasto;
	}
}
