package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FamiliaConversionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;

public interface UnidadMedidaExtendedMapper {

	public List<UnidadMedidaTO> listarUnidadMedidaxNombre(@Param("codigoProducto") String codigoProducto);
	public int buscarUnidadMedidaxCodigoSap(@Param("codigoSap") String codigoSap); 
	public UnidadMedidaTO obtenerUnidadMedidaxCodigoSap(@Param("codigoSap") String codigoSap); 
	public UnidadMedidaTO obtenerUnidadMedidaxProductoUnidad(@Param("codigoProducto") String codigoProducto, @Param("unidadMedida") String unidadMedida);
	public List<FamiliaConversionTO> listarFamiliasConversion(); 
}