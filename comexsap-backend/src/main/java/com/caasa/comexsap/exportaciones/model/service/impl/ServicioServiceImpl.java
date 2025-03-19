package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ServicioDao;
import com.caasa.comexsap.exportaciones.model.service.ServicioService;
import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;

@Service
public class ServicioServiceImpl implements ServicioService {

	@Autowired
	private ServicioDao servicioDao;
	
	@Override
	public List<ServicioTO> listarServicioxFiltro(FiltroServicioRequestTO filtro) {
		return servicioDao.listarServicioxFiltro(filtro);
	}

	@Override
	public ServicioTO obtenerServicioxId(int idServicio) {
		return servicioDao.obtenerServicioxId(idServicio);
	}

	@Override
	public int registrarServicio(ServicioTO servicio, String usuario) {
		return servicioDao.registrarServicio(servicio, usuario);
	}

	@Override
	public void eliminarServicio(int idServicio, String usuario) {
		servicioDao.eliminarServicio(idServicio, usuario);
	}

}
