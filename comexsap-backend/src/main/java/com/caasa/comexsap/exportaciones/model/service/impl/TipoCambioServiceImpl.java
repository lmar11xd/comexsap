package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.TipoCambioDao;
import com.caasa.comexsap.exportaciones.model.service.TipoCambioService;
import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {
	@Autowired
	private TipoCambioDao tipoCambioDao;

	@Override
	public TipoCambioTO obtenerTipoCambioActual(String moneda, String fecha) {
		List<TipoCambioTO> lista = new ArrayList<>();
		lista = tipoCambioDao.obtenerTipoCambioActual(moneda, fecha);
		if(lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		
		return null;
	}

}
