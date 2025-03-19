package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;

public interface ServicioService {
	public List<ServicioTO> listarServicioxFiltro(FiltroServicioRequestTO filtro);
	public ServicioTO obtenerServicioxId(int idServicio);
	public int registrarServicio(ServicioTO servicio, String usuario);
	public void eliminarServicio(int idServicio, String usuario);
}
