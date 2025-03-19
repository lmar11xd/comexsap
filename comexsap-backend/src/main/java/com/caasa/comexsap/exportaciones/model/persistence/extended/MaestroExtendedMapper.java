package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

public interface MaestroExtendedMapper {
	
	public List<AlmacenTO> listarAlmacen(int idCentro);
	public List<CentroTO> listarCentro(String buscar);

}
