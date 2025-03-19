package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.GrupoCorreoDetalleDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstGrupocorreodetalle;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstGrupocorreodetalleMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.GrupoCorreoDetalleExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("GrupoCorreoDetalleDao")
public class GrupoCorreoDetalleDaoImpl implements GrupoCorreoDetalleDao{

	@Autowired
	private GrupoCorreoDetalleExtendedMapper grupoCorreoDetalleExtendedMapper;
	
	@Autowired 
	private ComexstGrupocorreodetalleMapper comexstGrupocorreodetalleMapper;
	
	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Override
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoDetalle() {
		return this.grupoCorreoDetalleExtendedMapper.listarGrupoCorreoDetalle();
	}

	@Override
	public List<GrupoCorreoDetalleTO> listarGrupoCorreoxFiltro(FiltroGrupoCorreoRequestTO filtro) {
		return this.grupoCorreoDetalleExtendedMapper.listarGrupoCorreoxFiltro(filtro);
	}

	@Override
	public GrupoCorreoDetalleTO obtenerGrupoCorreoxId(int idGrupoCorreo) {
		return this.grupoCorreoDetalleExtendedMapper.obtenerGrupoCorreoxId(idGrupoCorreo);
	}

	@Override
	public int registrarGrupoCorreo(GrupoCorreoDetalleTO grupoCorreo, String usuario) {
		ComexstGrupocorreodetalle correoBean = new ComexstGrupocorreodetalle();
		if(grupoCorreo.getId() == 0) {
			int nuevoId = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_GRUPOCORREODETALLE.getValue());
			correoBean.setGcdenId(nuevoId);
			correoBean.setGcdenIdgrupo(grupoCorreo.getIdGrupo());
			correoBean.setGcdevNombre(grupoCorreo.getNombre());
			correoBean.setGcdevCorreo(grupoCorreo.getCorreo());
			correoBean.setGcdenEstado(Constantes.ACTIVO);
			correoBean.setGcdevUsuariocreacion(usuario);
			correoBean.setGcdedFechacreacion(new Date());
			this.comexstGrupocorreodetalleMapper.insert(correoBean);
		} else {
			correoBean = this.comexstGrupocorreodetalleMapper.selectByPrimaryKey(grupoCorreo.getId());
			correoBean.setGcdenIdgrupo(grupoCorreo.getIdGrupo());
			correoBean.setGcdevNombre(grupoCorreo.getNombre());
			correoBean.setGcdevCorreo(grupoCorreo.getCorreo());
			correoBean.setGcdevUsuariomodificacion(usuario);
			correoBean.setGcdedFechamodificacion(new Date());
			this.comexstGrupocorreodetalleMapper.updateByPrimaryKey(correoBean);
		}
		return correoBean.getGcdenId();
	}

	@Override
	public void eliminarGrupoCorreo(int idGrupoCorreo, String usuario) {
		this.grupoCorreoDetalleExtendedMapper.eliminarGrupoCorreo(idGrupoCorreo, usuario);
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return this.tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}
}
