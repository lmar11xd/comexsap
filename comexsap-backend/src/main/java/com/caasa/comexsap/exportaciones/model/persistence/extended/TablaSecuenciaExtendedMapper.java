package com.caasa.comexsap.exportaciones.model.persistence.extended;

import org.apache.ibatis.annotations.Param;

public interface TablaSecuenciaExtendedMapper {
	
   public Integer obtenerSecuencia(@Param("appcode") String appcode,@Param("tabla") String tabla);
   
}