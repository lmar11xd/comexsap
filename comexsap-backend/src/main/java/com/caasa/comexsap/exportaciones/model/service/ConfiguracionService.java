package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;
import java.util.Map;

import com.caasa.comexsap.exportaciones.model.to.DominioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroConfiguracionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;

public interface ConfiguracionService {
	
	public DominioTO registrarDominio(DominioTO dominio, String codigoApp, String usuario);
    public void borrarDominio(DominioTO dominio, String usuario);
    public List<DominioTO> listarDominio(String codigoApp);    
    public ParametroTO obtenerParametroxId(int id);
    public List<ParametroTO> listarParametroxDominio(String codigoApp, String codigoDominio);
	public List<ParametroTO> listarParametroxCodigoSap(String codigoApp, String codigoDominio, String codigoSap);
    public List<ParametroTO> buscarParametroxDominio(String codigoApp, String codigoDominio, String buscar);
    public ParametroTO registrarParametro(ParametroTO parametro, String codigoApp, String usuario);
    public void borrarParametro(ParametroTO parametro, String usuario);
	public Map<String, Object> obtenerMapaParametros(String codigoApp) throws Exception;
	public Map<String, String> obtenerMapaParametrosCorreo(String codigoApp);
    public DominioTO obtenerDominioxId(int id);
    public ParametroTO obtenerParametroPorValor(String codigoDominio, String codigoApp, String valor);
    public List<ParametroTO> listarParametroxFiltro(FiltroConfiguracionRequestTO filtro);
    public int registrarParametro(ParametroTO parametro, String usuario);
	
}
