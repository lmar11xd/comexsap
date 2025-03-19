package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;

public class ExportacionTerrestreTO {
	private int id;
	private String codigo;
	private String codigoPacking;
	private String consignatario;
	private String direccionConsignatario;
	private String notificante;
	private String direccionNotificante;
	private String shipper;
	private String direccionShipper;
	private String glosa;
	private String booking;
	private String bl;
	private String guiaDhl;
	private String nave;
	private BigDecimal flete;
	private BigDecimal seguro;
	private int numeroContenedor;
	private Date etaOrigen;
	private Date fechaBlProgramado;
	private Date fechaCarguio;
	private Date fechaBlReal;
	private Date etaDestino;
	private Date fechaEnvioDocum;
	private Date fechaManifAduana;
	private Date fechaLevante;
	private Date fechaCrtFirmado;
	private Date fechaTransito;
	private Date fechaEntregaDua;
	private Date inicioEmbarque;
	private String dam;
	private String dam41;
	private Date fechaEntrega;
	private Date fechaDam;
	private Date fechaDamRegularizacion;
	private Date fechaDam41;
	private String agenteAduana;
	private String mesDespacho;
	private String dataAnterior;
	private String dataAnterior2;
	private int idCliente;
	private String codigoSapCliente;
	private String nombreCliente;
	private String calleCliente;
	private String rucCliente;
	private int idDestinatario;
	private String codigoSapDestinatario;
	private String nombreDestinatario;
	private String calleDestinatario;
	private String rucDestinatario;
	private int idDestinatarioMercancia;
	private String codigoSapDestinatMerc;
	private String nombreDestinatMerc;
	private String calleDestinatMerc;
	private String rucDestinatMerc;
	private int idCondicionPago;
	private String codigoCondicionPago;
	private String nombreCondicionPago;
	private int idPuertoOrigen;
	private String codigoSapPuertoOrigen;
	private String puertoOrigen;
	private int idPaisPuertoOrigen;
	private String paisPuertoOrigen;
	private String codigoPaisPuertoOrig;
	private String paisDescripOrigen;
	private int idPuertoDestino;
	private String codigoPuertoDestino;
	private String puertoDestino;
	private int idPaisPuertoDestino;
	private String paisPuertoDestino;
	private String paisDescripDestino;
	private String codigoPaisPuertoDestino;
	private int idCentro;
	private String codigoCentro;
	private String descripCentro;
	private int idDestino;
	private String codigoDestino;
	private String nombreDestino;
	private String descripDestino;
	private String origen;
	private String codigoOrigen;
	private String destino;
	private int idTipoTransporte;
	private String nombreTransporte;
	private String descripTransporte;
	private int idIncoterm;
	private String nombreIncoterm;
	private String codigoIncoterm;
	private int idIncotermComercial;
	private String codigoIncotermComercial;
	private String nombreIncotermComercial;
	private int idDespacho;
	private String nombreDespacho;
	private String descripDespacho;
	private int idAgenteAduana;
	private int idAgenteCarga;
	private String codigoAgenteCarga;
	private String nombreAgenteCarga;
	private String agenteCarga;
	private int idAgenteNaviera;
	private String codigoAgenteNaviera;
	private String nombreAgenteNaviera;
	private String agenteNaviera;
	private String empresaTransporte;
	private String cantidad;
	private int idServicioCabecera;
	private int cantidadTonelada;
	private int diaCarga;
	private int numeroJornada;
	private int numeroFactura;
	private int numeroDam;
	private int cabeceraNroContenedor;
	private int numeroBl;
	private int tipoCambio;
	private String atendido;
	private String canalControlExport;
	private String ordenAgenteAduana;
	private String estadoOrden;
	private int tiempoRegulDam41;
	private String comentario;
	private BigDecimal pesoReal;
	private int idFactura;
	private String claseExpedicion;
	private String estadoFactura;
	private Date fechaSalidaFactura;
	private String codigoFactura;
	private String orgVentaFactura;
	private String canalFactura;
	private String sectorFactura;
	private String folioFactura;
	private String serieFactura;
	private String serieCodFactura;
	private Date fechaFactura;
	private String horaSunatFactura;
	private int importeFactura;
	private String folioNotaCredito;
	private String codigoNotaCredito;
	private int idRegimen;
	private String codigoRegimen;
	private String nombreRegimen;
	private String descripRegimen;
	private Date fechaListaPrecio;
	private int idListaPrecio;
	private String codigoListaPrecio;
	private String nombreListaPrecio;
	private int idRuta;
	private String codigoRuta;
	private String nombreRuta;
	private String codigoMoneda;
	private int idMoneda;
	private String nombreMoneda;
	private int idCarpeta;
	private String nodeId;
	private String nodeIdControlGasto;
	private int idCarpetaPacking;
	private String nodeIdPacking;
	private int idEstadoDocumento;
	private String estadodocumentoExport;
	private int idEstadoContGast;
	private int idEstadoPacking;
	private int guardadoExport;
	private int guardadoControlGasto;
	private int guardadoPacking;
	private int idMonedaSeguro;
	private int idMonedaFlete;
	private int idAlmacen;
	private String codigoAlmacen;
	private String descripcionAlmacen;
	private String emitidoEn;
	private Date fechaEntregaDocu;
	private Date fechaFacturaPacking;
	private String etiquetaEmpaque;
	private String empaque;
	private String etiquetaMarca;
	private String marca;
	private String etiquetaUnidProd;
	private String unidadProductiva;
	private String etiquetaPrecioUnitario;
	private String precioUnitario;
	private String etiquetaFacturacion;
	private String facturacion;
	private String etiquetaFormaPago;
	private String formaPago;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;
	private String pedidoFirme;
	
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
	public String getCodigoPacking() {
		return codigoPacking;
	}
	public void setCodigoPacking(String codigoPacking) {
		this.codigoPacking = codigoPacking;
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
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
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
	public String getGuiaDhl() {
		return guiaDhl;
	}
	public void setGuiaDhl(String guiaDhl) {
		this.guiaDhl = guiaDhl;
	}
	public String getNave() {
		return nave;
	}
	public void setNave(String nave) {
		this.nave = nave;
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
	public int getNumeroContenedor() {
		return numeroContenedor;
	}
	public void setNumeroContenedor(int numeroContenedor) {
		this.numeroContenedor = numeroContenedor;
	}
	public Date getEtaOrigen() {
		return etaOrigen;
	}
	public void setEtaOrigen(Date etaOrigen) {
		this.etaOrigen = etaOrigen;
	}
	public Date getFechaBlProgramado() {
		return fechaBlProgramado;
	}
	public void setFechaBlProgramado(Date fechaBlProgramado) {
		this.fechaBlProgramado = fechaBlProgramado;
	}
	public Date getFechaCarguio() {
		return fechaCarguio;
	}
	public void setFechaCarguio(Date fechaCarguio) {
		this.fechaCarguio = fechaCarguio;
	}
	
	public Date getFechaBlReal() {
		return fechaBlReal;
	}
	public void setFechaBlReal(Date fechaBlReal) {
		this.fechaBlReal = fechaBlReal;
	}
	
	public Date getEtaDestino() {
		return etaDestino;
	}
	public void setEtaDestino(Date etaDestino) {
		this.etaDestino = etaDestino;
	}
	public Date getFechaEnvioDocum() {
		return fechaEnvioDocum;
	}
	public void setFechaEnvioDocum(Date fechaEnvioDocum) {
		this.fechaEnvioDocum = fechaEnvioDocum;
	}
	public Date getFechaManifAduana() {
		return fechaManifAduana;
	}
	public void setFechaManifAduana(Date fechaManifAduana) {
		this.fechaManifAduana = fechaManifAduana;
	}
	public Date getFechaLevante() {
		return fechaLevante;
	}
	public void setFechaLevante(Date fechaLevante) {
		this.fechaLevante = fechaLevante;
	}
	public Date getFechaCrtFirmado() {
		return fechaCrtFirmado;
	}
	public void setFechaCrtFirmado(Date fechaCrtFirmado) {
		this.fechaCrtFirmado = fechaCrtFirmado;
	}
	public Date getFechaTransito() {
		return fechaTransito;
	}
	public void setFechaTransito(Date fechaTransito) {
		this.fechaTransito = fechaTransito;
	}
	public Date getFechaEntregaDua() {
		return fechaEntregaDua;
	}
	public void setFechaEntregaAdua(Date fechaEntregaDua) {
		this.fechaEntregaDua = fechaEntregaDua;
	}
	public Date getInicioEmbarque() {
		return inicioEmbarque;
	}
	public void setInicioEmbarque(Date inicioEmbarque) {
		this.inicioEmbarque = inicioEmbarque;
	}
	public String getDam() {
		return dam;
	}
	public void setDam(String dam) {
		this.dam = dam;
	}
	public String getDam41() {
		return dam41;
	}
	public void setDam41(String dam41) {
		this.dam41 = dam41;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaDam() {
		return fechaDam;
	}
	public void setFechaDam(Date fechaDam) {
		this.fechaDam = fechaDam;
	}
	public Date getFechaDamRegularizacion() {
		return fechaDamRegularizacion;
	}
	public void setFechaDamRegularizacion(Date fechaDamRegularizacion) {
		this.fechaDamRegularizacion = fechaDamRegularizacion;
	}
	public Date getFechaDam41() {
		return fechaDam41;
	}
	public void setFechaDam41(Date fechaDam41) {
		this.fechaDam41 = fechaDam41;
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
	public String getDataAnterior() {
		return dataAnterior;
	}
	public void setDataAnterior(String dataAnterior) {
		this.dataAnterior = dataAnterior;
	}
	public String getDataAnterior2() {
		return dataAnterior2;
	}
	public void setDataAnterior2(String dataAnterior2) {
		this.dataAnterior2 = dataAnterior2;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	public String getCalleCliente() {
		return calleCliente;
	}
	public void setCalleCliente(String calleCliente) {
		this.calleCliente = calleCliente;
	}
	public String getRucCliente() {
		return rucCliente;
	}
	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}
	public int getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	public String getCodigoSapDestinatario() {
		return codigoSapDestinatario;
	}
	public void setCodigoSapDestinatario(String codigoSapDestinatario) {
		this.codigoSapDestinatario = codigoSapDestinatario;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	public String getCalleDestinatario() {
		return calleDestinatario;
	}
	public void setCalleDestinatario(String calleDestinatario) {
		this.calleDestinatario = calleDestinatario;
	}
	public String getRucDestinatario() {
		return rucDestinatario;
	}
	public void setRucDestinatario(String rucDestinatario) {
		this.rucDestinatario = rucDestinatario;
	}
	public int getIdDestinatarioMercancia() {
		return idDestinatarioMercancia;
	}
	public void setIdDestinatarioMercancia(int idDestinatarioMercancia) {
		this.idDestinatarioMercancia = idDestinatarioMercancia;
	}
	public String getCodigoSapDestinatMerc() {
		return codigoSapDestinatMerc;
	}
	public void setCodigoSapDestinatMerc(String codigoSapDestinatMerc) {
		this.codigoSapDestinatMerc = codigoSapDestinatMerc;
	}
	public String getNombreDestinatMerc() {
		return nombreDestinatMerc;
	}
	public void setNombreDestinatMerc(String nombreDestinatMerc) {
		this.nombreDestinatMerc = nombreDestinatMerc;
	}
	public String getCalleDestinatMerc() {
		return calleDestinatMerc;
	}
	public void setCalleDestinatMerc(String calleDestinatMerc) {
		this.calleDestinatMerc = calleDestinatMerc;
	}
	public String getRucDestinatMerc() {
		return rucDestinatMerc;
	}
	public void setRucDestinatMerc(String rucDestinatMerc) {
		this.rucDestinatMerc = rucDestinatMerc;
	}
	public int getIdCondicionPago() {
		return idCondicionPago;
	}
	public void setIdCondicionPago(int idCondicionPago) {
		this.idCondicionPago = idCondicionPago;
	}
	public String getCodigoCondicionPago() {
		return codigoCondicionPago;
	}
	public void setCodigoCondicionPago(String codigoCondicionPago) {
		this.codigoCondicionPago = codigoCondicionPago;
	}
	public String getNombreCondicionPago() {
		return nombreCondicionPago;
	}
	public void setNombreCondicionPago(String nombreCondicionPago) {
		this.nombreCondicionPago = nombreCondicionPago;
	}
	public int getIdPuertoOrigen() {
		return idPuertoOrigen;
	}
	public void setIdPuertoOrigen(int idPuertoOrigen) {
		this.idPuertoOrigen = idPuertoOrigen;
	}
	public String getCodigoSapPuertoOrigen() {
		return codigoSapPuertoOrigen;
	}
	public void setCodigoSapPuertoOrigen(String codigoSapPuertoOrigen) {
		this.codigoSapPuertoOrigen = codigoSapPuertoOrigen;
	}
	public String getPuertoOrigen() {
		return puertoOrigen;
	}
	public void setPuertoOrigen(String puertoOrigen) {
		this.puertoOrigen = puertoOrigen;
	}
	public int getIdPaisPuertoOrigen() {
		return idPaisPuertoOrigen;
	}
	public void setIdPaisPuertoOrigen(int idPaisPuertoOrigen) {
		this.idPaisPuertoOrigen = idPaisPuertoOrigen;
	}
	public String getPaisPuertoOrigen() {
		return paisPuertoOrigen;
	}
	public void setPaisPuertoOrigen(String paisPuertoOrigen) {
		this.paisPuertoOrigen = paisPuertoOrigen;
	}
	public String getCodigoPaisPuertoOrig() {
		return codigoPaisPuertoOrig;
	}
	public void setCodigoPaisPuertoOrig(String codigoPaisPuertoOrig) {
		this.codigoPaisPuertoOrig = codigoPaisPuertoOrig;
	}
	public String getPaisDescripOrigen() {
		return paisDescripOrigen;
	}
	public void setPaisDescripOrigen(String paisDescripOrigen) {
		this.paisDescripOrigen = paisDescripOrigen;
	}
	public int getIdPuertoDestino() {
		return idPuertoDestino;
	}
	public void setIdPuertoDestino(int idPuertoDestino) {
		this.idPuertoDestino = idPuertoDestino;
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
	public int getIdPaisPuertoDestino() {
		return idPaisPuertoDestino;
	}
	public void setIdPaisPuertoDestino(int idPaisPuertoDestino) {
		this.idPaisPuertoDestino = idPaisPuertoDestino;
	}
	public String getPaisPuertoDestino() {
		return paisPuertoDestino;
	}
	public void setPaisPuertoDestino(String paisPuertoDestino) {
		this.paisPuertoDestino = paisPuertoDestino;
	}
	public String getPaisDescripDestino() {
		return paisDescripDestino;
	}
	public void setPaisDescripDestino(String paisDescripDestino) {
		this.paisDescripDestino = paisDescripDestino;
	}
	public String getCodigoPaisPuertoDestino() {
		return codigoPaisPuertoDestino;
	}
	public void setCodigoPaisPuertoDestino(String codigoPaisPuertoDestino) {
		this.codigoPaisPuertoDestino = codigoPaisPuertoDestino;
	}
	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	public String getCodigoCentro() {
		return codigoCentro;
	}
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}
	public String getDescripCentro() {
		return descripCentro;
	}
	public void setDescripCentro(String descripCentro) {
		this.descripCentro = descripCentro;
	}
	public int getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}
	public String getCodigoDestino() {
		return codigoDestino;
	}
	public void setCodigoDestino(String codigoDestino) {
		this.codigoDestino = codigoDestino;
	}
	public String getNombreDestino() {
		return nombreDestino;
	}
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	public String getDescripDestino() {
		return descripDestino;
	}
	public void setDescripDestino(String descripDestino) {
		this.descripDestino = descripDestino;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public int getIdTipoTransporte() {
		return idTipoTransporte;
	}
	public void setIdTipoTransporte(int idTipoTransporte) {
		this.idTipoTransporte = idTipoTransporte;
	}
	public String getNombreTransporte() {
		return nombreTransporte;
	}
	public void setNombreTransporte(String nombreTransporte) {
		this.nombreTransporte = nombreTransporte;
	}
	public String getDescripTransporte() {
		return descripTransporte;
	}
	public void setDescripTransporte(String descripTransporte) {
		this.descripTransporte = descripTransporte;
	}
	public int getIdIncoterm() {
		return idIncoterm;
	}
	public void setIdIncoterm(int idIncoterm) {
		this.idIncoterm = idIncoterm;
	}
	public String getNombreIncoterm() {
		return nombreIncoterm;
	}
	public void setNombreIncoterm(String nombreIncoterm) {
		this.nombreIncoterm = nombreIncoterm;
	}
	public String getCodigoIncoterm() {
		return codigoIncoterm;
	}
	public void setCodigoIncoterm(String codigoIncoterm) {
		this.codigoIncoterm = codigoIncoterm;
	}
	public int getIdIncotermComercial() {
		return idIncotermComercial;
	}
	public void setIdIncotermComercial(int idIncotermComercial) {
		this.idIncotermComercial = idIncotermComercial;
	}
	public String getCodigoIncotermComercial() {
		return codigoIncotermComercial;
	}
	public void setCodigoIncotermComercial(String codigoIncotermComercial) {
		this.codigoIncotermComercial = codigoIncotermComercial;
	}
	
	public String getNombreIncotermComercial() {
		return nombreIncotermComercial;
	}
	public void setNombreIncotermComercial(String nombreIncotermComercial) {
		this.nombreIncotermComercial = nombreIncotermComercial;
	}
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public String getNombreDespacho() {
		return nombreDespacho;
	}
	public void setNombreDespacho(String nombreDespacho) {
		this.nombreDespacho = nombreDespacho;
	}
	public String getDescripDespacho() {
		return descripDespacho;
	}
	public void setDescripDespacho(String descripDespacho) {
		this.descripDespacho = descripDespacho;
	}
	public int getIdAgenteAduana() {
		return idAgenteAduana;
	}
	public void setIdAgenteAduana(int idAgenteAduana) {
		this.idAgenteAduana = idAgenteAduana;
	}
	public int getIdAgenteCarga() {
		return idAgenteCarga;
	}
	public void setIdAgenteCarga(int idAgenteCarga) {
		this.idAgenteCarga = idAgenteCarga;
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
	public int getIdAgenteNaviera() {
		return idAgenteNaviera;
	}
	public void setIdAgenteNaviera(int idAgenteNaviera) {
		this.idAgenteNaviera = idAgenteNaviera;
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
	public String getEmpresaTransporte() {
		return empresaTransporte;
	}
	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdServicioCabecera() {
		return idServicioCabecera;
	}
	public void setIdServicioCabecera(int idServicioCabecera) {
		this.idServicioCabecera = idServicioCabecera;
	}
	public int getCantidadTonelada() {
		return cantidadTonelada;
	}
	public void setCantidadTonelada(int cantidadTonelada) {
		this.cantidadTonelada = cantidadTonelada;
	}
	public int getDiaCarga() {
		return diaCarga;
	}
	public void setDiaCarga(int diaCarga) {
		this.diaCarga = diaCarga;
	}
	public int getNumeroJornada() {
		return numeroJornada;
	}
	public void setNumeroJornada(int numeroJornada) {
		this.numeroJornada = numeroJornada;
	}
	public int getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public int getNumeroDam() {
		return numeroDam;
	}
	public void setNumeroDam(int numeroDam) {
		this.numeroDam = numeroDam;
	}
	public int getCabeceraNroContenedor() {
		return cabeceraNroContenedor;
	}
	public void setCabeceraNroContenedor(int cabeceraNroContenedor) {
		this.cabeceraNroContenedor = cabeceraNroContenedor;
	}
	public int getNumeroBl() {
		return numeroBl;
	}
	public void setNumeroBl(int numeroBl) {
		this.numeroBl = numeroBl;
	}
	public int getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(int tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getAtendido() {
		return atendido;
	}
	public void setAtendido(String atendido) {
		this.atendido = atendido;
	}
	public String getCanalControlExport() {
		return canalControlExport;
	}
	public void setCanalControlExport(String canalControlExport) {
		this.canalControlExport = canalControlExport;
	}
	public String getOrdenAgenteAduana() {
		return ordenAgenteAduana;
	}
	public void setOrdenAgenteAduana(String ordenAgenteAduana) {
		this.ordenAgenteAduana = ordenAgenteAduana;
	}
	public String getEstadoOrden() {
		return estadoOrden;
	}
	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}
	public int getTiempoRegulDam41() {
		return tiempoRegulDam41;
	}
	public void setTiempoRegulDam41(int tiempoRegulDam41) {
		this.tiempoRegulDam41 = tiempoRegulDam41;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public BigDecimal getPesoReal() {
		return pesoReal;
	}
	public void setPesoReal(BigDecimal pesoReal) {
		this.pesoReal = pesoReal;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getClaseExpedicion() {
		return claseExpedicion;
	}
	public void setClaseExpedicion(String claseExpedicion) {
		this.claseExpedicion = claseExpedicion;
	}
	public String getEstadoFactura() {
		return estadoFactura;
	}
	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}
	public Date getFechaSalidaFactura() {
		return fechaSalidaFactura;
	}
	public void setFechaSalidaFactura(Date fechaSalidaFactura) {
		this.fechaSalidaFactura = fechaSalidaFactura;
	}
	public String getCodigoFactura() {
		return codigoFactura;
	}
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
	public String getOrgVentaFactura() {
		return orgVentaFactura;
	}
	public void setOrgVentaFactura(String orgVentaFactura) {
		this.orgVentaFactura = orgVentaFactura;
	}
	public String getCanalFactura() {
		return canalFactura;
	}
	public void setCanalFactura(String canalFactura) {
		this.canalFactura = canalFactura;
	}
	public String getSectorFactura() {
		return sectorFactura;
	}
	public void setSectorFactura(String sectorFactura) {
		this.sectorFactura = sectorFactura;
	}
	public String getFolioFactura() {
		return folioFactura;
	}
	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}
	public String getSerieFactura() {
		return serieFactura;
	}
	public void setSerieFactura(String serieFactura) {
		this.serieFactura = serieFactura;
	}
	public String getSerieCodFactura() {
		return serieCodFactura;
	}
	public void setSerieCodFactura(String serieCodFactura) {
		this.serieCodFactura = serieCodFactura;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getHoraSunatFactura() {
		return horaSunatFactura;
	}
	public void setHoraSunatFactura(String horaSunatFactura) {
		this.horaSunatFactura = horaSunatFactura;
	}
	public int getImporteFactura() {
		return importeFactura;
	}
	public void setImporteFactura(int importeFactura) {
		this.importeFactura = importeFactura;
	}
	public String getFolioNotaCredito() {
		return folioNotaCredito;
	}
	public void setFolioNotaCredito(String folioNotaCredito) {
		this.folioNotaCredito = folioNotaCredito;
	}
	public String getCodigoNotaCredito() {
		return codigoNotaCredito;
	}
	public void setCodigoNotaCredito(String codigoNotaCredito) {
		this.codigoNotaCredito = codigoNotaCredito;
	}
	public int getIdRegimen() {
		return idRegimen;
	}
	public void setIdRegimen(int idRegimen) {
		this.idRegimen = idRegimen;
	}
	public String getCodigoRegimen() {
		return codigoRegimen;
	}
	public void setCodigoRegimen(String codigoRegimen) {
		this.codigoRegimen = codigoRegimen;
	}
	public String getNombreRegimen() {
		return nombreRegimen;
	}
	public void setNombreRegimen(String nombreRegimen) {
		this.nombreRegimen = nombreRegimen;
	}
	public String getDescripRegimen() {
		return descripRegimen;
	}
	public void setDescripRegimen(String descripRegimen) {
		this.descripRegimen = descripRegimen;
	}
	public Date getFechaListaPrecio() {
		return fechaListaPrecio;
	}
	public void setFechaListaPrecio(Date fechaListaPrecio) {
		this.fechaListaPrecio = fechaListaPrecio;
	}
	public int getIdListaPrecio() {
		return idListaPrecio;
	}
	public void setIdListaPrecio(int idListaPrecio) {
		this.idListaPrecio = idListaPrecio;
	}
	public String getCodigoListaPrecio() {
		return codigoListaPrecio;
	}
	public void setCodigoListaPrecio(String codigoListaPrecio) {
		this.codigoListaPrecio = codigoListaPrecio;
	}
	public String getNombreListaPrecio() {
		return nombreListaPrecio;
	}
	public void setNombreListaPrecio(String nombreListaPrecio) {
		this.nombreListaPrecio = nombreListaPrecio;
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public String getNombreRuta() {
		return nombreRuta;
	}
	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public int getIdCarpeta() {
		return idCarpeta;
	}
	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public int getIdCarpetaPacking() {
		return idCarpetaPacking;
	}
	public void setIdCarpetaPacking(int idCarpetaPacking) {
		this.idCarpetaPacking = idCarpetaPacking;
	}
	public int getIdEstadoDocumento() {
		return idEstadoDocumento;
	}
	public void setIdEstadoDocumento(int idEstadoDocomento) {
		this.idEstadoDocumento = idEstadoDocomento;
	}
	public String getEstadodocumentoExport() {
		return estadodocumentoExport;
	}
	public void setEstadodocumentoExport(String estadodocumentoExport) {
		this.estadodocumentoExport = estadodocumentoExport;
	}
	public int getIdEstadoContGast() {
		return idEstadoContGast;
	}
	public void setIdEstadoContGast(int idEstadoContGast) {
		this.idEstadoContGast = idEstadoContGast;
	}
	public int getIdEstadoPacking() {
		return idEstadoPacking;
	}
	public void setIdEstadoPacking(int idEstadoPacking) {
		this.idEstadoPacking = idEstadoPacking;
	}
	public int getGuardadoExport() {
		return guardadoExport;
	}
	public void setGuardadoExport(int guardadoExport) {
		this.guardadoExport = guardadoExport;
	}
	public int getGuardadoControlGasto() {
		return guardadoControlGasto;
	}
	public void setGuardadoControlGasto(int guardadoControlGasto) {
		this.guardadoControlGasto = guardadoControlGasto;
	}
	public int getGuardadoPacking() {
		return guardadoPacking;
	}
	public void setGuardadoPacking(int guardadoPacking) {
		this.guardadoPacking = guardadoPacking;
	}
	public int getIdMonedaSeguro() {
		return idMonedaSeguro;
	}
	public void setIdMonedaSeguro(int idMonedaSeguro) {
		this.idMonedaSeguro = idMonedaSeguro;
	}
	public int getIdMonedaFlete() {
		return idMonedaFlete;
	}
	public void setIdMonedaFlete(int idMonedaFlete) {
		this.idMonedaFlete = idMonedaFlete;
	}
	public int getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}
	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}
	public String getEmitidoEn() {
		return emitidoEn;
	}
	public void setEmitidoEn(String emitidoEn) {
		this.emitidoEn = emitidoEn;
	}
	public Date getFechaEntregaDocu() {
		return fechaEntregaDocu;
	}
	public void setFechaEntregaDocu(Date fechaEntregaDocu) {
		this.fechaEntregaDocu = fechaEntregaDocu;
	}
	public Date getFechaFacturaPacking() {
		return fechaFacturaPacking;
	}
	public void setFechaFacturaPacking(Date fechaFacturaPacking) {
		this.fechaFacturaPacking = fechaFacturaPacking;
	}
	public String getEtiquetaEmpaque() {
		return etiquetaEmpaque;
	}
	public void setEtiquetaEmpaque(String etiquetaEmpaque) {
		this.etiquetaEmpaque = etiquetaEmpaque;
	}
	public String getEmpaque() {
		return empaque;
	}
	public void setEmpaque(String empaque) {
		this.empaque = empaque;
	}
	public String getEtiquetaMarca() {
		return etiquetaMarca;
	}
	public void setEtiquetaMarca(String etiquetaMarca) {
		this.etiquetaMarca = etiquetaMarca;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getEtiquetaUnidProd() {
		return etiquetaUnidProd;
	}
	public void setEtiquetaUnidProd(String etiquetaUnidProd) {
		this.etiquetaUnidProd = etiquetaUnidProd;
	}
	public String getUnidadProductiva() {
		return unidadProductiva;
	}
	public void setUnidadProductiva(String unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}
	public String getEtiquetaPrecioUnitario() {
		return etiquetaPrecioUnitario;
	}
	public void setEtiquetaPrecioUnitario(String etiquetaPrecioUnitario) {
		this.etiquetaPrecioUnitario = etiquetaPrecioUnitario;
	}
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getEtiquetaFacturacion() {
		return etiquetaFacturacion;
	}
	public void setEtiquetaFacturacion(String etiquetaFacturacion) {
		this.etiquetaFacturacion = etiquetaFacturacion;
	}
	public String getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(String facturacion) {
		this.facturacion = facturacion;
	}
	public String getEtiquetaFormaPago() {
		return etiquetaFormaPago;
	}
	public void setEtiquetaFormaPago(String etiquetaFormaPago) {
		this.etiquetaFormaPago = etiquetaFormaPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getNodeIdControlGasto() {
		return nodeIdControlGasto;
	}
	public void setNodeIdControlGasto(String nodeIdControlGasto) {
		this.nodeIdControlGasto = nodeIdControlGasto;
	}
	public String getNodeIdPacking() {
		return nodeIdPacking;
	}
	public void setNodeIdPacking(String nodeIdPacking) {
		this.nodeIdPacking = nodeIdPacking;
	}
	public String getCodigoRuta() {
		return codigoRuta;
	}
	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}
	public String getPedidoFirme() {
		return pedidoFirme;
	}
	public void setPedidoFirme(String pedidoFirme) {
		this.pedidoFirme = pedidoFirme;
	}
	public String getAgenteCarga() {
		return agenteCarga;
	}
	public void setAgenteCarga(String agenteCarga) {
		this.agenteCarga = agenteCarga;
	}
	public String getAgenteNaviera() {
		return agenteNaviera;
	}
	public void setAgenteNaviera(String agenteNaviera) {
		this.agenteNaviera = agenteNaviera;
	}

}
