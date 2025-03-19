package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;

public class CotizacionTO{

	private int idCotizacion;
	private int idCarpeta;
	private int idCliente;
	private int tipoSolicitud;
	private int idCondicionPago;
	private int idDespacho;
	private int idDestinatario;
	private int idEstadoDocumento;
	private int idFormaPago;
	private int idIncoterm;
	private int idIncotermComercial;
	private int idListaPrecio;
	private int idMoneda;
	private int idPaisPuertoDestino;
	private int idPaisPuertoOrigen;
	private int idPuertoDestino;
	private int idPuertoOrigen;
	private int idRuta;
	private int idPadre;
	private int idGuardado;
	private int idTipoTransporte;
	private BigDecimal importeFlete;
	private BigDecimal seguroInternacional;
	private String nodeId;
	private String nombreCliente;
	private String nombreCliente2;
	private String nombreCondicionPago;
	private String nombreContacto;
	private String nombreDespacho;
	private String nombreDestinatario;
	private String nombreDestinatario2;
	private String nombreEstadoDocumento;
	private String nombreFormaPago;
	private String nombreIncoterm;
	private String nombreIncotermComercial;
	private String nombreListaPrecio;
	private String nombreMoneda;
	private String nombreNuevoCliente;
	private String nombreNuevoDestinatario;
	private String nombrePaisPuertoDestino;
	private String nombrePaisPuertoOrigen;
	private String nombrePuertoDestino;
	private String nombrePuertoOrigen;
	private String nombreRuta;
	private String nombreTipoTransporte;
	private int numeroContenedor;
	private String observacion;
	private String tiempoEntrega;
	private int vigenciaOferta;
	private String cargoContacto;
	private String codigoPedido;
	private String codigoCliente;
	private String codigoCondicionPago;
	private String codigoDespacho;
	private String codigoDestinatario;
	private String codigoEstadoDocumento;
	private String codigoFormaPago;
	private String codigoIncoterm;
	private String codigoListaPrecio;
	private String codigoMoneda;
	private String codigoPuertoDestino;
	private String codigoPuertoOrigen;
	private String codigoRuta;
	private String codigoSapPaisPuertoDest;
	private String codigoSapPaisPuestoOrig;
	private String codigoTipoTransporte;
	private String correoContacto;
	private Date fechaListaPrecio;
	private Date fechaSolicitud;
	private boolean clienteSapExiste;
	private String cargoContactoAdicional;
	private String nombreContactoAdicional;
	private String correoContactoAdicional;
	private String razonSocial;
	private String lugarContacto;
	private String descripcionMaterial;
	private String descripcionPrecio;
	private String vigencia;
	private boolean tipoDocumento;
	private String incotermTotal;
	private String lugarTotal;
	private String precioCabecera;
    private String codigoPersonal;
    
