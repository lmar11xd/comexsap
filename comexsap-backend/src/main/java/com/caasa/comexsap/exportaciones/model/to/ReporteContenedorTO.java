package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class ReporteContenedorTO {
	private int id;
	private String numero;
	private String codigoPacking;
	private String contenedor;
	private List<ReporteContenedorMaterialTO> posiciones;
	private float pesoTotal;
	private String piezasTotales;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCodigoPacking() {
		return codigoPacking;
	}
	public void setCodigoPacking(String codigoPacking) {
		this.codigoPacking = codigoPacking;
	}
	public String getContenedor() {
		return contenedor;
	}
	public void setContenedor(String contenedor) {
		this.contenedor = contenedor;
	}
	public List<ReporteContenedorMaterialTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<ReporteContenedorMaterialTO> posiciones) {
		this.posiciones = posiciones;
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
