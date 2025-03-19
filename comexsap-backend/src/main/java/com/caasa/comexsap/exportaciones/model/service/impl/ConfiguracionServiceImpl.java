package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ConfiguracionDao;
import com.caasa.comexsap.exportaciones.model.service.ConfiguracionService;
import com.caasa.comexsap.exportaciones.model.to.DominioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroConfiguracionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {
	
	@Autowired
	private ConfiguracionDao configuracionDao;	
	
	@Override
	public List<ParametroTO> listarParametroxDominio(String codigoApp, String codigoDominio){
		return configuracionDao.listarParametroxDominio(codigoApp, codigoDominio);
	}
	
	@Override
	public List<ParametroTO> listarParametroxCodigoSap(String codigoApp, String codigoDominio, String codigoSap){
		return configuracionDao.listarParametroxCodigoSap(codigoApp, codigoDominio, codigoSap);
	}
	
	@Override
	public List<ParametroTO> buscarParametroxDominio(String codigoApp, String codigoDominio, String buscar){
		return configuracionDao.buscarParametroxDominio(codigoApp, codigoDominio, buscar);
	}
	
	@Override	
	public ParametroTO obtenerParametroPorValor(String codigoDominio, String codigoApp, String valor){
		return configuracionDao.obtenerParametroPorValor(codigoDominio, codigoApp, valor);
	}

	@Override
	public DominioTO registrarDominio(DominioTO dominio, String codigoApp, String usuario){
		return configuracionDao.registrarDominio(dominio, codigoApp, usuario);
	}

	@Override
	public void borrarDominio(DominioTO dominio, String usuario){
		configuracionDao.borrarDominio(dominio, usuario);		
	}

	@Override
	public List<DominioTO> listarDominio(String codigoApp){
		return configuracionDao.listarDominio(codigoApp);
	}

	@Override
	public ParametroTO obtenerParametroxId(int id){
		return configuracionDao.obtenerParametroxId(id);
	}

	@Override
	public ParametroTO registrarParametro(ParametroTO parametro, String codigoApp, String usuario){
		return configuracionDao.registrarParametro(parametro, codigoApp, usuario);
	}

	@Override
	public void borrarParametro(ParametroTO parametro, String usuario){
		configuracionDao.borrarParametro(parametro, usuario);		
	}

	@Override
	public Map<String, Object> obtenerMapaParametros(String codigoApp){
		return configuracionDao.obtenerMapaParametros(codigoApp);
	}
	
	@Override
	public Map<String, String> obtenerMapaParametrosCorreo(String codigoApp){
		return configuracionDao.obtenerMapaParametrosCorreo(codigoApp);
	}

	@Override
	public DominioTO obtenerDominioxId(int id){
		return configuracionDao.obtenerDominioxId(id);
	}

	@Override
	public List<ParametroTO> listarParametroxFiltro(FiltroConfiguracionRequestTO filtro) {
		return configuracionDao.listarParametroxFiltro(filtro);
	}

	@Override
	public int registrarParametro(ParametroTO parametro, String usuario) {
		return configuracionDao.registrarParametro(parametro, usuario);
	}

}