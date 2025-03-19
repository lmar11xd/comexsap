package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;
import java.util.Date;

public class PedidoExportacionPosicionTO {

	private int id;
	private int idPosicion;
	private int idExportacionPedido;
	private BigDecimal cantidadVenta;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;
	private Short estado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPosicion() {
		return idPosicion;
	}
	public void setIdPosicion(int idPosicion) {
		this.idPosicion = idPosicion;
	}
	public int getIdExportacionPedido() {
		return idExportacionPedido;
	}
	public void setIdExportacionPedido(int idExportacionPedido) {
		this.idExportacionPedido = idExportacionPedido;
	}
	public BigDecimal getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(BigDecimal cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
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
	public Short getEstado() {
		return estado;
	}
	public void setEstado(Short estado) {
		this.estado = estado;
	}
	
	
}
