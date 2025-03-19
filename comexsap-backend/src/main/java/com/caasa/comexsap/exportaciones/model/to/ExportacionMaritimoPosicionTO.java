package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ExportacionMaritimoPosicionTO {

	private int id;
	private int idPedido;
	private int item;
	private int idProducto;
	private int idPadre;
	private String codigoSAP;
	private String codigoPedido;
	private String descripcionProducto;
	private String descripcionComercialProducto;
	private int cantidad;
	private BigDecimal cantidadVenta;
	private int factor;
	private int idUnidadMedida;
	private String codigoSapUnidadMedida;
	private String descripcionUnidadMedida;
	private int idUnidadMedidaVenta;
	private String codigoSAPUnidadMedidaVenta;
	private String descripcionUnidadMedidaVenta;
	private int idCentro;
	private String codigoSapCentro;
	private String descripcionCentro;
	private int idAlmacen;
	private String codigoSapAlmacen;
	private String descripcionAlmacen;
	private BigDecimal pesoTonelada;
	private BigDecimal pesoNominal;
	private int cantidadTonelada;
	private Date fechaDisponibilidad;
	private BigDecimal precioUnitario;
	private BigDecimal precioUnitarioSap;
	private BigDecimal importe;
	private int estado;
	private int idPosicion;
	private BigDecimal cantidadConversion;
	private String codigoSapCliente;
	private String mensajeSap;
	private String bloqueo;
	private int cantidadSaldo;
	private String codigoMoneda;
	private String codigoPartidaArancelaria;
	private String codigoSapUnidadMedidaConv;
	private String componenteTexto;
	private String entrega;
	private Date fechaListaPrecio;
	private String folio;
	private int idCliente;
	private int idCondicionPago;
	private int idDestinatario;
	private int idExportacion;
	private int idIncoterm;
	private int idListaPrecio;
	private int idMoneda;
	private int idMonedaPedido;
	private int idPaisPuertoDestino;
	private int idPartidaArancelaria;
	private int idPuertoOrigen;
	private int idRuta;
	private int idUnidadMedidaConv;
	private int itemSap;
	private String nombreMoneda;
	private String nombrePartidaArancelaria;
	private String pedidoSap;
	private BigDecimal precioUnitarioConv;
	private String referencia;
	private String unidadMedidaVenta;
	private String codigoLinea;
	private List<ComponenteTO> componentes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getDescripcionComercialProducto() {
		return descripcionComercialProducto;
	}

	public void setDescripcionComercialProducto(String descripcionComercialProducto) {
		this.descripcionComercialProducto = descripcionComercialProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}

	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}

	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public String getCodigoSapUnidadMedida() {
		return codigoSapUnidadMedida;
	}

	public void setCodigoSapUnidadMedida(String codigoSapUnidadMedida) {
		this.codigoSapUnidadMedida = codigoSapUnidadMedida;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public int getIdUnidadMedidaVenta() {
		return idUnidadMedidaVenta;
	}

	public void setIdUnidadMedidaVenta(int idUnidadMedidaVenta) {
		this.idUnidadMedidaVenta = idUnidadMedidaVenta;
	}

	public String getCodigoSAPUnidadMedidaVenta() {
		return codigoSAPUnidadMedidaVenta;
	}

	public void setCodigoSAPUnidadMedidaVenta(String codigoSAPUnidadMedidaVenta) {
		this.codigoSAPUnidadMedidaVenta = codigoSAPUnidadMedidaVenta;
	}

	public String getDescripcionUnidadMedidaVenta() {
		return descripcionUnidadMedidaVenta;
	}

	public void setDescripcionUnidadMedidaVenta(String descripcionUnidadMedidaVenta) {
		this.descripcionUnidadMedidaVenta = descripcionUnidadMedidaVenta;
	}

	public int getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	public String getCodigoSapCentro() {
		return codigoSapCentro;
	}

	public void setCodigoSapCentro(String codigoSapCentro) {
		this.codigoSapCentro = codigoSapCentro;
	}

	public String getDescripcionCentro() {
		return descripcionCentro;
	}

	public void setDescripcionCentro(String descripcionCentro) {
		this.descripcionCentro = descripcionCentro;
	}

	public int getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(int idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getCodigoSapAlmacen() {
		return codigoSapAlmacen;
	}

	public void setCodigoSapAlmacen(String codigoSapAlmacen) {
		this.codigoSapAlmacen = codigoSapAlmacen;
	}

	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}

	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}

	public BigDecimal getPesoTonelada() {
		return pesoTonelada;
	}

	public void setPesoTonelada(BigDecimal pesoTonelada) {
		this.pesoTonelada = pesoTonelada;
	}

	public BigDecimal getPesoNominal() {
		return pesoNominal;
	}

	public void setPesoNominal(BigDecimal pesoNominal) {
		this.pesoNominal = pesoNominal;
	}

	public int getCantidadTonelada() {
		return cantidadTonelada;
	}

	public void setCantidadTonelada(int cantidadTonelada) {
		this.cantidadTonelada = cantidadTonelada;
	}

	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}

	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getPrecioUnitarioSap() {
		return precioUnitarioSap;
	}

	public void setPrecioUnitarioSap(BigDecimal precioUnitarioSap) {
		this.precioUnitarioSap = precioUnitarioSap;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdPosicion() {
		return idPosicion;
	}

	public void setIdPosicion(int idPosicion) {
		this.idPosicion = idPosicion;
	}

	public BigDecimal getCantidadConversion() {
		return cantidadConversion;
	}

	public void setCantidadConversion(BigDecimal cantidadConversion) {
		this.cantidadConversion = cantidadConversion;
	}

	public String getCodigoSapCliente() {
		return codigoSapCliente;
	}

	public void setCodigoSapCliente(String codigoSapCliente) {
		this.codigoSapCliente = codigoSapCliente;
	}

	public String getMensajeSap() {
		return mensajeSap;
	}

	public void setMensajeSap(String mensajeSap) {
		this.mensajeSap = mensajeSap;
	}

	public String getBloqueo() {
		return bloqueo;
	}

	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}

	public int getCantidadSaldo() {
		return cantidadSaldo;
	}

	public void setCantidadSaldo(int cantidadSaldo) {
		this.cantidadSaldo = cantidadSaldo;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getCodigoPartidaArancelaria() {
		return codigoPartidaArancelaria;
	}

	public void setCodigoPartidaArancelaria(String codigoPartidaArancelaria) {
		this.codigoPartidaArancelaria = codigoPartidaArancelaria;
	}

	public String getCodigoSapUnidadMedidaConv() {
		return codigoSapUnidadMedidaConv;
	}

	public void setCodigoSapUnidadMedidaConv(String codigoSapUnidadMedidaConv) {
		this.codigoSapUnidadMedidaConv = codigoSapUnidadMedidaConv;
	}

	public String getComponenteTexto() {
		return componenteTexto;
	}

	public void setComponenteTexto(String componenteTexto) {
		this.componenteTexto = componenteTexto;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public Date getFechaListaPrecio() {
		return fechaListaPrecio;
	}

	public void setFechaListaPrecio(Date fechaListaPrecio) {
		this.fechaListaPrecio = fechaListaPrecio;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
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

	public int getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public int getIdExportacion() {
		return idExportacion;
	}

	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}

	public int getIdIncoterm() {
		return idIncoterm;
	}

	public void setIdIncoterm(int idIncoterm) {
		this.idIncoterm = idIncoterm;
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

	public int getIdMonedaPedido() {
		return idMonedaPedido;
	}

	public void setIdMonedaPedido(int idMonedaPedido) {
		this.idMonedaPedido = idMonedaPedido;
	}

	public int getIdPaisPuertoDestino() {
		return idPaisPuertoDestino;
	}

	public void setIdPaisPuertoDestino(int idPaisPuertoDestino) {
		this.idPaisPuertoDestino = idPaisPuertoDestino;
	}

	public int getIdPartidaArancelaria() {
		return idPartidaArancelaria;
	}

	public void setIdPartidaArancelaria(int idPartidaArancelaria) {
		this.idPartidaArancelaria = idPartidaArancelaria;
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

	public int getIdUnidadMedidaConv() {
		return idUnidadMedidaConv;
	}

	public void setIdUnidadMedidaConv(int idUnidadMedidaConv) {
		this.idUnidadMedidaConv = idUnidadMedidaConv;
	}

	public int getItemSap() {
		return itemSap;
	}

	public void setItemSap(int itemSap) {
		this.itemSap = itemSap;
	}

	public String getNombreMoneda() {
		return nombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}

	public String getNombrePartidaArancelaria() {
		return nombrePartidaArancelaria;
	}

	public void setNombrePartidaArancelaria(String nombrePartidaArancelaria) {
		this.nombrePartidaArancelaria = nombrePartidaArancelaria;
	}

	public String getPedidoSap() {
		return pedidoSap;
	}

	public void setPedidoSap(String pedidoSap) {
		this.pedidoSap = pedidoSap;
	}

	public BigDecimal getPrecioUnitarioConv() {
		return precioUnitarioConv;
	}

	public void setPrecioUnitarioConv(BigDecimal precioUnitarioConv) {
		this.precioUnitarioConv = precioUnitarioConv;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getUnidadMedidaVenta() {
		return unidadMedidaVenta;
	}

	public void setUnidadMedidaVenta(String unidadMedidaVenta) {
		this.unidadMedidaVenta = unidadMedidaVenta;
	}

	public String getCodigoLinea() {
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public List<ComponenteTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteTO> componentes) {
		this.componentes = componentes;
	}
}
