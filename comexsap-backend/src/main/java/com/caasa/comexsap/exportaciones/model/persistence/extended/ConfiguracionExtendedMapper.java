package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.DominioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroConfiguracionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;

public interface ConfiguracionExtendedMapper {

	public List<DominioTO> listarDominio(@Param("codigoAplicacion") String codigoAplicacion);

	public List<ParametroTO> listarParametroxDominio(@Param("codigoAplicacion") String codigoAplicacion,
			@Param("codigoDominio") String codigoDominio);
	
	public List<ParametroTO> listarParametroxCodigoSap(@Param("codigoAplicacion") String codigoAplicacion,
			@Param("codigoDominio") String codigoDominio, @Param("codigoSap") String codigoSap);
	
	public List<ParametroTO> buscarParametroxDominio(@Param("codigoAplicacion") String codigoAplicacion,
			@Param("codigoDominio") String codigoDominio, @Param("buscar") String buscar);

	public ParametroTO obtenerPaisxParametro(@Param("codigoAplicacion") String codigoAplicacion,
			@Param("codigoDominio") String codigoDominio,@Param("valorParametro") String valorParametro);
	
	public List<ParametroTO> listarParametroxFiltro(FiltroConfiguracionRequestTO filtro);
	
	public int existeCodigoParametroxDominio(
			@Param("codigoAplicacion") String codigoAplicacion, 
			@Param("codigoDominio") String codigoDominio, 
			@Param("valorParametro") String valorParametro,
			@Param("idParametro") int idParametro);

}