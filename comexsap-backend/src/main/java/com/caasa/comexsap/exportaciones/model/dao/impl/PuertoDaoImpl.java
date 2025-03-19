package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.model.dao.PuertoDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.PuertoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

@Repository("PuertoDao")
public class PuertoDaoImpl implements PuertoDao{

	@Autowired
	private PuertoExtendedMapper puertoExtendedMapper;

	@Override
	public List<PuertoTO> listarPuertoxNombre(String buscar) {
		return puertoExtendedMapper.listarPuertoxNombre(buscar);
	}

}
