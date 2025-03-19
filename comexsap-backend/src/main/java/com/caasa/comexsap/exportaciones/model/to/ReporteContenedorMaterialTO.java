package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class ReporteContenedorMaterialTO {
	private String codigo;
	private String paquetes;
	private String descripcion;
	private List<ReportePackingListTO> packinglist;
	private float pesoTotal;
	private String piezasTotales;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPaquetes() {
		return paquetes;
	}
	public void setPaquetes(String paquetes) {
		this.paquetes = paquetes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ReportePackingListTO> getPackinglist() {
		return packinglist;
	}
	public void setPackinglist(List<ReportePackingListTO> packinglist) {
		this.packinglist = packinglist;
	}
	public float getPesoTotal() {
		return pesoTotal;
	}
	public void setPesoTotal(float pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	public String getPiezasTotales() {
		return piezasTotales;
	}
	public void setPiezasTotales(String piezasTotales) {
		this.piezasTotales = piezasTotales;
	}
}
