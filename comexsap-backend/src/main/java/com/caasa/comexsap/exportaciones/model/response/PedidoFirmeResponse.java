package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;

public class PedidoFirmeResponse {
	
	PedidoFirmeTO pedido;
	List<PedidoFirmePosicionTO> posiciones;
	String cod_rpta;
	String rpta;
	
	public PedidoFirmeTO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoFirmeTO pedido) {
		this.pedido = pedido;
	}
	public List<PedidoFirmePosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<PedidoFirmePosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	public String getCod_rpta() {
		return cod_rpta;
	}
	public void setCod_rpta(String cod_rpta) {
		this.cod_rpta = cod_rpta;
	}
	public String getRpta() {
		return rpta;
	}
	public void setRpta(String rpta) {
		this.rpta = rpta;
	}

}
