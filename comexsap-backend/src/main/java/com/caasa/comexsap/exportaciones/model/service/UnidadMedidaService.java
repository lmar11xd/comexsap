package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FamiliaConversionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;

public interface UnidadMedidaService {
	
	public List<UnidadMedidaTO> listarUnidadMedidaxNombre(String codigoProducto);
	public UnidadMedidaTO obtenerUnidadMedidaxProductoUnidad(String codigoProducto, String unidadMedida);
	public List<FamiliaConversionTO> listarFamiliasConversion();	
	
}
