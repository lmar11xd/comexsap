package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestDetalleCotizacionTO {

	private String codigoPedidoFirme;
	private CotizacionTO cabecera;
	private List<PosicionPedidoTO> posiciones;

	public String getCodigoPedidoFirme() {
		return codigoPedidoFirme;
	}
	public void setCodigoPedidoFirme(String codigoPedidoFirme) {
		this.codigoPedidoFirme = codigoPedidoFirme;
	}
	public CotizacionTO getCabecera() {
		return cabecera;
	}
	public void setCabecera(CotizacionTO cabecera) {
		this.cabecera = cabecera;
	}
	public List<PosicionPedidoTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<PosicionPedidoTO> posiciones) {
		this.posiciones = posiciones;
	}
	
}
