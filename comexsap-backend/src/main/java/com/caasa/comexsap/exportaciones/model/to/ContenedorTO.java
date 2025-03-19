package com.caasa.comexsap.exportaciones.model.to;

import java.math.BigDecimal;

public class ContenedorTO {

	private int id;
	private String taco;
	private String precinto1;
	private String precinto2;
	private String placaCarrete;
	private String placa;
	private BigDecimal pesoComex;
	private int numero;
	private int idExportacion;
	private String horaInicio;
	private String horaFinal;
	private short estado;
	private String dtSAP;
	private String descripcion;
	private String codigo;
	private String chofer;
	private String brevete;
	private String bulto;
	private String booking;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaco() {
		return taco;
	}
	public void setTaco(String taco) {
		this.taco = taco;
	}
	public String getPrecinto1() {
		return precinto1;
	}
	public void setPrecinto1(String precinto1) {
		this.precinto1 = precinto1;
	}
	public String getPrecinto2() {
		return precinto2;
	}
	public void setPrecinto2(String precinto2) {
		this.precinto2 = precinto2;
	}
	public String getPlacaCarrete() {
		return placaCarrete;
	}
	public void setPlacaCarrete(String placaCarrete) {
		this.placaCarrete = placaCarrete;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public BigDecimal getPesoComex() {
		return pesoComex;
	}
	public void setPesoComex(BigDecimal pesoComex) {
		this.pesoComex = pesoComex;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public short getEstado() {
		return estado;
	}
	public void setEstado(short estado) {
		this.estado = estado;
	}
	public String getDtSAP() {
		return dtSAP;
	}
	public void setDtSAP(String dtSAP) {
		this.dtSAP = dtSAP;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getChofer() {
		return chofer;
	}
	public void setChofer(String chofer) {
		this.chofer = chofer;
	}
	public String getBrevete() {
		return brevete;
	}
	public void setBrevete(String brevete) {
		this.brevete = brevete;
	}
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public String getBulto() {
		return bulto;
	}
	public void setBulto(String bulto) {
		this.bulto = bulto;
	}

	
}
