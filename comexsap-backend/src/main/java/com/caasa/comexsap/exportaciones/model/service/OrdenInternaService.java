package com.caasa.comexsap.exportaciones.model.service;

import com.caasa.comexsap.exportaciones.model.to.FiltroOrdenInterna;
import com.caasa.comexsap.exportaciones.model.to.OrdenInternaTO;

public interface OrdenInternaService {
	public OrdenInternaTO obtenerOrdenInternaxFiltro(FiltroOrdenInterna filtro);
	public OrdenInternaTO obtenerOrdenInterna(int id);
}
