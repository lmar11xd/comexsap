package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestDetallePedidoFirmeTO {
	
	private PedidoFirmeTO cabecera;
	private List<PedidoFirmePosicionTO> posiciones;

	public PedidoFirmeTO getCabecera() {
		return cabecera;
	}
	public void setCabecera(PedidoFirmeTO cabecera) {
		this.cabecera = cabecera;
	}
	public List<PedidoFirmePosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<PedidoFirmePosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	

	
}
