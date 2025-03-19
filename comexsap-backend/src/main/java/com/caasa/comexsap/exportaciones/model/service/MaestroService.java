package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

public interface MaestroService {
	public List<AlmacenTO> listarAlmacen(int idCentro);
	public List<CentroTO> listarCentro(String buscar);

}
