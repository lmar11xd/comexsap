package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.EstadosEstandarEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.ConfiguracionDao;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtDominio;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtDominioExample;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtParametro;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtParametroExample;
import com.caasa.comexsap.exportaciones.model.persistence.ConfigtDominioMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ConfigtParametroMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ConfiguracionExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.DominioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroConfiguracionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.comexsap.exportaciones.util.DateUtil;

@Repository("ConfiguracionDao")
public class ConfiguracionDaoImpl implements ConfiguracionDao {

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ConfigtDominioMapper dominioMapper;

	@Autowired
	private ConfigtParametroMapper parametroMapper;

	@Autowired
	private ConfiguracionExtendedMapper configuracionExtendedMapper;
	
	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public DominioTO registrarDominio(DominioTO dominio, String codigoApp, String usuario) {
		if (dominio.getIdDominio() == null) {
			int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.CONFIGT_DOMINIO.getValue());
			dominio.setIdDominio(intL_secuencia);
			dominio.setFechaCreacion(DateUtil.nowUtilDate());
			dominio.setCodEstado(EstadosEstandarEnum.ACTIVO.getValue());
			dominio.setFlagConfigSistema(EstadosEstandarEnum.INACTIVO.getValue());
			dominio.setCreadoPor(usuario);
			dominioMapper.insert(dominio);
		} else {
			dominio.setFechaModificacion(DateUtil.nowUtilDate());
			dominio.setModificadoPor(usuario);
			dominioMapper.updateByPrimaryKey(dominio);
		}
		return dominio;
	}

	@Override
	public void borrarDominio(DominioTO dominio, String usuario) {
		ConfigtDominio record = dominioMapper.selectByPrimaryKey(dominio.getIdDominio());
		record.setFechaModificacion(DateUtil.nowUtilDate());
		record.setModificadoPor(usuario);
		record.setCodEstado(EstadosEstandarEnum.BORRADO.getValue());
		dominioMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ParametroTO> listarParametroxDominio(String codigoApp, String codigoDominio) {
		return configuracionExtendedMapper.listarParametroxDominio(codigoApp, codigoDominio);
	}
	
	@Override
	public List<ParametroTO> listarParametroxCodigoSap(String codigoApp, String codigoDominio, String codigoSap) {
		return configuracionExtendedMapper.listarParametroxCodigoSap(codigoApp, codigoDominio, codigoSap);
	}
	
	@Override
	public List<ParametroTO> buscarParametroxDominio(String codigoApp, String codigoDominio, String buscar) {
		return configuracionExtendedMapper.buscarParametroxDominio(codigoApp, codigoDominio, buscar);
	}

	@Override
	public ParametroTO obtenerParametroxId(int id) {
		ParametroTO parametro = null;
		ConfigtParametro record = parametroMapper.selectByPrimaryKey(id);
		if (record != null) {
			parametro = new ParametroTO();
			BeanUtils.copyProperties(record, parametro);
		}
		return parametro;
	}

	@Override
	public DominioTO obtenerDominioxId(int id) {
		DominioTO dominio = null;
		ConfigtDominio record = dominioMapper.selectByPrimaryKey(id);
		if (record != null) {
			dominio = new DominioTO();
			BeanUtils.copyProperties(record, dominio);
		}
		return dominio;
	}

	@Override
	public ParametroTO obtenerParametroPorValor(String codigoDominio, String codigoApp, String valor) {
		ParametroTO parametro = null;
		ConfigtParametroExample parametroExample = new ConfigtParametroExample();
		parametroExample.createCriteria().andCodigoDominioEqualTo(codigoDominio).andValorParametroEqualTo(valor)
				.andAppcodeEqualTo(codigoApp);

		List<ConfigtParametro> listaParametros = parametroMapper.selectByExample(parametroExample);
		if (!listaParametros.isEmpty()) {
			ConfigtParametro record = listaParametros.get(0);
			parametro = new ParametroTO();
			BeanUtils.copyProperties(record, parametro);
		}
		return parametro;
	}

	@Override
	public ParametroTO registrarParametro(ParametroTO parametro, String codigoApp, String usuario) {
		if (parametro.getIdParametro() == null) {
			int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.CONFIGT_PARAMETRO.getValue());
			parametro.setIdParametro(intL_secuencia);
			parametro.setFechaCreacion(DateUtil.nowUtilDate());
			parametro.setCodEstado(EstadosEstandarEnum.ACTIVO.getValue());
			parametro.setCreadoPor(usuario);
			parametroMapper.insert(parametro);
		} else {
			parametro.setFechaModificacion(DateUtil.nowUtilDate());
			parametro.setModificadoPor(usuario);
			parametroMapper.updateByPrimaryKey(parametro);
		}
		return parametro;
	}

	@Override
	public void borrarParametro(ParametroTO parametro, String usuario) {
		ConfigtParametro record = parametroMapper.selectByPrimaryKey(parametro.getIdParametro());
		record.setFechaModificacion(DateUtil.nowUtilDate());
		record.setModificadoPor(usuario);
		record.setCodEstado(EstadosEstandarEnum.BORRADO.getValue());
		parametroMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<String, Object> obtenerMapaParametros(String codigoApp) {

		ConfigtDominioExample dominioExample = new ConfigtDominioExample();
		dominioExample.createCriteria().andCodEstadoEqualTo(EstadosEstandarEnum.ACTIVO.getValue())
				.andAppcodeEqualTo(codigoApp);
		dominioExample.setOrderByClause("CODIGO_DOMINIO ASC, ID_PARAMETRO ASC");
		List<ConfigtDominio> listaDominios = dominioMapper.selectByExample(dominioExample);
		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		for (ConfigtDominio dominio : listaDominios) {
			List<ParametroTO> listaParametro = configuracionExtendedMapper.listarParametroxDominio(codigoApp,
					dominio.getCodigoDominio());
			mapaParametros.put(dominio.getCodigoDominio(), listaParametro);
		}

		return mapaParametros;
	}
	
	@Override
	public Map<String, String> obtenerMapaParametrosCorreo(String codigoApp) {
		Map<String, String> mapaParametros = new HashMap<String, String>();
		List<ParametroTO> listaParametro = configuracionExtendedMapper.listarParametroxDominio(codigoApp, Constantes.PARAMETRO_D002);
		if(listaParametro != null) {
			for (ParametroTO parametro : listaParametro) {
				mapaParametros.put(parametro.getValorParametro(), parametro.getValorParametro2());
			}
		}
		return mapaParametros;
	}

	@Override
	public List<DominioTO> listarDominio(String codigoApp) {
		return configuracionExtendedMapper.listarDominio(codigoApp);
	}

	@Override
	public List<ParametroTO> listarParametroxFiltro(FiltroConfiguracionRequestTO filtro) {
		return configuracionExtendedMapper.listarParametroxFiltro(filtro);
	}

	@Override
	public int registrarParametro(ParametroTO parametro, String usuario) {
		ConfigtParametro parametroBean = new ConfigtParametro();
		int existeCodigo = this.existeCodigoParametroxDominio(
				AplicacionEnum.APLICACION.getValue(), parametro.getCodigoDominio(), 
				parametro.getCodigo(), parametro.getId());
		if(existeCodigo == 0) {
			if(parametro.getId() == 0) {
				int nuevoId = this.obtenerSecuencia(TablasAplicativoEnum.CONFIGT_PARAMETRO.getValue());
				parametroBean.setIdParametro(nuevoId);
				parametroBean.setAppcode(AplicacionEnum.APLICACION.getValue());
				parametroBean.setCodigoDominio(parametro.getCodigoDominio());
				parametroBean.setDescripcion(parametro.getDescripcion());
				parametroBean.setValorParametro(parametro.getCodigo());
				parametroBean.setCreadoPor(usuario);
				parametroBean.setFechaCreacion(DateUtil.nowUtilDate());
				parametroBean.setCodEstado(EstadosEstandarEnum.ACTIVO.getValue());
				this.parametroMapper.insert(parametroBean);
			} else {
				parametroBean = this.parametroMapper.selectByPrimaryKey(parametro.getId());
				parametroBean.setAppcode(AplicacionEnum.APLICACION.getValue());
				parametroBean.setCodigoDominio(parametro.getCodigoDominio());
				parametroBean.setDescripcion(parametro.getDescripcion());
				parametroBean.setValorParametro(parametro.getCodigo());
				parametroBean.setModificadoPor(usuario);
				parametroBean.setFechaModificacion(DateUtil.nowUtilDate());
				this.parametroMapper.updateByPrimaryKey(parametroBean);
			}
			return parametroBean.getIdParametro();
		} else {//Codigo ya existe
			return -1;
		}
	}

	@Override
	public int existeCodigoParametroxDominio(String codigoApp, String codigoDominio, String valor, int id) {
		return this.configuracionExtendedMapper.existeCodigoParametroxDominio(codigoApp, codigoDominio, valor, id);
	}

}
