package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class FiltroDTConsultarStockReqTO {
	
	private String grupoVendedor;
	private String oficina;
	private String centro;
	private String almacen;
	private String orgVentas;
	private String canalDistribucion;
	
	private List<DetalleStockTO> detalles;
	
	public String getGrupoVendedor() {
		return grupoVendedor;
	}
	public void setGrupoVendedor(String grupoVendedor) {
		this.grupoVendedor = grupoVendedor;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public List<DetalleStockTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleStockTO> detalles) {
		this.detalles = detalles;
	}
	public String getOrgVentas() {
		return orgVentas;
	}
	public void setOrgVentas(String orgVentas) {
		this.orgVentas = orgVentas;
	}
	public String getCanalDistribucion() {
		return canalDistribucion;
	}
	public void setCanalDistribucion(String canalDistribucion) {
		this.canalDistribucion = canalDistribucion;
	}
	
}
