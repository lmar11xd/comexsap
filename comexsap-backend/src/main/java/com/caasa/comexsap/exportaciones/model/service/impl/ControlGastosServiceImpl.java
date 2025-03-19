package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ControlGastosDao;
import com.caasa.comexsap.exportaciones.model.service.ControlGastosService;
import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestControlGastosTO;

@Service
public class ControlGastosServiceImpl implements ControlGastosService {

	@Autowired
	private ControlGastosDao controlGastosDao;
	
	@Override
	public List<DocumentoControlGastoTO> listarDocumentosxFiltro(FiltroControlGastosTO filtro) {
		return controlGastosDao.listarDocumentosxFiltro(filtro);
	}

	@Override
	public void guardarControlGastos(RequestControlGastosTO formulario, String usuario) {
		controlGastosDao.guardarControlGastos(formulario, usuario);
	}

}
