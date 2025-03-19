package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.ServicioDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstServicio;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstServicioMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ServicioExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("ServicioDao")
public class ServicioDaoImpl implements ServicioDao {

	@Autowired
	private ServicioExtendedMapper servicioExtendedMapper;
	
	@Autowired
	private ComexstServicioMapper comexstServicioMapper;
	
	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;
	
	@Override
	public List<ServicioTO> listarServicioxFiltro(FiltroServicioRequestTO filtro) {
		return servicioExtendedMapper.listarServicioxFiltro(filtro);
	}

	@Override
	public ServicioTO obtenerServicioxId(int idServicio) {
		return servicioExtendedMapper.obtenerServicioxId(idServicio);
	}

	@Override
	public int registrarServicio(ServicioTO servicio, String usuario) {
		ComexstServicio servicioBean = new ComexstServicio();
		if(servicio.getId() == 0) {
			int nuevoId = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_SERVICIO.getValue());
			servicioBean.setServnId(nuevoId);
			servicioBean.setServvConcepto(servicio.getConcepto());
			servicioBean.setServvDescripcion(servicio.getDescripcion());
			servicioBean.setServnIdtipotransporte(servicio.getIdTipoTransporte());
			servicioBean.setServnIddespacho(servicio.getIdDespacho());
			servicioBean.setServnIdmoneda(servicio.getIdMoneda());
			servicioBean.setServnPrecio(servicio.getPrecio());
			servicioBean.setServvUnidad(servicio.getUnidad());
			servicioBean.setServvUsuariocreacion(usuario);
			servicioBean.setServdFechacreacion(new Date());
			servicioBean.setServnEstado(Constantes.ACTIVO);
			this.comexstServicioMapper.insert(servicioBean);
		} else {
			servicioBean = this.comexstServicioMapper.selectByPrimaryKey(servicio.getId());
			servicioBean.setServvConcepto(servicio.getConcepto());
			servicioBean.setServvDescripcion(servicio.getDescripcion());
			servicioBean.setServnIdtipotransporte(servicio.getIdTipoTransporte());
			servicioBean.setServnIddespacho(servicio.getIdDespacho());
			servicioBean.setServnIdmoneda(servicio.getIdMoneda());
			servicioBean.setServnPrecio(servicio.getPrecio());
			servicioBean.setServvUnidad(servicio.getUnidad());
			servicioBean.setServvUsuariomodificacion(usuario);
			servicioBean.setServdFechamodificacion(new Date());
			this.comexstServicioMapper.updateByPrimaryKey(servicioBean);
		}
		return servicioBean.getServnId();
	}

	@Override
	public void eliminarServicio(int idServicio, String usuario) {
		servicioExtendedMapper.eliminarServicio(idServicio, usuario);
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

}
