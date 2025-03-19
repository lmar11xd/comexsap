package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

public interface TipoCambioExtendedMapper {

	public List<TipoCambioTO> obtenerTipoCambioActual(@Param("moneda") String moneda, @Param("fecha") String fecha);
}
