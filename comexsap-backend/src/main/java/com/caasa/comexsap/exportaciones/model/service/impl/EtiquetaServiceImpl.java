package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.EtiquetaDao;
import com.caasa.comexsap.exportaciones.model.service.EtiquetaService;
import com.caasa.comexsap.exportaciones.model.to.EtiquetaTO;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

	@Autowired
	private EtiquetaDao etiquetaDao;
	
	@Override
	public List<EtiquetaTO> listarEtiquetas(String buscar) {
		return etiquetaDao.listarEtiquetas(buscar);
	}

	@Override
	public void guardarEtiqueta(EtiquetaTO etiqueta, String usuario) {
		etiquetaDao.guardarEtiqueta(etiqueta, usuario);
	}

	@Override
	public void eliminarEtiqueta(int id, String usuario) {
		etiquetaDao.eliminarEtiqueta(id, usuario);
	}

}
