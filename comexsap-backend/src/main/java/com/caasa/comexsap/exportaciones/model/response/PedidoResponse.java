package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;
import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;

public class PedidoResponse {
	
	List<CotizacionTO> cotizacion;
	List<PosicionPedidoTO> posiciones;
	String cod_rpta;
	String rpta;
    String cod_pedido;
	public List<CotizacionTO> getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(List<CotizacionTO> cotizacion) {
		this.cotizacion = cotizacion;
	}
	public List<PosicionPedidoTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<PosicionPedidoTO> posiciones) {
		this.posiciones = posiciones;
	}
	public String getRpta() {
		return rpta;
	}
	public void setRpta(String rpta) {
		this.rpta = rpta;
	}
	public String getCod_rpta() {
		return cod_rpta;
	}
	public void setCod_rpta(String cod_rpta) {
		this.cod_rpta = cod_rpta;
	}
	public String getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(String cod_pedido) {
		this.cod_pedido = cod_pedido;
	}	
	
}
