package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;

public interface ServicioDao {
	public List<ServicioTO> listarServicioxFiltro(FiltroServicioRequestTO filtro);
	public ServicioTO obtenerServicioxId(int idServicio);
	public int registrarServicio(ServicioTO servicio, String usuario);
	public void eliminarServicio(int idServicio, String usuario);
	public int obtenerSecuencia(String nombreTabla);
}
