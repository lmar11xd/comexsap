package com.caasa.comexsap.exportaciones.soap.to;

import javax.xml.bind.annotation.XmlElement;

public class Repartos {

	private String posnr;
    private String fechaPrefe;
    private String cantidad;
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}
	public String getFechaPrefe() {
		return fechaPrefe;
	}
	public void setFechaPrefe(String fechaPrefe) {
		this.fechaPrefe = fechaPrefe;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
    
    
}
