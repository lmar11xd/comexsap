package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.caasa.comexsap.exportaciones.model.dao.MaestroDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.MaestroExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

@Repository("MaestroDao")
public class MaestroDaoImpl implements MaestroDao {

	@Autowired
	private MaestroExtendedMapper maestroExtendedMapper;

	@Override
	public List<AlmacenTO> listarAlmacen(int idCentro) {
		return this.maestroExtendedMapper.listarAlmacen(idCentro);
	}

	@Override
	public List<CentroTO> listarCentro(String buscar) {
		return this.maestroExtendedMapper.listarCentro(buscar);
	}

}
