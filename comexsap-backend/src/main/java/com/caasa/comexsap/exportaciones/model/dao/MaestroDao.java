package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

public interface MaestroDao {

	public List<AlmacenTO> listarAlmacen(int idCentro);
	public List<CentroTO> listarCentro(String buscar);

}
