package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.ProductoSapTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;

public interface ProductoExtendedMapper {

	public List<ProductoTO> listarProductoxNombre(@Param("buscar") String buscar);
	public ProductoTO obtenerProductoxCodigo(@Param("codigoSap") String codigoSap);
	public int buscarProductoxCodigoSap(@Param("codigoSap") String codigoSap); 
	public ProductoTO obtenerProductoxCodigoSap(@Param("codigoSap") String codigoSap);
	public ProductoSapTO obtenerProductoSap(@Param("codigoSap") String codigoSap);
	public ProductoTO obtenerUnidadesPaquetexCodigo(@Param("codigoSap") String codigoSap);
}