package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

public interface PuertoDao {

	public List<PuertoTO> listarPuertoxNombre(String buscar);
}
