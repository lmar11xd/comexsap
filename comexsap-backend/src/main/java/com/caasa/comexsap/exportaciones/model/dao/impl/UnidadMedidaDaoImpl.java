package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.model.dao.UnidadMedidaDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.UnidadMedidaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.FamiliaConversionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;

@Repository("UnidadMedidaDao")
public class UnidadMedidaDaoImpl implements UnidadMedidaDao{

	@Autowired
	private UnidadMedidaExtendedMapper unidadMedidaExtendedMapper;

	@Override
	public List<UnidadMedidaTO> listarUnidadMedidaxNombre(String codigoProducto) {
		return unidadMedidaExtendedMapper.listarUnidadMedidaxNombre(codigoProducto);
	}
	
	@Override
	public UnidadMedidaTO obtenerUnidadMedidaxProductoUnidad(String codigoProducto, String unidadMedida) {
		return unidadMedidaExtendedMapper.obtenerUnidadMedidaxProductoUnidad(codigoProducto, unidadMedida);
	}

	@Override
	public List<FamiliaConversionTO> listarFamiliasConversion() {
		return unidadMedidaExtendedMapper.listarFamiliasConversion();
	}
}
