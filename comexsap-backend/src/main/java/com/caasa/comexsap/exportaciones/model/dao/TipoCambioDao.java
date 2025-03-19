package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

public interface TipoCambioDao {
	public List<TipoCambioTO> obtenerTipoCambioActual(String moneda, String fecha);
}
