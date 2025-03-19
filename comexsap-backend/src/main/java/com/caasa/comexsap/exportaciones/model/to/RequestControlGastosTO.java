package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestControlGastosTO {

	private int[] documentos;
	private ControlGastosTO cabecera;
	private List<ControlGastosDetalleTO> detalle;
	
	public int[] getDocumentos() {
		return documentos;
	}
	public void setDocumentos(int[] documentos) {
		this.documentos = documentos;
	}
	public ControlGastosTO getCabecera() {
		return cabecera;
	}
	public void setCabecera(ControlGastosTO cabecera) {
		this.cabecera = cabecera;
	}
	public List<ControlGastosDetalleTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<ControlGastosDetalleTO> detalle) {
		this.detalle = detalle;
	}
}
