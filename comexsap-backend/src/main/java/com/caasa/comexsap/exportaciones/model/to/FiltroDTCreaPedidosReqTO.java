package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

import com.caasa.comexsap.exportaciones.soap.to.Cabecera;
import com.caasa.comexsap.exportaciones.soap.to.Condiciones;
import com.caasa.comexsap.exportaciones.soap.to.Detalle;
import com.caasa.comexsap.exportaciones.soap.to.Interlocutor;
import com.caasa.comexsap.exportaciones.soap.to.Repartos;
import com.caasa.comexsap.exportaciones.soap.to.Textos;

public class FiltroDTCreaPedidosReqTO {

	private String testrun;
	private String codigoCliente;
	private List<Cabecera> cabecera;
	private List<Detalle> detalle;
	private List<Interlocutor> interlocutor;
	private List<Condiciones> condiciones;
	private List<Textos> textos;
	private List<Repartos> repartos;
	
	public String getTestrun() {
		return testrun;
	}

	public void setTestrun(String testrun) {
		this.testrun = testrun;
	}

	public List<Cabecera> getCabecera() {
		return cabecera;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public void setCabecera(List<Cabecera> cabecera) {
		this.cabecera = cabecera;
	}

	public List<Detalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<Detalle> detalle) {
		this.detalle = detalle;
	}

	public List<Interlocutor> getInterlocutor() {
		return interlocutor;
	}

	public void setInterlocutor(List<Interlocutor> interlocutor) {
		this.interlocutor = interlocutor;
	}

	public List<Condiciones> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condiciones> condiciones) {
		this.condiciones = condiciones;
	}

	public List<Textos> getTextos() {
		return textos;
	}

	public void setTextos(List<Textos> textos) {
		this.textos = textos;
	}

	public List<Repartos> getRepartos() {
		return repartos;
	}

	public void setRepartos(List<Repartos> repartos) {
		this.repartos = repartos;
	}
}
