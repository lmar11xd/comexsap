package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caasa.comexsap.exportaciones.model.dao.GrupoCorreoDetalleDao;
import com.caasa.comexsap.exportaciones.model.service.GrupoCorreoDetalleService;
import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;

@Service
public class GrupoCorreoDetalleServiceImpl implements GrupoCorreoDetalleService{
	
	@Autowired
	private GrupoCorreoDetalleDao grupoCorreoDetalleDao;

	@Override
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoDetalle() {
		return this.grupoCorreoDetalleDao.listarGrupoCorreoDetalle();
	}

	@Override
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoxFiltro(FiltroGrupoCorreoRequestTO filtro) {
		return this.grupoCorreoDetalleDao.listarGrupoCorreoxFiltro(filtro);
	}

	@Override
	public GrupoCorreoDetalleTO obtenerGrupoCorreoxId(int idGrupoCorreo) {
		return this.grupoCorreoDetalleDao.obtenerGrupoCorreoxId(idGrupoCorreo);
	}

	@Override
	public int registrarGrupoCorreo(GrupoCorreoDetalleTO grupoCorreo, String usuario) {
		return this.grupoCorreoDetalleDao.registrarGrupoCorreo(grupoCorreo, usuario);
	}

	@Override
	public void eliminarGrupoCorreo(int idGrupoCorreo, String usuario) {
		this.grupoCorreoDetalleDao.eliminarGrupoCorreo(idGrupoCorreo, usuario);
	}
}