package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class CotizacionDetalleResponse {
	
	List<CotizacionTO> cotizacion;
	List<CotizacionTO> posiciones;
	public List<CotizacionTO> getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(List<CotizacionTO> cotizacion) {
		this.cotizacion = cotizacion;
	}
	public List<CotizacionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<CotizacionTO> posiciones) {
		this.posiciones = posiciones;
	}

	
	
}
