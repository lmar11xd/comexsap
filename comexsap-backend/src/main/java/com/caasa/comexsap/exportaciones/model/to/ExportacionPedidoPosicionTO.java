package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ExportacionPedidoPosicionTO {

	private int  id;
	private int  idPedido;
	private int  item;
	private int  idProducto;
	private int  idPadre;
    private String codigoPedido;
    private String codigoSapCliente;
    private String descripcionProducto;
    private String descripcionComercialProducto;
    private BigDecimal cantidadSaldo;
    private BigDecimal cantidadTonelada;
    private BigDecimal cantidad;
    private BigDecimal cantidadMoneda;
    private BigDecimal cantidadVenta;
    private String factor;
    private int idUnidadMedida;
    private String codigoSapUnidadMedida;
    private String descripcionUnidadMedida;
    private int idUnidadMedidaVenta;
    private String codigoSapUnidadMedidaVenta;
    private String descripcionUnidadMedidaVenta;
    private int idCentro;
    private String codigoSapCentro;
    private String descripcionCentro;
    private int idAlmacen;
    private String codigoSapAlmacen;
    private String descripcionAlmacen;
    private BigDecimal pesoTonelada;
    private BigDecimal pesoNominal;
    private BigDecimal precioUnitario;
    private BigDecimal precioUnitarioSap;
    private BigDecimal importe;
    private short estado;
    private String codigoMoneda;
    private String codigoSap;
    private int idCondicionPago;
    private int idIncoterm;
    private int idMoneda;
    private String nombreMoneda;
    private String unidadMedida;
    private String unidadMedidaVenta;
    private Date  fechaDisponibilidad;
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
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getCodigoSapCliente() {
		return codigoSapCliente;
	}
	public void setCodigoSapCliente(String codigoSapCliente) {
		this.codigoSapCliente = codigoSapCliente;
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
	public BigDecimal getCantidadSaldo() {
		return cantidadSaldo;
	}
	public void setCantidadSaldo(BigDecimal cantidadSaldo) {
		this.cantidadSaldo = cantidadSaldo;
	}
	public BigDecimal getCantidadTonelada() {
		return cantidadTonelada;
	}
	public void setCantidadTonelada(BigDecimal cantidadTonelada) {
		this.cantidadTonelada = cantidadTonelada;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCantidadMoneda() {
		return cantidadMoneda;
	}
	public void setCantidadMoneda(BigDecimal cantidadMoneda) {
		this.cantidadMoneda = cantidadMoneda;
	}
	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
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
	public String getCodigoSapUnidadMedidaVenta() {
		return codigoSapUnidadMedidaVenta;
	}
	public void setCodigoSapUnidadMedidaVenta(String codigoSapUnidadMedidaVenta) {
		this.codigoSapUnidadMedidaVenta = codigoSapUnidadMedidaVenta;
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
	public short getEstado() {
		return estado;
	}
	public void setEstado(short estado) {
		this.estado = estado;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public int getIdCondicionPago() {
		return idCondicionPago;
	}
	public void setIdCondicionPago(int idCondicionPago) {
		this.idCondicionPago = idCondicionPago;
	}
	public int getIdIncoterm() {
		return idIncoterm;
	}
	public void setIdIncoterm(int idIncoterm) {
		this.idIncoterm = idIncoterm;
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
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getUnidadMedidaVenta() {
		return unidadMedidaVenta;
	}
	public void setUnidadMedidaVenta(String unidadMedidaVenta) {
		this.unidadMedidaVenta = unidadMedidaVenta;
	}
	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}
	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
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
