package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

public interface PuertoExtendedMapper {

	public List<PuertoTO> listarPuertoxNombre(@Param("buscar") String buscar);
}