package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;

public interface ServicioExtendedMapper {
	public List<ServicioTO> listarServicioxFiltro(FiltroServicioRequestTO filtro);
	public ServicioTO obtenerServicioxId(@Param("idServicio") int idServicio);
	public void eliminarServicio(@Param("idServicio") int idServicio, @Param("usuario") String usuario);
}
