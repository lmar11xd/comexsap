package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.model.dao.TipoCambioDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TipoCambioExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

@Repository("TipoCambioDao")
public class TipoCambioDaoImpl implements TipoCambioDao {

	@Autowired
	private TipoCambioExtendedMapper tipoCambioExtendedMapper;
	
	@Override
	public List<TipoCambioTO> obtenerTipoCambioActual(String moneda, String fecha) {
		return tipoCambioExtendedMapper.obtenerTipoCambioActual(moneda, fecha);
	}

}
