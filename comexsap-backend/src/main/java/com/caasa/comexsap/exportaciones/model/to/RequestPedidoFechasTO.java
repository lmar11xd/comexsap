package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestPedidoFechasTO {

	private int idPedido;
	private int confirmar;
	private List<PedidoFirmePosicionTO> posiciones;
	
	public RequestPedidoFechasTO() {
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(int confirmar) {
		this.confirmar = confirmar;
	}

	public List<PedidoFirmePosicionTO> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<PedidoFirmePosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
}
