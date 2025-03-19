package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class PackingListCargaSueltaTO {

	private int id;
	private int idExportacion;
	private String codigo;
	private String material;
	private BigDecimal pesoTeorico;
	private BigDecimal pesoNeto;
	private BigDecimal pesoBruto;
	private int paquetes;
	private int piezas;
	private String color;
	private int estado;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public BigDecimal getPesoTeorico() {
		return pesoTeorico;
	}
	public void setPesoTeorico(BigDecimal pesoTeorico) {
		this.pesoTeorico = pesoTeorico;
	}
	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public int getPaquetes() {
		return paquetes;
	}
	public void setPaquetes(int paquetes) {
		this.paquetes = paquetes;
	}
	public int getPiezas() {
		return piezas;
	}
	public void setPiezas(int piezas) {
		this.piezas = piezas;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
