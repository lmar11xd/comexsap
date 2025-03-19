package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;

public interface GrupoCorreoDetalleExtendedMapper {

	public List<GrupoCorreoDetalleTO> listarGrupoCorreoDetalle();
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoxFiltro(FiltroGrupoCorreoRequestTO filtro);
	public GrupoCorreoDetalleTO obtenerGrupoCorreoxId(@Param("idGrupoCorreo") int idGrupoCorreo);
	public void eliminarGrupoCorreo(@Param("idGrupoCorreo") int idGrupoCorreo, @Param("usuario") String usuario);

}