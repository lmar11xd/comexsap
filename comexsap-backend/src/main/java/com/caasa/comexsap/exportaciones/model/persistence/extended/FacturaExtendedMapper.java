package com.caasa.comexsap.exportaciones.model.persistence.extended;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FacturaTO;

public interface FacturaExtendedMapper {

	public FacturaTO obtenerFacturaxSerieCodigo(@Param("serieCodigo") String serieCodigo);
}
