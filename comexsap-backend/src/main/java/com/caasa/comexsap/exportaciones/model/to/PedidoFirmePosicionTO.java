package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class PedidoFirmePosicionTO {

	private int  id;
	private int  idPedido;
	private int  item;
	private int  idProducto;
	private int  idPadre;
	private String  codigoSAP;
	private String  descripcionProducto;
	private String  descripcionComercialProducto;
	private BigDecimal  cantidad;
	private BigDecimal  cantidadVenta;
	private BigDecimal  factor;
	private int  idUnidadMedida;
	private String  codigoSAPUnidadMedida;
	private String  descripcionUnidadMedida;
	private int  idUnidadMedidaVenta;
	private String  codigoSAPUnidadMedidaVenta;
	private String  descripcionUnidadMedidaVenta;
	private int  idCentro;
	private String  codigoSAPCentro;
	private String  descripcionCentro;
	private int  idAlmacen;
	private String  codigoSAPAlmacen;
	private String  descripcionAlmacen;
	private BigDecimal  pesoTonelada;
	private BigDecimal  pesoNominal;
	private BigDecimal  cantidadTonelada;
	private Date  fechaDisponibilidad;
	private BigDecimal  precioUnitario;
	private BigDecimal  precioUnitarioSAP;
	private BigDecimal  importe;
	private int  estado;
	private int idPosicion;
	private float unidadTotal;
	private float pesoTeoricoTotal;
	private float pesoTeorico;
	private String descripcionPedidoMaterial;
	private float precioCFR;
	private float subtotal;
	private BigDecimal  nroPaquete;
	private String precioPosicion;
	private String descripcionTotal;
	private String descripcionTotalFob;
    private String descripcionFlete;
	private String totalSubtotalSinFlete;
	private float totalFlete;
    private String sumaSubtotal;
    private String materialPrecio;
    private List<PedidoFirmeSubPosicionTO> subPosicion;
    
	public float getTotalFlete() {
		return totalFlete;
	}
	public void setTotalFlete(float totalFlete) {
		this.totalFlete = totalFlete;
	}
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
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}
	public BigDecimal getFactor() {
		return factor;
	}
	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	public String getCodigoSAPUnidadMedida() {
		return codigoSAPUnidadMedida;
	}
	public void setCodigoSAPUnidadMedida(String codigoSAPUnidadMedida) {
		this.codigoSAPUnidadMedida = codigoSAPUnidadMedida;
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
	public String getCodigoSAPCentro() {
		return codigoSAPCentro;
	}
	public void setCodigoSAPCentro(String codigoSAPCentro) {
		this.codigoSAPCentro = codigoSAPCentro;
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
	public String getCodigoSAPAlmacen() {
		return codigoSAPAlmacen;
	}
	public void setCodigoSAPAlmacen(String codigoSAPAlmacen) {
		this.codigoSAPAlmacen = codigoSAPAlmacen;
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
	public BigDecimal getCantidadTonelada() {
		return cantidadTonelada;
	}
	public void setCantidadTonelada(BigDecimal cantidadTonelada) {
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
	public BigDecimal getPrecioUnitarioSAP() {
		return precioUnitarioSAP;
	}
	public void setPrecioUnitarioSAP(BigDecimal precioUnitarioSAP) {
		this.precioUnitarioSAP = precioUnitarioSAP;
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
	public float getUnidadTotal() {
		return unidadTotal;
	}
	public void setUnidadTotal(float unidadTotal) {
		this.unidadTotal = unidadTotal;
	}
	public float getPesoTeoricoTotal() {
		return pesoTeoricoTotal;
	}
	public void setPesoTeoricoTotal(float pesoTeoricoTotal) {
		this.pesoTeoricoTotal = pesoTeoricoTotal;
	}
	
	public float getPesoTeorico() {
		return pesoTeorico;
	}
	public void setPesoTeorico(float pesoTeorico) {
		this.pesoTeorico = pesoTeorico;
	}
	public String getDescripcionPedidoMaterial() {
		return descripcionPedidoMaterial;
	}
	public void setDescripcionPedidoMaterial(String descripcionPedidoMaterial) {
		this.descripcionPedidoMaterial = descripcionPedidoMaterial;
	}
	public float getPrecioCFR() {
		return precioCFR;
	}
	public void setPrecioCFR(float precioCFR) {
		this.precioCFR = precioCFR;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getNroPaquete() {
		return nroPaquete;
	}
	public void setNroPaquete(BigDecimal nroPaquete) {
		this.nroPaquete = nroPaquete;
	}
	public String getPrecioPosicion() {
		return precioPosicion;
	}
	public void setPrecioPosicion(String precioPosicion) {
		this.precioPosicion = precioPosicion;
	}
	public String getDescripcionTotal() {
		return descripcionTotal;
	}
	public void setDescripcionTotal(String descripcionTotal) {
		this.descripcionTotal = descripcionTotal;
	}
	public String getDescripcionTotalFob() {
		return descripcionTotalFob;
	}
	public void setDescripcionTotalFob(String descripcionTotalFob) {
		this.descripcionTotalFob = descripcionTotalFob;
	}
	public String getDescripcionFlete() {
		return descripcionFlete;
	}
	public void setDescripcionFlete(String descripcionFlete) {
		this.descripcionFlete = descripcionFlete;
	}
	
	public String getTotalSubtotalSinFlete() {
		return totalSubtotalSinFlete;
	}
	public void setTotalSubtotalSinFlete(String totalSubtotalSinFlete) {
		this.totalSubtotalSinFlete = totalSubtotalSinFlete;
	}
	public String getSumaSubtotal() {
		return sumaSubtotal;
	}
	public void setSumaSubtotal(String sumaSubtotal) {
		this.sumaSubtotal = sumaSubtotal;
	}
	public String getMaterialPrecio() {
		return materialPrecio;
	}
	public void setMaterialPrecio(String materialPrecio) {
		this.materialPrecio = materialPrecio;
	}
	public int getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}
	public List<PedidoFirmeSubPosicionTO> getSubPosicion() {
		return subPosicion;
	}
	public void setSubPosicion(List<PedidoFirmeSubPosicionTO> subPosicion) {
		this.subPosicion = subPosicion;
	}
	
	
}
