package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

public interface PuertoService {
	
	public List<PuertoTO> listarPuertoxNombre(String buscar);
	
}
