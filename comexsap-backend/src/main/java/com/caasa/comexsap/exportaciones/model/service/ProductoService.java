package com.caasa.comexsap.exportaciones.model.service;

import java.text.ParseException;
import java.util.List;

import com.caasa.comexsap.exportaciones.api.to.ResultadoPrecioMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.PrecioMaterialRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoSapTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductoService {
	
	public List<ProductoTO> listarProductoxNombre(String buscar);
	public ProductoTO obtenerProductoxCodigo(String codigo);	
	public List<ResultadoPrecioMaterialTO> obtenerPrecioMaterial(PrecioMaterialRequestTO request) throws JsonProcessingException, ParseException ;
	public ProductoSapTO obtenerProductoSap(String codigo);
}
