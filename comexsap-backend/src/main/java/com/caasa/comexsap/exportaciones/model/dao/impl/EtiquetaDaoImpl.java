package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.EtiquetaDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstEtiqueta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstEtiquetaExample;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstEtiquetaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.EtiquetaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("EtiquetaDao")
public class EtiquetaDaoImpl implements EtiquetaDao {
	
	@Autowired
	private ComexstEtiquetaMapper comexstEtiquetaMapper;
	
	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Override
	public List<EtiquetaTO> listarEtiquetas(String buscar) {
		List<EtiquetaTO> lista = new ArrayList<>();
		ComexstEtiquetaExample query = new ComexstEtiquetaExample();
		query.createCriteria()
			.andEtiqnEstadoEqualTo(Constantes.ACTIVO);
		
		List<ComexstEtiqueta> listaLocal = comexstEtiquetaMapper.selectByExample(query);
		
		if(buscar == null) {
			buscar = "";
		}
		
		final String filtro = buscar;
		
		lista = listaLocal.stream()
				.filter(etiq -> etiq.getEtiqvNombre().contains(filtro) || etiq.getEtiqvNombreIngles().contains(filtro))
				.map(etiq -> new EtiquetaTO(etiq.getEtiqnId(), etiq.getEtiqvNombre(), etiq.getEtiqvNombreIngles()))
				.collect(Collectors.toList());
		return lista;
	}

	@Override
	public void guardarEtiqueta(EtiquetaTO etiqueta, String usuario) {
		int id = 0;
		
		ComexstEtiqueta comexstEtiqueta = new ComexstEtiqueta();
		comexstEtiqueta.setEtiqvNombre(etiqueta.getNombre());
		comexstEtiqueta.setEtiqvNombreIngles(etiqueta.getNombreIngles());
		
		if(etiqueta.getId() == 0) {
			id = tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), TablasAplicativoEnum.COMEXST_ETIQUETA.getValue());
			comexstEtiqueta.setEtiqnId(id);
			comexstEtiqueta.setEtiqvUsuarioCreacion(usuario);
			comexstEtiqueta.setEtiqdFechaCreacion(new Date());
			comexstEtiqueta.setEtiqnEstado(Constantes.ACTIVO);
			comexstEtiquetaMapper.insert(comexstEtiqueta);
		} else {
			comexstEtiqueta.setEtiqnId(etiqueta.getId());
			comexstEtiqueta.setEtiqvUsuarioModificacion(usuario);
			comexstEtiqueta.setEtiqdFechaModificacion(new Date());
			comexstEtiquetaMapper.updateByPrimaryKeySelective(comexstEtiqueta);
		}
	}

	@Override
	public void eliminarEtiqueta(int id, String usuario) {
		ComexstEtiqueta comexstEtiqueta = new ComexstEtiqueta();
		comexstEtiqueta.setEtiqnId(id);
		comexstEtiqueta.setEtiqvUsuarioModificacion(usuario);
		comexstEtiqueta.setEtiqdFechaModificacion(new Date());
		comexstEtiqueta.setEtiqnEstado(Constantes.INACTIVO);
		comexstEtiquetaMapper.updateByPrimaryKeySelective(comexstEtiqueta);
	}

}