	public int getIdCotizacion() {
		return idCotizacion;
	}
	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	public int getIdCarpeta() {
		return idCarpeta;
	}
	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdCondicionPago() {
		return idCondicionPago;
	}
	public void setIdCondicionPago(int idCondicionPago) {
		this.idCondicionPago = idCondicionPago;
	}
	public int getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}
	public int getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	public int getIdEstadoDocumento() {
		return idEstadoDocumento;
	}
	public void setIdEstadoDocumento(int idEstadoDocumento) {
		this.idEstadoDocumento = idEstadoDocumento;
	}
	public int getIdFormaPago() {
		return idFormaPago;
	}
	public void setIdFormaPago(int idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	public int getIdIncoterm() {
		return idIncoterm;
	}
	public void setIdIncoterm(int idIncoterm) {
		this.idIncoterm = idIncoterm;
	}
	public int getIdIncotermComercial() {
		return idIncotermComercial;
	}
	public void setIdIncotermComercial(int idIncotermComercial) {
		this.idIncotermComercial = idIncotermComercial;
	}
	public int getIdListaPrecio() {
		return idListaPrecio;
	}
	public void setIdListaPrecio(int idListaPrecio) {
		this.idListaPrecio = idListaPrecio;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public int getIdPaisPuertoDestino() {
		return idPaisPuertoDestino;
	}
	public void setIdPaisPuertoDestino(int idPaisPuertoDestino) {
		this.idPaisPuertoDestino = idPaisPuertoDestino;
	}
	public int getIdPaisPuertoOrigen() {
		return idPaisPuertoOrigen;
	}
	public void setIdPaisPuertoOrigen(int idPaisPuertoOrigen) {
		this.idPaisPuertoOrigen = idPaisPuertoOrigen;
	}
	public int getIdPuertoDestino() {
		return idPuertoDestino;
	}
	public void setIdPuertoDestino(int idPuertoDestino) {
		this.idPuertoDestino = idPuertoDestino;
	}
	public int getIdPuertoOrigen() {
		return idPuertoOrigen;
	}
	public void setIdPuertoOrigen(int idPuertoOrigen) {
		this.idPuertoOrigen = idPuertoOrigen;
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public int getIdTipoTransporte() {
		return idTipoTransporte;
	}
	public void setIdTipoTransporte(int idTipoTransporte) {
		this.idTipoTransporte = idTipoTransporte;
	}
	public BigDecimal getImporteFlete() {
		return importeFlete;
	}
	public void setImporteFlete(BigDecimal importeFlete) {
		this.importeFlete = importeFlete;
	}
	public BigDecimal getSeguroInternacional() {
		return seguroInternacional;
	}
	public void setSeguroInternacional(BigDecimal seguroInternacional) {
		this.seguroInternacional = seguroInternacional;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNombreCliente2() {
		return nombreCliente2;
	}
	public void setNombreCliente2(String nombreCliente2) {
		this.nombreCliente2 = nombreCliente2;
	}
	public String getNombreCondicionPago() {
		return nombreCondicionPago;
	}
	public void setNombreCondicionPago(String nombreCondicionPago) {
		this.nombreCondicionPago = nombreCondicionPago;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getNombreDespacho() {
		return nombreDespacho;
	}
	public void setNombreDespacho(String nombreDespacho) {
		this.nombreDespacho = nombreDespacho;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	public String getNombreDestinatario2() {
		return nombreDestinatario2;
	}
	public void setNombreDestinatario2(String nombreDestinatario2) {
		this.nombreDestinatario2 = nombreDestinatario2;
	}
	public String getNombreEstadoDocumento() {
		return nombreEstadoDocumento;
	}
	public void setNombreEstadoDocumento(String nombreEstadoDocumento) {
		this.nombreEstadoDocumento = nombreEstadoDocumento;
	}
	public String getNombreFormaPago() {
		return nombreFormaPago;
	}
	public void setNombreFormaPago(String nombreFormaPago) {
		this.nombreFormaPago = nombreFormaPago;
	}
	public String getNombreIncoterm() {
		return nombreIncoterm;
	}
	public void setNombreIncoterm(String nombreIncoterm) {
		this.nombreIncoterm = nombreIncoterm;
	}
	public String getNombreIncotermComercial() {
		return nombreIncotermComercial;
	}
	public void setNombreIncotermComercial(String nombreIncotermComercial) {
		this.nombreIncotermComercial = nombreIncotermComercial;
	}
	public String getNombreListaPrecio() {
		return nombreListaPrecio;
	}
	public void setNombreListaPrecio(String nombreListaPrecio) {
		this.nombreListaPrecio = nombreListaPrecio;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public String getNombreNuevoCliente() {
		return nombreNuevoCliente;
	}
	public void setNombreNuevoCliente(String nombreNuevoCliente) {
		this.nombreNuevoCliente = nombreNuevoCliente;
	}
	public String getNombreNuevoDestinatario() {
		return nombreNuevoDestinatario;
	}
	public void setNombreNuevoDestinatario(String nombreNuevoDestinatario) {
		this.nombreNuevoDestinatario = nombreNuevoDestinatario;
	}
	public String getNombrePaisPuertoDestino() {
		return nombrePaisPuertoDestino;
	}
	public void setNombrePaisPuertoDestino(String nombrePaisPuertoDestino) {
		this.nombrePaisPuertoDestino = nombrePaisPuertoDestino;
	}
	public String getNombrePaisPuertoOrigen() {
		return nombrePaisPuertoOrigen;
	}
	public void setNombrePaisPuertoOrigen(String nombrePaisPuertoOrigen) {
		this.nombrePaisPuertoOrigen = nombrePaisPuertoOrigen;
	}
	public String getNombrePuertoDestino() {
		return nombrePuertoDestino;
	}
	public void setNombrePuertoDestino(String nombrePuertoDestino) {
		this.nombrePuertoDestino = nombrePuertoDestino;
	}
	public String getNombrePuertoOrigen() {
		return nombrePuertoOrigen;
	}
	public void setNombrePuertoOrigen(String nombrePuertoOrigen) {
		this.nombrePuertoOrigen = nombrePuertoOrigen;
	}
	public String getNombreRuta() {
		return nombreRuta;
	}
	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}
	public String getNombreTipoTransporte() {
		return nombreTipoTransporte;
	}
	public void setNombreTipoTransporte(String nombreTipoTransporte) {
		this.nombreTipoTransporte = nombreTipoTransporte;
	}
	
	public int getNumeroContenedor() {
		return numeroContenedor;
	}
	public void setNumeroContenedor(int numeroContenedor) {
		this.numeroContenedor = numeroContenedor;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTiempoEntrega() {
		return tiempoEntrega;
	}
	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}
	public int getVigenciaOferta() {
		return vigenciaOferta;
	}
	public void setVigenciaOferta(int vigenciaOferta) {
		this.vigenciaOferta = vigenciaOferta;
	}
	public String getCargoContacto() {
		return cargoContacto;
	}
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoCondicionPago() {
		return codigoCondicionPago;
	}
	public void setCodigoCondicionPago(String codigoCondicionPago) {
		this.codigoCondicionPago = codigoCondicionPago;
	}
	public String getCodigoDespacho() {
		return codigoDespacho;
	}
	public void setCodigoDespacho(String codigoDespacho) {
		this.codigoDespacho = codigoDespacho;
	}
	public String getCodigoDestinatario() {
		return codigoDestinatario;
	}
	public void setCodigoDestinatario(String codigoDestinatario) {
		this.codigoDestinatario = codigoDestinatario;
	}
	public String getCodigoEstadoDocumento() {
		return codigoEstadoDocumento;
	}
	public void setCodigoEstadoDocumento(String codigoEstadoDocumento) {
		this.codigoEstadoDocumento = codigoEstadoDocumento;
	}
	public String getCodigoFormaPago() {
		return codigoFormaPago;
	}
	public void setCodigoFormaPago(String codigoFormaPago) {
		this.codigoFormaPago = codigoFormaPago;
	}
	public String getCodigoIncoterm() {
		return codigoIncoterm;
	}
	public void setCodigoIncoterm(String codigoIncoterm) {
		this.codigoIncoterm = codigoIncoterm;
	}
	public String getCodigoListaPrecio() {
		return codigoListaPrecio;
	}
	public void setCodigoListaPrecio(String codigoListaPrecio) {
		this.codigoListaPrecio = codigoListaPrecio;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public String getCodigoPuertoDestino() {
		return codigoPuertoDestino;
	}
	public void setCodigoPuertoDestino(String codigoPuertoDestino) {
		this.codigoPuertoDestino = codigoPuertoDestino;
	}
	public String getCodigoPuertoOrigen() {
		return codigoPuertoOrigen;
	}
	public void setCodigoPuertoOrigen(String codigoPuertoOrigen) {
		this.codigoPuertoOrigen = codigoPuertoOrigen;
	}
	public String getCodigoRuta() {
		return codigoRuta;
	}
	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}
	public String getCodigoSapPaisPuertoDest() {
		return codigoSapPaisPuertoDest;
	}
	public void setCodigoSapPaisPuertoDest(String codigoSapPaisPuertoDest) {
		this.codigoSapPaisPuertoDest = codigoSapPaisPuertoDest;
	}
	public String getCodigoSapPaisPuestoOrig() {
		return codigoSapPaisPuestoOrig;
	}
	public void setCodigoSapPaisPuestoOrig(String codigoSapPaisPuestoOrig) {
		this.codigoSapPaisPuestoOrig = codigoSapPaisPuestoOrig;
	}
	public String getCodigoTipoTransporte() {
		return codigoTipoTransporte;
	}
	public void setCodigoTipoTransporte(String codigoTipoTransporte) {
		this.codigoTipoTransporte = codigoTipoTransporte;
	}
	public String getCorreoContacto() {
		return correoContacto;
	}
	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}
	public Date getFechaListaPrecio() {
		return fechaListaPrecio;
	}
	public void setFechaListaPrecio(Date fechaListaPrecio) {
		this.fechaListaPrecio = fechaListaPrecio;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public int getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(int tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	public boolean isClienteSapExiste() {
		return clienteSapExiste;
	}
	public void setClienteSapExiste(boolean clienteSapExiste) {
		this.clienteSapExiste = clienteSapExiste;
	}
	public int getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}
	public int getIdGuardado() {
		return idGuardado;
	}
	public void setIdGuardado(int idGuardado) {
		this.idGuardado = idGuardado;
	}
	public String getCargoContactoAdicional() {
		return cargoContactoAdicional;
	}
	public void setCargoContactoAdicional(String cargoContactoAdicional) {
		this.cargoContactoAdicional = cargoContactoAdicional;
	}
	public String getNombreContactoAdicional() {
		return nombreContactoAdicional;
	}
	public void setNombreContactoAdicional(String nombreContactoAdicional) {
		this.nombreContactoAdicional = nombreContactoAdicional;
	}
	public String getCorreoContactoAdicional() {
		return correoContactoAdicional;
	}
	public void setCorreoContactoAdicional(String correoContactoAdicional) {
		this.correoContactoAdicional = correoContactoAdicional;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getLugarContacto() {
		return lugarContacto;
	}
	public void setLugarContacto(String lugarContacto) {
		this.lugarContacto = lugarContacto;
	}
	public String getDescripcionMaterial() {
		return descripcionMaterial;
	}
	public void setDescripcionMaterial(String descripcionMaterial) {
		this.descripcionMaterial = descripcionMaterial;
	}
	public String getDescripcionPrecio() {
		return descripcionPrecio;
	}
	public void setDescripcionPrecio(String descripcionPrecio) {
		this.descripcionPrecio = descripcionPrecio;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public boolean isTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(boolean tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getIncotermTotal() {
		return incotermTotal;
	}
	public void setIncotermTotal(String incotermTotal) {
		this.incotermTotal = incotermTotal;
	}
	public String getLugarTotal() {
		return lugarTotal;
	}
	public void setLugarTotal(String lugarTotal) {
		this.lugarTotal = lugarTotal;
	}
	public String getPrecioCabecera() {
		return precioCabecera;
	}
	public void setPrecioCabecera(String precioCabecera) {
		this.precioCabecera = precioCabecera;
	}
	public String getCodigoPersonal() {
		return codigoPersonal;
	}
	public void setCodigoPersonal(String codigoPersonal) {
		this.codigoPersonal = codigoPersonal;
	}
}
