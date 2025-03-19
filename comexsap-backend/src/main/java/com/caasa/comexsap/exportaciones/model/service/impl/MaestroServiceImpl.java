package com.caasa.comexsap.exportaciones.model.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caasa.comexsap.exportaciones.model.dao.MaestroDao;
import com.caasa.comexsap.exportaciones.model.service.MaestroService;
import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaestroServiceImpl implements MaestroService {

	@Autowired
	private MaestroDao maestroDao;

	@Override
	public List<AlmacenTO> listarAlmacen(int idCentro) {
		return this.maestroDao.listarAlmacen(idCentro);
	}

	@Override
	public List<CentroTO> listarCentro(String buscar) {
		return this.maestroDao.listarCentro(buscar);
	}
}
