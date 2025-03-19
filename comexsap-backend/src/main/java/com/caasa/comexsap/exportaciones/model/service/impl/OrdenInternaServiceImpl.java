package com.caasa.comexsap.exportaciones.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.OrdenInternaDao;
import com.caasa.comexsap.exportaciones.model.service.OrdenInternaService;
import com.caasa.comexsap.exportaciones.model.to.FiltroOrdenInterna;
import com.caasa.comexsap.exportaciones.model.to.OrdenInternaTO;

@Service
public class OrdenInternaServiceImpl implements OrdenInternaService {

	@Autowired
	private OrdenInternaDao ordenInternaDao;
	
	@Override
	public OrdenInternaTO obtenerOrdenInternaxFiltro(FiltroOrdenInterna filtro) {
		return ordenInternaDao.obtenerOrdenInternaxFiltro(filtro);
	}

	@Override
	public OrdenInternaTO obtenerOrdenInterna(int id) {
		return ordenInternaDao.obtenerOrdenInterna(id);
	}

}
