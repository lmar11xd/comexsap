package com.caasa.comexsap.exportaciones.model.service;

import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

public interface TipoCambioService {
	public TipoCambioTO obtenerTipoCambioActual(String moneda, String fecha);
}
