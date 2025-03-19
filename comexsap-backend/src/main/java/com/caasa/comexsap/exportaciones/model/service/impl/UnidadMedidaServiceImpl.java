package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.UnidadMedidaDao;
import com.caasa.comexsap.exportaciones.model.service.UnidadMedidaService;
import com.caasa.comexsap.exportaciones.model.to.FamiliaConversionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService{
	
	@Autowired
	private UnidadMedidaDao unidadMedidaDao;	

	@Override
	public List<UnidadMedidaTO> listarUnidadMedidaxNombre(String codigoProducto) {
		return unidadMedidaDao.listarUnidadMedidaxNombre(codigoProducto);
	}
	
	@Override
	public UnidadMedidaTO obtenerUnidadMedidaxProductoUnidad(String codigoProducto, String unidadMedida) {
		return unidadMedidaDao.obtenerUnidadMedidaxProductoUnidad(codigoProducto, unidadMedida);
	}

	@Override
	public List<FamiliaConversionTO> listarFamiliasConversion() {
		return unidadMedidaDao.listarFamiliasConversion();
	}
}