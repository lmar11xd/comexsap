package com.caasa.comexsap.exportaciones.model.dao;

import com.caasa.comexsap.exportaciones.model.to.FiltroOrdenInterna;
import com.caasa.comexsap.exportaciones.model.to.OrdenInternaTO;

public interface OrdenInternaDao {
	public OrdenInternaTO obtenerOrdenInternaxFiltro(FiltroOrdenInterna filtro);
	public OrdenInternaTO obtenerOrdenInterna(int id);
}
