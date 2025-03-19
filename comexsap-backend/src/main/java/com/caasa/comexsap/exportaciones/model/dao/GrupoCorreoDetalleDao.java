package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;

public interface GrupoCorreoDetalleDao {
	
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoDetalle();
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoxFiltro(FiltroGrupoCorreoRequestTO filtro);
	public GrupoCorreoDetalleTO obtenerGrupoCorreoxId(int idGrupoCorreo);
	public int registrarGrupoCorreo(GrupoCorreoDetalleTO grupoCorreo, String usuario);
	public void eliminarGrupoCorreo(int idGrupoCorreo, String usuario);
	public int obtenerSecuencia(String nombreTabla);

}
